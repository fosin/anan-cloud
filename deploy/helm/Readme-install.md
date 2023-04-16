# 发布helm服务

## 4.2、更新 helm 关联 charts

```shell script

cd deploy\helm

helm dependency update .\mysql-cluster
helm dependency update .\mysql-backup
helm dependency update .\rabbitmq
helm dependency update .\redis
helm dependency update .\redisinsight
helm dependency update .\nacos
helm dependency update .\anan-cloud\anan-config
helm dependency update .\anan-cloud\anan-platformserver
helm dependency update .\anan-cloud\anan-authserver
helm dependency update .\anan-cloud\anan-cloudgateway
helm dependency update .\anan-cloud\anan-sbaserver
helm dependency update .\anan-cloud\anan-zuulgateway

helm dependency update .\efk\es
helm dependency update .\efk\es-cleaner
helm dependency update .\efk\filebeat
helm dependency update .\efk\kibana
helm dependency update .\efk\elastichd
helm dependency update .\efk\zipkin
helm dependency update .\nginx-ingress
helm dependency update .\monitor\node-exporter
helm dependency update .\monitor\blackbox-exporter
helm dependency update .\monitor\alertmanager
helm dependency update .\monitor\prometheus
helm dependency update .\monitor\grafana
helm dependency update .\monitor\dashboard
helm dependency update .\monitor\dashboard-metrics-scrapper

```

## 5、按顺序启动服务

### 5.1、部署MySQL MGR集群

```shell script

#1、上传anan部署包到到服务器，并解压到/data/helm/相对目录下
mkdir -p ${ANAN_WORKDIR}/helm && cd ${ANAN_WORKDIR}/helm
mkdir -p ${ANAN_WORKDIR}/mysql-cluster${NODE_INDEX}
mkdir -p ${ANAN_WORKDIR}/logs/mysql-cluster${NODE_INDEX}

#mysql容器中的用户名和组名都是mysql，id都是999，但是这样不能生效，还是需要777权限
#chown 999:999 -R ${ANAN_WORKDIR}/logs/mysql-cluster${NODE_INDEX}
chmod 777 -R ${ANAN_WORKDIR}/logs/mysql-cluster${NODE_INDEX}

#2、创建基础ConfigMap和启动mysql数据库、容器会自动创建对应的数据库，并启用主从同步

helm install mysql-cluster mysql-cluster/
helm install mysql-backup mysql-backup/

#所有的节点都执行安装MGR插件
INSTALL PLUGIN group_replication SONAME 'group_replication.so';
show plugins;

#所有的节点都执行设置复制账号
SET SQL_LOG_BIN=0;
CREATE USER 'repl'@'%';
GRANT REPLICATION SLAVE ON *.* to 'repl'@'%' identified by 'local';
FLUSH PRIVILEGES;
SET SQL_LOG_BIN=1;
CHANGE MASTER TO MASTER_USER='repl', MASTER_PASSWORD='local' FOR CHANNEL 'group_replication_recovery';

#启动MGR，在主库节点上上执行
SET GLOBAL group_replication_bootstrap_group=ON;
START GROUP_REPLICATION;
SET GLOBAL group_replication_bootstrap_group=OFF;

#在其他节点加入MGR集群
START GROUP_REPLICATION;

#查看MGR组信息,主节点状态为ONLINE（任意节点执行）
select * from performance_schema.replication_group_members;

select * from performance_schema.replication_group_member_stats\G;

select * from performance_schema.replication_connection_status\G;

select * from performance_schema.replication_applier_status\G;

#创建用户、导入表结构和数据（任意节点执行）
CREATE USER 'anan'@'%' IDENTIFIED BY 'local';
GRANT ALL PRIVILEGES ON anan_platform.* TO 'anan'@'%';
GRANT ALL PRIVILEGES ON exam.* TO 'anan'@'%';

CREATE USER 'nacos'@'%' IDENTIFIED BY 'local';
GRANT ALL PRIVILEGES ON nacos.* TO 'nacos'@'%';

CREATE USER 'grafana'@'%' IDENTIFIED BY 'local';
create database grafana;
GRANT ALL PRIVILEGES ON grafana.* TO 'grafana'@'%';
FLUSH PRIVILEGES;

```

### 5.2、部署rabbitmq集群

```shell script

mkdir -p ${ANAN_WORKDIR}/rabbitmq${NODE_INDEX}

helm install rabbitmq rabbitmq/

```

### 5.3、部署nacos集群


```shell script

mkdir -p ${ANAN_WORKDIR}/nacos${NODE_INDEX}
mkdir -p ${ANAN_WORKDIR}/logs/nacos${NODE_INDEX}

helm install nacos nacos/

```

### 5.4、部署redis集群

```shell script

mkdir -p ${ANAN_WORKDIR}/redis${NODE_INDEX}

rm -rf /data/redis${NODE_INDEX}/*

helm install redis redis/

mkdir -p ${ANAN_WORKDIR}/redisinsight
mkdir -p ${ANAN_WORKDIR}/logs/redisinsight
chmod 777 ${ANAN_WORKDIR}/redisinsight
chmod 777 ${ANAN_WORKDIR}/logs/redisinsight

helm install redisinsight redisinsight/

```

