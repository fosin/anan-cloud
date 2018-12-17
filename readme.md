#介绍
##设计定位
cdp基于spring boot和spring cloud生态体系技术，采用微服务架构，为个人及企业微服务架构提供一种解决方案，供开发人员学习和交流。
其中包括服务注册与发现、服务监控、服务管理、服务治理、服务网关、服务熔断、配置管理等常见微服务组件。

## 技术选型
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
jQuery BootStrap | 前段开发框架
#环境部署
## 一、设置mysql关系数据库、Redis内存数据库、RabbitMQ消息中间件的IP、用户、密码
       1、创建mysql数据库cdp，并导入相关sql语句和基础数据
## 二、生产环境将eureka注册中心地址替换成实际地址并建议修改当前连接用户(cdp)和密码(cdp)为较安全复杂的用户和密码，如果是本机为开发环境则可以不需要修改。
## 三、Spring Config配置中心
       1、生成加密证书
            keytool -genkeypair -alias cdp -keyalg RSA -dname "CN=cdp, OU=starlight, O=startlight, L=gy, ST=gz, C=cn" -keypass 123456 -keystore cdp.keystore -storepass 123456 -validity 365
       2、将cdp.keystore分别放到各个项目的src/main/resources目录下
       3、生产环境中将cdp-configserver配置中心替换实际地址并建议修改当前连接用户(cdp)和密码(cdp)为较安全复杂的用户和密码,
           然后启动cdp-configserver项目后，通过以下命令生成密码：
            curl -u cdp:cdp http://localhost:1100/encrypt -d cdp
       4、替换各yml配置文件中的eureka.password中的密码参数，密码前缀必须有{cipher}并以单引号包含，例如:
            '{cipher}AQApsg6Qzq9bdXcH2BntfbquV9CD2arg9bP9HFGuvww5EppMU1fsUqzFPtjXH5Gblkj7tE5N4/p1zIp5KpTZwDAM8wxLNrK8m9626Rb1eAlEG4Cfs8aJqoYi8LItfTo/QA1u8zoJKdcFZ4xe77CQBDhUiJ36m+Q8s2ItFMZHsM1dC2NsiuCB9D8f74a2DFeoLSyvkSeSE9jQr2tv8COy0NtpLChmgFL4dM4ffTwiPx7cMsdoabL/C2CD9YqQLfk6TChrNq9xjvfXUhiRcekzXd2ccHqnZ9trEtKzaRfmEOWUNsmnlwMjY/Lz8I9wnWo8ZHB+hxoP2uyqw4twx2NnILERVLKFO1ZqhVsrMxOBEjX8ccAqeYbnEDYTXqYl4b3o='
## 四、启动顺序
        --spring.config.name=bootstrap-dev --spring.profiles.active=native --spring.cloud.inetutils.preferred-networks=192.168.137.
       1、启动cdp-registery服务注册中心
       2、启动cdp-configserver配置中心
       3、启动cdp-zuulgateway服务路由网关
       4、启动cdp-adminmonitor服务监控
       5、启动cdp-
       