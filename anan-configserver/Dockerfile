FROM registry.cn-hangzhou.aliyuncs.com/fosin/openjdk:11-jdk
MAINTAINER fosin 28860823@qq.com
VOLUME /anan-config
COPY target/*.jar app.jar
EXPOSE 51100
ENTRYPOINT ["/bin/entrypoint.sh"]
