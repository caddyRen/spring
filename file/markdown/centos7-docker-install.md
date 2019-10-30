#online
- 查看系统内核版本,需要3.10+
```
 sudo uname -a
```
- 安装依赖包
```
yum install -y yum-utils device-mapper-persistent-data lvm2
```
- 设置yum源
```
yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
```
- 查看docker版本
```
yum list docker-ce --showduplicates | sort -r
```
- 安装
```
yum install docker-ce-17.12.1.ce
```
- 启动
```
systemctl start doker
```
- 检查
```
   docker info
   docker -v
   docker images
   docker ps
```
#offline===>rpm
- 首先用联网的linux下载rpm安装包以及相关依赖包
```
yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
yum-config-manager --add-repo https://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
yum install yum-utils --downloadonly --downloaddir=/root/dockerrpm
yum install device-mapper-persistent-data --downloadonly --downloaddir=/root/dockerrpm/
yum install lvm2 --downloadonly --downloaddir=/root/dockerrpm/
yum install policycoreutils-python --downloadonly --downloaddir=/root/dockerrpm/
yum install docker-ce --downloadonly --downloaddir=/root/dockerrpm/
yum install container-selinux --downloadonly --downloaddir=/root/dockerrpm/
yum install docker-ce-18.06.1.ce --downloadonly --downloaddir=/root/dockerrpm/
```
- 或者
```
yumdownloader yum-utils --downloaddir=/root/dockerrpm
```
- 安装依赖rpm:--nodeps --force表示不检查是否依赖直接安装
```
rpm -ivh *.rpm --nodeps --force
```
- 安装docker rpm：
```
rpm -ivh docer-ce-18.09.7-3.el7.x86_64.rpm
```
- 升级docker rpm
```
rpm -Uvh docer-ce-18.09.7-3.el7.x86_64.rpm
```
- 启动
```
systemctl start doker
```
- 检查
```
   docker info
   docker -v
   docker images
   docker ps
```
- 卸载 -e ,不含依赖--nodeps
```
rpm -e docer-ce-18.09.7
```
- 配置国内镜像加速(阿里云开发者平台可以获取)/etc/docker/daemon.json
```json
{
 "registry-mirrors":["https://rjm3pmfv.mirror.aliyuncs.com"]
}
```
- 重启docker
```
systemctl restart doker
```


