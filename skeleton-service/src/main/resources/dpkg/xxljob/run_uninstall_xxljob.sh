#!/bin/sh

#######################卸载deb######################
uninstall_package()
{
	package_name=$1

	dpkg --status ${package_name} >/dev/null 2>&1

	if [ $? -eq 0 ]
	then
		dpkg --purge ${package_name}
	fi
}

#---------------- master ----------------------------
echo "------- Uninstall [xxljob] ---------"
uninstall_package "skeleton-xxljob"
uninstall_package "skeleton-jdk2xxljob"

#--------------------------------------------
if [ -f "/skeleton/xxlJobCheck.sh" ]
then
	rm -f /skeleton/xxlJobCheck.sh
fi
#--------------------------------------------


#--------------------------------------------
if [ -f "/skeleton/run_uninstall_xxljob.sh" ]
then
	rm -f /skeleton/run_uninstall_xxljob.sh

	cnt=`ls /skeleton/|wc -l`
	if [ ${cnt} -eq 0 ]
	then
		rm -rf /u2s
	fi
fi
#--------------------------------------------

#--------------------------------------------
echo "Uninstall skeleton xxljob OK!"

