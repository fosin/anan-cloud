#!/bin/bash
set -x
java -Djava.security.egd=file:/dev/./urandom -jar /app.jar
