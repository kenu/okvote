#!/bin/bash
GITHUB=https://github.com/kenu/okvote
REPOSITORY=~/git/okvote

## build jar
cd $REPOSITORY
git pull origin main
./mvnw clean package && echo "build.."

## kill process
CURRENT_PID=$(pgrep -f okvote)
if [ -z $CURRENT_PID ]
then
  echo ">>>> java process not found."
else
  echo ">>>> PID: $CURRENT_PID kill."
  kill -15 $CURRENT_PID
  sleep 15
fi

## start jar
JAR_NAME=$(ls $REPOSITORY/target | grep jar | head -n 1)
JAR_PATH=$REPOSITORY/target/$JAR_NAME
nohup java -jar $JAR_PATH > /dev/null 2> /dev/null < /dev/null &

sleep 10

# curl -X POST -H 'Content-type: application/json' \
# --data '{"text":"okvote Deployment Finished!\nhttps://v.okdevtv.com/"}' \
# https://hooks.slack.com/services/x/x/xxx