WORKDIR=../docker

cd $WORKDIR
source ./define.sh

docker logs -f -t --tail 500 $(docker ps | grep $service_name | awk '{print $1}')
