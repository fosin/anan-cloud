ARG TEMURIN_JDK_VERSION=17-jdk-centos7
FROM eclipse-temurin:${TEMURIN_JDK_VERSION}

LABEL maintainer=fosin
LABEL email=28860823@qq.com
LABEL description="基于TEMURIN JDK17、常用Linux命令、上海时区"

ARG WORK_DIR=/anan
ARG LOGS_DIR=${WORK_DIR}/logs
ARG DEPENDENCY_DIR=${WORK_DIR}/dependency
ARG CMD_DIR=${WORK_DIR}/cmd
ARG APP_NAME=app.jar
ARG HEAP_DUMP_PATH=${LOGS_DIR}/
ARG GC_LOGS_PATH=${LOGS_DIR}/gclog.log
ARG ERROR_FILE_PATH=$LOGS_DIR/hs_err_pid%p.log
ARG JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=50 -XX:InitialRAMPercentage=30 -XX:MinRAMPercentage=30 -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=200m"
ARG JAVA_OPTS_LOGS="-Xlog:gc*:file=$GC_LOGS_PATH:time,level,tags:filecount=5,filesize=30M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${HEAP_DUMP_PATH} -XX:ErrorFile=$ERROR_FILE_PATH"

ENV WORK_DIR ${WORK_DIR}
ENV LOGS_DIR ${LOGS_DIR}
ENV DEPENDENCY_DIR ${DEPENDENCY_DIR}
ENV CMD_DIR ${CMD_DIR}
ENV APP_NAME ${APP_NAME}
ENV HEAP_DUMP_PATH ${HEAP_DUMP_PATH}
ENV GC_LOGS_PATH ${GC_LOGS_PATH}
ENV ERROR_FILE_PATH ${ERROR_FILE_PATH}
ENV JAVA_OPTS ${JAVA_OPTS}
ENV JAVA_OPTS_LOGS ${JAVA_OPTS_LOGS}

WORKDIR ${WORK_DIR}

COPY *.sh ${CMD_DIR}/
COPY grpcurl /usr/sbin/

RUN chmod +x ${CMD_DIR}/*.sh \
    && echo Asia/Shanghai > /etc/timezone \
    && chmod +x /usr/sbin/grpcurl \
    && /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime -f \
    && yum makecache fast \
    && yum -y install nmap-ncat net-tools sysstat xinetd telnet telnet-server \
    && yum clean all

ENTRYPOINT ["cmd/entrypoint.sh"]