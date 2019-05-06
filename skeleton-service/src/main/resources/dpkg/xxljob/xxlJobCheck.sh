#!/bin/sh

# 包名 jar包 服务名
check()
{
	package_name=$1
	match_str=$2
	service_name=$3

	cnt=`ps aux | grep -P "${match_str}" | grep -v "\sgrep\s" | wc -l`
	if [ ${cnt} -eq 0 ]; then
		echo -e "\\033[41;30m[WARN]\\033[0m ${package_name} is not running, please use 'sudo service ${service_name} start'"
	else
		echo -e "\\033[42;30m[INFO]\\033[0m ${package_name} is running, you can use 'sudo service ${service_name} status' to check status."
	fi
}

#----------------------------------
check "skeleton-xxljob" "xxl-job.jar" "skeleton-xxljob"
