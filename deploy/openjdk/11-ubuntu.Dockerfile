ARG TEMURIN_JDK_VERSION=11-jdk
FROM eclipse-temurin:${TEMURIN_JDK_VERSION}

LABEL maintainer=fosin
LABEL email=28860823@qq.com
LABEL description="基于TEMURIN JDK11、常用Linux命令、上海时区"

ARG WORK_DIR=/anan
ARG LOGS_DIR=${WORK_DIR}/logs
ARG DEPENDENCY_DIR=${WORK_DIR}/dependency
ARG CMD_DIR=${WORK_DIR}/cmd
ARG JAR_NAME=app.jar
ARG HEAP_DUMP_DIR=${LOGS_DIR}
ARG GC_LOGS_PATH=${LOGS_DIR}/gclog.log
ARG ERROR_FILE_PATH=$LOGS_DIR/hs_err_pid%p.log
ARG JAVA_OPTS="-XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:MaxRAMPercentage=70 -XX:InitialRAMPercentage=40 -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=200m"
ARG JAVA_OPTS_LOGS="-Xlog:gc*:file=$GC_LOGS_PATH:time,level,tags:filecount=5,filesize=10M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${HEAP_DUMP_DIR} -XX:ErrorFile=$ERROR_FILE_PATH"

ENV WORK_DIR ${WORK_DIR}
ENV LOGS_DIR ${LOGS_DIR}
ENV DEPENDENCY_DIR ${DEPENDENCY_DIR}
ENV CMD_DIR ${CMD_DIR}
ENV JAR_NAME ${JAR_NAME}
ENV HEAP_DUMP_DIR ${HEAP_DUMP_DIR}
ENV GC_LOGS_PATH ${GC_LOGS_PATH}
ENV ERROR_FILE_PATH ${ERROR_FILE_PATH}
ENV JAVA_OPTS ${JAVA_OPTS}
ENV JAVA_OPTS_LOGS ${JAVA_OPTS_LOGS}

WORKDIR ${WORK_DIR}
COPY wait-for.sh ${CMD_DIR}/wait-for.sh
COPY sources.list /etc/apt/

VOLUME ["${LOGS_DIR}"]

RUN chmod +x ${CMD_DIR}/wait-for.sh \
    && echo "Asia/Shanghai" > /etc/timezone \
    && /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime -f \
    && apt-get update \
    && set -eux \
    && apt-get -y install netcat net-tools iputils-ping telnet \
    && rm -rf /var/lib/apt/lists/*

ENTRYPOINT ["cmd/entrypoint.sh"]