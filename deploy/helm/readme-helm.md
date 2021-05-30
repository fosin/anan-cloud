# 3、Helm部署指南

## 3.1、整体规划

     主机名    |     内网IP       |  节点类型
    -------   |  ------------   |  ----------
    local0       172.16.1.206       master、worker
    local1       172.16.1.207       master、worker
    local2       172.16.1.208       master、worker

```shell script
# 设置环境变量(每台主机上执行)
# NODE_INDEX：主机唯一编号，从0开始，每增加一台服务器，数值+1
# NODE_NAME_PREFIX：主机名称的前缀
# NODE_IPS：主机IP数组列表，第一个IP就是部署主节点
# K8S_LB_IP：K8S多控制面板的VIP
# K8S_VERSION：K8S部署版本
# NGINX_LB_IP：用于nginx做高可用的VIP
# ANAN_WORKDIR：部署主目录，后面不带/

cat >> .bash_profile <<EOF
export NODE_INDEX=0
export NODE_NAME_PREFIX=local
export NODE_IPS=(172.16.1.206 172.16.1.207 172.16.1.208)
export K8S_LB_IP=172.16.1.200
export NGINX_LB_IP=100.100.1.200
export K8S_VERSION=1.18.12
export ANAN_WORKDIR=/data
EOF

source .bash_profile

# 替换hosts文件
cat > /etc/hosts <<EOF
127.0.0.1   localhost localhost.localdomain localhost4 localhost4.localdomain4
::1         localhost localhost.localdomain localhost6 localhost6.localdomain6
`for ((i=0;i<=${#NODE_IPS[*]} - 1;i++));
  do
   echo ${NODE_IPS[$i]} ${NODE_NAME_PREFIX}$i
done`
EOF

# 所有节点执行
hostnamectl set-hostname ${NODE_NAME_PREFIX}${NODE_INDEX}
su -

```

## 3.2、准备nfs存储

```shell script
# 在所有节点上安装NFS
#（主包提供文件系统）（提供rpc协议）

yum -y install nfs-utils rpcbind

# 新增/etc/exports文件，用于设置需要共享的路径
#insecure    当mount监听端口大于1024时需要使用此参数
#rw   read-write，可读写；
#sync：文件同时写入硬盘和内存；
#async：文件暂存于内存，而不是直接写入内存；
#no_root_squash：NFS客户端连接服务端时如果使用的是root的话，那么对服务端分享的目录来说，也拥有root权限。显然开启这项是不安全的。
#root_squash：NFS客户端连接服务端时如果使用的是root的话，那么对服务端分享的目录来说，拥有匿名用户权限，通常他将使用nobody或nfsnobody身份；
#all_squash：不论NFS客户端连接服务端时使用什么用户，对服务端分享的目录来说都是拥有匿名用户权限；
#anonuid：匿名用户的UID值，通常是nobody或nfsnobody，可以在此处自行设定；
#anongid：匿名用户的GID值。
mkdir -p ${ANAN_WORKDIR} && cd ${ANAN_WORKDIR}
mkdir -p ${ANAN_WORKDIR}/nfs/mysql-leader${NODE_INDEX}
mkdir -p ${ANAN_WORKDIR}/nfs/rabbitmq${NODE_INDEX}
mkdir -p ${ANAN_WORKDIR}/nfs/redis${NODE_INDEX}
mkdir -p ${ANAN_WORKDIR}/nfs/redis$(expr ${NODE_INDEX} + 3)
mkdir -p ${ANAN_WORKDIR}/nfs/es${NODE_INDEX}
mkdir -p ${ANAN_WORKDIR}/nfs/nacos${NODE_INDEX}
chmod 777 -R ${ANAN_WORKDIR}/nfs/es${NODE_INDEX}

cat > /etc/exports <<EOF
`for ((i=0;i<=${#NODE_IPS[*]} - 1;i++));
  do
   echo ${ANAN_WORKDIR}'/nfs/' ${NODE_IPS[$i]}'(insecure,rw,sync,no_root_squash)'
done`
EOF
exportfs -r
exportfs -v

# 重启相应服务（在每个节点上执行）
# 由于必须先启动rpcbind服务，再启动nfs服务，这样才能让nfs服务在rpcbind服务上注册成功
systemctl enable --now rpcbind && systemctl enable --now nfs
systemctl restart rpcbind && sleep 10 && systemctl restart nfs

# 在nfs存储节点查看（在每个节点上执行）
exportfs -v

# 在其他节点上查看nfs存储
showmount -e ${NODE_IPS[0]}

```

## 3.3、部署k8s环境

[详细部署文档请看readme-k8s.md](https://github.com/fosin/anan-cloud/blob/master/deploy/k8s/readme-k8s.md)

## 3.4、部署helm

## Helm部署指南

```shell script
#安装helm
ntpdate ntp1.aliyun.com
#如果wget下载没有进度，使用浏览器下载后手动上传
wget https://get.helm.sh/helm-v3.4.1-linux-amd64.tar.gz --user-agent="Mozilla/5.0 (X11;U;Linux i686;en-US;rv:1.9.0.3) Geco/2008092416 Firefox/3.0.3"  --no-check-certificate
tar -zxvf helm-v3.4.1-linux-amd64.tar.gz && mv linux-amd64/helm /usr/local/bin/ && rm -rf linux-amd64

#设置helm命令补全
yum install bash-completion -y
source /usr/share/bash-completion/bash_completion
echo 'source <(helm completion bash)' >> ~/.bash_profile
source ~/.bash_profile

#设置阿里的镜像仓库
helm repo add stable https://kubernetes-charts.storage.googleapis.com
helm repo add stable https://charts.helm.sh/stable
helm repo add aliyun https://kubernetes.oss-cn-hangzhou.aliyuncs.com/charts

#卸载helm软件
rm -rf /usr/local/bin/helm

```

## 3.5、按顺序启动服务

```shell script
#1、上传anan部署包到到服务器，并解压到/data/helm/相对目录下
mkdir -p ${ANAN_WORKDIR}/helm

#2、创建基础ConfigMap和启动mysql数据库、容器会自动创建对应的数据库，并启用主从同步

cd ${ANAN_WORKDIR}/helm
helm install mysql-leader anan -f anan/mysql-leader.yaml
#这步当前还存在问题，也可以手动创建从节点
#helm install mysql-follower anan -f anan/mysql-follower.yaml

#3、部署rabbitmq集群
helm install rabbitmq ./rabbitmq

#4、部署nacos集群
helm install nacos anan -f anan/nacos.yaml

#5、部署redis集群
#创建6节点redis集群环境
helm install redis anan -f anan/redis.yaml
#组建redis集群 
kubectl exec -it redis-0 -- redis-cli -a local --cluster create $(kubectl get pods -l name=redis -o jsonpath='{range.items[*]}{.status.podIP}:6379 ') --cluster-replicas 1

# 使用template可以查看helm生成的k8s的yaml文件，例如redis
helm template redis anan -f anan/redis.yaml

# 如果是调试测试需要使用--dry-run和--debug，例如redis
helm install redis anan -f anan/redis.yaml --dry-run --debug

#6、部署anan服务
# 可以优先并行启动得
helm install anan-authserver anan -f anan/anan-authserver.yaml
helm install anan-platformserver anan -f anan/anan-platformserver.yaml
helm install anan-adminserver anan -f anan/anan-adminserver.yaml
# 依赖anan-authserver
helm install anan-zuulgateway anan -f anan/anan-zuulgateway.yaml

#7、部署边缘服务
# 部署k8s dashboard
helm install dashboard anan -f anan/dashboard.yaml
helm install dashboard-metrics-scraper anan -f anan/dashboard-metrics-scraper.yaml

#helm install dejavu anan -f anan/dejavu.yaml

#8、部署elk
helm install es anan -f anan/es.yaml
helm install filebeat anan -f anan/filebeat.yaml
helm install kibana anan -f anan/kibana.yaml
helm install elastichd anan -f anan/elastichd.yaml

# 部署zipkin
helm install zipkin anan -f anan/zipkin.yaml

#部署promethus及相关组件(node-exporter、grafana、cadvise、unsee、altmanager)
#helm install promethus ./promethus

#9、部署nginx和静态资源
helm install anan-vue anan -f anan/anan-vue.yaml

```
