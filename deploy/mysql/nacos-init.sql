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
/*!40111 SET SQL_LOG_BIN=0 */;
--
-- Current Database: `nacos`
--
CREATE DATABASE
/*!32312 IF NOT EXISTS*/
`nacos`
/*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
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
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`, `group_id`, `tenant_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 44 DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = 'config_info';
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `config_info`
--
LOCK TABLES `config_info` WRITE;
/*!40000 ALTER TABLE `config_info` DISABLE KEYS */;
INSERT INTO
  `config_info` (
    `id`,
    `data_id`,
    `group_id`,
    `content`,
    `md5`,
    `gmt_create`,
    `gmt_modified`,
    `src_user`,
    `src_ip`,
    `app_name`,
    `tenant_id`,
    `c_desc`,
    `c_use`,
    `effect`,
    `type`,
    `c_schema`
  )
VALUES
  (
    4,
    'anan-authserver.yaml',
    'DEFAULT_GROUP',
    'server:
  port: 51400
spring:
  session:
    store-type: redis
  thymeleaf:
    cache: false
  # mvc:
  #   static-path-pattern: /auth/**
anan:
  security:
    sso:
      authority:
        root-path: ^/((?!oauth).)*$
      form-login:
        enabled: true
        login-page: /sso/login
        login-processing-url: /sso/login
        password-parameter: password
        username-parameter: usercode
        default-success-url: /sso/index
        failure-url: /sso/login?error
        logout-url: /sso/logout
        logout-success-url: /sso/login?logout
        #clear-authentication: true
        #invalidate-http-session: false
      remember-me:
        enabled: true
      session:
        session-creation-policy: if_required
      exception-handling:
        access-denied-page: /error
        enabled: true
      http-basic:
        enabled: true
      anonymous:
        enabled: true
      web-ignoring:
        - paths: /**/*.html,/**/*.css,/**/*.js,/**/*.woff*,/**/*.ttf*,/**/*.map,/**/*.ico
        - paths: /**/*.swf,/**/*.jpg,/**/*.png,/**/*.svg,/**/*.gif
        - paths: /error,/**/api-docs,/actuator/**,/v1/permission/**
    oauth2:
      authority:
        root-path: ^/oauth/.*
      resource-server:
        enabled: true
        token-type: jwt
      session:
        session-creation-policy: NEVER
  swagger:
    enabled: true
    title: ${spring.application.name}
    description: ${info.description}
    version: ${info.version}
    base-package: top.fosin.anan.auth
    authorization:
      name: Oauth2.0 Authorization
      keyName: Authorization
    #      authRegex: ^[^/oauth/token]$
    contact:
      name: fosin
      email: 28860823@qq.com
  redis:
    idempotent:
      enabled: false
    cache:
      manager: false
    session:
      manager: true',
    'eca38d64f1ee85c0eb52633d48e80dbc',
    '2019-11-10 17:23:05',
    '2020-03-15 18:35:10',
    null,
    '192.168.137.1',
    '',
    '6138f451-2d5b-42fe-a793-df3744d7257c',
    'null',
    'null',
    'null',
    'yaml',
    'null'
  );
INSERT INTO
  `config_info` (
    `id`,
    `data_id`,
    `group_id`,
    `content`,
    `md5`,
    `gmt_create`,
    `gmt_modified`,
    `src_user`,
    `src_ip`,
    `app_name`,
    `tenant_id`,
    `c_desc`,
    `c_use`,
    `effect`,
    `type`,
    `c_schema`
  )
VALUES
  (
    7,
    'application.yaml',
    'DEFAULT_GROUP',
    'logging:
  # pattern:
  #   file: ''%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{50} %msg%n%throwablen%''
  #   console: ''%highlight(%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level) %cyan(%logger{50}) %highlight(%msg%n%throwablen%)''
  # file:
  #   max-size: 50MB
  #   max-history: 7
  #   name: logs/${spring.application.name}.log
  #   total-size-cap: 400MB
  #   clean-history-on-start: true
  level:
    com.alibaba.nacos.naming.log.level: warn
    com.alibaba.nacos.client.naming: warn
    com.alibaba.nacos.client.config: warn
    #打印SQL语句（需改成DEBUG）
    org.hibernate.SQL: DEBUG
    #打印SQL语句参数（需改成TRACE）
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
    #打印SQL执行结果（需改成TRACE）
    org.hibernate.type.descriptor.sql.BasicExtractor: INFO
    #打印查询中命名参数的值
    org.hibernate.engine.spi.QueryParameters: DEBUG
    org.hibernate.engine.query.spi.HQLQueryPlan: DEBUG
    #    zipkin2: debug
    #top.fosin.anan.security: DEBUG
    #org.springframework.security: DEBUG
spring:
  datasource:
    url: ${anan.datasource.url}
    username: ${anan.datasource.username}
    password: ${anan.datasource.password}
    #    type: com.alibaba.druid.pool.DruidDataSource
    # Hikari 连接池配置
    hikari:
      # 最小空闲连接数量
      minimum-idle: 1
      # 空闲连接存活最大时间，默认600000（10分钟）
      idle-timeout: 180000
      # 连接池最大连接数，默认是10
      maximum-pool-size: 5
      # 此属性控制从池返回的连接的默认自动提交行为,默认值：true
      auto-commit: true
      # 连接池名称
      pool-name: MyHikariCP
      # 此属性控制池中连接的最长生命周期，值0表示无限生命周期，默认1800000即30分钟
      max-lifetime: 1800000
      # 数据库连接超时时间,默认30秒，即30000
      connection-timeout: 30000
      connection-test-query: SELECT 1
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
    host: redis
    password: ENC(svOoGRZ5qlC1bRGGh+7YwA==)
    # 连接超时时间（毫秒）
    timeout: 10s
    lettuce:
      # 连接池最大阻塞等待时间 毫秒（使用负值表示没有限制） 默认 -1
      max-active: 3
      # 连接池最大连接数（使用负值表示没有限制） 默认 8
      max-wait: -1
      # 连接池中的最大空闲连接 默认 8
      max-idle: 1
      # 连接池中的最小空闲连接 默认 0
      min-idle: 1
  cloud:
    stream:
      default:
        group: ${spring.application.name}-${spring.cloud.client.ip-address}-${server.port}
      rabbit:
        bindings:
          springCloudBusInput:
            consumer:
              # 队列声明重试次数
              queue-declaration-retries: 2000
              # 重试间隔(ms)
              recovery-interval: 5000
              # 为true时，使用‘group’作为配置刷新队列的名称
              queue-name-group-only: true
  jpa:
    #Hibernate自动创建表的时候使用InnoDB存储引擎，不然就会以默认存储引擎MyISAM来建表，而MyISAM存储引擎是没有事务的。
    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect
    open-in-view: true
    properties:
      hibernate:
        format_sql: true
        use_sql_comments: true
    #show-sql: true
  #    hibernate:
  #      ddl-auto: update
  zipkin:
    message-timeout: 10
    #    baseUrl: http://zipkin:9411/
    sender:
      type: rabbit
    rabbitmq:
      queue: zipkin
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
  jackson:
    #日期格式化
    date-format: yyyy-MM-dd HH:mm:ss
    time-zone: GMT+8
    # serialization:
    #    #格式化输出
    #   indent_output: true
    #   #忽略无法转换的对象
    #   fail_on_empty_beans: false
    # #设置空如何序列化
    # defaultPropertyInclusion: NON_EMPTY
    # deserialization:
    #   #允许对象忽略json中不存在的属性
    #   fail_on_unknown_properties: false
    # parser:
    #   #允许出现特殊字符和转义符
    #   allow_unquoted_control_chars: true
    #   #允许出现单引号
    #   allow_single_quotes: true
  oauth2:
    client:
      registration:
        anan-authserver:
          client-id: appServer
          client-secret: appServer
          provider: anan-authserver
      provider:
        anan-authserver:
          authorization-uri: http://anan-authserver:51400/oauth2/authorize
          token-uri: http://anan-authserver:51400/oauth2/token
          user-info-uri: http://anan-authserver:51400/oauth2/userinfo/jwt
          jwk-set-uri: http://anan-authserver:51400/oauth2/token_key
server:
  servlet:
    session:
      cookie:
        # This is to prevent cookie clash with other service as they run on the same host and context path
        name: ${spring.application.name}
  undertow:
    threads:
      # 设置IO线程数, 它主要执行非阻塞的任务,它们会负责多个连接, 默认设置每个CPU核心一个线程
      # 不要设置过大，如果过大，启动项目会报错：打开文件数过多
      io: 4
      # 阻塞任务线程池, 当执行类似servlet请求阻塞IO操作, undertow会从这个线程池中取得线程
      # 它的值设置取决于系统线程执行任务的阻塞系数，默认值是IO线程数*8
      worker: 4
    # 以下的配置会影响buffer,这些buffer会用于服务器连接的IO操作,有点类似netty的池化内存管理
    # 每块buffer的空间大小,越小的空间被利用越充分，不要设置太大，以免影响其他应用，合适即可
    buffer-size: 1024
    direct-buffers: true
management:
  endpoints:
    web:
      exposure:
        include: ''*''
  endpoint:
    shutdown:
      enabled: true
    health:
      show-details: ALWAYS
  metrics:
    web:
      server:
        request:
          autotime:
            enabled: false
anan:
  datasource:
    url: jdbc:mysql://mysql:3306/anan_platform?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2b8
    username: anan
    password: ENC(svOoGRZ5qlC1bRGGh+7YwA==)
  security:
    jwt:
      key-store: ${encrypt.key-store.location}
      key-alias: ${encrypt.key-store.alias}
      key-password: ${encrypt.key-store.password}
      key-store-password: ${encrypt.key-store.secret}
      public-key-location: classpath:anan.pub
  oauth2:
    client:
      client-id: appServer
      client-secret: appServer
      access-token-uri: http://anan-authserver:51400/oauth/token
security:
  oauth2:
    resource:
      loadBalanced: true
      user-info-uri: http://anan-authserver/oauth/userinfo/principal
      prefer-token-info: false
eureka:
  client:
    healthcheck:
      # 开启健康检查（需要spring-boot-starter-actuator依赖）
      enabled: true
  instance:
    #续约到期时间（默认90秒）
    lease-expiration-duration-in-seconds: 10
    #续约更新时间间隔（默认30秒）
    lease-renewal-interval-in-seconds: 5

# feign:
#  compression:
#    request:
#      enabled: true
#    #Feign request compression gives you settings similar to what you may set for your web server:
#      mime-types: text/xml,application/xml,application/json
#      min-request-size: 2048
#    response:
#      enabled: true
#    #For http clients except OkHttpClient, default gzip decoder can be
#    # enabled to decode gzip response in UTF-8 encoding:
#      useGzipDecoder: true
# circuitbreaker:
#   enabled: true
# okhttp:
#   enabled: true
#httpclient:
#  enabled: false
#    max-connections: 1000 #最大连接数
#    max-connections-per-route: 100 #每个url的连接数
#  client:
#    config:
#      anan-platformserver:
#        connectTimeout: 5000
#        readTimeout: 5000
#        loggerLevel: full
#        errorDecoder: com.example.SimpleErrorDecoder
#        retryer: com.example.SimpleRetryer
#        defaultQueryParameters:
#          query: queryValue
#        defaultRequestHeaders:
#          header: headerValue
#        requestInterceptors:
#          - com.example.FooRequestInterceptor
#          - com.example.BarRequestInterceptor
#        decode404: false
#        encoder: com.example.SimpleEncoder
#        decoder: com.example.SimpleDecoder
#        contract: com.example.SimpleContract
#        capabilities:
#          - com.example.FooCapability
#          - com.example.BarCapability
        #        metrics.enabled: false',
    '626830ef58e9b827b03feeda3492d01f',
    '2019-11-10 17:39:32',
    '2019-11-22 21:28:31',
    null,
    '192.168.137.1',
    '',
    '6138f451-2d5b-42fe-a793-df3744d7257c',
    '服务公用设置',
    'null',
    'null',
    'yaml',
    'null'
  );
INSERT INTO
  `config_info` (
    `id`,
    `data_id`,
    `group_id`,
    `content`,
    `md5`,
    `gmt_create`,
    `gmt_modified`,
    `src_user`,
    `src_ip`,
    `app_name`,
    `tenant_id`,
    `c_desc`,
    `c_use`,
    `effect`,
    `type`,
    `c_schema`
  )
VALUES
  (
    13,
    'anan-sbaserver.yaml',
    'DEFAULT_GROUP',
    'server:
  port: 51700
turbine:
  cluster-name-expression: new String(''default'')
  app-config: anan-zuulgateway
spring:
  boot:
    admin:
      context-path: /sba
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
          enabled: true',
    'e21f85d17b34ccfdbc9bf544af884e61',
    '2019-11-10 18:17:25',
    '2020-02-26 22:12:50',
    null,
    '192.168.137.1',
    '',
    '6138f451-2d5b-42fe-a793-df3744d7257c',
    'null',
    'null',
    'null',
    'yaml',
    'null'
  );
INSERT INTO
  `config_info` (
    `id`,
    `data_id`,
    `group_id`,
    `content`,
    `md5`,
    `gmt_create`,
    `gmt_modified`,
    `src_user`,
    `src_ip`,
    `app_name`,
    `tenant_id`,
    `c_desc`,
    `c_use`,
    `effect`,
    `type`,
    `c_schema`
  )
VALUES
  (
    14,
    'anan-cloudgateway.yaml',
    'DEFAULT_GROUP',
    'server:
  port: 51900
spring:
  cloud:
    sentinel:
      transport:
        #配置 Sentinel dashboard 地址
        dashboard: localhost:8718
        #默认8719端口，假如被占用会自动从8719开始依次+1扫描,直至找到未被占用的端口
        port: 8719
    gateway:
      enabled: true
      discovery:
        locator:
          enabled: true
      routes:
        - id: anan-cloudgateway
          uri: lb://anan-cloudgateway
          order: 99
          predicates:
            - Path=/gateway/**
          filters:
            - StripPrefix=1
        - id: anan-platformserver
          uri: lb://anan-platformserver
          order: 1
          predicates:
            - Path=/gateway/platform/**
          filters:
            - StripPrefix=2
                    #   # 限流 依赖spring-boot-starter-data-redis-reactive，与swagger、zipkin等使用会报错
                    # - name: RequestRateLimiter
                    #   args:
                    #     # 限流策略
                    #     key-resolver: ''#{@remoteAddrKeyResolver}''
                    #     # 令牌桶每秒填充率
                    # redis-rate-limiter.replenishRate: 1
                    #     # 令牌桶容量
                    # redis-rate-limiter.burstCapacity: 10
            # redis-rate-limiter.requestedTokens: 1
            #   # 熔断
            # - name: Hystrix
            #   args:
            #     name: appService1EchoCmd

        - id: anan-authserver
          uri: lb://anan-authserver
          order: 1
          predicates:
            - Path=/gateway/auth/**
          filters:
            - StripPrefix=2
        - id: anan-mpi
          uri: lb://anan-mpi
          order: 1
          predicates:
            - Path=/gateway/mpi/**
          filters:
            - RewritePath=/mpi/(?<segment>.*), /$\{segment}
        - id: anan-vhr
          uri: lb://anan-vhr
          order: 1
          predicates:
            - Path=/gateway/vhr/**
          filters:
            - StripPrefix=2
        - id: anan-exam
          uri: lb://anan-exam
          order: 1
          predicates:
            - Path=/gateway/exam/**
          filters:
            - StripPrefix=2
anan:
  security:
    oauth2:
      session:
        session-creation-policy: stateless
      resource-server:
        enabled: true
        token-type: jwt
      corss:
        enabled: true
        cors:
          - path: /**
            allowed-origins: ''http://localhost:9528''
            allowed-methods: ''*''
            allowed-headers: ''*''
            # exposed-headers: ''*''
            allow-credentials: true
      web-ignoring:
        - paths: /*/*.html,/*/*.css,/*/*.js,/*/*.woff*,/*/*.ttf*,/*/*.map,/*/*.ico
        - paths: /*/*.swf,/*/*.jpg,/*/*.png,/*/*.svg,/*/*.gif
        - paths: /*/*/*/api-docs,/*/swagger-resources/**,/*/swagger-ui/**,/swagger-resources/**,/swagger-ui/**
        - paths: /actuator/**
        - paths: /gateway/auth/oauth/**,/gateway/auth/sso/**,/gateway/platform/*/international/status/*,/gateway/platform/*/international/code/*,/gateway/platform/*/international/default,/gateway/platform/*/international/charset/internationalId/*
          methods: GET,POST
  swagger:
    enabled: true
    title: ${spring.application.name}
    description: ${info.description}
    version: ${info.version}
    base-package: top.fosin.anan.cloudgateway
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
      manager: true',
    '55b6239e5aed6a522b16d48872a0eedb',
    '2019-11-10 18:17:51',
    '2020-03-15 18:36:42',
    null,
    '192.168.137.1',
    '',
    '6138f451-2d5b-42fe-a793-df3744d7257c',
    'null',
    'null',
    'null',
    'yaml',
    'null'
  );
INSERT INTO
  `config_info` (
    `id`,
    `data_id`,
    `group_id`,
    `content`,
    `md5`,
    `gmt_create`,
    `gmt_modified`,
    `src_user`,
    `src_ip`,
    `app_name`,
    `tenant_id`,
    `c_desc`,
    `c_use`,
    `effect`,
    `type`,
    `c_schema`
  )
VALUES
  (
    18,
    'anan-zuulgateway.yaml',
    'DEFAULT_GROUP',
    'server:
  port: 51900
zuul:
  add-host-header: true
  add-proxy-headers: true
  #如果不过滤，则须显式设为空，默认Cookie,Set-Cookie,Authorization。
  #在Hoxton.SR6中这个参数在application.yaml不会生效，但是转移值bootstrap.yaml中就可以
  #当前只能通过设置每个服务的custom-sensitive-headers: true来解决
  sensitive-headers:
  #  - Cookie
  #  - Set-Cookie
  #  - Authorization
  #默认启用重试
  retryable: true
  ignored-services: ''*''
  #是否移除代理前缀,默认为true
  #strip-prefix: true
  #为所有http请求前增加/zuul前缀
  prefix: /gateway
  routes:
    anan-platformserver:
      path: /platform/**
      serviceId: anan-platformserver
      retryable: true
      custom-sensitive-headers: true
      sensitive-headers:
    anan-authserver:
      path: /auth/**
      serviceId: anan-authserver
      retryable: true
      custom-sensitive-headers: true
      sensitive-headers:
    anan-zuulgateway:
      path: /**
      serviceId: anan-zuulgateway
      retryable: true
      custom-sensitive-headers: true
      sensitive-headers:
    anan-exam:
      path: /exam/**
      serviceId: anan-exam
      retryable: true
      custom-sensitive-headers: true
      sensitive-headers:
  #  semaphore:
  #    max-semaphores: 100
  #  ribbon-isolation-strategy: THREAD
  #  thread-pool:
  #    use-separate-thread-pools: true
  #    thread-pool-key-prefix: zuulgw
  ratelimit:
    #对应用来标识请求的key的前缀
    key-prefix: zuulgateway
    enabled: true
    #对应存储类型（用来存储统计信息）,可选值REDIS、IN_MEMORY、JPA、CONSUL,默认IN_MEMORY
    repository: REDIS
    #代理之后
    behind-proxy: true
    response-headers: VERBOSE
    #可选 - 针对所有的路由配置的策略，除非特别配置了policies
    default-policy-list:
      - limit: 600000 #可选 - 每个刷新时间窗口对应的请求数量限制
        quota: 100000 #可选-  每个刷新时间窗口对应的请求时间限制（秒）
        refresh-interval: 60 #刷新时间窗口的时间，默认值 60(秒)
        #可选 限流方式
        type:
          #用户粒度
          #        - user
          #ORIGIN粒度 (用户请求的origin作为粒度控制)
          #        - origin
          #接口粒度 (请求接口的地址作为粒度控制)
          - url
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
ribbon:
  #对所有操作请求都进行重试,默认false
  OkToRetryOnAllOperations: false
  #对当前实例的重试次数，默认0
  MaxAutoRetries: 0
  #对切换实例的重试次数，默认1
  MaxAutoRetriesNextServer: 1
  #负载均衡超时时间，默认值1000，单位ms
  ReadTimeout: 10000
  #ribbon请求连接的超时时间，默认值1000，单位ms
  ConnectTimeout: 5000
  #从注册中心刷新servelist的时间 默认30秒，单位ms
  ServerListRefreshInterval: 15000
hystrix:
  threadpool:
    default:
      coreSize: 100
      maximumSize: 2000
      #允许maximumSize起作用
      allowMaximumSizeToDivergeFromCoreSize: true
      #如该值为-1，那么使用的是SynchronousQueue，否则使用的是LinkedBlockingQueue
      maxQueueSize: -1
  command:
    default:
      execution:
        isolation:
          thread:
            #断路器的超时时间；如果ribbon配置了重试那么该值必需大于ribbonTimeout = (ribbonReadTimeout + ribbonConnectTimeout) * (maxAutoRetries + 1) * (maxAutoRetriesNextServer + 1)，重试才能生效
            timeoutInMilliseconds: 40001
      timeout:
        enabled: false
      circuitBreaker:
        enabled: true
        #当在配置时间窗口内达到此数量的失败后，进行短路。默认20个
        requestVolumeThreshold: 20
        #短路多久以后开始尝试是否恢复，默认5s
        sleepWindowInMilliseconds: 5000
        #出错百分比阈值，当达到此阈值后，开始短路。默认50%
        errorThresholdPercentage: 50%
anan:
  security:
    oauth2:
      session:
        session-creation-policy: stateless
      resource-server:
        enabled: true
        token-type: jwt
    corss:
      enabled: true
      cors:
        - path: /**
          allowed-origins: ''http://localhost:9528''
          allowed-methods: ''*''
          allowed-headers: ''*''
          # exposed-headers: ''*''
          allow-credentials: true
    web-ignoring:
      - paths: /**/*.html,/**/*.css,/**/*.js,/**/*.woff*,/**/*.ttf*,/**/*.map,/**/*.ico
      - paths: /**/*.swf,/**/*.jpg,/**/*.png,/**/*.svg,/**/*.gif
      - paths: /**/api-docs,/**/swagger-resources/**,/**/swagger-ui/**,/swagger-resources/**,/swagger-ui/**
      - paths: /actuator/**
      - paths: /gateway/auth/oauth/**,/gateway/auth/sso/**,/gateway/platform/**/international/status/*,/gateway/platform/**/international/code/*,/gateway/platform/**/international/default,/gateway/platform/**/international/charset/internationalId/*
        methods: GET,POST
  swagger:
    enabled: true
    title: ${spring.application.name}
    description: ${info.description}
    version: ${info.version}
    base-package: top.fosin.anan.zuulgateway
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
      manager: true',
    'b7353d61807d909009a90b70e0a7114e',
    '2019-11-10 20:04:04',
    '2020-03-15 18:36:23',
    null,
    '192.168.137.1',
    '',
    '6138f451-2d5b-42fe-a793-df3744d7257c',
    'null',
    'null',
    'null',
    'yaml',
    'null'
  );
INSERT INTO
  `config_info` (
    `id`,
    `data_id`,
    `group_id`,
    `content`,
    `md5`,
    `gmt_create`,
    `gmt_modified`,
    `src_user`,
    `src_ip`,
    `app_name`,
    `tenant_id`,
    `c_desc`,
    `c_use`,
    `effect`,
    `type`,
    `c_schema`
  )
VALUES
  (
    20,
    'anan-platformserver.yaml',
    'DEFAULT_GROUP',
    'server:
  port: 51500
anan:
  security:
    oauth2:
      web-ignoring:
        - paths: /**
  swagger:
    enabled: true
    title: ${spring.application.name}
    description: ${info.description}
    version: ${info.version}
    base-package: top.fosin.anan.platform
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
        #    required: true',
    'c859d1296638e9cd9795a0bb74220ee2',
    '2019-11-10 20:16:42',
    '2020-03-15 18:35:53',
    null,
    '192.168.137.1',
    '',
    '6138f451-2d5b-42fe-a793-df3744d7257c',
    'null',
    'null',
    'null',
    'yaml',
    'null'
  );
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
    UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`, `group_id`, `tenant_id`, `datum_id`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段';
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
    UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`, `group_id`, `tenant_id`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta';
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
    UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`, `group_id`, `tenant_id`, `tag_id`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag';
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
    UNIQUE KEY `uk_configtagrelation_configidtag` (`id`, `tag_name`, `tag_type`),
    KEY `idx_tenant_id` (`tenant_id`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation';
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
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表';
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
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造';
  /*!40101 SET character_set_client = @saved_cs_client */;
--
  -- Dumping data for table `his_config_info`
  --
  LOCK TABLES `his_config_info` WRITE;
  /*!40000 ALTER TABLE `his_config_info` DISABLE KEYS */;
  /*!40000 ALTER TABLE `his_config_info` ENABLE KEYS */;
UNLOCK TABLES;
--
  -- Table structure for table `permissions`
  --
  DROP TABLE IF EXISTS `permissions`;
  /*!40101 SET @saved_cs_client     = @@character_set_client */;
  /*!40101 SET character_set_client = utf8 */;
create table if not exists permissions (
    role varchar(50) not null,
    resource varchar(512) not null,
    action varchar(8) not null,
    constraint uk_role_permission unique (role, resource, action)
  );
--
  -- Table structure for table `roles`
  --
  DROP TABLE IF EXISTS `roles`;
  /*!40101 SET @saved_cs_client     = @@character_set_client */;
  /*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
    `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
    `role` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
  /*!40101 SET character_set_client = @saved_cs_client */;
--
  -- Dumping data for table `roles`
  --
  LOCK TABLES `roles` WRITE;
  /*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO
  `roles`
VALUES
  ('nacos', 'ROLE_ADMIN');
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
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表';
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
    UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`, `tenant_id`),
    KEY `idx_tenant_id` (`tenant_id`)
  ) ENGINE = InnoDB AUTO_INCREMENT = 5 DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info';
  /*!40101 SET character_set_client = @saved_cs_client */;
--
  -- Dumping data for table `tenant_info`
  --
  LOCK TABLES `tenant_info` WRITE;
  /*!40000 ALTER TABLE `tenant_info` DISABLE KEYS */;
INSERT INTO
  `tenant_info`
VALUES
  (
    1,
    '1',
    'local',
    'local',
    '本地开发环境',
    'nacos',
    1573371901768,
    1573371901768
  );
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
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;
  /*!40101 SET character_set_client = @saved_cs_client */;
--
  -- Dumping data for table `users`
  --
  LOCK TABLES `users` WRITE;
  /*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO
  `users`
VALUES
  (
    'nacos',
    '$2a$10$NAkPmjD2jhWZr68cZUnByuj2jCKRVkafFWGrYcg6ODVZBQ2F/Wzzq',
    1
  );
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
/*!40111 SET SQL_LOG_BIN=1 */;
-- Dump completed on 2020-02-14 14:10:48
