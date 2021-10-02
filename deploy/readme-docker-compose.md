### 1.1、安装docker-compose(按需安装注意版本更新和匹配)

    sudo curl -L https://github.com/docker/compose/releases/download/1.25.4/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
    sudo chmod +x /usr/local/bin/docker-compose

### 1.2、基础运行环境安装篇，使用docker-compose.yml文件(mysql、Redis、RabbitMQ、nacos)

       所有涉及帐号和密码的地方默认帐号都是anan，默认密码都是local，如果不是账户不是anan得地方，下面会单独说明。
       如果需要修改密码，则需要修改以下地方：
           docker-compose.yml（中间件帐号和密码）
           nacos配置中心（anan服务相关帐号密码）
           anan-cloud下面的pom.xml中的profile local 的配置信息（spring security帐号密码）
       docker-compose.yml中redis、rabbitmq、nacos-0、mysql-leader、mysql-follower5个是后台开发环境必须启动的
       1.2.1、使用docker默认的host网络，需要在docker主机上设置hosts映射     
            cat > /etc/hosts << EOF
            192.168.137.8 redis
            192.168.137.8 rabbitmq
            192.168.137.8 nacos-0
            192.168.137.8 mysql-leader
            192.168.137.8 mysql-follower
            
            192.168.137.8 anan-authserver
            192.168.137.8 anan-platformserver
            192.168.137.8 anan-zuulgateway
            EOF
       1.2.2、启动mysql主从同步模式和nacos服务发现和配置管理
            docker-compose -f anan-cloud\docker-compose.yml up -d mysql-leader nacos-0
            
            启动完成后，可以访问http://容器主机IP:8848/nacos/来查看nacos服务发现和配置中心。
            账户：nacos
            密码：local
       1.2.3、安装Redis(3.x、4.x、5.x都支持)
            docker-compose -f anan-cloud\docker-compose.yml up -d redis
       1.2.4、安装Rabbitmq(只测试过3.x)
            docker-compose -f anan-cloud\docker-compose.yml up -d rabbitmq
       1.2.5、如果对机器性能有信息，以上组件也可以使用一个命令启动
            docker-compose -f D:\myproject\anan\anan-cloud\docker-compose.yml up -d redis rabbitmq mysql-leader nacos-0
       1.2.6、关闭compose
            docker-compose -f D:\myproject\anan\anan-cloud\docker-compose.yml down --remove-orphans

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

### 1.6、配置开发环境

       1.6.1、安装jdk1.8及以上、lombok插件、ignore插件，开发工具推荐使用Idea
       1.6.2、Windows下修改c:/windows/system32/drives/etc/hosts文件增加以下信息，IP地址根据实际情况设定
            192.168.137.1是本机IP，192.168.137.8是Docker容器的主机IP

            192.168.137.8 nacos-0
            192.168.137.8 redis
            192.168.137.8 rabbitmq
            192.168.137.8 mysql-leader
            192.168.137.8 mysql-follower
            192.168.137.1 anan-eurekaserver
            192.168.137.1 anan-authserver
            
       1.6.3、 配置 log4j.skipJansi使log4j2的日志支持颜色字体
            IDEA中，点击右上角->Edit Configurations，在VM options中添加
            -Dlog4j.skipJansi=false
            
            MYECLIPSE中，Run Configurations->Arguments-> VM arguments 中添加
            -Dlog4j.skipJansi=false
            
            MYECLIPSE 需要ansi 插件的支持

### 1.7、按顺序启动服务

       1.7.1、启动anan-authserver授权认证中心
       1.7.2、启动anan-platformserver平台服务中心、anan-zuulgateway服务路由网关
       1.7.3、启动anan-adminserver服务监控

### 1.9、如果使用Spring Cloud Eureka作为服务注册和发现组件、Config作为配置中心()

       1.9.1、修改在以下模块的源码目录下的启动配置文件src/main/resources/bootstrap.yml
            anan-authserver
            anan-platformserver
            anan-zuulgateway
            anan-adminserver
            anan-cloudgateway
       1.9.1、关闭Nacos的作为服务发现和配置中心的设置
            spring.cloud.nacos.enabled=false
            spring.cloud.nacos.discovery.enabled=false
        
       1.9.2、启用Eureka和Config作为服务发现和配置中心的设置
           euerka.client.enabled=true
           spring.cloud.config.discovery.enabled=true
           spring.cloud.config.enabled=true
       1.9.3、设置启动配置
            修改anan-cloud的pom.xml中的相关配置，local环境可以不用修改，一般需要修改生产环境
       1.9.4、启动服务 
            1.9.4.1、启动anan-eurekaserver服务注册与发现中心
            1.9.4.2、启动anan-configserver配置中心
            1.9.4.3、启动anan-authserver授权认证中心
            1.9.4.4、启动anan-platformserver平台服务中心、anan-zuulgateway服务路由网关
            1.9.4.5、启动anan-adminserver服务监控
