# eureka-client 配置
# management 配置
# log 配置
## usage
1. build.gradle
```groovy
implementation(project(":eureka-client"))
```
2. application.yml
```yaml
spring:
  application:
    name: eureka
  profiles:
    active: ${PROFILES_ACTIVE:dev},ec
    
# 服务端口
server:
  port: ${PORT:8760}

#eureka
eureka:
  client: #eureka客户端配置
    healthcheck:
      enabled: true #开启健康检查 eureka.client.healthcheck.enabled=true should only be set in application.yml. Setting the value in bootstrap.yml causes undesirable side effects, such as registering in Eureka with an UNKNOWN status
# eureka配置，
HOSTNAME: blackbox # 本服务实例域名
PORT: 8763 # 本服务实例端口
GZ_IP: 127.0.0.1 # eureka 注册中心ip
GZ_PORT: 8761 # eureka 注册中心端口
SZ_IP: 127.0.0.1 # eureka 注册中心ip
SZ_PORT: 8762 # eureka 注册中心端口
REGISTER-WITH-EUREKA: true #实例是否在eureka服务器上注册自己的信息以供其他服务发现，默认为true 注册中心可设为false
FETCH_REGISTRY: true #实例是否在eureka服务器上注册自己的信息以供其他服务发现，默认为true 注册中心可设为false
# 管理接口 配置
MPATH: /admin # management.endpoints.web.base-path
VERSION: 0.0.1 # 本服务版本号
APPDESC: 注册中心 # 本服务描述信息
# 日志
log:
  level: INFO
# enc() 加密配置文件
KEY: bougainvillea
```