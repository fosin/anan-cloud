-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: localhost    Database: nacos
-- ------------------------------------------------------
-- Server version	5.7.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `nacos`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `nacos` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `nacos`;

--
-- Table structure for table `config_info`
--

DROP TABLE IF EXISTS `config_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `c_use` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `effect` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `c_schema` text COLLATE utf8_bin,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info`
--

LOCK TABLES `config_info` WRITE;
/*!40000 ALTER TABLE `config_info` DISABLE KEYS */;
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`)
VALUES (4, 'anan-authserver.yaml', 'DEFAULT_GROUP', 'server:
  port: 51400
spring:
  datasource:
    url: jdbc:mysql://mysql-master:3306/anan_platform?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: anan
    password: local
#    type: com.alibaba.druid.pool.DruidDataSource
    druid:
      initial-size: 5
      min-idle: 5
      maxActive: 10
      maxWait: 60000
      timeBetweenEvictionRunsMillis: 60000
      minEvictableIdleTimeMillis: 300000
      #      validationQuery: SELECT 1 FROM DUAL
      testWhileIdle: true
      testOnBorrow: false
      testOnReturn: false
  #      poolPreparedStatements: true
  #      maxPoolPreparedStatementPerConnectionSize: 20
  #      filters: stat,wall,log4j
  #      connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
  #      useGlobalDataSourceStat: true
  #    platform: mysql
  #    continue-on-error: true
  redis:
    #database: 10
#    cluster:
#      max-redirects:
#      nodes: redis:6379
    host: redis
    port: 6379
    password: local
  session:
    store-type: redis
  thymeleaf:
    cache: false
  # mvc:
  #   static-path-pattern: /auth/**
anan:
  swagger:
    enabled: true
    title: ${spring.application.name}
    description: ${info.description}
    version: ${info.version}
    base-package: com.github.fosin.anan.auth
    authorization:
      name: Oauth2.0 Authorization
      keyName: Authorization
#      authRegex: ^[^/oauth/token]$
    contact:
      name: fosin
      email: 28860823@qq.com
  oauth2:
    resource:
      server:
        enabled: true
        disablecsrf: true
        disableHttpBasic: true
        blackWhiteList:
          - path: /**/v2/api-docs
          - path: /oauth/authorize
          - path: /oauth/login
          - path: /oauth/logout
          - path: /actuator/health
          - path: /actuator/shutdown
    redis:
      idempotent:
        enabled: false
      cache:
        manager: false
      session:
        manager: true', 'eca38d64f1ee85c0eb52633d48e80dbc', '2019-11-10 17:23:05', '2020-03-15 18:35:10', null, '192.168.137.1', '', '6138f451-2d5b-42fe-a793-df3744d7257c', 'null', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (7, 'application.yaml', 'DEFAULT_GROUP', 'spring:
  jpa:
    properties:
      hibernate:
        format_sql: true
        use_sql_comments: true
    #show-sql: true
#    hibernate:
#      ddl-auto: update
  zipkin:
#    baseUrl: http://zipkin:9411/
    sender:
      type: rabbit
#    locator:
#      discovery:
#        enabled: true
#    discovery-client-enabled: true
  sleuth:
#    enabled: false
    sampler:
      probability: 1.0
#    feign:
#      enabled: false
#      processor:
#        enabled: false
anan:
  oauth2:
    client:
      client-id: appServer
      client-secret: appServer
      access-token-uri: http://anan-authserver:51400/oauth/token
security:
  oauth2:
    resource:
      loadBalanced: true
      user-info-uri: http://anan-authserver/oauth/principal
      prefer-token-info: false
management:
  endpoints:
    web:
      exposure:
        include: ''*''
  endpoint:
    health:
      show-details: ALWAYS
  metrics:
    web:
      server:
        auto-time-requests: false
logging:
#  file: logs/${spring.application.name}.log
#  file.max-size: 50mb
#  file.max-history: 365
  level:
    com.alibaba.nacos.naming.log.level: warn
    com.alibaba.nacos.client.naming: warn
    #打印SQL语句
    org.hibernate.SQL: info
    #打印SQL语句参数
    org.hibernate.type.descriptor.sql.BasicBinder: info
    #打印SQL执行结果
    org.hibernate.type.descriptor.sql.BasicExtractor: info
    #打印查询中命名参数的值
    org.hibernate.engine.spi.QueryParameters: info
    org.hibernate.engine.query.spi.HQLQueryPlan: info
#    zipkin2: debug
#server:
#  undertow:
#    io-threads: 16
#    worker-threads: 256
#    buffer-size: 1024
#    direct-buffers: true
eureka:
  client:
    healthcheck:
      enabled: true # 开启健康检查（需要spring-boot-starter-actuator依赖）
  instance:
    lease-expiration-duration-in-seconds: 10 # 续约到期时间（默认90秒）
    lease-renewal-interval-in-seconds: 5 # 续约更新时间间隔（默认30秒）', '626830ef58e9b827b03feeda3492d01f', '2019-11-10 17:39:32', '2019-11-22 21:28:31', null, '192.168.137.1', '', '6138f451-2d5b-42fe-a793-df3744d7257c', '服务公用设置', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (13, 'anan-adminserver.yaml', 'DEFAULT_GROUP', 'server:
  port: 51700
turbine:
  cluster-name-expression: new String(''default'')
  app-config: anan-zuulgateway
anan:
  oauth2:
    resource:
      server:
        disablecsrf: true
        disableHttpBasic: true
        cors:
          allowedOrigins: ''*''
          allowedMethods: ''*''
          allowedHeaders: ''*''
          allowCredentials: true
        blackWhiteList:
          - path: /**/*.html
          - path: /**/*.css
          - path: /**/*.js
          - path: /**/img/**
          - path: /third-party/**
          - path: /**/api/**
          - path: /**/login/**
          - path: /**/logout/**
          - path: /**/applications/**
          - path: /**/instances/**
          - method: OPTIONS
          - path: /actuator/health
          - path: /actuator/shutdown
security:
  oauth2:
    client:
      client-id: ${anan.oauth2.client.client-id}
      client-secret: ${anan.oauth2.client.client-secret}
      access-token-uri: ${anan.oauth2.client.access-token-uri}
      grant-type: client_credentials
spring:
  boot:
    admin:
      ui:
#        favicon-danger: "assets/img/favicon-danger.png"
#        favicon: "assets/img/favicon.png"
        title: "服务指标监控"
#        public-url:
#      instance-proxy:
#        ignored-headers: "Cookie", "Set-Cookie", "Authorization"
#      probed-endpoints: "health", "env", "metrics", "httptrace:trace", "threaddump:dump", "jolokia", "info", "logfile", "refresh", "flyway", "liquibase", "heapdump", "loggers", "auditevents"
      notify:
        mail:
          additional-properties:
          from: "Spring Boot Admin <noreply@localhost>"
          cc:
          to: "28860823@qq.com"
          template: "classpath:/META-INF/spring-boot-admin-server/mail/status-changed.html"
          ignore-changes: "UNKNOWN:UP"
          enabled: true', 'e21f85d17b34ccfdbc9bf544af884e61', '2019-11-10 18:17:25', '2020-02-26 22:12:50', null, '192.168.137.1', '', '6138f451-2d5b-42fe-a793-df3744d7257c', 'null', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (14, 'anan-cloudgateway.yaml', 'DEFAULT_GROUP', 'server:
  port: 9000
spring:
  cloud:
    gateway:
      enabled: true #If you include the starter, but, for some reason, you do not want the gateway to be enabled
    routes:
      - id: anan-platformserver
        uri: lb://anan-platformserver
        predicates:
          - path=/platform/{segment}
        filters:
          - SetPath=/{segment}
          # - StripPrefix=1
          #   # 限流
          # - name: RequestRateLimiter
          #   args:
          #     # 限流策略
          #     key-resolver: ''#{@remoteAddrKeyResolver}''
          #     # 令牌桶每秒填充率
          #     redis-rate-limiter.replenishRate: 1
          #     # 令牌桶容量
          #     redis-rate-limiter.burstCapacity: 2
          #   # 熔断
          # - name: Hystrix
          #   args:
          #     name: appService1EchoCmd

      - id: anan-authserver
        uri: lb://anan-authserver
        predicates:
          - path=/auth/**
        filters:
          - StripPrefix=1
      - id: anan-mpi
        uri: lb://anan-mpi
        predicates:
          - path=/mpi/**
        filters:
          - RewritePath=/mpi/(?<segment>.*), /$\\{segment}
      - id: anan-vhr
        uri: lb://anan-vhr
        predicates:
          - path=/vhr/**
        filters:
          - StripPrefix=1
ribbon:
  OkToRetryOnAllOperations: false #对所有操作请求都进行重试,默认false
  MaxAutoRetries: 0     #对当前实例的重试次数，默认0
  MaxAutoRetriesNextServer: 1 #对切换实例的重试次数，默认1
  ReadTimeout: 5000   #负载均衡超时时间，默认值5000，单位ms
  ConnectTimeout: 5000 #ribbon请求连接的超时时间，默认值2000，单位ms
  ServerListRefreshInterval: 15000 # 从注册中心刷新servelist的时间 默认30秒，单位ms
hystrix:
  threadpool:
    default:
      coreSize: 100
      maximumSize: 2000
      allowMaximumSizeToDivergeFromCoreSize: true #允许maximumSize起作用
      maxQueueSize: -1 #如该值为-1，那么使用的是SynchronousQueue，否则使用的是LinkedBlockingQueue
  command:
    default:
      execution:
        isolation:
          thread:
            timeoutInMilliseconds: 20001 #断路器的超时时间；如果ribbon配置了重试那么该值必需大于ribbonTimeout，重试才能生效
      timeout:
        enabled: false
      metrics:
        rollingStats:
          timeInMilliseconds: 10000 #判断健康度的滚动时间窗长度（10000）
      circuitBreaker:
        requestVolumeThreshold: 20 #当在配置时间窗口内达到此数量的失败后，进行短路。默认20个
        sleepWindowInMilliseconds: 5 #短路多久以后开始尝试是否恢复，默认5s
        errorThresholdPercentage: 50% #出错百分比阈值，当达到此阈值后，开始短路。默认50%
      threadPool:
        coreSize: 10  #命令线程池执行最大并发量（10）
##禁用自定义过滤器ThrowExceptionFilter
#zuul.ThrowExceptionFilter.pre.disable=true
#security:
#  sessions: stateless
#  oauth2:
#    client:
#      client-id: anan
#      client-secret: local
##      access-token-uri: http://localhost:9000/auth/oauth/token
##      user-authorization-uri: http://localhost:9000/auth/oauth/authorize
#      access-token-uri: http://localhost:51400/oauth/token
#      user-authorization-uri: http://localhost:51400/oauth/authorize
##      auto-approve-scopes:
##      pre-established-redirect-uri: http://${security.user.name}:${security.user.password}@${eureka.instance.hostname}:${server.port}/
##      token-name:
##      refresh-token-validity-seconds:
##      access-token-validity-seconds:
##      scope:
#      authorized-grant-types: authorization_code
##      use-current-uri: false
##      registered-redirect-uri: http://localhost:9000/platform/login
##      client-authentication-scheme: form
anan:
  swagger:
    enabled: true
    title: ${spring.application.name}
    description: ${info.description}
    version: ${info.version}
    base-package: com.github.fosin.anan.cloudgateway
    authorization:
      name: Oauth2.0 Authorization
      keyName: Authorization
    #      authRegex: ^[^/oauth/token]$
    contact:
      name: fosin
      email: 28860823@qq.com
    ignoreResourceNames:
      - anan-zuulgateway
      - anan-cloudgateway
  redis:
    idempotent:
      enabled: false
    cache:
      manager: false
    session:
      manager: true
  oauth2:
    resource:
      server:
        disablecsrf: true
        disableHttpBasic: true
        cors:
          allowedOrigins: ''*''
          allowedMethods: ''*''
          allowedHeaders: ''*''
          allowCredentials: true
        blackWhiteList:
          - path: /**/auth/oauth/**
          - path: /**/auth/sso/**
          - path: /**/*.js
          - path: /**/*.html
          - path: /**/*.css
          - path: /**/*.gif
          - path: /**/*.png
          - path: /**/*.jpg
          - path: /**/*.jpeg
          - path: /**/*.svg
          - path: /**/*.bmp
          - path: /**/*.ico
          - path: /**/*.swf
          - path: /**/*.woff
          - path: /**/*.woff2
          - path: /**/*.ttf
          - path: /**/*.map
          - path: /hystrix
          - path: /hystrix.stream
          - path: /hystrix/**
          - path: /**/webjars/**
    #    - path: /**/springfox-swagger-ui/**
          - path: /**/swagger-resources/**
          - path: /**/v2/api-docs
          - path: /**/third-party/**
          - path: /**/api/**
          - path: /actuator/health
          - path: /actuator/shutdown
          - path: /**/images/**', '55b6239e5aed6a522b16d48872a0eedb', '2019-11-10 18:17:51', '2020-03-15 18:36:42', null, '192.168.137.1', '', '6138f451-2d5b-42fe-a793-df3744d7257c', 'null', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (15, 'anan-mpi.yaml', 'DEFAULT_GROUP', 'server:
  port: 53000
spring:
  datasource:
    url: jdbc:mysql://mysql-master:3306/mpi?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: anan
    password: local
    druid:
      initial-size: 5 #初始化大小
      min-idle: 5 #最小
      maxActive: 10 # 最大
      maxWait: 60000 #配置获取连接等待超时的时间
      timeBetweenEvictionRunsMillis: 60000 # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
      minEvictableIdleTimeMillis: 300000 # 配置一个连接在池中最小生存的时间，单位是毫秒
      #      validationQuery: SELECT 1 FROM DUAL
      testWhileIdle: true
      testOnBorrow: false
      testOnReturn: false
  #      poolPreparedStatements: true # 打开PSCache，并且指定每个连接上PSCache的大小
  #      maxPoolPreparedStatementPerConnectionSize: 20
  #      filters: stat,wall,log4j # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，''wall''用于防火墙
  #      connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000 # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
  #      useGlobalDataSourceStat: true # 合并多个DruidDataSource的监控数据
#    platform: mysql
#    continue-on-error: true
  redis:
    #database: 10
    #    cluster:
    #      max-redirects:  # （普通集群，不使用则不用开启）在群集中执行命令时要遵循的最大重定向数目。
    #      nodes: redis:6379 # （普通集群，不使用则不用开启）以逗号分隔的“主机：端口”对列表进行引导。
    host: redis
    port: 6379
    password: local
  session:
    store-type: redis
anan:
  swagger:
    enabled: true
    title: ${spring.application.name}
    description: ${info.description}
    version: ${info.version}
    base-package: com.github.fosin.mpi
    authorization:
      name: Oauth2.0 Authorization
      keyName: Authorization
    contact:
      name: fosin
      email: 28860823@qq.com
  oauth2:
    resource:
      server:
        disablecsrf: true
        disableHttpBasic: true
        blackWhiteList:
          - path: /**/v2/api-docs
          - path: /actuator/health
          - path: /actuator/shutdown
security:
  oauth2:
    client:
      client-id: ${anan.oauth2.client.client-id}
      client-secret: ${anan.oauth2.client.client-secret}
      access-token-uri: ${anan.oauth2.client.access-token-uri}
      grant-type: client_credentials
', 'd8eb297dfc893ce8f9ab939bbe264c36', '2019-11-10 18:18:19', '2020-02-18 15:41:38', null, '192.168.137.1', '', '6138f451-2d5b-42fe-a793-df3744d7257c', 'null', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (16, 'anan-vhr.yaml', 'DEFAULT_GROUP', 'server:
  port: 53001
spring:
  mail:
    host: smtp.qq.com
    port: 25
    username: 1186340749@qq.com
    password: ghkmjtncgemsbaae
    default-encoding: UTF-8
    properties:
      mail:
        debug: false
        smtp:
          user: ${spring.mail.username}
          host: ${spring.mail.host}
          auth: true
          port: ${spring.mail.port}
          starttls:
            enable: true
          socketFactory:
            class: javax.net.ssl.SSLSocketFactory
            fallback: false
            port: ${spring.mail.port}
  datasource:
    url: jdbc:mysql://mysql-master:3306/vhr?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: anan
    password: local
    druid:
      initial-size: 5 #初始化大小
      min-idle: 5 #最小
      maxActive: 10 # 最大
      maxWait: 60000 #配置获取连接等待超时的时间
      timeBetweenEvictionRunsMillis: 60000 # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
      minEvictableIdleTimeMillis: 300000 # 配置一个连接在池中最小生存的时间，单位是毫秒
      #      validationQuery: SELECT 1 FROM DUAL
      testWhileIdle: true
      testOnBorrow: false
      testOnReturn: false
  #      poolPreparedStatements: true # 打开PSCache，并且指定每个连接上PSCache的大小
  #      maxPoolPreparedStatementPerConnectionSize: 20
  #      filters: stat,wall,log4j # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，''wall''用于防火墙
  #      connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000 # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
  #      useGlobalDataSourceStat: true # 合并多个DruidDataSource的监控数据
#    platform: mysql
#    continue-on-error: true
  redis:
    #database: 10
    #    cluster:
    #      max-redirects:  # （普通集群，不使用则不用开启）在群集中执行命令时要遵循的最大重定向数目。
    #      nodes: redis:6379 # （普通集群，不使用则不用开启）以逗号分隔的“主机：端口”对列表进行引导。
    host: redis
    port: 6379
    password: local
  session:
    store-type: redis
#MyBatis
mybatis:
  config-location: classpath:/mybatis-config.xml
anan:
  swagger:
    enabled: true
    title: ${spring.application.name}
    description: ${info.description}
    version: ${info.version}
    base-package: com.github.fosin.vhr
    authorization:
      name: Oauth2.0 Authorization
      keyName: Authorization
    contact:
      name: fosin
      email: 28860823@qq.com
  oauth2:
    resource:
      server:
        disablecsrf: true
        disableHttpBasic: true
        blackWhiteList:
          - path: /**/v2/api-docs
          - path: /actuator/health
          - path: /actuator/shutdown
security:
  oauth2:
    client:
      client-id: ${anan.oauth2.client.client-id}
      client-secret: ${anan.oauth2.client.client-secret}
      access-token-uri: ${anan.oauth2.client.access-token-uri}
      grant-type: client_credentials
', '53b35f158b1d4642813e78335940a2d9', '2019-11-10 18:18:43', '2020-03-13 15:33:29', null, '192.168.137.1', '', '6138f451-2d5b-42fe-a793-df3744d7257c', 'null', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (18, 'anan-zuulgateway.yaml', 'DEFAULT_GROUP', 'server:
  port: 9000
zuul:
  add-host-header: true
  add-proxy-headers: true
  sensitiveHeaders:   #Cookie,Set-Cookie,Authorization   blacklist，如果不过滤，则须显式设为空。
  retryable: true #默认启用重试
  ignored-services: ''*''
#  strip-prefix: true #是否移除代理前缀
  prefix: /gateway #为所有http请求前增加/zuul前缀
  routes:
    anan-platformserver:
      path: /platform/**
      serviceId: anan-platformserver
      retryable: true
    anan-authserver:
      path: /auth/**
      serviceId: anan-authserver
      retryable: true
    anan-zuulgateway:
      path: /**
      serviceId: anan-zuulgateway
      retryable: true
    anan-mpi:
      path: /mpi/**
      serviceId: anan-mpi
      retryable: true
    anan-vhr:
      path: /vhr/**
      serviceId: anan-vhr
      retryable: true
#  semaphore:
#    max-semaphores: 100
#  ribbon-isolation-strategy: THREAD
#  thread-pool:
#    use-separate-thread-pools: true
#    thread-pool-key-prefix: zuulgw
  ratelimit:
    key-prefix: zuulgateway #对应用来标识请求的key的前缀
    enabled: true
    repository: REDIS #对应存储类型（用来存储统计信息）,可选值REDIS、IN_MEMORY、JPA、CONSUL,默认IN_MEMORY
    behind-proxy: true #代理之后
    add-response-headers: true
    default-policy-list: #可选 - 针对所有的路由配置的策略，除非特别配置了policies
      - limit: 1200 #可选 - 每个刷新时间窗口对应的请求数量限制
        quota: 1000 #可选-  每个刷新时间窗口对应的请求时间限制（秒）
        refresh-interval: 60 # 刷新时间窗口的时间，默认值 60(秒)
        type: #可选 限流方式
  #        - user #用户粒度
  #        - origin #ORIGIN粒度 (用户请求的origin作为粒度控制)
          - url #接口粒度 (请求接口的地址作为粒度控制)
    policy-list:
      anan-mpi:
        - limit: 900
          quota: 1000
          refresh-interval: 60
          type:
          - url
  host:
    connect-timeout-millis: 10000
    socket-timeout-millis: 10000
spring:
 redis:
   host: 192.168.137.8
   port: 6379
   password: local
   timeout: 10000
   lettuce:
     max-active: 3
     max-wait: -1
     max-idle: 1
     min-idle: 1
ribbon:
  OkToRetryOnAllOperations: false #对所有操作请求都进行重试,默认false
  MaxAutoRetries: 0     #对当前实例的重试次数，默认0
  MaxAutoRetriesNextServer: 1 #对切换实例的重试次数，默认1
  ReadTimeout: 5000   #负载均衡超时时间，默认值1000，单位ms
  ConnectTimeout: 5000 #ribbon请求连接的超时时间，默认值1000，单位ms
  ServerListRefreshInterval: 15000 # 从注册中心刷新servelist的时间 默认30秒，单位ms
hystrix:
  threadpool:
    default:
      coreSize: 100
      maximumSize: 2000
      allowMaximumSizeToDivergeFromCoreSize: true #允许maximumSize起作用
      maxQueueSize: -1 #如该值为-1，那么使用的是SynchronousQueue，否则使用的是LinkedBlockingQueue
  command:
    default:
      execution:
        isolation:
          thread:
            timeoutInMilliseconds: 20001 #断路器的超时时间；如果ribbon配置了重试那么该值必需大于ribbonTimeout = (ribbonReadTimeout + ribbonConnectTimeout) * (maxAutoRetries + 1) * (maxAutoRetriesNextServer + 1)，重试才能生效
      timeout:
        enabled: false
      circuitBreaker:
        requestVolumeThreshold: 20 #当在配置时间窗口内达到此数量的失败后，进行短路。默认20个
        sleepWindowInMilliseconds: 5 #短路多久以后开始尝试是否恢复，默认5s
        errorThresholdPercentage: 50% #出错百分比阈值，当达到此阈值后，开始短路。默认50%

##禁用自定义过滤器ThrowExceptionFilter
#zuul.ThrowExceptionFilter.pre.disable=true
#security:
#  sessions: stateless
#  oauth2:
#    client:
#      client-id: anan
#      client-secret: local
##      access-token-uri: http://localhost:9000/auth/oauth/token
##      user-authorization-uri: http://localhost:9000/auth/oauth/authorize
#      access-token-uri: http://localhost:51400/oauth/token
#      user-authorization-uri: http://localhost:51400/oauth/authorize
##      auto-approve-scopes:
##      pre-established-redirect-uri: http://${security.user.name}:${security.user.password}@${eureka.instance.hostname}:${server.port}/
##      token-name:
##      refresh-token-validity-seconds:
##      access-token-validity-seconds:
##      scope:
#      authorized-grant-types: authorization_code
##      use-current-uri: false
##      registered-redirect-uri: http://localhost:9000/platform/login
##      client-authentication-scheme: form
anan:
  swagger:
    enabled: true
    title: ${spring.application.name}
    description: ${info.description}
    version: ${info.version}
    base-package: com.github.fosin.anan.zuulgateway
    authorization:
      name: Oauth2.0 Authorization
      keyName: Authorization
    #      authRegex: ^[^/oauth/token]$
    contact:
      name: fosin
      email: 28860823@qq.com
    ignoreResourceNames:
      - anan-zuulgateway
  redis:
    idempotent:
      enabled: false
    cache:
      manager: false
    session:
      manager: true
  oauth2:
    resource:
      server:
        disablecsrf: true
        disableHttpBasic: true
        cors:
          allowedOrigins: ''*''
          allowedMethods: ''*''
          allowedHeaders: ''*''
          allowCredentials: true
        blackWhiteList:
          - path: /**/auth/oauth/**
          - path: /**/auth/sso/**
          - path: /**/*.js
          - path: /**/*.html
          - path: /**/*.css
          - path: /**/*.gif
          - path: /**/*.png
          - path: /**/*.jpg
          - path: /**/*.jpeg
          - path: /**/*.svg
          - path: /**/*.bmp
          - path: /**/*.ico
          - path: /**/*.swf
          - path: /**/*.woff
          - path: /**/*.woff2
          - path: /**/*.ttf
          - path: /**/*.map
          - path: /hystrix
          - path: /hystrix.stream
          - path: /hystrix/**
          - path: /**/webjars/**
    #    - path: /**/springfox-swagger-ui/**
          - path: /**/swagger-resources/**
          - path: /**/v2/api-docs
          - path: /**/third-party/**
          - path: /**/api/**
          - path: /**/images/**
          - path: /actuator/health
          - path: /actuator/shutdown', 'b7353d61807d909009a90b70e0a7114e', '2019-11-10 20:04:04', '2020-03-15 18:36:23', null, '192.168.137.1', '', '6138f451-2d5b-42fe-a793-df3744d7257c', 'null', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (20, 'anan-platformserver.yaml', 'DEFAULT_GROUP', 'server:
  port: 51500
spring:
  datasource:
    url: jdbc:mysql://mysql-master:3306/anan_platform?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: anan
    password: local
    druid:
      initial-size: 5 #初始化大小
      min-idle: 5 #最小
      maxActive: 10 # 最大
      maxWait: 60000 #配置获取连接等待超时的时间
      timeBetweenEvictionRunsMillis: 60000 # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
      minEvictableIdleTimeMillis: 300000 # 配置一个连接在池中最小生存的时间，单位是毫秒
      #      validationQuery: SELECT 1 FROM DUAL
      testWhileIdle: true
      testOnBorrow: false
      testOnReturn: false
  #      poolPreparedStatements: true # 打开PSCache，并且指定每个连接上PSCache的大小
  #      maxPoolPreparedStatementPerConnectionSize: 20
  #      filters: stat,wall,log4j # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，''wall''用于防火墙
  #      connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000 # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
  #      useGlobalDataSourceStat: true # 合并多个DruidDataSource的监控数据
  #    platform: mysql
  #    continue-on-error: true
  redis:
    #database: 10
    #    cluster:
    #      max-redirects:  # （普通集群，不使用则不用开启）在群集中执行命令时要遵循的最大重定向数目。
    #      nodes: redis:6379 # （普通集群，不使用则不用开启）以逗号分隔的“主机：端口”对列表进行引导。
    host: redis
    port: 6379
    password: local
  session:
    store-type: redis

feign:
#  compression: #开启这个设置比较耗CPU
#    request:
#      enabled: true #开启Feign请求压缩
#      mime-types: text/xml,application/xml,application/json # 配置压缩文档类型
#    response:
#      enabled: true #开启Feign响应压缩
#      min-request-size: 2048 # 配置最小压缩的文档大小
  okhttp:
    enabled: true
  httpclient:
    enabled: false
    max-connections: 1000 #最大连接数
    max-connections-per-route: 100 #每个url的连接数
security:
  oauth2:
    client:
      client-id: ${anan.oauth2.client.client-id}
      client-secret: ${anan.oauth2.client.client-secret}
      access-token-uri: ${anan.oauth2.client.access-token-uri}
      grant-type: client_credentials
anan:
  swagger:
    enabled: true
    title: ${spring.application.name}
    description: ${info.description}
    version: ${info.version}
    base-package: com.github.fosin.anan.platform
    authorization:
      name: Oauth2.0 Authorization
      keyName: Authorization
    contact:
      name: fosin
      email: 28860823@qq.com
#  global-operation-parameters:
#  - name: Authorization
#    description: Oauth2.0令牌信息,格式例如：Bearer 58cb49cd-be59-4706-bbc5-9c41fc3cbef4
#    modelRef: string
#    parameterType: header
#    required: true
  oauth2:
    resource:
      server:
        disablecsrf: true
        disableHttpBasic: true
        blackWhiteList:
          - path: /actuator/health
          - path: /actuator/shutdown
          - path: /**/v2/api-docs', 'c859d1296638e9cd9795a0bb74220ee2', '2019-11-10 20:16:42', '2020-03-15 18:35:53', null, '192.168.137.1', '', '6138f451-2d5b-42fe-a793-df3744d7257c', 'null', 'null', 'null', 'yaml', 'null');
/*!40000 ALTER TABLE `config_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_aggr`
--

DROP TABLE IF EXISTS `config_info_aggr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_info_aggr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='增加租户字段';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_aggr`
--

LOCK TABLES `config_info_aggr` WRITE;
/*!40000 ALTER TABLE `config_info_aggr` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_aggr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_beta`
--

