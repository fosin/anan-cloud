## 安装 Docker 软件
```shell script
# 安装docker依赖包
yum install -y yum-utils device-mapper-persistent-data lvm2

# 导入阿里云docker镜像仓库
yum-config-manager --add-repo https://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo

# 注意：
# 官方软件源默认启用了最新的软件，您可以通过编辑软件源的方式获取各个版本的软件包。例如官方并没有将测试版本的软件源置为可用，你可以通过以下方式开启。同理可以开启各种测试版本等。
# vim /etc/yum.repos.d/docker-ce.repo
#   将 [docker-ce-test] 下方的 enabled=0 修改为 enabled=1
#
# 安装指定版本的Docker-CE:
# Step 1: 安装指定版本的docker-ce-selinux
# yum install https://download.docker.com/linux/centos/7/x86_64/stable/Packages/docker-ce-selinux-17.03.2.ce-1.el7.centos.noarch.rpm
# Step 2: 查找Docker-CE的版本:
# yum list docker-ce.x86_64 --showduplicates | sort -r
#   Loading mirror speeds from cached hostfile
#   Loaded plugins: branch, fastestmirror, langpacks
#   docker-ce.x86_64            17.03.1.ce-1.el7.centos            docker-ce-stable
#   docker-ce.x86_64            17.03.1.ce-1.el7.centos            @docker-ce-stable
#   docker-ce.x86_64            17.03.0.ce-1.el7.centos            docker-ce-stable
#   Available Packages
# Step 3: 安装指定版本的Docker-CE: (VERSION 例如上面的 17.03.0.ce.1-1.el7.centos)
# sudo yum -y install docker-ce-[VERSION]
sudo yum makecache fast
sudo yum -y install docker-ce

# 配置 daemon.json,执行命令前需要手动去掉前面的空格
mkdir /etc/docker
cat > /etc/docker/daemon.json <<EOF
{
  "exec-opts": ["native.cgroupdriver=systemd"],
  "log-driver": "json-file",
  "log-opts": {"max-size":"50m", "max-file":"3"},
  "registry-mirrors": ["https://dockerhub.azk8s.cn","https://c70a1b9z.mirror.aliyuncs.com","https://docker.mirrors.ustc.edu.cn/"],
  "experimental": true
}
EOF

# 创建目录存放docker配置文件
mkdir -p /etc/systemd/system/docker.service.d

# 开启Docker远程端口2375(按需选做)
sed -i 's/usr\/bin\/dockerd -H/\/usr\/bin\/dockerd -H tcp:\/\/0.0.0.0:2375 -H' /usr/lib/systemd/system/docker.service

# 重启docker服务
systemctl daemon-reload && systemctl restart docker && systemctl enable docker
```
