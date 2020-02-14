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
INSERT INTO `config_info` VALUES (4,'anan-authserver.yaml','DEFAULT_GROUP','server:\r\n  port: 51400\r\nspring:\r\n  jpa:\r\n    show-sql: true\r\n  datasource:\r\n    url: jdbc:mysql://mysql-master:3306/anan_platform?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: anan\r\n    password: local\r\n#    type: com.alibaba.druid.pool.DruidDataSource\r\n    druid:\r\n      initial-size: 5\r\n      min-idle: 5\r\n      maxActive: 10\r\n      maxWait: 60000\r\n      timeBetweenEvictionRunsMillis: 60000\r\n      minEvictableIdleTimeMillis: 300000\r\n      #      validationQuery: SELECT 1 FROM DUAL\r\n      testWhileIdle: true\r\n      testOnBorrow: false\r\n      testOnReturn: false\r\n  #      poolPreparedStatements: true\r\n  #      maxPoolPreparedStatementPerConnectionSize: 20\r\n  #      filters: stat,wall,log4j\r\n  #      connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000\r\n  #      useGlobalDataSourceStat: true \r\n  #    platform: mysql\r\n  #    continue-on-error: true\r\n  redis:\r\n    #database: 10\r\n#    cluster:\r\n#      max-redirects:  \r\n#      nodes: redis:6379\r\n    host: redis\r\n    port: 6379\r\n    password: local\r\n  session:\r\n    store-type: redis\r\n  thymeleaf:\r\n    cache: false\r\nanan:\r\n  swagger:\r\n    enabled: true\r\n    title: ${spring.application.name}\r\n    description: ${info.description}\r\n    version: ${info.version}\r\n    base-package: com.github.fosin.anan.auth\r\n    authorization:\r\n      name: Oauth2.0 Authorization\r\n      keyName: Authorization\r\n#      authRegex: ^[^/oauth/token]$\r\n    contact:\r\n      name: fosin\r\n      email: 28860823@qq.com\r\n  oauth2:\r\n    enabled: true\r\n    disablecsrf: true\r\n    disableHttpBasic: true\r\n    blackWhiteList:\r\n    - path: /**/v2/api-docs\r\n    - path: /login\r\n    - path: /logout','fd2c2b316162ad80d2bc3d8f401da311','2019-11-10 17:23:05','2019-11-22 17:45:05',NULL,'192.168.137.1','','6138f451-2d5b-42fe-a793-df3744d7257c','null','null','null','yaml','null'),(7,'application.yaml','DEFAULT_GROUP','spring:\r\n  zipkin:\r\n#    baseUrl: http://zipkin:9411/\r\n    sender:\r\n      type: rabbit\r\n#    locator:\r\n#      discovery:\r\n#        enabled: true\r\n#    discovery-client-enabled: true\r\n  sleuth:\r\n#    enabled: false\r\n    sampler:\r\n      probability: 1.0\r\n#    feign:\r\n#      enabled: false\r\n#      processor:\r\n#        enabled: false\r\nsecurity:\r\n  oauth2:\r\n    resource:\r\n      loadBalanced: true\r\n      user-info-uri: http://anan-authserver/oauth/principal\r\n      prefer-token-info: false\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'\r\n  endpoint:\r\n    health:\r\n      show-details: ALWAYS\r\n  metrics:\r\n    web:\r\n      server:\r\n        auto-time-requests: false\r\nlogging:\r\n#  file: logs/${spring.application.name}.log\r\n#  file.max-size: 50mb\r\n#  file.max-history: 365\r\n  level:\r\n    com.alibaba.nacos.naming.log.level: warn\r\n    com.alibaba.nacos.client.naming: warn\r\n#    root: info\r\n#    zipkin2: debug\r\n#server:\r\n#  undertow:\r\n#    io-threads: 16\r\n#    worker-threads: 256\r\n#    buffer-size: 1024\r\n#    direct-buffers: true\r\neureka:\r\n  client:\r\n    healthcheck:\r\n      enabled: true # 开启健康检查（需要spring-boot-starter-actuator依赖）\r\n  instance:\r\n    lease-expiration-duration-in-seconds: 10 # 续约到期时间（默认90秒）\r\n    lease-renewal-interval-in-seconds: 5 # 续约更新时间间隔（默认30秒）','626830ef58e9b827b03feeda3492d01f','2019-11-10 17:39:32','2019-11-22 21:28:31',NULL,'192.168.137.1','','6138f451-2d5b-42fe-a793-df3744d7257c','服务公用设置','null','null','yaml','null'),(13,'anan-adminserver.yaml','DEFAULT_GROUP','server:\r\n  port: 51700\r\nturbine:\r\n  cluster-name-expression: new String(\'default\')\r\n  app-config: anan-zuulgateway\r\nanan:\r\n  oauth2:\r\n    disablecsrf: true\r\n    disableHttpBasic: true\r\n    cors:\r\n      allowedOrigins: \'*\'\r\n      allowedMethods: \'*\'\r\n      allowedHeaders: \'*\'\r\n      allowCredentials: true\r\n    blackWhiteList:\r\n      - path: /**/*.html\r\n      - path: /**/*.css\r\n      - path: /**/*.js\r\n      - path: /**/img/**\r\n      - path: /third-party/**\r\n      - path: /**/api/**\r\n      - path: /**/login/**\r\n      - path: /**/logout/**\r\n      - path: /**/applications/**\r\n      - path: /**/instances/**\r\n      - method: OPTIONS\r\nsecurity:\r\n  oauth2:\r\n    client:\r\n      client-id: appServer\r\n      client-secret: appServer\r\n      access-token-uri: http://anan-authserver:51400/oauth/token\r\n      grant-type: client_credentials','47be296696afd9a5ce345ab81f30fff2','2019-11-10 18:17:25','2019-11-22 17:45:18',NULL,'192.168.137.1','','6138f451-2d5b-42fe-a793-df3744d7257c','null','null','null','yaml','null'),(14,'anan-cloudgateway.yaml','DEFAULT_GROUP','server:\r\n  port: 9000\r\nspring:\r\n  cloud:\r\n    gateway:\r\n      enabled: true #If you include the starter, but, for some reason, you do not want the gateway to be enabled\r\n    routes:\r\n      - id: anan-platformserver\r\n        uri: lb://anan-platformserver\r\n        predicates:\r\n          - path=/platform/{segment}\r\n        filters:\r\n          - SetPath=/{segment}\r\n          # - StripPrefix=1\r\n          #   # 限流\r\n          # - name: RequestRateLimiter\r\n          #   args:\r\n          #     # 限流策略\r\n          #     key-resolver: \'#{@remoteAddrKeyResolver}\'\r\n          #     # 令牌桶每秒填充率\r\n          #     redis-rate-limiter.replenishRate: 1\r\n          #     # 令牌桶容量\r\n          #     redis-rate-limiter.burstCapacity: 2\r\n          #   # 熔断\r\n          # - name: Hystrix\r\n          #   args:\r\n          #     name: appService1EchoCmd\r\n\r\n      - id: anan-authserver\r\n        uri: lb://anan-authserver\r\n        predicates:\r\n          - path=/auth/**\r\n        filters:\r\n          - StripPrefix=1\r\n      - id: anan-mpi\r\n        uri: lb://anan-mpi\r\n        predicates:\r\n          - path=/mpi/**\r\n        filters:\r\n          - RewritePath=/mpi/(?<segment>.*), /$\\{segment}\r\n      - id: anan-vhr\r\n        uri: lb://anan-vhr\r\n        predicates:\r\n          - path=/vhr/**\r\n        filters:\r\n          - StripPrefix=1\r\nribbon:\r\n  OkToRetryOnAllOperations: false #对所有操作请求都进行重试,默认false\r\n  MaxAutoRetries: 0     #对当前实例的重试次数，默认0\r\n  MaxAutoRetriesNextServer: 1 #对切换实例的重试次数，默认1\r\n  ReadTimeout: 15000   #负载均衡超时时间，默认值5000，单位ms\r\n  ConnectTimeout: 15000 #ribbon请求连接的超时时间，默认值2000，单位ms\r\n  ServerListRefreshInterval: 15000 # 从注册中心刷新servelist的时间 默认30秒，单位ms\r\nhystrix:\r\n  threadpool:\r\n    default:\r\n      coreSize: 100\r\n      maximumSize: 2000\r\n      allowMaximumSizeToDivergeFromCoreSize: true #允许maximumSize起作用\r\n      maxQueueSize: -1 #如该值为-1，那么使用的是SynchronousQueue，否则使用的是LinkedBlockingQueue\r\n  command:\r\n    default:\r\n      execution:\r\n        isolation:\r\n          thread:\r\n            timeoutInMilliseconds: 60000 #断路器的超时时间；如果ribbon配置了重试那么该值必需大于ribbonTimeout，重试才能生效\r\n      timeout:\r\n        enabled: false\r\n      metrics:\r\n        rollingStats:\r\n          timeInMilliseconds: 10000 #判断健康度的滚动时间窗长度（10000）\r\n      circuitBreaker:\r\n        requestVolumeThreshold: 20 #当在配置时间窗口内达到此数量的失败后，进行短路。默认20个\r\n        sleepWindowInMilliseconds: 5 #短路多久以后开始尝试是否恢复，默认5s\r\n        errorThresholdPercentage: 50% #出错百分比阈值，当达到此阈值后，开始短路。默认50%\r\n      threadPool:\r\n        coreSize: 10  #命令线程池执行最大并发量（10）\r\n##禁用自定义过滤器ThrowExceptionFilter\r\n#zuul.ThrowExceptionFilter.pre.disable=true\r\n#security:\r\n#  sessions: stateless\r\n#  oauth2:\r\n#    client:\r\n#      client-id: anan\r\n#      client-secret: local\r\n##      access-token-uri: http://localhost:9000/auth/oauth/token\r\n##      user-authorization-uri: http://localhost:9000/auth/oauth/authorize\r\n#      access-token-uri: http://localhost:51400/oauth/token\r\n#      user-authorization-uri: http://localhost:51400/oauth/authorize\r\n##      auto-approve-scopes:\r\n##      pre-established-redirect-uri: http://${security.user.name}:${security.user.password}@${eureka.instance.hostname}:${server.port}/\r\n##      token-name:\r\n##      refresh-token-validity-seconds:\r\n##      access-token-validity-seconds:\r\n##      scope:\r\n#      authorized-grant-types: authorization_code\r\n##      use-current-uri: false\r\n##      registered-redirect-uri: http://localhost:9000/platform/login\r\n##      client-authentication-scheme: form\r\nanan:\r\n  swagger:\r\n    enabled: true\r\n    title: ${spring.application.name}\r\n    description: ${info.description}\r\n    version: ${info.version}\r\n    base-package: com.github.fosin.anan.cloudgateway\r\n    authorization:\r\n      name: Oauth2.0 Authorization\r\n      keyName: Authorization\r\n    #      authRegex: ^[^/oauth/token]$\r\n    contact:\r\n      name: fosin\r\n      email: 28860823@qq.com\r\n    ignoreResourceNames:\r\n      - anan-zuulgateway\r\n      - anan-cloudgateway\r\n  oauth2:\r\n    disablecsrf: true\r\n    disableHttpBasic: true\r\n    cors:\r\n      allowedOrigins: \'*\'\r\n      allowedMethods: \'*\'\r\n      allowedHeaders: \'*\'\r\n      allowCredentials: true\r\n    blackWhiteList:\r\n    - path: /**/oauth/**\r\n    - path: /**/*.js\r\n    - path: /**/*.html\r\n    - path: /**/*.css\r\n    - path: /hystrix\r\n    - path: /hystrix.stream\r\n    - path: /hystrix/**\r\n    - path: /**/webjars/**\r\n#    - path: /**/springfox-swagger-ui/**\r\n    - path: /**/swagger-resources/**\r\n    - path: /**/v2/api-docs\r\n    - path: /**/third-party/**\r\n    - path: /**/api/**\r\n    - path: /**/images/**\r\n','1c66a44111b7c35812489c7b2f112e83','2019-11-10 18:17:51','2019-11-22 17:45:29',NULL,'192.168.137.1','','6138f451-2d5b-42fe-a793-df3744d7257c','null','null','null','yaml','null'),(15,'anan-mpi.yaml','DEFAULT_GROUP','server:\r\n  port: 53000\r\nspring:\r\n  jpa:\r\n    show-sql: true\r\n#    hibernate:\r\n#      ddl-auto: update\r\n  datasource:\r\n    url: jdbc:mysql://192.168.137.8:3307/mpi?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: anan\r\n    password: local\r\n    druid:\r\n      initial-size: 5 #初始化大小\r\n      min-idle: 5 #最小\r\n      maxActive: 10 # 最大\r\n      maxWait: 60000 #配置获取连接等待超时的时间\r\n      timeBetweenEvictionRunsMillis: 60000 # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\r\n      minEvictableIdleTimeMillis: 300000 # 配置一个连接在池中最小生存的时间，单位是毫秒\r\n      #      validationQuery: SELECT 1 FROM DUAL\r\n      testWhileIdle: true\r\n      testOnBorrow: false\r\n      testOnReturn: false\r\n  #      poolPreparedStatements: true # 打开PSCache，并且指定每个连接上PSCache的大小\r\n  #      maxPoolPreparedStatementPerConnectionSize: 20\r\n  #      filters: stat,wall,log4j # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，\'wall\'用于防火墙\r\n  #      connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000 # 通过connectProperties属性来打开mergeSql功能；慢SQL记录\r\n  #      useGlobalDataSourceStat: true # 合并多个DruidDataSource的监控数据\r\n#    platform: mysql\r\n#    continue-on-error: true\r\n  redis:\r\n    #database: 10\r\n    #    cluster:\r\n    #      max-redirects:  # （普通集群，不使用则不用开启）在群集中执行命令时要遵循的最大重定向数目。\r\n    #      nodes: redis:6379 # （普通集群，不使用则不用开启）以逗号分隔的“主机：端口”对列表进行引导。\r\n    host: redis\r\n    port: 6379\r\n    password: local\r\n  session:\r\n    store-type: redis\r\nanan:\r\n  swagger:\r\n    enabled: true\r\n    title: ${spring.application.name}\r\n    description: ${info.description}\r\n    version: ${info.version}\r\n    base-package: com.github.fosin.mpi\r\n    authorization:\r\n      name: Oauth2.0 Authorization\r\n      keyName: Authorization\r\n    contact:\r\n      name: fosin\r\n      email: 28860823@qq.com\r\n  oauth2:\r\n    disablecsrf: true\r\n    disableHttpBasic: true\r\n    blackWhiteList:\r\n    - path: /**/v2/api-docs\r\nsecurity:\r\n  oauth2:\r\n    client:\r\n      client-id: appServer\r\n      client-secret: appServer\r\n      access-token-uri: http://anan-authserver:51400/oauth/token\r\n      grant-type: client_credentials\r\n','fff7f006580a8152e328173afacc7f51','2019-11-10 18:18:19','2019-11-23 15:08:51',NULL,'192.168.137.1','','6138f451-2d5b-42fe-a793-df3744d7257c','null','null','null','yaml','null'),(16,'anan-vhr.yaml','DEFAULT_GROUP','server:\r\n  port: 53001\r\nspring:\r\n  mail:\r\n    host: smtp.qq.com\r\n    port: 465\r\n    username: 1186340749@qq.com\r\n    password: ghkmjtncgemsbaae\r\n    default-encoding: UTF-8\r\n    properties:\r\n      mail:\r\n        debug: false\r\n        smtp:\r\n          user: ${spring.mail.username}\r\n          host: ${spring.mail.host}\r\n          auth: true\r\n          port: ${spring.mail.port}\r\n          starttls:\r\n            enable: true\r\n          socketFactory:\r\n            class: javax.net.ssl.SSLSocketFactory\r\n            fallback: false\r\n            port: ${spring.mail.port}\r\n  jpa:\r\n    show-sql: true\r\n#    hibernate:\r\n#      ddl-auto: update\r\n  datasource:\r\n    url: jdbc:mysql://192.168.137.8:3308/vhr?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: anan\r\n    password: local\r\n    druid:\r\n      initial-size: 5 #初始化大小\r\n      min-idle: 5 #最小\r\n      maxActive: 10 # 最大\r\n      maxWait: 60000 #配置获取连接等待超时的时间\r\n      timeBetweenEvictionRunsMillis: 60000 # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\r\n      minEvictableIdleTimeMillis: 300000 # 配置一个连接在池中最小生存的时间，单位是毫秒\r\n      #      validationQuery: SELECT 1 FROM DUAL\r\n      testWhileIdle: true\r\n      testOnBorrow: false\r\n      testOnReturn: false\r\n  #      poolPreparedStatements: true # 打开PSCache，并且指定每个连接上PSCache的大小\r\n  #      maxPoolPreparedStatementPerConnectionSize: 20\r\n  #      filters: stat,wall,log4j # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，\'wall\'用于防火墙\r\n  #      connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000 # 通过connectProperties属性来打开mergeSql功能；慢SQL记录\r\n  #      useGlobalDataSourceStat: true # 合并多个DruidDataSource的监控数据\r\n#    platform: mysql\r\n#    continue-on-error: true\r\n  redis:\r\n    #database: 10\r\n    #    cluster:\r\n    #      max-redirects:  # （普通集群，不使用则不用开启）在群集中执行命令时要遵循的最大重定向数目。\r\n    #      nodes: redis:6379 # （普通集群，不使用则不用开启）以逗号分隔的“主机：端口”对列表进行引导。\r\n    host: redis\r\n    port: 6379\r\n    password: local\r\n  session:\r\n    store-type: redis\r\n#MyBatis\r\nmybatis:\r\n  config-location: classpath:/mybatis-config.xml\r\nanan:\r\n  swagger:\r\n    enabled: true\r\n    title: ${spring.application.name}\r\n    description: ${info.description}\r\n    version: ${info.version}\r\n    base-package: com.github.fosin.vhr\r\n    authorization:\r\n      name: Oauth2.0 Authorization\r\n      keyName: Authorization\r\n    contact:\r\n      name: fosin\r\n      email: 28860823@qq.com\r\n  oauth2:\r\n    disablecsrf: true\r\n    disableHttpBasic: true\r\n    blackWhiteList:\r\n    - path: /**/v2/api-docs\r\nsecurity:\r\n  oauth2:\r\n    client:\r\n      client-id: appServer\r\n      client-secret: appServer\r\n      access-token-uri: http://anan-authserver:51400/oauth/token\r\n      grant-type: client_credentials\r\n','fe25c854cf8aea2dae399c89bd31a98e','2019-11-10 18:18:43','2019-11-23 15:25:25',NULL,'192.168.137.1','','6138f451-2d5b-42fe-a793-df3744d7257c','null','null','null','yaml','null'),(18,'anan-zuulgateway.yaml','DEFAULT_GROUP','server:\r\n  port: 9000\r\nzuul:\r\n  add-host-header: true\r\n  add-proxy-headers: true\r\n  sensitiveHeaders:   #Cookie,Set-Cookie,Authorization   blacklist，如果不过滤，则须显式设为空。\r\n  retryable: true #默认启用重试\r\n  ignored-services: \'*\'\r\n#  strip-prefix: true #是否移除代理前缀\r\n  prefix: /gateway #为所有http请求前增加/zuul前缀\r\n  routes:\r\n    anan-platformserver:\r\n      path: /platform/**\r\n      serviceId: anan-platformserver\r\n      retryable: true\r\n    anan-authserver:\r\n      path: /auth/**\r\n      serviceId: anan-authserver\r\n      retryable: true\r\n    anan-zuulgateway:\r\n      path: /**\r\n      serviceId: anan-zuulgateway\r\n      retryable: true\r\n    anan-mpi:\r\n      path: /mpi/**\r\n      serviceId: anan-mpi\r\n      retryable: true\r\n    anan-vhr:\r\n      path: /vhr/**\r\n      serviceId: anan-vhr\r\n      retryable: true\r\n#  semaphore:\r\n#    max-semaphores: 100\r\n#  ribbon-isolation-strategy: THREAD\r\n#  thread-pool:\r\n#    use-separate-thread-pools: true\r\n#    thread-pool-key-prefix: zuulgw\r\n  ratelimit:\r\n    key-prefix: zuulgateway #对应用来标识请求的key的前缀\r\n    enabled: true\r\n#    repository: IN_MEMORY #对应存储类型（用来存储统计信息）,可选值REDIS、IN_MEMORY、JPA、CONSUL,默认IN_MEMORY\r\n    behind-proxy: true #代理之后\r\n    default-policy: #可选 - 针对所有的路由配置的策略，除非特别配置了policies\r\n      limit: 60 #可选 - 每个刷新时间窗口对应的请求数量限制\r\n      quota: 60 #可选-  每个刷新时间窗口对应的请求时间限制（秒）\r\n      refresh-interval: 3 # 刷新时间窗口的时间，默认值 (秒)\r\n      type: #可选 限流方式\r\n#        - user #用户粒度\r\n#        - origin #ORIGIN粒度 (用户请求的origin作为粒度控制)\r\n        - url #接口粒度 (请求接口的地址作为粒度控制)\r\n    policies:\r\n      anan-platformserver: #特定的路由\r\n        limit: 10 #可选- 每个刷新时间窗口对应的请求数量限制\r\n        quota: 10 #可选-  每个刷新时间窗口对应的请求时间限制（秒）\r\n        refresh-interval: 1 # 刷新时间窗口的时间，默认值 (秒)\r\n        type: #可选 限流方式\r\n#          - user\r\n#          - origin\r\n          - url\r\n      anan-mpi: #特定的路由\r\n        limit: 15 #可选- 每个刷新时间窗口对应的请求数量限制\r\n        quota: 15 #可选-  每个刷新时间窗口对应的请求时间限制（秒）\r\n        refresh-interval: 1 # 刷新时间窗口的时间，默认值 (秒)\r\n        type: #可选 限流方式\r\n        - url\r\n  host:\r\n    connect-timeout-millis: 60000\r\n    socket-timeout-millis: 60000\r\n#spring:\r\n#  redis:\r\n#    host: 192.168.137.155\r\n#    port: 6379\r\n#    password: local\r\n#    timeout: 10000\r\n#    pool:\r\n#      max-active: -1\r\n#      max-wait: -1\r\n#      max-idle: 8\r\n#      min-idle: 0\r\n#\r\nribbon:\r\n  OkToRetryOnAllOperations: false #对所有操作请求都进行重试,默认false\r\n  MaxAutoRetries: 0     #对当前实例的重试次数，默认0\r\n  MaxAutoRetriesNextServer: 1 #对切换实例的重试次数，默认1\r\n  ReadTimeout: 15000   #负载均衡超时时间，默认值1000，单位ms\r\n  ConnectTimeout: 15000 #ribbon请求连接的超时时间，默认值1000，单位ms\r\n  ServerListRefreshInterval: 15000 # 从注册中心刷新servelist的时间 默认30秒，单位ms\r\nhystrix:\r\n  threadpool:\r\n    default:\r\n      coreSize: 100\r\n      maximumSize: 2000\r\n      allowMaximumSizeToDivergeFromCoreSize: true #允许maximumSize起作用\r\n      maxQueueSize: -1 #如该值为-1，那么使用的是SynchronousQueue，否则使用的是LinkedBlockingQueue\r\n  command:\r\n    default:\r\n      execution:\r\n        isolation:\r\n          thread:\r\n            timeoutInMilliseconds: 60000 #断路器的超时时间；如果ribbon配置了重试那么该值必需大于ribbonTimeout = (ribbonReadTimeout + ribbonConnectTimeout) * (maxAutoRetries + 1) * (maxAutoRetriesNextServer + 1)，重试才能生效\r\n      timeout:\r\n        enabled: false\r\n      circuitBreaker:\r\n        requestVolumeThreshold: 20 #当在配置时间窗口内达到此数量的失败后，进行短路。默认20个\r\n        sleepWindowInMilliseconds: 5 #短路多久以后开始尝试是否恢复，默认5s\r\n        errorThresholdPercentage: 50% #出错百分比阈值，当达到此阈值后，开始短路。默认50%\r\n\r\n##禁用自定义过滤器ThrowExceptionFilter\r\n#zuul.ThrowExceptionFilter.pre.disable=true\r\n#security:\r\n#  sessions: stateless\r\n#  oauth2:\r\n#    client:\r\n#      client-id: anan\r\n#      client-secret: local\r\n##      access-token-uri: http://localhost:9000/auth/oauth/token\r\n##      user-authorization-uri: http://localhost:9000/auth/oauth/authorize\r\n#      access-token-uri: http://localhost:51400/oauth/token\r\n#      user-authorization-uri: http://localhost:51400/oauth/authorize\r\n##      auto-approve-scopes:\r\n##      pre-established-redirect-uri: http://${security.user.name}:${security.user.password}@${eureka.instance.hostname}:${server.port}/\r\n##      token-name:\r\n##      refresh-token-validity-seconds:\r\n##      access-token-validity-seconds:\r\n##      scope:\r\n#      authorized-grant-types: authorization_code\r\n##      use-current-uri: false\r\n##      registered-redirect-uri: http://localhost:9000/platform/login\r\n##      client-authentication-scheme: form\r\nanan:\r\n  swagger:\r\n    enabled: true\r\n    title: ${spring.application.name}\r\n    description: ${info.description}\r\n    version: ${info.version}\r\n    base-package: com.github.fosin.anan.zuulgateway\r\n    authorization:\r\n      name: Oauth2.0 Authorization\r\n      keyName: Authorization\r\n    #      authRegex: ^[^/oauth/token]$\r\n    contact:\r\n      name: fosin\r\n      email: 28860823@qq.com\r\n    ignoreResourceNames:\r\n      - anan-zuulgateway\r\n  oauth2:\r\n    disablecsrf: true\r\n    disableHttpBasic: true\r\n    cors:\r\n      allowedOrigins: \'*\'\r\n      allowedMethods: \'*\'\r\n      allowedHeaders: \'*\'\r\n      allowCredentials: true\r\n    blackWhiteList:\r\n    - path: /**/oauth/**\r\n    - path: /**/*.js\r\n    - path: /**/*.html\r\n    - path: /**/*.css\r\n    - path: /hystrix\r\n    - path: /hystrix.stream\r\n    - path: /hystrix/**\r\n    - path: /**/webjars/**\r\n#    - path: /**/springfox-swagger-ui/**\r\n    - path: /**/swagger-resources/**\r\n    - path: /**/v2/api-docs\r\n    - path: /**/third-party/**\r\n    - path: /**/api/**\r\n    - path: /**/images/**\r\n','8609a414a382dfa4234cb75c76ab71e2','2019-11-10 20:04:04','2020-02-14 13:53:12',NULL,'192.168.137.1','','6138f451-2d5b-42fe-a793-df3744d7257c','null','null','null','yaml','null'),(20,'anan-platformserver.yaml','DEFAULT_GROUP','server:\r\n  port: 51500\r\nspring:\r\n  jpa:\r\n    show-sql: true\r\n  datasource:\r\n    url: jdbc:mysql://mysql-master:3306/anan_platform?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: anan\r\n    password: local\r\n    druid:\r\n      initial-size: 5 #初始化大小\r\n      min-idle: 5 #最小\r\n      maxActive: 10 # 最大\r\n      maxWait: 60000 #配置获取连接等待超时的时间\r\n      timeBetweenEvictionRunsMillis: 60000 # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\r\n      minEvictableIdleTimeMillis: 300000 # 配置一个连接在池中最小生存的时间，单位是毫秒\r\n      #      validationQuery: SELECT 1 FROM DUAL\r\n      testWhileIdle: true\r\n      testOnBorrow: false\r\n      testOnReturn: false\r\n  #      poolPreparedStatements: true # 打开PSCache，并且指定每个连接上PSCache的大小\r\n  #      maxPoolPreparedStatementPerConnectionSize: 20\r\n  #      filters: stat,wall,log4j # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，\'wall\'用于防火墙\r\n  #      connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000 # 通过connectProperties属性来打开mergeSql功能；慢SQL记录\r\n  #      useGlobalDataSourceStat: true # 合并多个DruidDataSource的监控数据\r\n  #    platform: mysql\r\n  #    continue-on-error: true\r\n  redis:\r\n    #database: 10\r\n    #    cluster:\r\n    #      max-redirects:  # （普通集群，不使用则不用开启）在群集中执行命令时要遵循的最大重定向数目。\r\n    #      nodes: redis:6379 # （普通集群，不使用则不用开启）以逗号分隔的“主机：端口”对列表进行引导。\r\n    host: redis\r\n    port: 6379\r\n    password: local\r\n  session:\r\n    store-type: redis\r\n\r\nfeign:\r\n#  compression: #开启这个设置比较耗CPU\r\n#    request:\r\n#      enabled: true #开启Feign请求压缩\r\n#      mime-types: text/xml,application/xml,application/json # 配置压缩文档类型\r\n#    response:\r\n#      enabled: true #开启Feign响应压缩\r\n#      min-request-size: 2048 # 配置最小压缩的文档大小\r\n  okhttp:\r\n    enabled: true\r\n  httpclient:\r\n    enabled: false\r\n    max-connections: 1000 #最大连接数\r\n    max-connections-per-route: 100 #每个url的连接数\r\nsecurity:\r\n  oauth2:\r\n    client:\r\n      client-id: appServer\r\n      client-secret: appServer\r\n      access-token-uri: http://anan-authserver:51400/oauth/token\r\n      grant-type: client_credentials\r\nanan:\r\n  swagger:\r\n    enabled: true\r\n    title: ${spring.application.name}\r\n    description: ${info.description}\r\n    version: ${info.version}\r\n    base-package: com.github.fosin.anan.platform\r\n    authorization:\r\n      name: Oauth2.0 Authorization\r\n      keyName: Authorization\r\n    contact:\r\n      name: fosin\r\n      email: 28860823@qq.com\r\n#  global-operation-parameters:\r\n#  - name: Authorization\r\n#    description: Oauth2.0令牌信息,格式例如：Bearer 58cb49cd-be59-4706-bbc5-9c41fc3cbef4\r\n#    modelRef: string\r\n#    parameterType: header\r\n#    required: true\r\n  oauth2:\r\n    disablecsrf: true\r\n    disableHttpBasic: true\r\n    blackWhiteList:\r\n    - path: /**/v2/api-docs\r\n','290fe9e55a3f35a264a55c0dd94b6725','2019-11-10 20:16:42','2019-11-22 17:46:24',NULL,'192.168.137.1','','6138f451-2d5b-42fe-a793-df3744d7257c','null','null','null','yaml','null');
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
