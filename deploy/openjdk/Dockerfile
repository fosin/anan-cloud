FROM openjdk:11-jdk

MAINTAINER fosin 28860823@qq.com

VOLUME ["/tmp","/logs"]

COPY deploy/openjdk/bin/ /bin/
COPY deploy/openjdk/debian/sources.list /etc/apt/
#COPY ./bin/ /bin/
#COPY ./debian/sources.list /etc/apt/

RUN chmod +x bin/entrypoint.sh bin/wait-for.sh \
    && echo "Asia/Shanghai" > /etc/timezone \
    && apt-key adv --recv-keys --keyserver keyserver.ubuntu.com 40976EAF437D05B5 3B4FE6ACC0B21F32 \
    && set -eux \
    && apt update \
    && apt -y install netcat \
    && apt -y install net-tools \
    && rm -rf /var/lib/apt/lists/*
#    && apt -y install aptitude \
#    && apt -y install vim \
