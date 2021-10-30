FROM eclipse-temurin:11-jdk-centos7
MAINTAINER fosin 28860823@qq.com

VOLUME ["/tmp","/logs"]

WORKDIR /anan

COPY entrypoint.sh wait-for.sh ./

RUN chmod +x entrypoint.sh wait-for.sh \
    && echo "Asia/Shanghai" > /etc/timezone \
    && /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime -f \
    && yum makecache fast \
    && yum -y install nmap-ncat net-tools sysstat xinetd telnet telnet-server \
    && yum clean all
