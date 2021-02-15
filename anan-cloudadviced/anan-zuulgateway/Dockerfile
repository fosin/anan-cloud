FROM registry.cn-hongkong.aliyuncs.com/fosin/adoptopenjdk:11-jdk-hotspot
MAINTAINER fosin 28860823@qq.com
EXPOSE 9000
ENTRYPOINT ["./entrypoint.sh"]
COPY target/dependency dependency/
COPY target/*.jar app.jar

