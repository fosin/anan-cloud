FROM registry.cn-hongkong.aliyuncs.com/fosin/eclipse-temurin:11-hotspot

LABEL maintainer=fosin
LABEL email=28860823@qq.com

ARG SERVER_PORT=6170

EXPOSE ${SERVER_PORT}

# 固定写法请勿随意修改，变量和路径来自底座镜像的设计
COPY target/dependency ${DEPENDENCY_DIR}
COPY target/*.jar ${JAR_NAME}

# 1、下载相关工具，可以按需添加
# ex：RUN yum -y install telnet

# 2、在镜像运行生产环境定义JVM内存参数JAVA_OPTS，否则默认内存512m（生产必设）
# ex：export JAVA_OPTS="-Xms2g -Xmx2g -XX:MetaspaceSize=180m -XX:MaxMetaspaceSize=256m"
# ex：export JAVA_OPTS_LOGS="-Xlog:gc*:file=$LOGS_DIR/gclog.log:time,level,tags:filecount=5,filesize=10M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${HEAP_DUMP_DIR} -XX:ErrorFile=$LOGS_DIR/hs_err_pid%p.log"

# 3、自定义工作目录，修改环境变量WORK_DIR（可选）

# 4、自定义JAR名称，修改环境变量JAR_NAME（可选）