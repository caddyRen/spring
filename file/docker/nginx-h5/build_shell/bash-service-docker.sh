WORKDIR=../docker

cd $WORKDIR
source ./define.sh

# alpine基础镜像使用
docker exec -it $(docker container ls | grep $service_name | awk '{print $1}') /bin/sh