-e #!/bin/sh

dir=`cd $(dirname $0) && pwd`

dpkg -i ${dir}/skeleton-xxl-job_1.1.0_amd64_ubuntu16.04.deb
