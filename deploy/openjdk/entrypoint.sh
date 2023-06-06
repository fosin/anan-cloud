#!/bin/bash
set -x
java $JAVA_OPTS $JAVA_OPTS_LOGS -Dloader.path=$DEPENDENCY_DIR -Djava.security.egd=file:/dev/./urandom -Dfile.encoding=UTF-8 -jar $JAR_NAME
