# 搭建三主的高可用k8s集群
## 1、准备环境
### 1.1、安装依赖包
```shell script
yum install -y conntrack ntp ipvsadm ipset jq iptables curl sysstat libseccomp wget vim net-tools git
``` 
### 1.2、设置防火墙为Iptables并清空规则
```shell script

systemctl stop firewalld && systemctl disable firewalld
yum install -y iptables-services && systemctl start iptables && systemctl enable iptables && iptables -F && service iptables save

``` 
### 1.3、关闭SELINUX
```shell script
swapoff -a && sed -i '/ swap / s/^\(.*\)$/#\1/g' /etc/fstab
setenforce 0 && sed -i 's/^SELINUX=.*/SELINUX=disabled/' /etc/selinux/config

``` 
### 1.4、调整内核参数，对于 K8S
```shell script
cat > /etc/sysctl.d/kubernetes.conf <<EOF
net.bridge.bridge-nf-call-iptables=1
net.bridge.bridge-nf-call-ip6tables=1
net.ipv4.ip_forward=1
net.ipv4.tcp_tw_recycle=0
vm.swappiness=0 # 禁止使用 swap 空间，只有当系统 OOM 时才允许使用它
vm.overcommit_memory=1 # 不检查物理内存是否够用
vm.panic_on_oom=0 # 开启 OOM
fs.inotify.max_user_instances=8192
fs.inotify.max_user_watches=1048576
fs.file-max=52706963
fs.nr_open=52706963
net.ipv6.conf.all.disable_ipv6=1
net.netfilter.nf_conntrack_max=2310720
EOF

sysctl -p /etc/sysctl.d/kubernetes.conf

#如果执行sysctl -p 时出现：
#[root@localhost ~]# sysctl -p
#sysctl: cannot stat /proc/sys/net/bridge/bridge-nf-call-ip6tables: No such file or directory
#sysctl: cannot stat /proc/sys/net/bridge/bridge-nf-call-iptables: No such file or directory

解决方法
[root@localhost ~]# modprobe br_netfilter
[root@localhost ~]# sysctl -p
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1
```
### 1.5、调整系统时区
```shell script

# 设置系统时区为 中国/上海
timedatectl set-timezone Asia/Shanghai
# 将当前的 UTC 时间写入硬件时钟
timedatectl set-local-rtc 0
#重启依赖于系统时间的服务
systemctl restart rsyslog && systemctl restart crond

```
### 1.6、关闭系统不需要服务
```shell script
systemctl stop postfix && systemctl disable postfix

```
### 1.7、设置 rsyslogd 和 systemd journald
```shell script
# 持久化保存日志的目录、持久化日志配置文件的目录

mkdir /var/log/journal 
mkdir /etc/systemd/journald.conf.d
cat > /etc/systemd/journald.conf.d/99-prophet.conf <<EOF
[Journal]
# 持久化保存到磁盘
Storage=persistent
# 压缩历史日志
Compress=yes
SyncIntervalSec=5m
RateLimitInterval=30s
RateLimitBurst=1000
# 最大占用空间 1G
SystemMaxUse=1G
# 单日志文件最大 200M
SystemMaxFileSize=200M
# 日志保存时间 1 周
MaxRetentionSec=1week
# 不将日志转发到 syslog
ForwardToSyslog=no
EOF

systemctl restart systemd-journald

```

### 1.9、关闭 NUMA
```shell script
# 在 GRUB_CMDLINE_LINUX 一行添加 `numa=off` 参数，如下所示：

cp /boot/grub2/grub.cfg{,.bak}
sed -i 's/quiet"/quiet numa=off"/g' /etc/default/grub
grub2-mkconfig -o /boot/grub2/grub.cfg

```

