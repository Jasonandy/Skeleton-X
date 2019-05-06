#!/bin/sh

set -e

##########获取当前目录#############
dir=`cd $(dirname $0) && pwd`

##########################安装deb包文件#####################
dpkg -i ${dir}/skeleton-xxl-job_1.1.0_amd64_ubuntu16.04.deb


echo " skeleton-xxl-job_install.sh had execute completed ... "
