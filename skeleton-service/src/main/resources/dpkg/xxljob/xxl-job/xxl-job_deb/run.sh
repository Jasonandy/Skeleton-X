#!/bin/sh

version=1.1.0

module_name="xxl-job"

out_deb="skeleton-${module_name}_${version}_amd64_ubuntu16.04.deb"

echo -e "#!/bin/sh\n\ndir=\`cd \$(dirname \$0) && pwd\`\n\ndpkg -i \${dir}/${out_deb}" > skeleton-${module_name}_install.sh
echo "dpkg --purge skeleton-${module_name}" > skeleton-${module_name}_uninstall.sh

sed -i "s/Version:.*/Version: ${version}/" ${module_name}/DEBIAN/control

dpkg-deb -b ${module_name} ${out_deb}
