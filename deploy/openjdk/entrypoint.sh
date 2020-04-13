#!/bin/bash
set -x
java -Dloader.path=/anan/dependency -Djava.security.egd=file:/dev/./urandom -jar /anan/app.jar
