FROM registry.cn-hangzhou.aliyuncs.com/fosin/openjdk:8-jre-alpine
MAINTAINER fosin 28860823@qq.com
VOLUME /anan-config
ARG jarFile
COPY ${jarFile} app.jar
EXPOSE 51100
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
