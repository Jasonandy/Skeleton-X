#!/bin/sh

###### 获取当前的目录 ######
basepath=`cd $(dirname $0) && pwd`

###################################### shell nohup 启动jar包服务 ###########################################
nohup /skeleton/xxl-job/jdk1.8.0_151/bin/java -jar  $basepath/xxl-job.jar > $basepath/nohup.out.txt  2>&1 &
