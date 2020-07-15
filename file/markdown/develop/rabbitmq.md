#RabbitMQ消息中间件
##docker
- run container
  - Management Plugin rabbitmq default username and password of guest / guest
      ```shell script
      docker run -d -p 5672:5672 -p 15672:15672 --name containerName imageID
      docker run -d -p 5672:5672 -p 15672:15672 --name containerName imageName:version
      ```
  - go to container exit container will not stop
      ```shell script
      docker exec -it containerID /bin/bash
      docker exec -it containerID /bin/sh
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

## 手动脚本
1. exchange
```shell script
rabbitmqadmin -u username -p password declare exchange name=exchange type=topic
```
1. queue
```shell script
rabbitmqadmin -u username -p password declare queue name=queue  durable=true
```
1. binding
```shell script
rabbitmqadmin -u username -p password declare binding source=exchange destination=queue routing_key=key
```