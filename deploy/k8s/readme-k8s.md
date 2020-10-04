# 1、准备环境
## 1.1、安装依赖包
```shell script
yum install -y conntrack ntp ipvsadm ipset jq iptables curl sysstat libseccomp wget vim net-tools git
``` 
## 1.2、设置防火墙为Iptables并清空规则
```shell script

systemctl stop firewalld && systemctl disable firewalld
yum install -y iptables-services && systemctl start iptables && systemctl enable iptables && iptables -F && service iptables save

``` 
## 1.3、关闭SELINUX
```shell script
swapoff -a && sed -i '/ swap / s/^\(.*\)$/#\1/g' /etc/fstab
setenforce 0 && sed -i 's/^SELINUX=.*/SELINUX=disabled/' /etc/selinux/config
``` 
## 1.4、调整内核参数，对于 K8S
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
## 1.5、调整系统时区
```shell script
# 设置系统时区为 中国/上海
timedatectl set-timezone Asia/Shanghai
# 将当前的 UTC 时间写入硬件时钟
timedatectl set-local-rtc 0
#重启依赖于系统时间的服务
systemctl restart rsyslog && systemctl restart crond
```
## 1.6、关闭系统不需要服务
```shell script
systemctl stop postfix && systemctl disable postfix
```
## 1.7、设置 rsyslogd 和 systemd journald
```shell script
# 持久化保存日志的目录
mkdir /var/log/journal 

# 持久化日志配置文件的目录
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
# 最大占用空间 10G
SystemMaxUse=10G
# 单日志文件最大 200M
SystemMaxFileSize=200M
# 日志保存时间 2 周
MaxRetentionSec=2week
# 不将日志转发到 syslog
ForwardToSyslog=no
EOF

systemctl restart systemd-journald
```

## 1.9、关闭 NUMA
```shell script
vim /etc/default/grub # 在 GRUB_CMDLINE_LINUX 一行添加 `numa=off` 参数，如下所示：
diff /etc/default/grub.bak /etc/default/grub

6c6
< GRUB_CMDLINE_LINUX="crashkernel=auto rd.lvm.lv=centos/root rhgb quiet"
---
> GRUB_CMDLINE_LINUX="crashkernel=auto rd.lvm.lv=centos/root rhgb quiet numa=off"

cp /boot/grub2/grub.cfg{,.bak}
grub2-mkconfig -o /boot/grub2/grub.cfg
```
###1.10、设置主机名映射(全部节点)
```shell script
cat >> /etc/hosts <<EOF
192.168.137.8 local1
EOF
```
# 2、Kubeadm部署安装

## 2.1、kube-proxy开启ipvs的前置条件
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
## 2.2、安装Docker软件
部署Docker[点这里readme-docker.md](../readme-docker.md) 
## 2.3、查询当前k8s可用的所有版本
```shell script
yum makecache fast && yum list --showduplicates kubeadm --disableexcludes=kubernetes
# 在列表中找到最新的 1.x 版本号
# 该版本号格式为 1.16.9-0，其中x是此版本号，y 是最新的补丁
# 以下所有1.16.9替换成具体的版本，例如1.16.9
```
## 2.4、安装 Kubeadm （主从配置）
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

yum -y install kubeadm-1.16.9 kubectl-1.16.9 kubelet-1.16.9

# 开机自动启动kubelet
systemctl enable kubelet.service

# 使⽤命令补全
yum install bash-completion -y
source /usr/share/bash-completion/bash_completion

# 启⽤kubectl（需要重新登录会话才能生效）
echo 'source <(kubectl completion bash)' >>~/.bashrc
kubectl completion bash >/etc/bash_completion.d/kubectl
 ```
## 2.5、初始化k8s集群
```shell script
## 开始初始化k8s集群（master节点执行）
kubeadm init --kubernetes-version=v1.16.9 \
--image-repository registry.aliyuncs.com/google_containers \
--pod-network-cidr=10.244.0.0/16 \
--service-cidr=10.96.0.0/12 \
--upload-certs \
--ignore-preflight-errors=Swap \
| tree kubeadm-init.log

说明：
--kubernetes-version：为控制平面选择特定的Kubernetes版本。
--image-repository ：指定镜像部署的地址，原地址https://k8s.gcr.io不可用
--pod-network-cidr：pod的网络地址
--service-cidr：service的网络地址

To start using your cluster, you need to run the following as a regular user:

  mkdir -p $HOME/.kube
  sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
  sudo chown $(id -u):$(id -g) $HOME/.kube/config

You should now deploy a pod network to the cluster.
Run "kubectl apply -f [podnetwork].yaml" with one of the options listed at:
  https://kubernetes.io/docs/concepts/cluster-administration/addons/

Then you can join any number of worker nodes by running the following on each as root:

kubeadm join 192.168.137.8:6443 --token ruvd9n.5rksmgwyye8h9mis \
    --discovery-token-ca-cert-hash sha256:e51320599d5ff7f0c5067a765a8403eb81b89043d446cacc3bb26b4a4598eb4a

## kuberntes服务nodeport端口，默认是3000-32767。但是某些场合下，这个限制并不适用。
## 在apiserver配置文件中command下添加 --service-node-port-range=1-65535 参数，修改后会自动生效，无需其他操作:
vim /etc/kubernetes/manifests/kube-apiserver.yaml
```
## 2.6、将其余工作节点加入集群     
```shell script
#在主节点上执行以下命令获取最新join命令（如果node节点和master是接着安装的则不要执行该步）
kubeadm token create --print-join-command

kubeadm join 192.168.137.8:6443 --token ruvd9n.5rksmgwyye8h9mis \
    --discovery-token-ca-cert-hash sha256:e51320599d5ff7f0c5067a765a8403eb81b89043d446cacc3bb26b4a4598eb4a