DROP TABLE IF EXISTS `config_info_beta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_info_beta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_beta';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_beta`
--

LOCK TABLES `config_info_beta` WRITE;
/*!40000 ALTER TABLE `config_info_beta` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_beta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_tag`
--

DROP TABLE IF EXISTS `config_info_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_info_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_tag';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_tag`
--

LOCK TABLES `config_info_tag` WRITE;
/*!40000 ALTER TABLE `config_info_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_tags_relation`
--

DROP TABLE IF EXISTS `config_tags_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_tags_relation` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_tag_relation';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_tags_relation`
--

LOCK TABLES `config_tags_relation` WRITE;
/*!40000 ALTER TABLE `config_tags_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_tags_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_capacity`
--

DROP TABLE IF EXISTS `group_capacity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='集群、各Group容量信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_capacity`
--

LOCK TABLES `group_capacity` WRITE;
/*!40000 ALTER TABLE `group_capacity` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_capacity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `his_config_info`
--

DROP TABLE IF EXISTS `his_config_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `his_config_info` (
  `id` bigint(64) unsigned NOT NULL,
  `nid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00',
  `src_user` text COLLATE utf8_bin,
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `op_type` char(10) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='多租户改造';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `his_config_info`
--

LOCK TABLES `his_config_info` WRITE;
/*!40000 ALTER TABLE `his_config_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `his_config_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('nacos','ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_capacity`
--

DROP TABLE IF EXISTS `tenant_capacity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tenant_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户容量信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_capacity`
--

LOCK TABLES `tenant_capacity` WRITE;
/*!40000 ALTER TABLE `tenant_capacity` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_capacity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_info`
--

DROP TABLE IF EXISTS `tenant_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tenant_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tenant_info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_info`
--

LOCK TABLES `tenant_info` WRITE;
/*!40000 ALTER TABLE `tenant_info` DISABLE KEYS */;
INSERT INTO `tenant_info` VALUES (1,'1','6138f451-2d5b-42fe-a793-df3744d7257c','local','本地开发环境','nacos',1573371901768,1573371901768);
/*!40000 ALTER TABLE `tenant_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('nacos','$2a$10$NAkPmjD2jhWZr68cZUnByuj2jCKRVkafFWGrYcg6ODVZBQ2F/Wzzq',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-14 14:10:48
