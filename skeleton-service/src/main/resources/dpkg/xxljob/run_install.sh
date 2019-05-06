#!/bin/sh

dir=`cd $(dirname $0) && pwd`

echo $dir

#========================================
echo "Install skeleton-jdk2xxljob Starting ... "
sh $dir/xxl-job/jdk_deb/skeleton-jdk2xxljob_install.sh
echo "Install skeleton-jdk2xxljob success !"
#========================================

#========================================
echo "Install skeleton-xxljob Starting ... "
sh $dir/xxl-job/xxl-job_deb/skeleton-xxl-job_install.sh
echo "Install skeleton-xxljob success !"
#========================================

#==安装完毕后将Uninstall脚本cp到安装目录=====
echo " 安装完毕后将Uninstall脚本cp到安装目录... "
cp ${dir}/run_uninstall_xxljob.sh /skeleton/run_uninstall_xxljob.sh
chmod 755 /skeleton/run_uninstall_xxljob.sh


#====安装完毕后将Check脚本cp到安装目录======
echo " 安装完毕后将Check脚本cp到安装目录... "
cp ${dir}/xxlJobCheck.sh /skeleton/xxlJobCheck.sh
chmod 755 /skeleton/xxlJobCheck.sh


echo "*********run install end*************"