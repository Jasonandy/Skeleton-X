#!/bin/sh

dir=`cd $(dirname $0) && pwd`
echo $dir

#===============================================
echo "Uninstall skeleton-xxl-job Starting ... "
sh $dir/xxl-job_deb/skeleton-xxl-job_uninstall.sh
#===============================================
echo "Uninstall skeleton-jdk2xxlJob success !"
sh $dir/jdk_deb/skeleton-jdk2xxljob_uninstall.sh
#===============================================

echo " run_uninstall.sh had execute completed ... "