### 1.11、配置负载均衡代理
```shell script
#所有主机安装keepalived代理，实现统一入口，也可以使用nginx、haproxy、LVS等

yum -y install keepalived

#所有控制平面节点创建apiserver检测脚本
cat > /etc/keepalived/check_apiserver.sh << EOF
#!/bin/sh
errorExit() {
    echo "*** $*" 1>&2
    exit 1
}

curl --silent --max-time 2 --insecure https://localhost:6443/ -o /dev/null || errorExit "Error GET https://localhost:6443/"
ps aux | grep mysqld | grep -v grep > /dev/null || errorExit "Error check mysqld process"
if ip addr | grep -q ${K8S_LB_IP}; then
    curl --silent --max-time 2 --insecure https://${K8S_LB_IP}:6443/ -o /dev/null || errorExit "Error GET https://${K8S_LB_IP}:6443/"
fi
EOF

chmod +x /etc/keepalived/check_apiserver.sh && sh /etc/keepalived/check_apiserver.sh

#设置nginx的健康检查脚本
cat > /etc/keepalived/check_nginx.sh << EOF
#!/bin/sh
errorExit() {
    echo "*** $*" 1>&2
    exit 1
}

curl --silent --max-time 2 --insecure http://localhost:80/ -o /dev/null || errorExit "Error GET http://localhost:80/"
if ip addr | grep -q ${NGINX_LB_IP}; then
    curl --silent --max-time 2 --insecure http://${NGINX_LB_IP}:80/ -o /dev/null || errorExit "Error GET http://${NGINX_LB_IP}:80/"
fi
EOF

chmod +x /etc/keepalived/check_nginx.sh && sh /etc/keepalived/check_nginx.sh

#所有控制平面节点修改配置文件
cat > /etc/keepalived/keepalived.conf << EOF
! /etc/keepalived/keepalived.conf
! Configuration File for keepalived
global_defs {
    #主机唯一标识，主机间不可相同(不需要修改)
    router_id $(hostname)
}
vrrp_script check_apiserver {
    script "/etc/keepalived/check_apiserver.sh"
    interval 3
    weight `expr 0 - ${#NODE_IPS[*]}`
}
vrrp_instance k8s {
    #服务启动时的角色,0节点为MASTER，其余节点为BACKUP(不需要修改)
    `if [ $NODE_INDEX -eq 0 ]; then
    echo "state MASTER"
    else
    echo "state BACKUP"
    fi`
    #绑定的网卡名(需要修改成实际情况)
    interface ens192
    #虚拟路由，三台主机必须一致
    virtual_router_id 110
    #服务启动时，抢占VIP的优先级(不需要修改)
    priority `expr 100 - $NODE_INDEX`
    authentication {
      auth_type PASS
      #广播时的密码，必须一致
      auth_pass 4567
    }
    virtual_ipaddress {
      #VIP地址
      ${K8S_LB_IP}/24
    }
    track_script {
      check_apiserver
    }
}

vrrp_script check_nginx {
    script "/etc/keepalived/check_nginx.sh"
    interval 3
    weight `expr 0 - ${#NODE_IPS[*]}`
}
vrrp_instance nginx {
    #服务启动时的角色,0节点为MASTER，其余节点为BACKUP(不需要修改)
    `if [ $NODE_INDEX -eq 0 ]; then
    echo "state MASTER"
    else
    echo "state BACKUP"
    fi`
    #绑定的网卡名(需要修改成实际情况)
    interface ens160
    #虚拟路由，三台主机必须一致
    virtual_router_id 111
    #服务启动时，抢占VIP的优先级(不需要修改)
    priority `expr 100 - $NODE_INDEX`
    authentication {
      auth_type PASS
      #广播时的密码，必须一致
      auth_pass 6789
    }
    virtual_ipaddress {
      #VIP地址
      ${NGINX_LB_IP}/24
    }
    track_script {
      check_nginx
    }
}
EOF

systemctl restart keepalived && systemctl status keepalived && systemctl enable keepalived 

# 第一个节点查看
ip a s | grep ${K8S_LB_IP}

```
## 2、Kubeadm部署安装

### 2.1、kube-proxy开启ipvs的前置条件
```shell script
modprobe br_netfilter
cat > /etc/sysconfig/modules/ipvs.modules <<EOF
#!/bin/sh
source ~/.bash_profile
modprobe -- ip_vs
modprobe -- ip_vs_rr
modprobe -- ip_vs_wrr
modprobe -- ip_vs_sh
modprobe -- nf_conntrack_ipv4
EOF

