FROM adoptopenjdk:11-jdk-openj9
MAINTAINER fosin 28860823@qq.com

VOLUME ["/tmp","/logs"]

WORKDIR /anan

COPY entrypoint.sh wait-for.sh ./
COPY sources.list /etc/apt/

RUN chmod +x entrypoint.sh wait-for.sh \
    && echo "Asia/Shanghai" > /etc/timezone \
    && apt update \
    && set -eux \
    && apt -y install netcat \
    && apt -y install net-tools \
    && apt -y install iputils-ping \
    && apt -y install telnet \
    && rm -rf /var/lib/apt/lists/*
