FROM adoptopenjdk:8-jdk
MAINTAINER fosin 28860823@qq.com

VOLUME ["/tmp","/logs"]

WORKDIR /anan

COPY entrypoint.sh wait-for.sh ./
COPY sources.list /etc/apt/

RUN chmod +x entrypoint.sh wait-for.sh \
    && echo "Asia/Shanghai" > /etc/timezone \
    && apt update \
    && apt -y install gnupg2 \
    && apt-key adv --recv-keys --keyserver keyserver.ubuntu.com 40976EAF437D05B5 3B4FE6ACC0B21F32 \
    && set -eux \
    && apt -y install netcat \
    && apt -y install net-tools \
    && rm -rf /var/lib/apt/lists/*