chmod 755 /etc/sysconfig/modules/ipvs.modules && bash /etc/sysconfig/modules/ipvs.modules && lsmod | grep -e ip_vs -e nf_conntrack_ipv4

```
### 2.2、安装Docker软件
部署Docker[点这里readme-docker.md](../readme-docker.md) 
### 2.3、查询当前k8s可用的所有版本
```shell script
yum makecache fast && yum list --showduplicates kubeadm --disableexcludes=kubernetes
# 在列表中找到最新的 1.x 版本号
# 该版本号格式为 1.16.15-0，其中x是此版本号，y 是最新的补丁
# 以下所有1.16.15替换成具体的版本，例如1.16.15
```
### 2.4、安装 Kubeadm
```shell script
cat <<EOF > /etc/yum.repos.d/kubernetes.repo
[kubernetes]
name=Kubernetes
baseurl=http://mirrors.aliyun.com/kubernetes/yum/repos/kubernetes-el7-x86_64
enabled=1
gpgcheck=0
repo_gpgcheck=0
gpgkey=http://mirrors.aliyun.com/kubernetes/yum/doc/yum-key.gpg
http://mirrors.aliyun.com/kubernetes/yum/doc/rpm-package-key.gpg
EOF

yum -y install kubeadm-${K8S_VERSION} kubectl-${K8S_VERSION} kubelet-${K8S_VERSION}

#  启⽤kubectl（需要重新登录会话才能生效）
yum install bash-completion -y
source /usr/share/bash-completion/bash_completion
echo 'source <(kubectl completion bash)' >> ~/.bash_profile
source ~/.bash_profile
kubectl completion bash >/etc/bash_completion.d/kubectl

 ```
### 2.5、初始化k8s集群(第一个控制平面节点执行)
```shell script
## 开始初始化k8s集群（控制平面节点执行）
## --kubernetes-version：为控制平面选择特定的Kubernetes版本。
## --image-repository ：指定镜像部署的地址，原地址https://k8s.gcr.io不可用
# --apiserver-advertise-address：apiserver选择的ip地址，默认网关关联的网络接口
## --control-plane-endpoint：多控制平面节点必须设置该参数，负载均衡器的地址和端口
## --pod-network-cidr：pod的网络地址
## --service-cidr：service的网络地址

kubeadm init --kubernetes-version=v${K8S_VERSION} \
--image-repository registry.aliyuncs.com/google_containers \
--apiserver-advertise-address=${NODE_IPS[${NODE_INDEX}]} \
--pod-network-cidr=10.244.0.0/16 \
--control-plane-endpoint="${K8S_LB_IP}:6443" \
--service-cidr=10.96.0.0/12 \
--upload-certs \
--ignore-preflight-errors=Swap

# 将控制平面节点加入集群(所有工作控制平面节点执行) 
#Secret会在两个小时后过期,如果过期就需要使用命令重新生成（选做）
kubeadm init phase upload-certs --upload-certs

#双网卡需要指定IP，增加 --apiserver-advertise-address参数
kubeadm join 172.16.1.200:6443 --token z9k16e.c64sazweotw3aagk \
    --discovery-token-ca-cert-hash sha256:3f7d50ffd1294549e209a2d7c7ad592a0e9ab9ea9d7bdf661d526bc9d01bc02f \
    --control-plane --certificate-key 91992c5d0b90f14297830f268f6b9365b0b007e83c8a23a2670a117f8d3b4661 \
    --apiserver-advertise-address=${NODE_IPS[${NODE_INDEX}]}

### 将其余工作节点加入集群(所有工作节点执行) 

#在主节点上执行以下命令获取最新join命令（如果node节点和master是接着安装的则不要执行该步）
kubeadm token create --print-join-command

