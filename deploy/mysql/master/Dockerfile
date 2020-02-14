FROM mysql:5.7

MAINTAINER fosin 28860823@qq.com

COPY ./anan-platform-init.sql /docker-entrypoint-initdb.d
COPY ./nacos-init.sql /docker-entrypoint-initdb.d
COPY ./master.cnf /etc/mysql/conf.d
COPY ./docker-entrypoint.sh /usr/local/bin/

ENV MYSQL_REPLICATION_USER anan_ru
ENV MYSQL_REPLICATION_PASSWORD anan_ru
ENV MYSQL_DATABASE2 anan_platform
ENV MYSQL_USER2 anan
ENV MYSQL_PASSWORD2 anan

RUN ln -s -f usr/local/bin/docker-entrypoint.sh /entrypoint.sh # backwards compat
ENTRYPOINT ["docker-entrypoint.sh"]

CMD ["mysqld"]
