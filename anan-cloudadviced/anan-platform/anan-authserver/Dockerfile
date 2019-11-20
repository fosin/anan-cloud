FROM openjdk:8-jre-alpine
MAINTAINER fosin 28860823@qq.com
VOLUME /tmp
VOLUME /logs
ENV LANG C.UTF-8
ENV JAVA_TOOL_OPTIONS -Dfile.encoding=UTF8
ARG jarFile
COPY ${jarFile} app.jar
RUN echo "Asia/Shanghai" > /etc/timezone
RUN apk add --update netcat-openbsd && rm -rf /var/cache/apk/*
EXPOSE 51400
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
