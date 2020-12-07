    ## 安装 Docker 软件
```shell script

### 1、 升级系统内核为 4.4
```shell script
# 检查系统内核和模块是否适合运行 docker (仅适用于linux 系统)
curl https://raw.githubusercontent.com/docker/docker/master/contrib/check-config.sh > check-config.sh
chmod +x check-config.sh
bash ./check-config.sh

# CentOS 7.x 系统自带的 3.10.x 内核存在一些 Bugs，导致运行的 Docker、Kubernetes 不稳定
#rpm -Uvh http://www.elrepo.org/elrepo-release-7.0-3.el7.elrepo.noarch.rpm
yum install https://www.elrepo.org/elrepo-release-7.el7.elrepo.noarch.rpm
# 安装完成后检查 /boot/grub2/grub.cfg 中对应内核 menuentry 中是否包含 initrd16 配置，如果没有，再安装一次！
yum --enablerepo=elrepo-kernel install -y kernel-lt

# 查看当前已安装所有内核
sudo awk -F\' '$1=="menuentry " {print i++ " : " $2}' /etc/grub2.cfg

0 : CentOS Linux (4.4.232-1.el7.elrepo.x86_64) 7 (Core)
1 : CentOS Linux (3.10.0-1062.18.1.el7.x86_64) 7 (Core)
2 : CentOS Linux (0-rescue-0ea734564f9a4e2881b866b82d679dfc) 7 (Core)

# 设置开机从新内核启动并重启生效
grub2-set-default 0
# 通过 gurb2-mkconfig 命令创建 grub2 的配置文件，然后重启
grub2-mkconfig -o /boot/grub2/grub.cfg && reboot
# 重启后安装内核源文件（可选）
yum --enablerepo=elrepo-kernel install kernel-lt-devel-$(uname -r) kernel-lt-headers-$(uname -r)

### 2、删除旧内核（可选）
# 查看系统中全部的内核：
rpm -qa | grep kernel

# yum remove 删除旧内核的 RPM 包
yum remove kernel-3.10.0-514.el7.x86_64 \
kernel-tools-libs-3.10.0-862.11.6.el7.x86_64 \
kernel-tools-3.10.0-862.11.6.el7.x86_64 \
kernel-3.10.0-862.11.6.el7.x86_64

### 3、安装docker依赖包
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
yum list docker-ce.x86_64 --showduplicates | sort -r
#   Loading mirror speeds from cached hostfile
#   Loaded plugins: branch, fastestmirror, langpacks
#   docker-ce.x86_64            17.03.1.ce-1.el7.centos            docker-ce-stable
#   docker-ce.x86_64            17.03.1.ce-1.el7.centos            @docker-ce-stable
#   docker-ce.x86_64            17.03.0.ce-1.el7.centos            docker-ce-stable
#   Available Packages
# Step 3: 安装指定版本的Docker-CE: (VERSION 例如上面的 17.03.0.ce.1-1.el7.centos)
# sudo yum -y install docker-ce-[VERSION]
# 当前选择最新版本
sudo yum makecache fast
sudo yum install -y docker-ce

# 配置 daemon.json,执行命令前需要手动去掉前面的空格
mkdir /etc/docker
cat > /etc/docker/daemon.json <<EOF
{
  "exec-opts": ["native.cgroupdriver=systemd"],
  "max-concurrent-downloads": 10,
  "log-level": "warn",
  "log-driver": "json-file",
  "log-opts": {"max-size":"50m", "max-file":"7"},
  "registry-mirrors": ["https://dockerhub.azk8s.cn","https://c70a1b9z.mirror.aliyuncs.com","https://docker.mirrors.ustc.edu.cn/"],
  "experimental": true,
  "data-root": "/var/lib/docker"
}
EOF

```
##  开启Docker远程端口2375(按需选做)
sed -i 's/usr\/bin\/dockerd -H/usr\/bin\/dockerd -H tcp:\/\/0.0.0.0:2375 -H/g' /usr/lib/systemd/system/docker.service

##  重启docker服务
systemctl daemon-reload && systemctl start docker && systemctl enable docker


## 卸载 Docker 软件
```shell script
### 查询docker安装过的包：
yum list installed | grep docker
### 删除安装包：
yum remove docker-ce.x86_64 docker-ce-cli.x86_64 -y
### 删除镜像/容器等
rm -rf /var/lib/docker

