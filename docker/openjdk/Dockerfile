FROM openjdk:8-jre-alpine
MAINTAINER fosin 28860823@qq.com
VOLUME /tmp
VOLUME /logs
COPY wait-for.sh /
COPY entrypoint.sh /
RUN echo "Asia/Shanghai" > /etc/timezone
RUN apk add --update netcat-openbsd && rm -rf /var/cache/apk/*
