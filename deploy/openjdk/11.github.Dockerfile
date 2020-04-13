FROM openjdk:11-jdk
MAINTAINER fosin 28860823@qq.com

VOLUME ["/tmp","/logs"]

WORKDIR /anan

COPY entrypoint.sh wait-for.sh ./

RUN chmod +x entrypoint.sh wait-for.sh \
    && echo "Asia/Shanghai" > /etc/timezone \
    && set -eux \
    && apt update \
    && apt -y install netcat \
    && apt -y install net-tools \
    && rm -rf /var/lib/apt/lists/*
