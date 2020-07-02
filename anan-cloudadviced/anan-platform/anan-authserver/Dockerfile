FROM fosin/adoptopenjdk:11-jdk-openj9
MAINTAINER fosin 28860823@qq.com
EXPOSE 51400
ENTRYPOINT ["./entrypoint.sh"]
COPY target/dependency dependency/
COPY target/*.jar app.jar