```
## 2.7、Etcd 集群状态查看（只需安装节点操作）
```shell script
kubectl -n kube-system exec etcd-local1 -- etcdctl \
--endpoints=https://localhost:2379 \
--ca-file=/etc/kubernetes/pki/etcd/ca.crt \
--cert-file=/etc/kubernetes/pki/etcd/server.crt \
--key-file=/etc/kubernetes/pki/etcd/server.key cluster-health

kubectl get endpoints kube-controller-manager --namespace=kube-system -o yaml

kubectl get endpoints kube-scheduler --namespace=kube-system -o yaml
```
## 2.8、部署网络（只需在master节点操作）
```shell script
kubectl apply -f https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel.yml
```
## 2.9、让 master节点参与POD负载（如果不让主节点参与负载则不需要操作这步）
```shell script
kubectl taint nodes --all node-role.kubernetes.io/master-

```

# 4、升级k8s版本
## 4.1、查询当前k8s可用的所有版本
```shell script
# 在列表中找到最新的 1.x 版本号
# 该版本号格式为 1.x.y-0，其中x是此版本号，y 是最新的补丁
# 以下所有1.x.y替换成具体的版本，例如1.16.9
yum makecache fast && yum list --showduplicates kubeadm --disableexcludes=kubernetes
```
## 4.2、升级 master 节点
### 4.2.1、升级第一个master节点
```shell script
#假设所有命令都以 root 身份执行
#在第一个 master 节点上执行如下命令，升级 kubeadm
yum install -y kubeadm-1.16.9 --disableexcludes=kubernetes

#执行命令，以验证升级结果
kubeadm version
```
### 4.2.2、在第一个 master 节点上执行命令 
```shell script
#请忽略错误 could not fetch a Kubernetes version from the internet: unable to get URL "https://dl.k8s.io/release/stable.txt"，
#在不能获得最新 kubernetes 版本列表的情况下，将使用 kubeadm 的版本作为升级的目标版本
#（在前面的步骤中，已经从 yum 仓库找到了最新 kubeadm 的版本）
kubeadm upgrade plan

# 替换 x 为最新补丁的版本号（kubeadm upgrade 同时会自动更新节点上的证书。如果不想更新证书，请使用参数 --certificate-renewal=false）
kubeadm upgrade apply v1.16.9
```
## 4.3、升级其他 master 节点
```shell script
#不需要执行 kubeadm upgrade plan
#第一个 master 节点上执行的是 kubeadm upgrade apply v1.16.9，此时执行的是 kubeadm upgrade node
kubeadm upgrade node
```
## 4.4、升级 kubelet 和 kubectl
### 4.4.1、在所有的 master 节点上执行如下命令以升级 kubelet 和 kubectl
```shell script
# 替换 x 为最新补丁的版本号
yum install -y kubelet-1.16.9 kubectl-1.16.9 --disableexcludes=kubernetes
```
### 4.4.2、执行如下命令，以重启 kubelet
```shell script
systemctl daemon-reload
systemctl restart kubelet
```
## 4.5、升级 worker 节点
    建议逐个升级 worker 节点，或者同一时间点只升级少量的 worker 节点，以避免集群出现资源紧缺的状况。
### 4.5.1、升级 kubeadm（在所有的 worker 节点上执行如下命令，升级 kubeadm）
```shell script
# 将 1.16.9 中的 x 替换为最新的补丁版本
yum install -y kubeadm-1.16.9 --disableexcludes=kubernetes
```
### 4.5.2、排空（drain）节点
```shell script
#执行以下命令，将节点标记为 不可调度的 并驱逐节点上所有的 Pod，
# 在可以执行 kubectl 命令的位置执行（通常是第一个 master节点）
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
### 4.5.3、升级 kubelet 的配置
```shell script
kubeadm upgrade node
```
### 4.5.4、升级 kubelet 和 kubectl
```shell script
#在所有的 worker 节点执行命令
# 替换 x 为最新补丁的版本号
yum install -y kubelet-1.16.9 kubectl-1.16.9 --disableexcludes=kubernetes

#执行如下命令，以重启 kubelet
systemctl daemon-reload
systemctl restart kubelet
```
### 4.5.5、恢复（uncordon）节点
```shell script
#执行如下命令，使节点重新接受调度并投入使用：
kubectl uncordon $NODE
```
## 4.6、检查集群的状态
```shell script
#在所有节点的 kubelet 本升级以后，执行如下命令以验证所有节点都可用：
#STATUS 字段应该为 Ready，版本号也应该显示目标版本号。
kubectl get nodes -o wide
```
## 4.7、从错误状态中恢复
    #如果 kubeadm upgrade 执行过程中出现错误且未曾回滚，例如执行过程中意外关机，您可以再次执行 kubeadm upgrade。
    #该命令是 幂等 的，并将最终保证您能够达到最终期望的升级结果。
    #从失败状态中恢复时，请执行 kubeadm upgrade --force 命令，注意要使用集群的当前版本号。
# 5、卸载k8s
```shell script
kubeadm reset -f
modprobe -r ipip
lsmod
yum clean all
yum remove kube*
rm -rf ~/.kube/
rm -rf /etc/kubernetes/
rm -rf /etc/systemd/system/kubelet.service.d
rm -rf /etc/systemd/system/kubelet.service
rm -rf /usr/bin/kube*
rm -rf /etc/cni
rm -rf /opt/cni
rm -rf /var/lib/etcd
rm -rf /var/etcd

```
# 6、使用Helm部署anan服务
部署anan [点这里readme-anan.md](../helm/readme-anan.md) 
