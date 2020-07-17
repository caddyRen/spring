#开发环境注意事项
```text
windows idea2019.2下运行public static void main()时
默认会将该main()方法当成gradle的Task，运行会报错，需要做如下配置
archlinux idea 2020版本下，未出现此问题
```
###windows idea2019
- idea配置 Build and run using为Intellij IDEA（默认是Gradle）
- idea配置 Run test using为Intellij IDEA（默认是Gradle）
###windows idea2020.1
- idea配置 Run test using为Intellij IDEA（默认是Gradle）
###archlinux idea2020.1
- idea配置 Run test using为Intellij IDEA（默认是Gradle）

#注意事项
- application.yml 和 bootstrap.yml
    1. 当使用Spring Cloud Config Server 配置中心时，这时需要在 bootstrap.yml 配置文件中指定 spring.application.name 和spring.cloud.config.server.git.uri添加连接到配置中心的配置属性来加载外部配置中心的配置信息
    1. bootstrap.yml 用来程序引导时执行，应用于更加早期配置信息读取。可以理解成系统级别的一些参数配置，这些参数一般是不会变动的。一旦bootStrap.yml 被加载，则内容不会被覆盖
    1. application.yml 可以用来定义应用级别的， 应用程序特有配置信息，可以用来配置后续各个模块中需使用的公共参数
    ```text
    启动上下文时，Spring Cloud 会创建一个 Bootstrap Context，作为 Spring 应用的 Application Context 的父上下文。
    初始化的时候，Bootstrap Context 负责从外部源加载配置属性并解析配置。这两个上下文共享一个从外部获取的 Environment。Bootstrap 属性有高优先级，默认情况下，它们不会被本地配置覆盖。
    也就是说如果加载的 application.yml 的内容标签与 bootstrap 的标签一致，application 也不会覆盖 bootstrap，而 application.yml 里面的内容可以动态替换
   ``` 
#已完善模块
1. spring-swagger
1. database-depency
1. spring-tomcat
1. spring-undertow
1. spring-jetty

#springboot样例
1. demo-springboot

#依赖中间件
1. RabbitMQ docker 5672 guest/guest
1. Mysql    docker 3306 root/toor
1. gitLib   docker 10101 caddyRen/caddyRen

#spring cloud
1. eureka-server 双注册中心 8761，8762
1. config-server 单点配置中心 10201
1. zipkin-server 单点zipkinserver 10202
1. zuul          单点网关 10203


#spring cloud
#spring boot 微服务入门级微架构
#spring cloud eureka 服务治理
#spring cloud Config 集中化管理集群配置，支持本地存储，git，Subversion，企业级配置中心
#spring cloud Bus 事件消息总线，用于再集群中传播状态变化，与Config联合实现热部署
#spring cloud Sleuth 日志收集工具包，封装Dapper和log-based追踪以及Zipkin和HTrace操作，分布式追踪解决方案
#spring cloud Stream Spring 数据流操作开发包，封装了Redis，Rabbit Kafka等发送接收消息
#spring cloud OAuth2 基于Spring Security 和OAuth2的安全工具包，提供安全控制
##spring cloud Zuul网关，不维护了，所有的客户端请求通过网关访问后台服务，可以使用一定的路由配置来判断某一个URL由哪个服务来处理，并从Eureka获取注册的服务来转发请求
#spring cloud Ribbon 负载均衡
#spring cloud Feign 服务客户端，服务间访问
#spring cloud Hystrix 监控和断路器，
#spring cloud Hystrix Dashboard 监控面板，监控服务调用所消耗的时间
#spring cloud Turbine 监控聚合，使用Hystrix监控，需要打开每一个服务实例的监控信息来查看，Turbine可以将所有服务实例监控信息聚合到一个地方统一查看
#spring cloud Archaius 配置管理api 提供动态类型化属性，线程安全配置操作，轮询框架，回调机制。实现动态获取配置
#docker

#关于
- eureka server port：8761，8762
- config server port：9999


#基础服务
- eureka 注册中心
- configServer 配置中心
- rabbitmq 消息队列 搭配配置中心
- 网关
    

