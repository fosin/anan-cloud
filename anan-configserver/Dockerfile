FROM registry.cn-hangzhou.aliyuncs.com/fosin/openjdk:11-jdk
MAINTAINER fosin 28860823@qq.com
VOLUME /anan-config
ARG jarFile
COPY ${jarFile} app.jar
EXPOSE 51100
ENTRYPOINT ["/bin/entrypoint.sh"]
