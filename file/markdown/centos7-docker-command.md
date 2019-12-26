[TOC]
# docker常用命令

- pull image
    ```shell script
    docker pull java:8-alpine
    ```
- look images
    ```shell script
    docker images
    docker images | grep java
    ```
- run container
  
  - rabbitmq
      ```shell script
      docker run -d -p 5672:5672 -p 15672:15672 --name demoRabbitmq 24cb552c7c00
      ```
  - redis
      ```shell script
      docker run -d -p 6379:6379 --name demoredis registry.docker-cn.com/library/redis
      ```
  - nextcloud
      ```shell script
      docker run -d --name nextcloud -p 80:80 -v /root/nextcloud:/var/www/html nextCloudId
      ```
  
  - mysql
      ```shell script
      docker run -d --name mysql5.7 -p 3306:3306 -e MYSQL_ROOT_PASSWORD=toor -v /home/mysql/conf/my.cnf:/etc/mysql/my.cnf -v /home/mysql/logs:/logs -v /home/mysql/data/mysql:/var/lib/mysql mysqlImagesId
      ```
  
  - oracle挂载目录赋权
      ```shell script
        chown 1000:1000 /root/oracle/oradata
      ```
  - 启动oracle
      ```shell script
        docker run --name oracle11g \
        --shm-size=1g \
        -p 1621:1521 -p 8081:8080 \
        -e ORACLE_PWD=xcjkxcjk \
        -e ORACLE_CHARACTERSET=AL32UTF8 \
        -v /root/oracle/oradata:/u01/app/oracle/oradata eb1f68dbe985
      ```
- go to container exit container will not stop

    ```shell script
    docker exec -it containerID /bin/bash
    docker exec -it containerID /bin/sh
    docker exec -it containerID mysql -uroot -ptoor
    docker exec -ti oracle11g sqlplus sys/xcjkxcjk@XE as sysdba
    docker exec -ti oracle11g sqlplus system/xcjkxcjk@XE
    ```
- go to container exit container will stop
    ```shell script
    docker attach containerID
    ```
- out container
    ```shell script
    exit 退出容器伪终端并关闭容器
    ctrl+d 退出容器伪终端并关闭容器
    ctrl+c 退出容器伪终端不关闭容器
    ctrl+p +  ctrl+q 退出容器伪终端不关闭容器
    ```
- build image
  
  - java8-base image Dockerfile,build.sh在同一目录下
    - Dockerfile
        ```shell script
          FROM java:8-alpine
          
          # 更新最新镜像源列表
          RUN apk update
          
          # 设置Docker 时间为上海时区
          RUN apk add -U tzdata && \
              cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
              echo "Asia/shanghai" >> /etc/timezone
          
          # 依次安装命令curl、scp
          RUN apk add curl && \
              apk add openssh-client
        ```
    - build.sh
      ```shell script
      #!/usr/bin/env bash
      docker build -t java8-base:1.0 .
      ```
  - jar images Dockerfile,jar,define.sh,build.sh,package.sh,start.sh,showlog.sh,shutdown.sh在同一目录下
    - Dockerfile
      ```shell script
      FROM java8-base:1.0
      
      ENV WORK_DIR /jgpush-service
      
      WORKDIR $WORK_DIR
      
      COPY jgpush-service.jar $WORK_DIR/
      COPY start.sh $WORK_DIR/
      
      RUN chmod a+x $WORK_DIR/start.sh
      
      ENTRYPOINT ["sh","start.sh"]
     
      ```
    - define.sh
      ```shell script
      #!/bin/bash
      
      service_name="jgpush-service"
      version="1.0.0-release-build-20190711"
    
      ```
    - build.sh
      ```shell script
      source ./define.sh
      
      # 先关停容器
      sh shutdown.sh
      
      # 删除历史所有镜像,注意包括所有历史版本
      echo -e "\n==> begin delete all images of " $service_name 
      docker rmi -f $(docker images | grep $service_name | awk '{print $3}')
      
      # 构建镜像
      echo -e "\n==> begin build your images of " $service_name
      docker build -t $service_name:$version .
    
      ```
    - package.sh
      ```shell script
      source ./define.sh
      
      echo -e "\n==> begin to package your image to tar file"
      docker save > $service_name-$version.tar $service_name:$version
      ```
    - start.sh
      ```shell script
      #!/bin/sh
      
      if [ ! $SPRING_PROFILES_ACTIVE ]; then
        if [ ! $RUN_CMD ]; then
          java -jar jgpush-service.jar
        else
          $RUN_CMD
        fi
      else
        if [ ! $RUN_CMD ]; then
          java -jar jgpush-service.jar --spring.profiles.active=$SPRING_PROFILES_ACTIVE
        else
          $RUN_CMD
        fi
      fi 
    
      ```
    - showlog.sh
      ```shell script
      source ./define.sh
      
      docker logs -f -t --tail 500 $(docker ps | grep $service_name | awk '{print $1}')
    
      ```
    - shutdown.sh
      ```shell script
      # 引用定义文件
      source ./define.sh
      
      # 关停所有已启动的容器
      echo -e "\n==> begin stop all containers of " $service_name
      docker stop $(docker ps -a | grep $service_name | awk '{print $1}')
      
      ```
- save image 尽量不要使用imageID导出，导入时候会出现没有name和tag问题
    ```shell script
    docker save > imageName-tag.tar imageName:tag
    docker save imageID | gzip > filename.tar.gz
    docker save imageID | bzip2 | ssh root@192.168.43.230 cat | docker load
    docker load -i filename.tar.gz
    ```
- look container
    ```shell script
    docker ps -a
    docker ps | grep mysql
    ```
- stop container
    ```shell script
    docker stop containerID
    ```
- change tag image
    ```shell script
    docker tag hello hello:1.0 
    ```
- remove image
    ```shell script
    docker rm -f containerID
    docker rmi -f imageID
    ```
-  zsh sh 切换 
```shell script
chsh -s /bin/zsh
chsh -s /bin/bash
```
- pull image
```

```
- pull image
```

```
- pull image
```

```
- pull image
```

```

