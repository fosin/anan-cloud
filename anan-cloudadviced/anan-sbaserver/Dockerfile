FROM registry.cn-hongkong.aliyuncs.com/fosin/eclipse-temurin:17-hotspot

LABEL maintainer=fosin
LABEL email=28860823@qq.com

ARG SERVER_PORT=6170

EXPOSE ${SERVER_PORT}

# 固定写法请勿随意修改，变量和路径来自底座镜像的设计
COPY target/dependency ${DEPENDENCY_DIR}
COPY target/*.jar ${APP_NAME}

# 1、下载相关工具，可以按需添加
# ex：RUN yum -y install telnet

# 2、在镜像运行生产环境定义JVM内存参数JAVA_OPTS，默认是堆内存最大值=容器内存的60%，生产环境可以根据实际情况调整
# ex：export JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=50 -XX:InitialRAMPercentage=30 -XX:MinRAMPercentage=30 -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=200m"
# ex：export JAVA_OPTS_LOGS="-Xlog:gc*:file=$LOGS_DIR/gclog.log:time,level,tags:filecount=5,filesize=10M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${HEAP_DUMP_PATH} -XX:ErrorFile=$LOGS_DIR/hs_err_pid%p.log"

# 3、需要将容器日志目录持久化到外部，挂载容器目录为：/anan/logs