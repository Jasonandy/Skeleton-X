#!/bin/sh

dir=`cd $(dirname $0) && pwd`

echo $dir

#========================================
echo "Uninstall skeleton-xxljob ..."
sh $dir/xxl-job/xxl-job_deb/skeleton-xxl-job_uninstall.sh

#========================================
echo "Uninstall skeleton-xxljob OK!"

#========================================
echo "Uninstall skeleton-jdk2xxljob ..."
sh $dir/xxl-job/jdk_deb/skeleton-jdk2xxljob_uninstall.sh

#========================================
echo "Uninstall skeleton-jdk2xxljob OK!"


#========================================

#========================================
if [ -f "/skeleton/xxlJobCheck.sh" ]
then
        rm -f /skeleton/xxlJobCheck.sh
fi
	
#========================================
if [ -f "/skeleton/run_uninstall_xxljob.sh" ]
then
        rm -f /skeleton/run_uninstall_xxljob.sh

        cnt=`ls /u2s/|wc -l`
        if [ ${cnt} -eq 0 ]
        then
               rm -rf /u2s
        fi
fi

#========================================
echo "Uninstall skeleton xxl-job OK!"
