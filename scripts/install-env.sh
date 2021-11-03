#!/bin/bash
GITHUB=https://github.com/kenu/okvote
REPOSITORY=~/git/okvote

## install git, java
sudo amazon-linux-extras install -y java-openjdk11
sudo yum install -y git

## git clone
mkdir ~/git
cd ~/git
git clone $GITHUB

## build jar
cd $REPOSITORY
./mvnw package && echo "build.."

JAR_NAME=$(ls $REPOSITORY/target | grep jar | head -n 1)
JAR_PATH=$REPOSITORY/target/$JAR_NAME
nohup java -jar $JAR_PATH > /dev/null 2> /dev/null < /dev/null &
sleep 10

## install nginx
sudo amazon-linux-extras install -y nginx1.12
sudo systemctl enable nginx

## replace configuration
sudo mv /etc/nginx/nginx.conf /etc/nginx/nginx.conf.orig
sudo sh -c "sed '38,87d' /etc/nginx/nginx.conf.orig > /etc/nginx/nginx.conf"
cd /etc/nginx/conf.d
sudo curl -O https://raw.githubusercontent.com/kenu/okvote/main/scripts/okvote.conf
sleep 2
sudo systemctl start nginx