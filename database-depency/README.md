#引入方式

1. 引入本模块依赖
    ```groovy
    dependencies {   
        implementation(project(":database-depency"))
    }
    ```
1. 在引入的模块application.yml内加入如下配置
    ```yaml
    spring:
      profiles:
         active: database
    ```