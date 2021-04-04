#引入方式

1. 引入本模块依赖
    ```groovy
    dependencies {   
        implementation(project(":redis-depency"))
    }
    ```
1. 在引入的模块application.yml内加入如下配置
    ```yaml
    spring:
      profiles:
         active: redis
    ```
## note
- 在 Spring Boot 1.x 版本默认使用的是 jedis ，而在 Spring Boot 2.x 版本默认使用的就是Lettuce
```txt
1. Jedis在实现上是直接连接的redis server，
   如果要保证在多线程环境下是非线程安全的，只有使用连接池，为每个Jedis实例增加物理连接
2. Lettuce的连接是基于Netty的，
   连接实例（StatefulRedisConnection）可以在多个线程间并发访问，
   因为StatefulRedisConnection是线程安全的，
   所以一个连接实例（StatefulRedisConnection）就可以满足多线程环境下的并发访问，
   当然这个也是可伸缩的设计，一个连接实例不够的情况也可以按需增加连接实例。
```
- RedisTemplate & StringRedisTemplate
   - [参考](https://blog.csdn.net/wo541075754/article/details/104798669/)