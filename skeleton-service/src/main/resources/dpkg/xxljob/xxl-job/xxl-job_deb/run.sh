#!/bin/sh

#########版本号#####################
version=1.1.0

###########模块名###################
module_name="xxl-job"

###########打包后的名字############################################
out_deb="skeleton-${module_name}_${version}_amd64_ubuntu16.04.deb"

### -e：激活转义字符
echo -e "#!/bin/sh\n\ndir=\`cd \$(dirname \$0) && pwd\`\n\ndpkg -i \${dir}/${out_deb}" > skeleton-${module_name}_install.sh

#卸载对应的数据 重新打包
echo "dpkg --purge skeleton-${module_name}" > skeleton-${module_name}_uninstall.sh

#### 将版本号输入至对应的DEBAIN文件###########################
sed -i "s/Version:.*/Version: ${version}/" ${module_name}/DEBIAN/control


##########打包出来#####################
dpkg-deb -b ${module_name} ${out_deb}

echo " run.sh had execute completed ... "