```

### 3、实用小技巧
#### 3.1、停止删除当前容器和镜像
##### 一条命令实现停用并删除容器
    docker stop $(docker ps -q) && docker rm $(docker ps -aq)

##### 修剪容器
    #默认情况下，所有停止状态的容器会被删除。可以使用 --filter 标志来限制范围。
    docker container prune -f
    docker rm $(docker ps -a| grep Exited | awk '{print $1}')

#### 3.2、删除指定的镜像
    #删除所有包含关键字fosin的镜像
    docker rmi $(docker images | grep 1.16.15 | awk '{print $3}')
    #删除所有未被tag标记（none）
    docker rmi $(docker images | grep none | awk '{print $3}')
    #清理无容器使用的镜像(虚悬镜像)
    docker image prune -af
    
### 3.3、修剪容器、数据卷、网络、镜像
    # 命令是修剪镜像、容器和网络的快捷方式，指定--volumes标志才会修剪卷
    # 在 Docker 17.06.0 及以前版本中，还好修剪卷。在 Docker 17.06.1 及更高版本中必须为 docker system prune 命令明确指定 --volumes 标志才会修剪卷。
    docker system prune --volumes -f
### 3.4、修剪volumn
    # 卷可以被一个或多个容器使用，并占用 Docker 主机上的空间。卷永远不会被自动删除，因为这么做会破坏数据。
    docker volume prune -f
### 3.5、修剪网络
    # Docker 网络不会占用太多磁盘空间，但是它们会创建 iptables 规则，桥接网络设备和路由表条目。
    # 要清理这些东西，可以使用 docker network prune 来清理没有被容器未使用的网络。
    docker network prune -f

## 配置Docker安全访问（非必须）
### 1、创建CA私钥和CA公钥，首先创建一个ca文件夹用来存放私钥跟公钥
mkdir -p /usr/local/ca
cd /usr/local/ca

### 然后在Docker守护程序的主机上，生成CA私钥和公钥：
openssl genrsa -aes256 -out ca-key.pem 4096

### 2、补全CA证书信息，执行如下指令：
openssl req -new -x509 -days 365 -key ca-key.pem -sha256 -out ca.pem

    Enter pass phrase for ca-key.pem:
    You are about to be asked to enter information that will be incorporated
    into your certificate request.
    What you are about to enter is what is called a Distinguished Name or a DN.
    There are quite a few fields but you can leave some blank
    For some fields there will be a default value,
    If you enter '.', the field will be left blank.
    -----
    Country Name (2 letter code) [XX]:cn
    State or Province Name (full name) []:guiyang
    Locality Name (eg, city) [Default City]:guiyang
    Organization Name (eg, company) [Default Company Ltd]:fosin
    Organizational Unit Name (eg, section) []:fosin
    Common Name (eg, your name or your server's hostname) []:fosin
    Email Address []:28860823@qq.com

### 3、生成server-key.pem
openssl genrsa -out server-key.pem 4096

### 4、用CA签署公钥
由于可以通过IP地址和DNS名称建立TLS连接，因此在创建证书时需要指定IP地址。例如，允许使用10.211.55.4进行连接：

openssl req -subj "/CN=10.211.55.4" -sha256 -new -key server-key.pem -out server.csr
如果你是用的网址(比如:www.sscai.club)则替换一下即可：

openssl req -subj "/CN=anan.fosin.top" -sha256 -new -key server-key.pem -out server.csr
注意：这里指的ip或者是域名，都是指的将来用于对外的地址。

### 5、匹配白名单
配置白名单的意义在于，允许哪些ip可以远程连接docker：
如果你对外docker的地址是ip地址，则命令如下：
echo subjectAltName = DNS:$HOST,IP:XX.XX.XX.XX,IP:XX.XX.XX.XX >> extfile.cnf

echo subjectAltName = DNS:anan.fosin.top,IP:0.0.0.0 >> extfile.cnf

### 6、执行命令
将Docker守护程序密钥的扩展使用属性设置为仅用于服务器身份验证：
echo extendedKeyUsage = serverAuth >> extfile.cnf

### 7、生成签名整数
openssl x509 -req -days 365 -sha256 -in server.csr -CA ca.pem -CAkey ca-key.pem \
  -CAcreateserial -out server-cert.pem -extfile extfile.cnf
执行后需要输入上方设置的密码


### 8、生成客户端的key.pem
openssl genrsa -out key.pem 4096
openssl req -subj '/CN=client' -new -key key.pem -out client.csr

### 9、要使秘钥适合客户端身份验证
创建扩展配置文件：

echo extendedKeyUsage = clientAuth >> extfile.cnf
echo extendedKeyUsage = clientAuth > extfile-client.cnf

### 10、生成签名整数,生成cert.pem，需要再次输入之前设置的密码：
openssl x509 -req -days 365 -sha256 -in client.csr -CA ca.pem -CAkey ca-key.pem \
  -CAcreateserial -out cert.pem -extfile extfile-client.cnf

### 11、删除不需要的文件，两个整数签名请求
生成后cert.pem，server-cert.pem您可以安全地删除两个证书签名请求和扩展配置文件：

rm -v client.csr server.csr extfile.cnf extfile-client.cnf

### 12、可修改权限
为了保护您的密钥免于意外损坏，请删除其写入权限。要使它们仅供您阅读，请按以下方式更改文件模式：

chmod -v 0400 ca-key.pem key.pem server-key.pem
chmod -v 0444 ca.pem server-cert.pem cert.pem

### 13、归集服务器证书
cp server-*.pem /etc/docker/
cp ca.pem /etc/docker/

### 14、修改Docker配置
使Docker守护程序仅接收来自提供CA信任的证书的客户端的链接

vim /lib/systemd/system/docker.service
将 ExecStart 属性值进行替换：

ExecStart=/usr/bin/dockerd --tlsverify --tlscacert=/etc/docker/ca.pem --tlscert=/etc/docker/server-cert.pem --tlskey=/etc/docker/server-key.pem -H tcp://0.0.0.0:2375 -H unix:///var/run/docker.sock

### 15、重新加载daemon并重启docker
systemctl daemon-reload && systemctl restart docker && systemctl enable docker

### 16、把ca.perm、ca-key.perm、cert.perm、key.perm四个文件拷贝到客户端用于远程连接Docker
