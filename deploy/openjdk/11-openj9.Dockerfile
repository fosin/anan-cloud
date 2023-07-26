ARG TEMURIN_JDK_VERSION=11-jdk-openj9
FROM eclipse-temurin:${TEMURIN_JDK_VERSION}

LABEL maintainer=fosin
LABEL email=28860823@qq.com
LABEL description="基于TEMURIN JDK11、常用Linux命令、上海时区"

ARG WORK_DIR=/anan
ARG LOGS_DIR=${WORK_DIR}/logs
ARG DEPENDENCY_DIR=${WORK_DIR}/dependency
ARG CMD_DIR=${WORK_DIR}/cmd
ARG APP_NAME=app.jar
ARG HEAP_DUMP_PATH=${LOGS_DIR}/dump_pid%p.hprof
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
COPY wait-for.sh ${CMD_DIR}/wait-for.sh
COPY sources.list /etc/apt/

RUN chmod +x ${CMD_DIR}/wait-for.sh \
    && echo "Asia/Shanghai" > /etc/timezone \
    && apt update \
    && set -eux \
    && apt -y install netcat \
    && apt -y install net-tools \
    && apt -y install iputils-ping \
    && apt -y install telnet \
    && rm -rf /var/lib/apt/lists/* \

ENTRYPOINT ["cmd/entrypoint.sh"]