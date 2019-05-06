#!/bin/sh

dir=`cd $(dirname $0) && pwd`
echo $dir
#========================================
echo "Install skeleton-jdk2xxljob ..."
sh $dir/jdk_deb/skeleton-jdk2xxljob_install.sh
#========================================
echo "Install skeleton-jdk2xxljob OK!"
#========================================
echo "Install skeleton-xxljob ..."
sh $dir/xxl-job_deb/skeleton-xxl-job_install.sh
#========================================
echo "Install skeleton-xxljob OK!"
#========================================
#========================================

