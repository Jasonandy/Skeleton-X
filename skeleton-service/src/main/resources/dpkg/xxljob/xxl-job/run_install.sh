#!/bin/sh

dir=`cd $(dirname $0) && pwd`
echo $dir
#========================================
echo "Install skeleton-jdk2xxljob Starting ... "
sh $dir/jdk_deb/skeleton-jdk2xxljob_install.sh
#========================================
echo "Install skeleton-jdk2xxljob success !"
#========================================
echo "Install skeleton-xxljob Starting ..."
sh $dir/xxl-job_deb/skeleton-xxl-job_install.sh
#========================================
echo "Install skeleton-xxljob success !"
#========================================

echo " run_install.sh had execute completed ... "
