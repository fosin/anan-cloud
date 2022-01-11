FROM mysql:5.7

MAINTAINER fosin 28860823@qq.com

COPY anan-platform-init.sql nacos-init.sql /docker-entrypoint-initdb.d/
COPY anan-mysql-temp.cnf /etc/mysql/conf.d

ENTRYPOINT ["docker-entrypoint.sh"]

CMD ["mysqld"]
