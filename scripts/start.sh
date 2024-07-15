#!/bin/bash

REPOSITORY="/home/ubuntu/spring-github-action"
cd $REPOSITORY

APP_NAME=gasip
JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep '.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/$JAR_NAME

START_LOG="$REPOSITORY/start.log"
ERROR_LOG="$REPOSITORY/error.log"
APP_LOG="$REPOSITORY/application.log"

SERVICE_PID=$(pgrep -f $JAR) # 실행중인 Spring 서버의 PID
NOW=$(date +%c)


echo "[$NOW] > $JAR 실행" >> $START_LOG
nohup java -jar $JAR_PATH > $APP_LOG 2> $ERROR_LOG &

echo "[$NOW] > 서비스 PID: $SERVICE_PID" >> $START_LOG

#echo "> $JAR_PATH 배포"
#nohup java -jar $JAR_PATH > /dev/null 2> /dev/null < /dev/null &