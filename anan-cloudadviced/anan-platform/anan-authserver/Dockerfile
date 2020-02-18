FROM registry.cn-hangzhou.aliyuncs.com/fosin/openjdk:11-jdk
MAINTAINER fosin 28860823@qq.com
ARG jarFile
COPY ${jarFile} app.jar
EXPOSE 51400
ENTRYPOINT ["/bin/entrypoint.sh"]
