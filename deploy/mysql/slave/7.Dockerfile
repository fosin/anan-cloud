FROM mysql:5.7

MAINTAINER fosin 28860823@qq.com

COPY slave.cnf /etc/mysql/conf.d
COPY docker-entrypoint.sh /usr/local/bin/

ENV MYSQL_REPLICATION_USER="anan_ru" \
    MYSQL_REPLICATION_PASSWORD="anan_ru" \
    MYSQL_MASTER_SERVICE_HOST="mysql-master" \
    MYSQL_MASTER_SERVICE_PORT="3306"

RUN chmod +x usr/local/bin/docker-entrypoint.sh \
    && ln -s -f usr/local/bin/docker-entrypoint.sh /entrypoint.sh

ENTRYPOINT ["docker-entrypoint.sh"]

CMD ["mysqld"]
