#开发环境注意事项
```text
windows idea2019.2下运行public static void main()时
默认会将该main()方法当成gradle的Task，运行会报错，需要做如下配置
archlinux idea 2020版本下，未出现此问题
```
###windows idea2019
- idea配置 Build and run using为Intellij IDEA（默认是Gradle）
- idea配置 Run test using为Intellij IDEA（默认是Gradle）
###archlinux idea2020
- idea配置 Run test using为Intellij IDEA（默认是Gradle）

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

#maven基础依赖
##已完善
- swagger
- spring-jetty
- spring-tomcat
- spring-undertow
##待完善
- springboot 
    - 1.5.X 
    - 2.0.X
- 拆分模块
    - 微服务基础
        - 单元测试
        - 基础样例
        - 消息相关
        - nosql相关
        - 安全相关模块
    - springclould组件们
    

