[toc]
#docker-nginx
##run
1. 启动容器，挂载外部配置文件时，注意先创建nginx.conf，可以先跑一个不挂载的容器，进入查看相关文件目录
    ```
     docker run --name mynginx -d =p 8080:80 imageID
     docker run --name mynginx -d -p8080:80 \
           -v /root/nginx/html:/usr/share/nginx/html:ro \
           -v /root/nginx/conf/nginx.conf:/etc/nginx/nginx.conf:ro \
           -v /root/nginx/conf.d:/etc/nginx/conf.d:ro \
           -v /root/nginx/logs:/var/log/nginx \ 
           nginx:latest 
    ```
2. 进入nginx容器
    1. 使用alpine作为linux基础镜像
       ```
        docker container exec -it containerID /bin/sh
       ```
    2. 使用其它linux发行版作为基础镜像  
        ```
        docker container exec -it containerID /bin/bash
        ```
3. 复制镜像内文件到宿主机
    1. 复制nginx.conf 到宿主机/root/nginx/conf/nginx.conf
        ```
        docker cp containerID:/etc/nginx/nginx.conf /root/nginx/conf/nginx.conf 
       ```
