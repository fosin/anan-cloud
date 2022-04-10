# 搭建三主的高可用 k8s 集群

## 1、准备环境

### 1.1、安装依赖包

```shell script
yum install -y conntrack ntp ipvsadm ipset jq iptables curl sysstat libseccomp wget vim net-tools git
```

### 1.2、设置防火墙为 Iptables 并清空规则

```shell script

systemctl stop firewalld && systemctl disable firewalld
yum install -y iptables-services && systemctl start iptables && systemctl enable iptables && iptables -F && service iptables save

```

### 1.3、关闭 SELINUX

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
vm.swappiness=0 #禁止使用 swap 空间，只有当系统 OOM 时才允许使用它
vm.overcommit_memory=1 #不检查物理内存是否够用
vm.panic_on_oom=0 #开启OOM
fs.inotify.max_user_instances=8192
fs.inotify.max_user_watches=1048576
fs.file-max=52706963
fs.nr_open=52706963
net.ipv6.conf.all.disable_ipv6=1
net.netfilter.nf_conntrack_max=2310720
net.ipv4.tcp_keepalive_time=600 #设置时间比ipvs (tcp默认900秒)短就可以保证连接不超
net.ipv4.tcp_keepalive_intvl=30
net.ipv4.tcp_keepalive_probes=10
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

## 2、Kubeadm 部署安装

### 2.1、kube-proxy 开启 ipvs 的前置条件

```shell script
modprobe br_netfilter
cat > /etc/sysconfig/modules/ipvs.modules <<EOF
#!/bin/sh
source ~/.bash_profile
modprobe -- ip_vs
modprobe -- ip_vs_rr
modprobe -- ip_vs_wrr
modprobe -- ip_vs_sh
modprobe -- nf_conntrack
EOF

chmod 755 /etc/sysconfig/modules/ipvs.modules && bash /etc/sysconfig/modules/ipvs.modules && lsmod | grep -e ip_vs -e nf_conntrack

```

### 2.3、查询当前 k8s 可用的所有版本

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

yum makecache fast && yum list --showduplicates kubeadm --disableexcludes=kubernetes
# 在列表中找到最新的 1.x 版本号
# 该版本号格式为 1.16.15-0，其中x是此版本号，y 是最新的补丁
# 以下所有1.16.15替换成具体的版本，例如1.16.15
```

### 2.4、安装 Kubeadm

```shell script

yum -y install kubeadm-${K8S_VERSION} kubectl-${K8S_VERSION} kubelet-${K8S_VERSION}

#启⽤k8s命令补全
yum install bash-completion -y
source /usr/share/bash-completion/bash_completion
echo 'source <(kubectl completion bash)' >> ~/.bash_profile
source ~/.bash_profile
kubectl completion bash >/etc/bash_completion.d/kubectl

```

### 2.5、初始化 k8s 集群(第一个控制平面节点执行)

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
--service-cidr=10.96.0.0/16 \
--upload-certs \
--ignore-preflight-errors=Swap

  # 将控制平面节点加入集群(所有工作控制平面节点执行)
  #Secret会在两个小时后过期,如果过期就需要使用命令重新生成（选做）
  kubeadm init phase upload-certs --upload-certs

  #双网卡需要指定IP，增加 --apiserver-advertise-address参数
  kubeadm join 172.16.1.30:6443 --token zfvx5r.mdfqh1jtfshu2svt \
    --discovery-token-ca-cert-hash sha256:069708cc902cf1ec8caca18186cc376ba5a3a4cade9e850554fa63c44392bb09 \
    --control-plane --certificate-key 2e1d6ebcbcbf7afe7e19c2bdd1b4922ad4f7efcdc5412fdda1a8563698e85531 \
    --apiserver-advertise-address=${NODE_IPS[${NODE_INDEX}]}

  ### 将其余工作节点加入集群(所有工作节点执行)

  #在主节点上执行以下命令获取最新join命令（如果node节点和master是接着安装的则不要执行该步）
  kubeadm token create --print-join-command

  #双网卡需要指定IP，增加--apiserver-advertise-address参数
  kubeadm join 172.16.1.30:6443 --token ruvd9n.5rksmgwyye8h9mis \
      --discovery-token-ca-cert-hash sha256:e51320599d5ff7f0c5067a765a8403eb81b89043d446cacc3bb26b4a4598eb4a \
      --apiserver-advertise-address=${NODE_IPS[${NODE_INDEX}]}

## kuberntes服务nodeport端口，默认是3000-32767。但是某些场合下，这个限制并不适用。
## 在apiserver配置文件中command下添加参数，修改后会自动生效:
##    - --service-node-port-range=1-65535
vim /etc/kubernetes/manifests/kube-apiserver.yaml

# 开机自动启动kubelet
systemctl daemon-reload && systemctl enable --now kubelet && systemctl status kubelet

```

### 2.7、Etcd 集群状态查看(所有控制平面节点都可执行)

```shell script

# k8s > 1.18.x
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

### 2.9、让控制平面节点参与 POD 负载（如果不让主节点参与负载则不需要操作这步）

```shell script

kubectl taint nodes --all node-role.kubernetes.io/master-

```

## 4、卸载 k8s

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

## 3、运维命令

```shell script

#强制删除pod
kubectl delete pod <your-pod-name> --force --grace-period=0

#强制删除pv、pvc
kubectl patch pv xxx -p '{"metadata":{"finalizers":null}}'
kubectl patch pvc xxx -p '{"metadata":{"finalizers":null}}'

#强制删除ns
kubectl delete ns <terminating-namespace> --force --grace-period=0

```
