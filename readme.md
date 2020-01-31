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
### 1.1、安装docker
     安装docker没有什么特殊要求，自行百度安装即可
### 1.2、中间件安装篇，使用docker-compose文件docker-compose.yml(mysql、Redis、RabbitMQ、ElasticSearch、zipkin)
       1.2.1、安装Mysql
            建议安装5.7及以上版本，设置root密码为local
            根据源码相对路径./docs/mysql/anan_platform.sql创建数据库anan_platform，并导入相关sql语句和基础数据
       1.2.2、安装Redis(3.x、4.x、5.x都支持)
            密码设置为local
       1.2.3、安装Rabbitmq(只测试过3.x)
            用户：anan
            密码：local
       1.2.4、安装ElasticSearch6.7及以上(6.7及以上kibana有中文版，不要中文版安装低版本也可以)
            启动时报错：max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144] 
            原因：最大虚拟内存太小 
            解决方案：切换到root用户下，修改配置文件sysctl.conf
             
            sudo vim /etc/sysctl.conf
             
            添加下面配置： 
            vm.max_map_count=262144
             
            并执行命令： 
            sysctl -p
       
       1.2.5、如果mysql、redis、rabbitmq等密码不是local，则需要修改以下地方：
            anan-cloud下面的pom.xml中的profile local 的配置信息
            anan-config目录下的配置文件
            docker-compose-base.yml中的相关内容
       1.2.6、使用docker-compose开启开发必须中间件
            创建docker网络
            docker network create -d bridge --subnet=172.28.0.0/16 anan-bridge
            
            docker-compose.yml中redis、rabbitmq、nacos-server1、nacos-mysql-master、nacos-mysql-slave六个是开发环境必须启动的
            需要导入deploy/mysql/anan_platform.sql到nacos-mysql-master中创建anan_platform2数据库
### 1.3、监控安装篇，使用文件docker-compose.yml(prometheus、node-exporter、cadvisor、alertmanager、grafana等)
       1.3.1、安装cadvisor版本:v0.33.0及以上
            发现容器没有正常启动，查看日志，有如下报错内容：    
            Failed to start container manager: inotify_add_watch 
            /sys/fs/cgroup/cpuacct,cpu: no such file or directory
            临时解决方法，但是doker主机重启后又要修改，执行：
            mount -o remount,rw '/sys/fs/cgroup'
            ln -s /sys/fs/cgroup/cpu,cpuacct /sys/fs/cgroup/cpuacct,cpu
### 1.4、服务安装篇，使用文件docker-compose.yml(anan-eurekaserver、anan-configserver、anan-authserver等)
            本地开发环境基本上不需要启动这个docker-compose文件，主要还是使用源码跑
### 1.5、配置环境
       1.5.1、安装jdk1.8及以上、lombok插件、ignore插件，开发工具推荐使用Idea
       1.5.2、Windows下修改c:/windows/system32/drives/etc/hosts文件增加以下信息，IP地址根据实际情况设定
              192.168.137.1是本机IP，192.168.137.8是虚拟机中的主机IP
            192.168.137.1 nacos-server1
            192.168.137.1 anan-eurekaserver
            192.168.137.1 anan-authserver
            192.168.137.8 redis
            192.168.137.8 anan-platform-mysql
            192.168.137.8 rabbitmq
            192.168.137.8 nacos-mysql-master
 
       1.5.3、 配置 log4j.skipJansi使log4j2的日志支持颜色字体
            IDEA中，点击右上角->Edit Configurations，在VM options中添加
            -Dlog4j.skipJansi=false
            
            MYECLIPSE中，Run Configurations->Arguments-> VM arguments 中添加
            -Dlog4j.skipJansi=false
            
            MYECLIPSE 需要ansi 插件的支持

### 1.6、按顺序启动服务
       1.6.1、启动anan-authserver授权认证中心
       1.6.2、启动anan-platformserver平台服务中心、anan-zuulgateway服务路由网关
       1.6.4、启动anan-adminserver服务监控
### 1.7、打开前端项目anan-vue,移步 https://github.com/fosin/anan-vue 下面的README.md查看前端项目的开发环境搭建过程
### 1.8、如果使用Spring Cloud Eureka作为服务注册和发现组件、Config作为配置中心
       1.8.1、注释Nacos组件
            注释anan-cloud的pom.xml中的nacos
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
                <version>${anan-spring-cloud-alibaba.version}</version>
            </dependency>
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
                <version>${anan-spring-cloud-alibaba.version}</version>
            </dependency>
       1.8.2、启用Eureka和Config
            启用anan-cloud的pom.xml中的Eureka
            <!-- <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
            </dependency>-->
            启用anan-cloudadviced的pom.xml中的Config
            <!--<dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-config</artifactId>
            </dependency>-->
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
            <spring.cloud.config.server.git.uri>https://github.com/fosin/anan-cloud/docker</spring.cloud.config.server.git.uri>
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
#### 2.1.1、创建集群管理节点(当主机上存在多个ip时，需要指定MANAGER-IP)
    $ docker swarm init --advertise-addr 192.168.137.8
    
    Swarm initialized: current node (jexina8uncvt29aeim6wcg05n) is now a manager.
    
    To add a worker to this swarm, run the following command:

    docker swarm join \
    --token SWMTKN-1-22p3xwtzd75l7e7h95x8lgsvb9efh9r4aix8ari0j32suhtua6-66lffpxnvywldww4rhysrexg9 \
    192.168.137.8:2377

    To add a manager to this swarm, run 'docker swarm join-token manager' and follow the instructions.
    
    再次查看集群加入命令
    $ docker swarm join-token worker
    
    To add a worker to this swarm, run the following command:

    docker swarm join \
    --token SWMTKN-1-03ub9x4wqn78ma6jg3bjahf9siq9mi536bxk6t6zuk3ep7iwsb-0zuut5hf56qmwdpb3umvw53ip \
    192.168.106.2:2377

#### 2.1.2、使用创建管理节点时返回的命令在worker节点上都执行一遍加入swarm集群

#### 2.1.3、创建swarm网络
    docker network create -d overlay --subnet=172.29.0.0/16 anan-overlay

#### 2.1.4、环境部署
    拷贝docker文件夹到Linux服务器上，每个节点都要拷贝
    分配权限
        chmod 755 deploy/ -R
    
#### 2.1.5、使用yml启动swarm集群
    启动基础中间件(mysql、redis、rabbitmq)
    docker stack deploy -c docker-stack-base.yml b
    
    启动服务(应用相关服务和nginx)
    docker stack deploy -c docker-stack-services.yml s
    
    启动elk日志收集分析(elasticsearch、logstash、kibana、filebeat)、分布式链路追踪zipkin
    docker stack deploy -c docker-stack-elk.yml e
    
    启动Prometheus监控(cadvisor、node-exporter、grafana、prometheus)
    docker stack deploy -c docker-stack-prometheus.yml p

#### 2.1.6、停止集群中所有服务并删除容器
    docker stack rm s
    docker stack rm p
    docker stack rm e
    docker stack rm b

### 2.2、Kubernetes部署
	移步看 deploy/k8s/readme-k8s.md
### 2.3、jar包部署

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
