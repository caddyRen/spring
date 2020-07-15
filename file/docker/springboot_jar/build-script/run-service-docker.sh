WORKDIR=../docker

cd $WORKDIR
source ./define.sh

echo -e "\n==> begin to create a container of " $service_name
docker run -d -p $port:$port \
  --name $service_name \
  --privileged=true \
  $service_name:$version
