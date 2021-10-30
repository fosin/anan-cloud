FROM eclipse-temurin:11-jdk
MAINTAINER fosin 28860823@qq.com

VOLUME ["/tmp","/logs"]

WORKDIR /anan

COPY entrypoint.sh wait-for.sh ./
COPY sources.list /etc/apt/

RUN chmod +x entrypoint.sh wait-for.sh \
    && echo "Asia/Shanghai" > /etc/timezone \
    && /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime -f \
    && apt-get update \
    && set -eux \
    && apt-get -y install netcat net-tools iputils-ping telnet \
    && rm -rf /var/lib/apt/lists/*
