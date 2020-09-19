# 部署jar
## 1、部署jdk11及以上版本、mysql、redis、rabbitmq、nacos

## 2、配置anan-cloud中的profile（重点修改rabbitmq、nacos的信息等）

## 3、配置nacos配置中心
导入模版配置并修改对应的mysql数据库、redis、rabbitmq [点这里](deploy/anan-config) 
## 4、编译打包jar
    -s是本地maven的settings.xml路径
    -P是anan-cloud中激活的profile
```shell script
mvn clean compile jar:jar spring-boot:repackage ^
-Dmaven.javadoc.skip=true ^
-s E:\tools\Apache\Maven\settings.xml ^
-P dev ^
-pl ./anan-eurekaserver,./anan-configserver,./anan-cloudgateway,^
./anan-cloudadviced/anan-authserver,./anan-cloudadviced/anan-platformserver,^
./anan-cloudadviced/anan-zuulgateway,./anan-cloudadviced/anan-adminserver
```

## 5、按顺序启动jar
    假设anan-cloud当前版本是2.3.0-SNAPSHOT
    Profile是dev
```shell script
# 如果使用了nacos作为服务注册和配置中心，则不需要启动erueka和config
./anan-eurekaserver-2.3.0-SNAPSHOT-dev.jar
./anan-configserver-2.3.0-SNAPSHOT-dev.jar

./anan-authserver-2.3.0-SNAPSHOT-dev.jar
./anan-platformserver-2.3.0-SNAPSHOT-dev.jar
./anan-adminserver-2.3.0-SNAPSHOT-dev.jar

# 网关选择zuul或者gateway其一即可
./anan-zuulgateway-2.3.0-SNAPSHOT-dev.jar
./anan-cloudgateway-2.3.0-SNAPSHOT-dev.jar

```