#双网卡需要指定IP，增加--apiserver-advertise-address参数
kubeadm join 100.100.1.243:6443 --token ruvd9n.5rksmgwyye8h9mis \
    --discovery-token-ca-cert-hash sha256:e51320599d5ff7f0c5067a765a8403eb81b89043d446cacc3bb26b4a4598eb4a \
    --apiserver-advertise-address=${NODE_IPS[${NODE_INDEX}]}

## kuberntes服务nodeport端口，默认是3000-32767。但是某些场合下，这个限制并不适用。
## 在apiserver配置文件中command下添加    - --service-node-port-range=1-65535参数，修改后会自动生效，无需其他操作:
vim /etc/kubernetes/manifests/kube-apiserver.yaml

# 开机自动启动kubelet
systemctl daemon-reload && systemctl enable --now kubelet && systemctl status kubelet

```

### 2.7、Etcd 集群状态查看(所有控制平面节点都可执行)
```shell script

# k8s 1.16.x
kubectl -n kube-system exec etcd-$(hostname) -- etcdctl \
--endpoints=https://localhost:2379 \
--ca-file=/etc/kubernetes/pki/etcd/ca.crt \
--cert-file=/etc/kubernetes/pki/etcd/server.crt \
--key-file=/etc/kubernetes/pki/etcd/server.key cluster-health

# k8s 1.18.x
kubectl -n kube-system exec etcd-$(hostname) -- etcdctl \
--endpoints=https://localhost:2379 \
--cacert=/etc/kubernetes/pki/etcd/ca.crt \
--cert=/etc/kubernetes/pki/etcd/server.crt \
--key=/etc/kubernetes/pki/etcd/server.key endpoint health

kubectl get endpoints kube-controller-manager --namespace=kube-system -o yaml
kubectl get endpoints kube-scheduler --namespace=kube-system -o yaml

```
### 2.8、部署网络(所有控制平面节点执行)
```shell script

kubectl apply -f https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel.yml

```
### 2.9、让控制平面节点参与POD负载（如果不让主节点参与负载则不需要操作这步）
```shell script
kubectl taint nodes --all node-role.kubernetes.io/master-

```

## 3、升级k8s版本
### 3.1、查询当前k8s可用的所有版本
```shell script
# 在列表中找到最新的 1.x 版本号
# 该版本号格式为 1.x.y-0，其中x是此版本号，y 是最新的补丁
# 以下所有1.x.y替换成具体的版本，例如1.16.15
yum makecache fast && yum list --showduplicates kubeadm --disableexcludes=kubernetes
```
### 3.2、升级 master 节点
#### 3.2.1、升级第一个控制平面节点
```shell script
#假设所有命令都以 root 身份执行
#在第一个 master 节点上执行如下命令，升级 kubeadm
yum install -y kubeadm-${K8S_VERSION} --disableexcludes=kubernetes

#执行命令，以验证升级结果
kubeadm version

```
#### 3.2.2、在第一个 master 节点上执行命令 
```shell script
#请忽略错误 could not fetch a Kubernetes version from the internet: unable to get URL "https://dl.k8s.io/release/stable.txt"，
#在不能获得最新 kubernetes 版本列表的情况下，将使用 kubeadm 的版本作为升级的目标版本
#（在前面的步骤中，已经从 yum 仓库找到了最新 kubeadm 的版本）
kubeadm upgrade plan

# 替换 x 为最新补丁的版本号（kubeadm upgrade 同时会自动更新节点上的证书。如果不想更新证书，请使用参数 --certificate-renewal=false）
kubeadm upgrade apply v${K8S_VERSION} -y

```
### 3.3、升级其他 master 节点
```shell script
#不需要执行 kubeadm upgrade plan
# master节点上执行的是 kubeadm upgrade apply v1.16.15，此时执行的是 kubeadm upgrade node
kubeadm upgrade node

```
### 3.4、升级 kubelet 和 kubectl
#### 3.4.1、在所有的 master 节点上执行如下命令以升级 kubelet 和 kubectl
```shell script
# 替换 x 为最新补丁的版本号
yum install -y kubelet-${K8S_VERSION} kubectl-${K8S_VERSION} --disableexcludes=kubernetes

