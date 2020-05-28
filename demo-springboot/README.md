## 部署
- 先使用PACKAGE.md内maven命令制作jar包
- 然后利用Dockerfile制作dockerImage
- 利用docker-compose.yml脚本本地启动测试
- 说明
```text
 docker测试环境为
 win10专业版、virtualBox、CentOS7、docker和docker-compose版本可以使用最新版
```
## docker环境相关
- docker开启远程
```shell script
vi /lib/systemd/system/docker.service
```
- 修改配置
```text
[Service]
ExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:2375 -H unix:///var/run/docker.sock
```
- 重启docker
```shell script
systemctl daemon-reload
systemctl restart docker
```
- 检测(需要安装curl)
```shell script
curl http://localhost:2375/version
```
- 开放远程端口
```shell script
firewall-cmd --zone=public --add-port=2375/tcp --permanent
```
- idea配置 
```text
Run configuration 新增一个Dockerfile配置
可自定义Name
Server配置，使用TCP socket，Engine API URL:tcp://localhost:2375
Dockerfile配置选择Dockerfile文件
Image tag：demo-springboot:1.0.0-SNAPSHOT
```
- 配置后直接点击运行即可制作镜像，注意当镜像tag一样时，旧的镜像名和tag会变成none，
- 将docker-compose.yml上传到/root/ppm/下,在centos内执行docker-compose命令管理容器
```shell script
docker-compose -f  /root/ppm/docker-compose.yml up -d
docker-compose -f  /root/ppm/docker-compose.yml ps
docker-compose -f  /root/ppm/docker-compose.yml logs -f
docker-compose -f  /root/ppm/docker-compose.yml stop
docker-compose -f  /root/ppm/docker-compose.yml start
```