
# 使用Helm部署服务指南

    本文说明使用localpv存储的helm部署指南
    部署环境：测试198

## 1、整体规划

     主机名    |     内网IP        |  节点类型
    -------   |  ------------    |  ----------
    exam-test     100.100.1.198       master、worker

### 准备环境

```shell script

#每台控制平台节点上都执行
cat >> .bash_profile <<EOF
export NODE_NAME_PREFIX=examtest # 主机名称的前缀
export NODE_IPS=(172.16.1.198) # 主机IP数组列表，第一个IP就是部署主IP
export K8S_VERSION=1.19.16 # K8S部署版本
export K8S_LB_IP=100.100.1.198 # K8S控制面板的VIP
export WORKER_LB_IP=100.100.1.198 # K8S工作节点VIP
export K8S_POD_CIDR=10.244.0.0/16 # K8S的POD的IP段
export K8S_SERVICE_CIDR=10.96.0.0/16 # K8S的Service的IP段
export HELM_VERSION=v3.7.2 # Helm的版本
export ANAN_WORKDIR=/data # 部署主目录，后面不带/
EOF

#第1台控制平面上执行（依次类推）
cat >> .bash_profile <<EOF
export NODE_INDEX=0 # 主机唯一编号（与NODE_IPS属性数组一一对应），从0开始，每增加一台服务器数值+1
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

# 设置主机名(所有节点执行)
hostnamectl set-hostname ${NODE_NAME_PREFIX}${NODE_INDEX}
su -

```

## 2、部署Docker

详细介绍 [点这里readme-docker.md](../docker/readme-docker.md)

## 3、部署K8S

详细介绍 [点这里readme-k8s.md](../k8s/readme-k8s.md)

## 4、部署Helm

详细介绍 [点这里readme-helm.md](Readme-helm.md)

## 5、发布服务

详细介绍 [点这里Readme-deploy.md](Readme-deploy.md)
