#设计定位
cdp基于spring boot和spring cloud生态体系技术，采用微服务架构，为个人及企业微服务架构提供一种解决方案，供开发人员学习和交流。
其中包括服务注册与发现、服务监控、服务管理、服务治理、服务网关、服务熔断、配置管理等常见微服务组件。

#技术选型
技术 | 简介 
---- | ------
Spring Boot | 基础构建框架，用于快速整合各资源 
Spring Framework | 底层容器 
SpringMVC | 分层框架 
Spring Cloud | 微服务框架 
Spring Cloud Config | 配置管理中心 
Spring Cloud Eureka | 服务注册中心 
Spring Cloud Zuul | 服务网关 
Spring Cloud Hystrix | 服务容错框架 
Spring Cloud Feign | 微服务声明式调用框架 
Spring Cloud Sleuth Zipkin| 分布式链路追踪
Spring Boot Admin | 服务管理中心 
Spring Data Jpa | 持久化框架 
Spring Data Redis | 缓存框架 
Spring Security| 安全框架 
Spring Validator | 后端验证框架 
Hibernate Validator | Hibernate验证框架 
lombok | 一个通过注解自动生成get/set方法的类库 
Thymeleaf | HTML5模板引擎  
Maven | 项目构建管理  
Redis | 分布式缓存数据库 
Mysql | 对象关系数据库 
RabbitMQ | 消息中间件
MongoDB | 文档数据库
Slf4j Log4j2 | 日志框架
OAuth2.0 | 单点登录、鉴权、认证框架
Vuejs、Nodejs、Webpack | 前段开发框架
#架构设计

#原理介绍

#搭建环境
##1、本地开发环境local设置
###1.1、安装mysql、Redis、RabbitMQ
       1.1.1、安装Mysql
            建议安装5.7及以上版本，设置root密码为local
            根据源码相对路径./docs/mysql/cdp_platform.sql创建数据库cdp_platform，并导入相关sql语句和基础数据
       1.1.2、安装Redis(3.x、4.x、5.x都支持)
            密码设置为local
       1.1.3、安装Rabbitmq(只测试过3.x)
            用户：cdp
            密码：local
       1.1.4、如果mysql、redis、rabbitmq等密码不是local，则需要修改cdp-cloud下面的pom.xml中的profile local 的配置信息 ，还有cdp-config目录下的配置文件    
###1.2、配置环境
       1.2.1、安装jdk1.8及以上、lombok插件、ignore插件（非必须），开发工具推荐使用Idea
       1.2.2、Windows下修改c:/windows/system32/drives/etc/hosts文件增加以下信息，IP地址根据实际情况设定
           127.0.0.1 cdp-eurekaserver
           127.0.0.1 cdp-authserver
           127.0.0.1 cdp-redis
           127.0.0.1 cdp-platform-mysql
           127.0.0.1 cdp-rabbitmq
####1.3、按顺序启动项目
       1.3.1、启动cdp-eurekaserver服务注册中心
       1.3.2、启动cdp-configserver配置中心
       1.3.3、启动cdp-authserver授权认证中心、cdp-platformserver平台服务中心、cdp-zuulgateway服务路由网关
       1.3.4、启动cdp-adminmonitor服务监控、cdp-sleuthserver分布式链路追踪
####1.4、打开前端项目cdp-vue,移步cdp-vue下面的README.md查看前端项目的开发环境搭建过程

##2、部署生产环境
###2.1、Docker部署
###2.2、传统部署

1.2.3、如果需要密码加密，则需要生成Spring Config配置中心非对称加密证书（非必须,如果不做这步，则使用源码中自带的证书cdp.jks）
           1.2.3.1、生成加密证书
              keytool -genkeypair -alias cdp -keyalg RSA -dname "CN=cdp, OU=starlight, O=startlight, L=gy, ST=gz, C=cn" -keypass 123456 -keystore /cdp.jks -storepass 123456 -validity 365
           1.2.3.2、将/cdp.jks分别放到各个项目的src/main/resources目录下
           1.2.3.3、生产环境中将cdp-configserver配置中心替换实际地址并建议修改当前连接用户(cdp)和密码(local)为较安全复杂的用户和密码,
               然后启动cdp-configserver项目后，通过以下命令生成密码：
                curl -u cdp:local http://localhost:51100/encrypt -d cdp
           1.2.3.4、替换各yml配置文件中的spring.security.user.password中的密码参数，密码前缀必须有{cipher}并以单引号包含，例如:
                '{cipher}AQApsg6Qzq9bdXcH2BntfbquV9CD2arg9bP9HFGuvww5EppMU1fsUqzFPtjXH5Gblkj7tE5N4/p1zIp5KpTZwDAM8wxLNrK8m9626Rb1eAlEG4Cfs8aJqoYi8LItfTo/QA1u8zoJKdcFZ4xe77CQBDhUiJ36m+Q8s2ItFMZHsM1dC2NsiuCB9D8f74a2DFeoLSyvkSeSE9jQr2tv8COy0NtpLChmgFL4dM4ffTwiPx7cMsdoabL/C2CD9YqQLfk6TChrNq9xjvfXUhiRcekzXd2ccHqnZ9trEtKzaRfmEOWUNsmnlwMjY/Lz8I9wnWo8ZHB+hxoP2uyqw4twx2NnILERVLKFO1ZqhVsrMxOBEjX8ccAqeYbnEDYTXqYl4b3o='