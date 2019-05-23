#服务客户端按区域注册，减少网络延迟造成的注册不成功
```yaml
eureka:
  client:
    region: guangdong #获取实例所在的地区。默认为us-east-1
    prefer-same-zone-eureka: true #实例是否使用同一zone里的eureka服务器，默认为true，理想状态下，eureka客户端与服务端是在同一zone下
    availability-zones: #指挥注册到可用区域的注册中心
      guangdong: gz,sz # 服务区域，会按照次处的顺序进行注册，先尝试注入gz，如果gz异常，则尝试注入sz，依次类推
    service-url:
      sz: http://mumu:66@127.0.0.1:8762/eureka/ #单机
      gz: http://mumu:66@127.0.0.1:8761/eureka/
```
- 测试过程，8761，8762两个注册中心，启动后只在8761注册
    - 当关掉gz，则会注入到sz-8762
    - 重启gz，依然注册在sz-8762
    - 关闭sz，重新注册进gz-8761