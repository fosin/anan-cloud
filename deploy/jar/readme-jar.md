# 部署jar

## 1、部署jdk11及以上版本、redis、rabbitmq

    没有什么特殊要求，按需部署即可

## 2、部署mysql数据库及导入数据

    部署没有特殊要求，按需部署即可
    导入deploy/mysql/leader/*.sql文件，创建数据库并导入表结构及基础数据

## 2、配置anan-cloud中的profile（按需修改rabbitmq、nacos、eureka、config的信息等）

修改[pom.xml](../../pom.xml) 文件中的profiles选项，定制自己的profile

## 3、部署nacos及导入nacos基础配置数据

### 3.1、部署nacos请看官方文档<https://nacos.io/zh-cn/docs/quick-start.html>
### 3.2、修改对应的mysql数据库、redis配置 [点这里](../anan-config)
### 3.3、使用nacos后台(一般是<http://localhost:8848/nacos/>)导入模版配置,nacos中的namespace 名称需要和[pom.xml](../../pom.xml)中的自定义profile名称一致

## 4、编译打包jar

    -s是本地maven的settings.xml路径
    -P是anan-cloud中激活的profile

```shell script
mvn clean compile jar:jar spring-boot:repackage ^
-Dmaven.javadoc.skip=true ^
-s E:\tools\Apache\Maven\settings.xml ^
-P {activeProfile} ^
-pl ./anan-eurekaserver,./anan-configserver,./anan-cloudgateway,^
./anan-cloudadviced/anan-authserver,./anan-cloudadviced/anan-platformserver,^
./anan-cloudadviced/anan-sbaserver
```

## 5、按顺序启动jar

    假设anan-cloud当前版本是3.0.0-SNAPSHOT
    Profile是dev

```shell script
# 默认使用nacos作为服务注册和配置中心，不需要启动erueka和config
./anan-eurekaserver-3.0.0-SNAPSHOT-{activeProfile}.jar
./anan-configserver-3.0.0-SNAPSHOT-{activeProfile}.jar

./anan-authserver-3.0.0-SNAPSHOT-{activeProfile}.jar
./anan-platformserver-3.0.0-SNAPSHOT-{activeProfile}.jar
./anan-sbaserver-3.0.0-SNAPSHOT-{activeProfile}.jar

./anan-cloudgateway-3.0.0-SNAPSHOT-{activeProfile}.jar

```