```
#### 3.4.2、执行如下命令，以重启 kubelet
```shell script
systemctl daemon-reload && systemctl restart kubelet && systemctl status kubelet 

```
### 3.5、升级 worker 节点
    建议逐个升级 worker 节点，或者同一时间点只升级少量的 worker 节点，以避免集群出现资源紧缺的状况。
#### 3.5.1、升级 kubeadm（在所有的 worker 节点上执行如下命令，升级 kubeadm）
```shell script
# 将 1.16.15 中的 x 替换为最新的补丁版本
yum install -y kubeadm-${K8S_VERSION} --disableexcludes=kubernetes

```
#### 3.5.2、排空（drain）节点
```shell script
#执行以下命令，将节点标记为 不可调度的 并驱逐节点上所有的 Pod，
# 在可以执行 kubectl 命令的位置执行（通常是第一个 控制平面节点）
# $NODE 代表一个变量，实际执行时，请用您的节点名替换
kubectl drain $NODE --ignore-daemonsets   
# 如果由于有状态Pod导致驱逐失败，只能删除本地数据，但是有风险，需自行评估
kubectl drain $NODE --ignore-daemonsets --delete-local-data
#输出结果如下所示：
node/local2 cordoned
WARNING: ignoring DaemonSet-managed Pods: kube-system/kube-flannel-ds-amd64-zlrbd, kube-system/kube-proxy-k7bvk
evicting pod "coredns-5644d7b6d9-m47fm"
pod/coredns-5644d7b6d9-m47fm evicted
node/local2 evicted
```
#### 3.5.3、升级 kubelet 的配置
```shell script
kubeadm upgrade node
```
#### 3.5.4、升级 kubelet 和 kubectl
```shell script
#在所有的 worker 节点执行命令
# 替换 x 为最新补丁的版本号
yum install -y kubelet-${K8S_VERSION} kubectl-${K8S_VERSION} --disableexcludes=kubernetes

#执行如下命令，以重启 kubelet
systemctl daemon-reload
systemctl restart kubelet
```
#### 3.5.5、恢复（uncordon）节点
```shell script
#执行如下命令，使节点重新接受调度并投入使用：
kubectl uncordon $NODE
```
### 3.6、检查集群的状态
```shell script
#在所有节点的 kubelet 本升级以后，执行如下命令以验证所有节点都可用：
#STATUS 字段应该为 Ready，版本号也应该显示目标版本号。
kubectl get nodes -o wide
```
### 3.7、从错误状态中恢复
    #如果 kubeadm upgrade 执行过程中出现错误且未曾回滚，例如执行过程中意外关机，您可以再次执行 kubeadm upgrade。
    #该命令是 幂等 的，并将最终保证您能够达到最终期望的升级结果。
    #从失败状态中恢复时，请执行 kubeadm upgrade --force 命令，注意要使用集群的当前版本号。
## 4、卸载k8s
```shell script
kubeadm reset -f
modprobe -r ipip
lsmod
systemctl disable kubelet
systemctl disable kube-proxy
yum clean all
yum remove -y kube*
rpm -qa|grep kube|xargs rpm --nodeps -e
rm -rf ~/.kube
rm -rf /etc/kubernetes/
rm -rf /usr/lib/systemd/system/kubelet.service.d
rm -rf /usr/bin/kube*
rm -rf /etc/cni
rm -rf /opt/cni
rm -rf /var/lib/etcd
rm -rf /var/lib/cni
rm -rf /var/lib/kubelet
rm -rf /var/etcd
rm -rf /etc/sysconfig/kubelet
sed -i 's/source <(kubectl completion bash)//g' ~/.bash_profile
sed -i 's/export KUBECONFIG=\/etc\/kubernetes\/admin.conf//g' ~/.bash_profile

```

## 5、使用Helm部署anan服务
部署anan [点这里readme-anan.md](../helm/readme-anan.md) 
