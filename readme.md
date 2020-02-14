# 设计定位
    anan基于spring boot和spring cloud生态体系技术，采用微服务前后端分离架构，为个人及企业微
    服务架构提供一种解决方案，供开发人员学习和交流。其中包括服务注册与发现、服务监控、服务管理、
    服务治理、服务网关、服务熔断、配置管理、认证授权中心等常见微服务组件。其中服务注册与发现支持
    nacos、eureka。支持swarm集群部署、Kubernetes集群部署、jar包集群部署、war包集群部署
    对应前端项目地址: https://github.com/fosin/anan-vue
# 技术选型
     技术     |   简介 
    ------   |  ------
    Spring Boot | 基础构建框架，用于快速整合各资源 
    SpringMVC | MVC分层框架 
    Spring Cloud Alibaba Nacos(备选Spring Cloud Config) | 配置管理中心 
    Spring Cloud Alibaba Nacos(备选Spring Cloud Eureka) | 服务注册中心 
    Spring Cloud Zuul | 服务网关 
    Spring Cloud Hystrix | 服务熔断框架 
    Spring Cloud Feign | 微服务声明式调用框架 
    Spring Cloud Sleuth Zipkin| 分布式链路追踪
    Spring Boot Admin | 服务管理中心 
    Spring Data Jpa | 持久化框架 
    Spring Data Redis | 缓存框架 
    Spring Security| 安全框架 
    Spring Cloud OAuth2.0 | 单点登录、鉴权、认证框架
    Slf4j Log4j2 | 日志框架
    Spring Validator | 后端验证框架 
    Hibernate Validator | Hibernate验证框架 
    lombok | 一个通过注解自动生成get/set方法的类库 
    Swagger | API文档
    Thymeleaf | HTML5模板引擎  
    Maven | 项目构建管理  
    Redis | 缓存内存数据库 
    Elasticsearch | 搜索内存数据库 
    Mysql | 对象关系数据库 
    RabbitMQ | 消息中间件
    EFK | 日志收集、分析组合框架
    Vuejs、Nodejs、Webpack、ElementUI | 前段开发框架
# 架构设计

# 原理介绍

# 框架版本对照
       anan-cloud    |     anan-boot     |     anan-vue
    -------------------------------------------------------
    1.0.0-SNAPSHOT      1.0.0-SNAPSHOT       1.0.0-SNAPSHOT
    2.0.0-SNAPSHOT      2.0.0-SNAPSHOT       2.0.0-SNAPSHOT
    2.1.0-SNAPSHOT      2.0.0-SNAPSHOT       2.0.0-SNAPSHOT 

# 搭建环境
## 1、本地开发环境local设置
    基于docker的运行环境都是在CentOS7上部署得，如果是其他操作系统需要自行研究，理论上也是没有问题得。
### 1.1、安装docker、docker-compose并配置镜像加速
#### 1.1.1、安装docker
    # step 1: 安装必要的一些系统工具
    sudo yum install -y yum-utils device-mapper-persistent-data lvm2
    # Step 2: 添加软件源信息
    sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
    # Step 3: 更新并安装 Docker-CE
    sudo yum makecache fast
    sudo yum -y install docker-ce
    # Step 4: 开启Docker服务
    sudo systemctl start docker
    # Step 5: 开启开机Docker服务自动启动
    sudo systemctl enable docker    
    
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
#### 1.1.2、安装docker-compose(按需安装注意版本更新和匹配)
    sudo curl -L "https://github.com/docker/compose/releases/download/1.24.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
    sudo chmod +x /usr/local/bin/docker-compose
#### 1.1.3、配置参数（阿里云镜像加速、开启实验性功能）
    sudo mkdir -p /etc/docker
    sudo tee /etc/docker/daemon.json <<-'EOF'
    {
      "registry-mirrors": ["https://c70a1b9z.mirror.aliyuncs.com"],
      "experimental": true
    }
    
    EOF

    sudo systemctl daemon-reload
    sudo systemctl restart docker

