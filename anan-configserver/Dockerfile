FROM registry.cn-hongkong.aliyuncs.com/fosin/eclipse-temurin:11-centos7-hotspot
MAINTAINER fosin 28860823@qq.com
VOLUME /anan-conf
EXPOSE 6110
ENTRYPOINT ["./entrypoint.sh"]
COPY target/dependency dependency/
COPY target/*.jar app.jar
