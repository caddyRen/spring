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