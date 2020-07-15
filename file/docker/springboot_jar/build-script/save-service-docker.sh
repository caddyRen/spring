WORKDIR=../docker

cd $WORKDIR
source ./define.sh

echo -e "\n==> begin to package your image to tar file"
docker save $service_name:$version > ../docker/$service_name-$version.tar
