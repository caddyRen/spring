WORKDIR=../docker

cd $WORKDIR
source ./define.sh

docker stop $(docker ps -a | grep $service_name | awk '{print $1}')
psid=$(docker ps -a | grep $service_name | awk '{print $1}')
if [[ -n $psid ]]; then
  docker rm $psid
fi
