FROM fosin/openjdk:11-jdk
MAINTAINER fosin 28860823@qq.com
EXPOSE 51700
ENTRYPOINT ["entrypoint.sh"]
COPY target/dependency dependency/
COPY target/*.jar app.jar

