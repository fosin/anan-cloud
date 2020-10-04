# 3、anan相关服务部署
## 3.1、整体规划
     主机名    |     内网IP      |  节点类型
    -------   |  ------------   |  ----------
    local1       192.168.137.8       master
## 3.2、准备nfs存储
```shell script
# 在所有节点上安装NFS
#（主包提供文件系统）（提供rpc协议）

yum -y install nfs-utils rpcbind

systemctl enable nfs && systemctl enable rpcbind

# 新增/etc/exports文件，用于设置需要共享的路径
# 在192.168.137.8上执行

#insecure    当mount监听端口大于1024时需要使用此参数
#rw   read-write，可读写；
#sync：文件同时写入硬盘和内存；
#async：文件暂存于内存，而不是直接写入内存；
#no_root_squash：NFS客户端连接服务端时如果使用的是root的话，那么对服务端分享的目录来说，也拥有root权限。显然开启这项是不安全的。
#root_squash：NFS客户端连接服务端时如果使用的是root的话，那么对服务端分享的目录来说，拥有匿名用户权限，通常他将使用nobody或nfsnobody身份；
#all_squash：不论NFS客户端连接服务端时使用什么用户，对服务端分享的目录来说都是拥有匿名用户权限；
#anonuid：匿名用户的UID值，通常是nobody或nfsnobody，可以在此处自行设定；
#anongid：匿名用户的GID值。

mkdir -p /root/deploy/nfs/mysql-leader1
mkdir -p /root/deploy/nfs/mysql-follower2
mkdir -p /root/deploy/nfs/rabbitmq0
mkdir -p /root/deploy/nfs/redis{0..5}
mkdir -p /root/deploy/nfs/es{0}
# 分配权限，否则es报错没有权限
chmod 777 -R /root/deploy/nfs/es{0}

cat >> /etc/exports <<EOF
/root/deploy/nfs *(insecure,rw,sync,no_root_squash)
EOF

# 重启相应服务（在每个节点上执行）
systemctl restart nfs-server && systemctl restart nfs && systemctl restart rpcbind

# 在nfs存储节点查看（在每个节点上执行）
exportfs -v	
# 在其他节点上查看nfs存储
showmount -e 192.168.137.8
```

### nfs动态存储方案（非必须，只作为参考）
```shell script
helm install anan-nfs stable/nfs-client-provisioner \
--set nfs.server=192.168.137.8 \
--set nfs.path=/root/deploy/nfs \
--set storageClass.name=anan-nfs
```
## 3.3、上传anan部署包到到服务器，并解压到/root/deploy/k8s/相对目录下
```shell script
tar -zxvf deploy.tar.gz ~/
chmod +x /root/deploy/**/*.sh -R
```
## 3.4、按顺序启动服务
一般使用helm操作k8s，部署helm [点这里readme-helm.md](readme-helm.md) 
```shell script
#1、创建基础ConfigMap和启动mysql数据库、容器会自动创建对应的数据库，并启用主从同步

cd /root/deploy/helm
helm install mysql-leader ./anan -f ./anan/mysql-leader.yaml
helm install mysql-follower ./anan -f ./anan/mysql-follower.yaml

#2、部署rabbitmq集群
#helm install rabbitmq ./anan -f ./anan/rabbitmq.yaml
helm install rabbitmq ./rabbitmq

#3、部署nacos集群
helm install nacos ./anan -f ./anan/nacos.yaml

#4、部署redis集群
#模拟6节点redis集群环境，redis持久化后的存储目录需要通过nfs和pv来实现
helm install redis ./anan -f ./anan/redis.yaml
kubectl exec -it redis-0 -- redis-cli -a local --cluster create $(kubectl get pods -l name=redis -o jsonpath='{range.items[*]}{.status.podIP}:6379 ') --cluster-replicas 1

#kubectl exec -it redis-0 -- redis-cli -a local --cluster create $(kubectl get pods -l name=redis -o=jsonpath='{range .items[*]}{.metadata.name}.redis-headless:6379 ') --cluster-replicas 1
#kubectl exec -it redis-0 -- redis-cli -a local --cluster create $(kubectl get pods -l name=redis -o=jsonpath='{range .items[*]}{.metadata.name}.redis-headless.default.svc.cluster.local:6379 ') --cluster-replicas 1

#5、部署anan服务
helm install anan-authserver ./anan -f ./anan/anan-authserver.yaml
helm install anan-platformserver ./anan -f ./anan/anan-platformserver.yaml
helm install anan-zuulgateway ./anan -f ./anan/anan-zuulgateway.yaml
helm install anan-adminserver ./anan -f ./anan/anan-adminserver.yaml
helm install anan-vue ./anan -f ./anan/anan-vue.yaml

#6、部署边缘服务
# 部署k8s dashboard
helm install dashboard ./anan -f ./anan/dashboard.yaml
helm install dashboard-metrics-scraper ./anan -f ./anan/dashboard-metrics-scraper.yaml

#部署elk
helm install es ./anan/ -f ./anan/es.yaml
helm install filebeat ./anan/ -f ./anan/filebeat.yaml
helm install kibana ./anan/ -f ./anan/kibana.yaml
helm install elastichd ./anan/ -f ./anan/elastichd.yaml

helm install dejavu ./anan/ -f ./anan/dejavu.yaml
# 部署zipkin
helm install zipkin ./anan -f ./anan/zipkin.yaml
#部署promethus及相关组件(node-exporter、grafana、cadvise、unsee、altmanager)
helm install promethus ./promethus

```
