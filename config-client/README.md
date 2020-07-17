#Spring Config
- 使用方式 参考 application.yml和bootstrap.yml（rabbitmq主要用于自动更新配置）
- 当server端更新后,需要调用client端post接口http://localhost:9996/refresh以刷新配置
- 引入配置
```groovy
    api("org.springframework.cloud:spring-cloud-starter-config")
```
