FROM openjdk:8-jre-alpine
MAINTAINER fosin 28860823@qq.com
VOLUME /tmp
ARG jarFile
COPY ${jarFile} app.jar
EXPOSE 1700
CMD java -Djava.security.egd=file:/dev/./urandom -jar app.jar