### 1.2、基础运行环境安装篇，使用docker-compose.yml文件(mysql、Redis、RabbitMQ、nacos)
       所有涉及帐号和密码的地方默认帐号都是anan，默认密码都是local，如果不是账户不是anan得地方，下面会单独说明。
       如果需要修改密码，则需要修改以下地方：
           docker-compose.yml（中间件帐号和密码）
           nacos配置中心（anan服务相关帐号密码）
           anan-cloud下面的pom.xml中的profile local 的配置信息（spring security帐号密码）
       docker-compose.yml中redis、rabbitmq、nacos-server1、mysql-master、mysql-slave5个是后台开发环境必须启动的
       1.2.1、创建docker网络     
            docker network create -d bridge --subnet=172.28.0.0/16 anan-bridge
       1.2.2、启动mysql主从同步模式和nacos服务发现和配置管理
            docker-compose -f anan-cloud\docker-compose.yml up -d mysql-master mysql-slave nacos-server1
            
            启动完成后，可以访问http://容器主机IP:8848/nacos/来查看nacos服务发现和配置中心。
            账户：nacos
            密码：local
       1.2.3、安装Redis(3.x、4.x、5.x都支持)
            docker-compose -f anan-cloud\docker-compose.yml up -d redis
       1.2.4、安装Rabbitmq(只测试过3.x)
            docker-compose -f anan-cloud\docker-compose.yml up -d rabbitmq
       1.2.5、如果对机器性能有信息，以上组件也可以使用一个命令启动
            docker-compose -f D:\myproject\anan\anan-cloud\docker-compose.yml up -d redis rabbitmq mysql-master mysql-slave nacos-server1

### 1.3、日志安装篇，使用文件docker-compose.yml(elsaticsearch、filebeat、kibana等) -非必须
       1.3.1、安装ElasticSearch6.7及以上(6.7及以上kibana有中文版，不要中文版安装低版本也可以)
            docker-compose -f anan-cloud\docker-compose.yml up -d elasticsearch
            
            启动时报错：max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144] 
            原因：最大虚拟内存太小 
            解决方案：切换到root用户下，修改配置文件sysctl.conf
             
            sudo vim /etc/sysctl.conf
             
            添加下面配置： 
            vm.max_map_count=262144
             
            并执行命令： 
            sysctl -p
            
       1.3.2、安装filebeat、kibana
            docker-compose -f anan-cloud\docker-compose.yml up -d filebeat kibana    
       1.3.3、启动完成后，可以访问以下站点：
            Kibana: http://容器主机IP:5601/app/kibana

### 1.4、监控安装篇，使用文件docker-compose.yml(prometheus、node-exporter、cadvisor、alertmanager、grafana等) -非必须

       docker-compose -f anan-cloud\docker-compose.yml up -d cadvisor alertmanager node-exporter prometheus grafana
       
       1.4.1、安装cadvisor版本:v0.33.0及以上
            docker-compose -f anan-cloud\docker-compose.yml up -d cadvisor
       
            发现容器没有正常启动，查看日志，有如下报错内容：    
            Failed to start container manager: inotify_add_watch 
            /sys/fs/cgroup/cpuacct,cpu: no such file or directory
            临时解决方法，但是doker主机重启后又要修改，执行：
            mount -o remount,rw '/sys/fs/cgroup'
            ln -s /sys/fs/cgroup/cpu,cpuacct /sys/fs/cgroup/cpuacct,cpu
       1.4.2、启动完成后，可以访问以下站点：
            Promethus: http://容器主机IP:9090
            grafana: http://容器主机IP:3000/
### 1.5、服务安装篇，使用文件docker-compose.yml(anan-eurekaserver、anan-configserver、anan-authserver等) -非必须
        本地开发环境基本上不需要启动这些服务，主要还是使用源码跑
            anan-eurekaserver
            anan-configserver
            anan-authserver
            anan-platformserver
            anan-zuulgateway
            anan-adminserver
### 1.5、配置开发环境
       1.5.1、安装jdk1.8及以上、lombok插件、ignore插件，开发工具推荐使用Idea
       1.5.2、Windows下修改c:/windows/system32/drives/etc/hosts文件增加以下信息，IP地址根据实际情况设定
            192.168.137.1是本机IP，192.168.137.8是Docker容器的主机IP

            192.168.137.8 nacos-server1
            192.168.137.8 redis
            192.168.137.8 rabbitmq
            192.168.137.8 mysql-master
            192.168.137.8 mysql-slave
            192.168.137.1 anan-eurekaserver
            192.168.137.1 anan-authserver
            
       1.5.3、 配置 log4j.skipJansi使log4j2的日志支持颜色字体
            IDEA中，点击右上角->Edit Configurations，在VM options中添加
            -Dlog4j.skipJansi=false
            
            MYECLIPSE中，Run Configurations->Arguments-> VM arguments 中添加
            -Dlog4j.skipJansi=false
            
            MYECLIPSE 需要ansi 插件的支持

