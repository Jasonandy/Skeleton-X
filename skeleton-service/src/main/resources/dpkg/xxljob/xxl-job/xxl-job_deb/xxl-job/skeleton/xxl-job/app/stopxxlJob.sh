#!/bin/sh

ps aux | grep -P "xxl-job.jar" |grep -v "grep" | awk '{print "kill -9 "$2}' | sh
