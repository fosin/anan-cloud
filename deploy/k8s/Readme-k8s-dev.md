# 使用k8s作为开发环境的指引

## 部署docker、k8s、helm及部署相关服务

    其中mysql、redis、rabbitmq、ngxin-ingress是开发环境必须部署得
    anan相关服务按需部署
    其他服务非必须

详细说明 [点这里Readme.md](../helm/Readme.md)

## 部署ktctl工具环境

### 配置开发环境机器环境

    #拷贝k8s配置文件到开发机器上
    windows：复制.kube文件夹中的conf文件到c:\user\<你的操作系统用户名>\.kube
    linux/mac：复制.kube文件夹中的conf文件到\root\.kube

    #设置机器环境变量
    KUBERNETES_SERVICE_HOST=10.96.0.1 #部署K8S时Service网段的网关地址，一般是x.x.0.1
    KUBERNETES_SERVICE_PORT=443 #端口都是443

### 部署ktctl工具

详细说明 <https://alibaba.github.io/kt-connect/#/zh-cn/guide/quickstart>

## 启动开发环境

### 启动ktctl命令（k8s去向通道）

```shell script
#从开发工具连接k8s集群用
ktctl connect
    
```

### 启动ktctl命令（k8s来向通道）

```shell script

# anan-sbaserver是k8s中服务的servie名称
# 6170是开发工具启动的服务对应的端口和anan-sbaserver服务的端口，一般一致

##需要将pod的副本集改成0，让所有流量都导向本地开发工具
kubectl scale --replicas=0 deployment anan-sbaserver


#使用开发工具启动对应服务


# 从k8s访问开发工具使用
ktctl exchange anan-sbaserver --expose 6170

```

## 可以愉快的调试了