### 1.6、按顺序启动服务
       1.6.1、启动anan-authserver授权认证中心
       1.6.2、启动anan-platformserver平台服务中心、anan-zuulgateway服务路由网关
       1.6.3、启动anan-adminserver服务监控
       
### 1.7、打开前端项目anan-vue,移步 https://github.com/fosin/anan-vue 下面的README.md查看前端项目的开发环境搭建过程
### 1.8、如果使用Spring Cloud Eureka作为服务注册和发现组件、Config作为配置中心()
       1.8.1、修改在以下模块的源码目录下的启动配置文件src/main/resources/bootstrap.yml
            anan-authserver
            anan-platformserver
            anan-zuulgateway
            anan-adminserver
            anan-cloudgateway
       1.8.1、关闭Nacos的作为服务发现和配置中心的设置
            spring.cloud.nacos.config.enabled=false
            spring.cloud.nacos.discovery.enabled=false
        
       1.8.2、启用Eureka和Config作为服务发现和配置中心的设置
           euerka.client.enabled=true
           spring.cloud.config.discovery.enabled=true
           spring.cloud.config.enabled=true
       1.8.3、设置启动配置
            修改anan-cloud的pom.xml中的相关配置，local环境可以不用修改，一般需要修改生产环境
            <eureka.client.service-url.defaultZone>
                http://${anan-placeholder1}spring.security.user.name${anan-placeholder2}:${anan-placeholder1}spring.security.user.password${anan-placeholder2}@anan-eurekaserver:51000/eureka/
            </eureka.client.service-url.defaultZone>
            <spring.cloud.config.profileActive>native</spring.cloud.config.profileActive>
            <spring.cloud.config.server.svn.uri></spring.cloud.config.server.svn.uri>
            <spring.cloud.config.server.svn.username></spring.cloud.config.server.svn.username>
            <spring.cloud.config.server.svn.password></spring.cloud.config.server.svn.password>
            <spring.cloud.config.server.native.search-locations>file:deploy/anan-config</spring.cloud.config.server.native.search-locations>
            <spring.cloud.config.server.git.uri>https://github.com/fosin/anan-cloud/deploy</spring.cloud.config.server.git.uri>
            <spring.cloud.config.server.git.search-paths>anan-config</spring.cloud.config.server.git.search-paths>
            <spring.cloud.inetutils.preferred-networks>192.168.137.</spring.cloud.inetutils.preferred-networks>
            <spring.rabbitmq.addresses>rabbitmq:5672</spring.rabbitmq.addresses>
            <spring.rabbitmq.username>'{cipher}AQAGy3LYSBkcOJA32VA/mT3jNtxscWGe0Eto3isApxPrUgv00Pw0629LROM3zCh9KC3/HqP4aoUexyBw3QhdLCkXr6zpXGFrj0x7EPcu+A9B9kbzZejfcdG3l+Wln+chjS50cLe0txPDt4ioZxbLb0BjgwfPS4THrIfozhJgyRKNwkrw4prTXSnE2o/tbo/rHKJutXF53ywkAqRK7sZNFUMiHZTKiQkKzzCTUQmFRoBYkYtoc2SjVMerEfySCyx8oEm0+FnLpUuFpexO/cVl/YbWxmeU4qvrpeDgj1OEJ9JAuKZGfSHF/1LdvaOP7HVlx1EiyqLoQys/pa1vFjkRu8YHSmSS7jOfiGty2sT3g4+fFG7G0K+8g5cmRGAUbhUppDI='</spring.rabbitmq.username>
            <spring.rabbitmq.password>'{cipher}AQAf9K0s8zLVaIk44+wqpHGuHsHEK5G4KKu1vk0HwxlojaYQHRkNsFhdQwmcrnkayWpGmNZezJCVb56JJTYL0wOus7L+zUIRWG0YIBzZBzG0iAX/6fQ0l7EVc6JyYmksGi6czSWhhAYvaJ2zupxU5pMou+k48ILP1MSZ+2n/mmM2OA/msXzum7hhSbuHKHkazorFlYLem2efeU0M61xWGRgzHAJ6pOxWGJx2NepgFDGLSTpDiqRTXD98AabHa28X8TpBbFynDONrU3mueuW/TSrXOlzp2LOO/cDbYGpZXAc/jypbH8SWfkSQlQrWaKBg2FJ8DYg/hUnxfSy+QbzyeGkPfJAcrDZ+9lhluKJ2RplT9bAedAWmvzPu2cJCQuf/RmM='</spring.rabbitmq.password>
            <spring.security.user.name>'{cipher}AQAGy3LYSBkcOJA32VA/mT3jNtxscWGe0Eto3isApxPrUgv00Pw0629LROM3zCh9KC3/HqP4aoUexyBw3QhdLCkXr6zpXGFrj0x7EPcu+A9B9kbzZejfcdG3l+Wln+chjS50cLe0txPDt4ioZxbLb0BjgwfPS4THrIfozhJgyRKNwkrw4prTXSnE2o/tbo/rHKJutXF53ywkAqRK7sZNFUMiHZTKiQkKzzCTUQmFRoBYkYtoc2SjVMerEfySCyx8oEm0+FnLpUuFpexO/cVl/YbWxmeU4qvrpeDgj1OEJ9JAuKZGfSHF/1LdvaOP7HVlx1EiyqLoQys/pa1vFjkRu8YHSmSS7jOfiGty2sT3g4+fFG7G0K+8g5cmRGAUbhUppDI='</spring.security.user.name>
            <spring.security.user.password>'{cipher}AQAf9K0s8zLVaIk44+wqpHGuHsHEK5G4KKu1vk0HwxlojaYQHRkNsFhdQwmcrnkayWpGmNZezJCVb56JJTYL0wOus7L+zUIRWG0YIBzZBzG0iAX/6fQ0l7EVc6JyYmksGi6czSWhhAYvaJ2zupxU5pMou+k48ILP1MSZ+2n/mmM2OA/msXzum7hhSbuHKHkazorFlYLem2efeU0M61xWGRgzHAJ6pOxWGJx2NepgFDGLSTpDiqRTXD98AabHa28X8TpBbFynDONrU3mueuW/TSrXOlzp2LOO/cDbYGpZXAc/jypbH8SWfkSQlQrWaKBg2FJ8DYg/hUnxfSy+QbzyeGkPfJAcrDZ+9lhluKJ2RplT9bAedAWmvzPu2cJCQuf/RmM='</spring.security.user.password>-->
            <spring.security.user.name>anan</spring.security.user.name>
            <spring.security.user.password>local</spring.security.user.password>
            <encrypt.key-store.location>classpath:/cdp.jks</encrypt.key-store.location>
            <encrypt.key-store.password>123456</encrypt.key-store.password>
            <encrypt.key-store.alias>cdp</encrypt.key-store.alias>
            <encrypt.key-store.secret>123456</encrypt.key-store.secret>
       1.8.4、启动服务 
            1.8.4.1、启动anan-eurekaserver服务注册与发现中心
            1.8.4.2、启动anan-configserver配置中心
            1.8.4.3、启动anan-authserver授权认证中心
            1.8.4.4、启动anan-platformserver平台服务中心、anan-zuulgateway服务路由网关
            1.8.4.5、启动anan-adminserver服务监控
## 2、部署生产环境
### 2.1、Docker Swarm集群环境部署
    移步看 deploy/swarm/readme-swarm.md
### 2.2、Kubernetes部署
	移步看 deploy/k8s/readme-k8s.md
### 2.3、jar包部署
    移步看 deploy/jar/readme-jar.md
### 2.4、war包部署

### 3、实用小技巧
#### 3.1、停止删除当前容器和镜像
##### 一条命令实现停用并删除容器
    docker stop $(docker ps -q) && docker rm $(docker ps -aq)

##### 删除所有异常退出的容器
    docker rm $(docker ps -a| grep Exited | awk '{print $1}')

#### 3.2、删除指定的镜像
    docker rmi $(docker images | grep registry.cn-hangzhou.aliyuncs.com/fosin/anan | awk '{print $3}')

    docker rmi $(docker images | grep none | awk '{print $3}')
