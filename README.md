# 开发环境注意事项
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

#敏捷开发
1. 最小可行性产品
    ```text
    Minimum Viable Product(MVP)
    通过快速构建一个最小可行性产品，然后通过快速的产品功能迭代，让产品尽可能早日上线，面向用户，并实现其商业价值
    重构是常态，敏捷开发要求开发者每次的改进(添加、优化、修改bug)都不能太大。对于大的改进可能需要多个迭代周期，
    ```
2. 测试驱动开发
    ```text
    Test Driven Development(TDD)
    编写业务逻辑之前，先编写单元测试，首先会让测试失败，然后再通过实现具体的代码让测试通过。
    测试驱动开发可以很好地驱使开发者对业务需求的深层次思考，及早发现伪需求，从而保障系统功能的正确性
    此外测试驱动开发的一个最大优势就是在保障不改变功能的情况下可以持续地对代码进行改进和重构，
    可以让开发者既能够快速开发又能够保障系统功能的稳定性
    ```
#RESTful API
1. 原则
    1. 以资源为中心进行URL设计
    ```text
    资源是RESTful API的核心，所有操作都是针对某一特定资源进行
    简洁、清晰、结构化的URL(统一资源定位符)设计至关重要
    /users
    /users/{id}
    /users/{loginName}
    /products/{id}/comments
    根据RFC3986标准中规定，URL是大小写敏感的，SpringMVC默认对URL区分大小写，避免歧义定义URL时最好都使用小写字母
    RESTful API设计最好做到Hypermedia化，也就是在返回结果中包含所提供相关资源的链接使得用户可以根据返回结果就能得到后续操作需要访问的地址
    这种设计也被称为HATEOAS(Hypermedia As The Engine Of Application State)
    例如github api设计：https://api.github.com 获取api列表
       {
         "current_user_url": "https://api.github.com/user",
         "current_user_authorizations_html_url": "https://github.com/settings/connections/applications{/client_id}",
           ......
       }
    ```
    2. 正确使用HTTP方法及状态码
        1. 2xx:请求正常处理并返回
        1. 3xx:重定向，请求资源位置发生变化
        1. 4xx:客户端发送的请求有错误
        1. 5xx:服务器端错误
    3. 查询及分页处理原则
    ```text
    例子：
    xxx?state=closed:查询指定状态
    xxx?limit=10:指定返回10条记录
    xxx?page=2&size=25&sort=created,desc：分页查询及排序方式
    spring Data的Pageable进行分页查询时，需要传入的分页参数的默认名如下
    page、size、sort(,ASC|DESC默认ASC)：sort=firstname&sort=lastname,asc
    如果分页参数和项目中不同，可以用过哦实现PageableHandlerMethodArgument ResolverCustomizer接口进行自定义
    ```
    4. 其他指导原则
        1. 使用JSON作为响应返回格式
        2. API域名，尽量将API部署在一个专用域名下
        3. API版本最好放到URL中 https://xxx/v1/xxx
        4. 给出明确的错误信息

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
    