### 5.6、部署anan服务

```shell script

#创建目录
mkdir -p ${ANAN_WORKDIR}/logs/anan-authserver${NODE_INDEX}
mkdir -p ${ANAN_WORKDIR}/logs/anan-platformserver${NODE_INDEX}
mkdir -p ${ANAN_WORKDIR}/logs/anan-cloudgateway${NODE_INDEX}
mkdir -p ${ANAN_WORKDIR}/logs/anan-sbaserver${NODE_INDEX}

#获取加密密码(可选)
java -cp E:\Tools\Apache\Maven\repository\org\jasypt\jasypt\1.9.3\jasypt-1.9.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="local" password=oF7tVrdfjrbQ0NfSsRL3 algorithm=PBEWithMD5AndDES

#如果只需要配置文件可以使用以下命令（特殊情况下使用）
kubectl create configmap application --from-file=anan-cloud/anan-platformserver/application.yaml
kubectl create configmap anan-platformserver --from-file=anan-cloud/anan-platformserver/anan-platformserver.yaml

#安装服务
helm install anan-platformserver anan-cloud/anan-platformserver
helm install anan-authserver anan-cloud/anan-authserver
helm install anan-cloudgateway anan-cloud/anan-cloudgateway
helm install anan-sbaserver anan-cloud/anan-sbaserver

#查询健康状态
curl http://`kubectl get pod -owide |grep anan-authserver | awk '{print $6}'`:6140/actuator/health -vvv
curl -XPOST http://`kubectl get pod -owide |grep anan-authserver | awk '{print $6}'`:6140/actuator/shutdown -vvv

```

### 5.7、部署日志服务（可选）

```shell script

#1、部署es

mkdir -p ${ANAN_WORKDIR}/es${NODE_INDEX}
chmod 777 -R ${ANAN_WORKDIR}/es${NODE_INDEX}

helm install es efk/es/
helm install es efk/es-cleaner/

#如果是第一次创建ES需要开启X-Pack认证
cd /usr/share/elasticsearch
#生成ssl证书
bin/elasticsearch-certutil ca
bin/elasticsearch-certutil cert --ca elastic-ca.p12
#复制证书到数据目录下
kubectl cp es-0:elastic-ca.p12 /data/es0/elastic-ca.p12
scp /data/es-0/elastic-ca.p12 exam1:/data/es1/
scp /data/es-0/elastic-ca.p12 exam2:/data/es2/

#修改密码（elastic、apm_system、kibana、logstash_system、beats_system、remote_monitoring_user）
bin/elasticsearch-setup-passwords interactive

#卸载es集群并重新部署
helm uninstall es && sleep 20 && helm install es efk/es/

#快速查看集群状态命令
kubectl exec -it es-0 -- curl -u elastic "http://localhost:9200/_nodes/hot_threads?pretty"
kubectl exec -it es-0 -- curl -u elastic "http://localhost:9200/_cluster/health?pretty"

#2、部署依赖es的模块
helm install filebeat efk/filebeat/
helm install kibana efk/kibana/
helm install zipkin efk/zipkin/
helm install elastichd efk/elastichd/

```

### 5.8、部署promethus监控体系（可选）

```shell script

#创建数据目录
mkdir -p ${ANAN_WORKDIR}/alertmanager
chmod 777 -R /data/alertmanager
mkdir -p ${ANAN_WORKDIR}/prometheus

helm install node-exporter monitor/node-exporter/
helm install blackbox-exporter monitor/blackbox-exporter/
helm install prometheus monitor/prometheus/
helm install alertmanager monitor/alertmanager/
helm install grafana monitor/grafana/

#运维常用命令
##热加载配置文件命令
curl -XPOST http://`kubectl get pod -owide |grep prometheus | awk '{print $6}'`:9090/prometheus/-/reload -vvv

curl -XPOST http://`kubectl get pod -owide |grep alertmanager-0 | awk '{print $6}'`:9093/-/reload -vvv
curl -XPOST http://`kubectl get pod -owide |grep alertmanager-1 | awk '{print $6}'`:9093/-/reload -vvv
curl -XPOST http://`kubectl get pod -owide |grep alertmanager-2 | awk '{print $6}'`:9093/-/reload -vvv

curl -XPOST http://`kubectl get pod -owide |grep node-exporter-1 | awk '{print $6}'`:9100/-/reload -vvv
##Grafana常用命令
###重置密码
grafana-cli admin reset-admin-password admin@1234

#部署k8s dashboard（可选）

详细介绍 [点这里Readme-k8s-dashboard.md](monitor/dashboard/Readme-k8s-dashboard.md)

```

### 5.9、部署Ingress和静态资源

```shell script

#1、部署ingress和SSL证书
helm install anan-nginx-ingress nginx-ingress/

```