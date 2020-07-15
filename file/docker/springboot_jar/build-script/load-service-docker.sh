WORKDIR=../docker

cd $WORKDIR
source ./define.sh

echo -e "\n==> begin delete all container of " $service_name
docker rm $(docker stop $(docker ps -a | grep $service_name | awk '{print $1}'))

echo -e "\n==> begin delete all images of " $service_name
docker rmi -f $(docker images | grep $service_name | awk '{print $3}')

echo -e "\n==> begin load your images of " $service_name
docker load < $service_name-$version.tar
