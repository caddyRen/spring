WORKDIR=../docker

cd $WORKDIR
source ./define.sh

docker exec -it $(docker container ls | grep $service_name | awk '{print $1}') /bin/bash
