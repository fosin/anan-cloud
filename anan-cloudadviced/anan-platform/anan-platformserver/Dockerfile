FROM registry.cn-hangzhou.aliyuncs.com/fosin/openjdk:8-jre-alpine
MAINTAINER fosin 28860823@qq.com
ARG jarFile
COPY ${jarFile} app.jar
EXPOSE 51500
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
