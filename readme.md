# 设计定位
    anan基于Spring Boot 2.3.x和Spring Cloud Hoxton.SR8生态体系技术，采用微服务前后端分离架构，为个人及企业微
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
    1.0.x-SNAPSHOT      1.0.x-SNAPSHOT       1.0.0-SNAPSHOT
    2.0.x-SNAPSHOT      2.0.x-SNAPSHOT       2.0.0-SNAPSHOT
    2.2.x-SNAPSHOT      2.0.x-SNAPSHOT       3.x.x
    2.3.x-SNAPSHOT      2.1.x-SNAPSHOT       3.x.x
# 搭建环境
## 1、本地（profile=local）开发环境设置
### 1.1、安装docker、docker-compose并配置镜像加速
#### 1.1.1、安装docker
详细介绍 [点这里deploy/readme-docker.md](deploy/readme-docker.md) 
#### 1.1.2、安装docker-compose(按需安装注意版本更新和匹配)
详细介绍 [点这里deploy/readme-docker-compose.md](deploy/readme-docker-compose.md) 
### 1.2、开发指南

### 1.3、运行前端项目anan-vue, 
详细介绍移步<https://github.com/fosin/anan-vue>查看前端项目的开发环境搭建过程
## 2、部署生产环境
### 2.1、Kubernetes部署
详细介绍 [点这里deploy/k8s/readme-k8s.md](deploy/k8s/readme-k8s.md) 
### 2.2、jar包部署
详细介绍 [点这里deploy/jar/readme-jar.md](deploy/jar/readme-jar.md) 
### 2.3、Docker Swarm集群环境部署
详细介绍 [点这里deploy/swarm/readme-swarm.md](deploy/swarm/readme-swarm.md) 
### 2.4、war包部署
   
