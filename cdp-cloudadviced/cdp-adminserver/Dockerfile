FROM openjdk:8-jre-alpine
MAINTAINER fosin 28860823@qq.com
VOLUME /tmp
VOLUME /logs
ARG jarFile
COPY ${jarFile} app.jar
RUN echo "Asia/Shanghai" > /etc/timezone
RUN apk add --update netcat-openbsd && rm -rf /var/cache/apk/*
EXPOSE 51700
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
