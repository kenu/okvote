#!/bin/bash
GITHUB=https://github.com/kenu/okvote
REPOSITORY=~/git/okvote

## build jar
cd $REPOSITORY
git pull origin main
./mvnw clean package && echo "build.."

JAR_NAME=$(ls $REPOSITORY/target | grep jar | head -n 1)
JAR_PATH=$REPOSITORY/target/$JAR_NAME
nohup java -jar $JAR_PATH > /dev/null 2> /dev/null < /dev/null &
