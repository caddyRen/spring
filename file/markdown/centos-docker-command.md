#docker常用命令
- pull image
```
docker pull java:8-alpine
```
- look images
```
docker images
docker images | grep java
```
- run container
```
docker run -d --name nextcloud -p 80:80 -v /root/nextcloud:/var/www/html nextCloudId
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=toor -v /home/mysql/conf/my.cnf:/etc/mysql/my.cnf -v /home/mysql/logs:/logs -v /home/mysql/data/mysql:/var/lib/mysql mysqlImagesId
```
- go to container exit container will not stop
```
docker exec -it containerID /bin/bash
docker exec -it containerID mysql -uroot -ptoor
```
- go to container exit container will stop
```
docker attach containerID
```
- out container
```
exit 退出容器伪终端并关闭容器
ctrl+d 退出容器伪终端并关闭容器
ctrl+c 退出容器伪终端不关闭容器
ctrl+p +  ctrl+q 退出容器伪终端不关闭容器
```
- build image
  
  - java8-base image Dockerfile,build.sh在同一目录下
    - Dockerfile
      ```
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
      ```
      #!/usr/bin/env bash
      docker build -t java8-base:1.0 .
      ```
  - jar images Dockerfile,jar,define.sh,build.sh,package.sh,start.sh,showlog.sh,shutdown.sh在同一目录下
    - Dockerfile
      ```
      FROM java8-base:1.0
      
      ENV WORK_DIR /jgpush-service
      
      WORKDIR $WORK_DIR
      
      COPY jgpush-service.jar $WORK_DIR/
      COPY start.sh $WORK_DIR/
      
      RUN chmod a+x $WORK_DIR/start.sh
      
      ENTRYPOINT ["sh","start.sh"]
     
      ```
    - define.sh
      ```
      #!/bin/bash
      
      service_name="jgpush-service"
      version="1.0.0-release-build-20190711"
  
      ```
    - build.sh
      ```
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
      ```
      source ./define.sh
      
      echo -e "\n==> begin to package your image to tar file"
      docker save > $service_name-$version.tar $service_name:$version
      ```
    - start.sh
      ```
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
      ```
      source ./define.sh
      
      docker logs -f -t --tail 500 $(docker ps | grep $service_name | awk '{print $1}')
  
      ```
    - shutdown.sh
      ```
      # 引用定义文件
      source ./define.sh
      
      # 关停所有已启动的容器
      echo -e "\n==> begin stop all containers of " $service_name
      docker stop $(docker ps -a | grep $service_name | awk '{print $1}')
   
      ```  
- save image
```
docker save imageID | gzip > filename.tar.gz
docker save imageID | bzip2 | ssh root@192.168.43.230 cat | docker load
docker load -i filename.tar.gz
```
- look container
```
docker ps -a
docker ps | grep mysql
```
- stop container
```
docker stop containerID
```
- change tag image
```
docker tag hello hello:1.0 
```
- remove image
```
docker rm -f containerID
docker rmi -f imageID
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
- pull image
```

```

