#!/bin/sh
echo "build docker images ..."

./mvnw clean package -DskipTests -P 'docker-build' -U 