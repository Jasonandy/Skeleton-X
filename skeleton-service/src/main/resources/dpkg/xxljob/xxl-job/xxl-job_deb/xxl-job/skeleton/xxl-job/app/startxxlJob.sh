#!/bin/sh

basepath=`cd $(dirname $0) && pwd`

#cnt=`ps aux | grep /u2s/master/MySQL/sbin/mysqld | grep -v "grep" | wc -l`
#if [ $cnt -eq 0 ]; then
#        service mysql restart
#fi

nohup /u2s/xxl-job/jdk1.8.0_151/bin/java -jar  $basepath/xxl-job.jar > $basepath/nohup.out.txt  2>&1 &
