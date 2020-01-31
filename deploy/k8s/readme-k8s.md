# 1、操作系统初始化

## 1.1、安装依赖包
	yum install -y conntrack ntp ipvsadm ipset jq iptables curl sysstat libseccomp wget vim net-tools git
## 1.2、设置防火墙为Iptables并清空规则
	systemctl stop firewalld && systemctl disable firewalld
	yum install -y iptables-services && systemctl start iptables && systemctl enable iptables && iptables -F && service iptables save
## 1.3、关闭SELINUX
	swapoff -a && sed -i '/ swap / s/^\(.*\)$/#\1/g' /etc/fstab
	setenforce 0 && sed -i 's/^SELINUX=.*/SELINUX=disabled/' /etc/selinux/config
## 1.4、调整内核参数，对于 K8S
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

## 1.5、调整系统时区
	# 设置系统时区为 中国/上海
	timedatectl set-timezone Asia/Shanghai
	# 将当前的 UTC 时间写入硬件时钟
	timedatectl set-local-rtc 0
	#重启依赖于系统时间的服务
	systemctl restart rsyslog && systemctl restart crond

## 1.6、关闭系统不需要服务
	systemctl stop postfix && systemctl disable postfix
## 1.7、设置 rsyslogd 和 systemd journald
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
## 1.8、升级系统内核为 4.44
	# CentOS 7.x 系统自带的 3.10.x 内核存在一些 Bugs，导致运行的 Docker、Kubernetes 不稳定
	
	rpm -Uvh http://www.elrepo.org/elrepo-release-7.0-3.el7.elrepo.noarch.rpm
	# 安装完成后检查 /boot/grub2/grub.cfg 中对应内核 menuentry 中是否包含 initrd16 配置，如果没有，再安装一次！
	yum --enablerepo=elrepo-kernel install -y kernel-lt
	# 设置开机从新内核启动并重启生效
	grub2-set-default "CentOS Linux (4.4.206-1.el7.elrepo.x86_64) 7 (Core)" && reboot
	# 重启后安装内核源文件（这步可以不做）
	yum --enablerepo=elrepo-kernel install kernel-lt-devel-$(uname -r) kernel-lt-headers-$(uname -r)

## 1.9、关闭 NUMA

	cp /etc/default/grub{,.bak}
	
	vim /etc/default/grub # 在 GRUB_CMDLINE_LINUX 一行添加 `numa=off` 参数，如下所示：
	diff /etc/default/grub.bak /etc/default/grub
	6c6
	< GRUB_CMDLINE_LINUX="crashkernel=auto rd.lvm.lv=centos/root rhgb quiet"
	---
	> GRUB_CMDLINE_LINUX="crashkernel=auto rd.lvm.lv=centos/root rhgb quiet numa=off"
	
	cp /boot/grub2/grub.cfg{,.bak}
	grub2-mkconfig -o /boot/grub2/grub.cfg

# 2、Kubeadm部署安装

## 2.1、kube-proxy开启ipvs的前置条件
	modprobe br_netfilter
	cat > /etc/sysconfig/modules/ipvs.modules <<EOF
	#!/bin/bash
	modprobe -- ip_vs
	modprobe -- ip_vs_rr
	modprobe -- ip_vs_wrr
	modprobe -- ip_vs_sh
	modprobe -- nf_conntrack_ipv4
	EOF
	
	chmod 755 /etc/sysconfig/modules/ipvs.modules && bash /etc/sysconfig/modules/ipvs.modules && lsmod | grep -e ip_vs -e nf_conntrack_ipv4
## 2.2、安装 Docker 软件
	# 安装docker依赖包
	yum install -y yum-utils device-mapper-persistent-data lvm2
	
	# 导入阿里云docker镜像仓库
	yum-config-manager --add-repo https://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
	
	# 更新操作系统并安装docker-ce最新版
	yum update -y && yum install -y docker-ce
	
	## 创建 /etc/docker 目录
	mkdir /etc/docker
	
	# 配置 daemon.
	cat > /etc/docker/daemon.json <<EOF
	{
	  "exec-opts": ["native.cgroupdriver=systemd"],
	  "log-driver": "json-file",
	  "log-opts": {
		"max-size": "100m"
	  },
	  "registry-mirrors": ["https://c70a1b9z.mirror.aliyuncs.com"],
	  "experimental": true
	}
	EOF
	
	# 创建目录存放docker配置文件
	mkdir -p /etc/systemd/system/docker.service.d
	
	# 重启docker服务
	systemctl daemon-reload && systemctl restart docker && systemctl enable docker
## 2.3、在主节点启动 Haproxy 与 Keepalived 容器
	导入脚本 > 运行 > 查看可用节点

## 2.4、安装 Kubeadm （主从配置）
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
	
	yum -y install kubeadm-1.15.1 kubectl-1.15.1 kubelet-1.15.1
	
	systemctl enable kubelet.service

## 2.5、初始化主节点
	# 初始化主节点时kubeadm会从google k8s仓库中拉取镜像，需要科学上网才能访问，可以使用load本地镜像来提前加载docker镜像
	# 上传镜像包kubeadm-basic.images.tar.gz和load-images.sh脚本
	tar -zxvf kubeadm-basic.images.tar.gz -C /root/
	chmod +x load-images.sh
	./load-images.sh
	
	# 生成配置文件
	kubeadm config print init-defaults > kubeadm-config.yaml
	
	# 修改kubeadm-config.yml配置文件
	localAPIEndpoint:
	advertiseAddress: 192.168.137.8 修改IP为本机IP 
	kubernetesVersion: v1.15.1 修改kubernetesVersion: v1.15.1
	networking:
	podSubnet: "10.244.0.0/16"  增加podSubnet: "10.244.0.0/16"
	serviceSubnet: 10.96.0.0/12
	
	## 增加以下子段
	---
	apiVersion: kubeproxy.config.k8s.io/v1alpha1
	kind: KubeProxyConfiguration
	featureGates:
	  SupportIPVSProxyMode: true
	mode: ipvs

	kubeadm init --config=kubeadm-config.yaml --experimental-upload-certs | tee kubeadm-init.log
## 2.6、加入主节点以及其余工作节点
	执行安装日志中的加入命令即可

## 2.7、Etcd 集群状态查看
	kubectl -n kube-system exec etcd-k8s-master01 -- etcdctl \
	--endpoints=https://192.168.137.8:2379 \
	--ca-file=/etc/kubernetes/pki/etcd/ca.crt \
	--cert-file=/etc/kubernetes/pki/etcd/server.crt \
	--key-file=/etc/kubernetes/pki/etcd/server.key cluster-health
	kubectl get endpoints kube-controller-manager --namespace=kube-system -o yaml
	kubectl get endpoints kube-scheduler --namespace=kube-system -o yaml
## 2.8、部署网络
	mkdir flannel
	cd flannel
	wget https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel.yml
	
	kubectl create -f kube-flannel.yml
	
	kubectl apply -f kube-flannel.yml
# 3、anan服务部署
