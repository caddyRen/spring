#Spring Config
- https://docs.spring.io/spring-cloud-config/docs/2.2.7.RELEASE/reference/html/#_git_refresh_rate
- 使用eureka 需要在bootstrap.yml服务注册发现 config server，才能使用配置
- 使用方式 参考 application.yml和bootstrap.yml（rabbitmq主要用于自动更新配置）
- server端自动更新,client端post接口http://localhost:9996/refresh以刷新配置
```shell
curl --location --request POST 'http://blackbox:10001/admin/refresh'

curl -H 'Content-Type:application/json' -X POST 'http://blackbox:10001/admin/refresh'
curl -H 'Content-Type:application/json' -X POST -d '{"username":"caddy","password":"123"}'  http://blackbox:10001/admin/refresh
```
- 引入配置
```groovy
    api("org.springframework.cloud:spring-cloud-starter-config")
```
