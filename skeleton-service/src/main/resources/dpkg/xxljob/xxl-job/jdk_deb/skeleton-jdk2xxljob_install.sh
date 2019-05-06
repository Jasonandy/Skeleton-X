-e #!/bin/sh

dir=`cd $(dirname $0) && pwd`

dpkg -i ${dir}/skeleton-jdk2xxljob_1.8.0.151_amd64_ubuntu16.04.deb
