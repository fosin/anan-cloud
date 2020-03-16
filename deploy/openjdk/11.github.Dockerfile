FROM openjdk:11-jdk

MAINTAINER fosin 28860823@qq.com

VOLUME ["/tmp","/logs"]

COPY entrypoint.sh wait-for.sh /bin/

RUN chmod +x bin/entrypoint.sh bin/wait-for.sh \
    && echo "Asia/Shanghai" > /etc/timezone \
    && set -eux \
    && apt update \
    && apt -y install netcat \
    && apt -y install net-tools \
    && rm -rf /var/lib/apt/lists/*
#    && apt -y install aptitude \
#    && apt -y install vim \
