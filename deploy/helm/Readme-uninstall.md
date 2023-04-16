
# 卸载anan服务

```shell

#卸载边缘服务
helm uninstall grafana
helm uninstall prometheus
helm uninstall node-exporter
helm uninstall blackbox-exporter
helm uninstall alertmanager

helm uninstall zipkin
helm uninstall kibana
helm uninstall elastichd
helm uninstall filebeat
helm uninstall es

#卸载ingress
helm uninstall anan-nginx-ingress

#卸载anan服务
helm uninstall anan-cloudgateway
helm uninstall anan-authserver
helm uninstall anan-platformserver
helm uninstall anan-exam
helm uninstall anan-sbaserver

#卸载基础服务
helm uninstall nacos
helm uninstall rabbitmq
helm uninstall redis
helm uninstall minio
helm uninstall mysql-cluster
helm uninstall mysql-backup
helm uninstall anan-config
```
