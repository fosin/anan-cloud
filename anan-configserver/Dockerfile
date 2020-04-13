FROM fosin/openjdk:11-jdk
MAINTAINER fosin 28860823@qq.com
VOLUME /anan-config
EXPOSE 51100
ENTRYPOINT ["entrypoint.sh"]
COPY target/dependency dependency/
COPY target/*.jar app.jar

