#TDD
- SpringBoot提供了spring-boot-start-test启动器，该启动器提供了常见的单元测试库
    1. JUnit — The de-facto standard for unit testing Java applications.一个Java语言的单元测试框架
    1. Spring Test & Spring Boot Test — Utilities and integration test support for Spring Boot applications. 为Spring Boot应用提供集成测试和工具支持
    1. AssertJ — A fluent assertion library.支持流式断言的Java测试框架
    1. Hamcrest — A library of matcher objects (also known as constraints or predicates).一个匹配器库
    1. Mockito — A Java mocking framework.一个java mock框架
    1. JSONassert — An assertion library for JSON.一个针对JSON的断言库
    1. JsonPath — XPath for JSON.JSON XPath库
- By default, Spring Boot uses Mockito 1.x. However it’s also possible to use 2.x if you wish
#隔离测试 层层隔离
- @MockBean
- @TestConfiguration
    ```text
      测试Service层：test下indi.ikun.spring.demospringboot.api.service.DemoServiceTests
      1. @TestConfiguration放到ServiceImpl类上，单元测试不会扫描装载这个ServiceImpl
      在测试类上加上@Import(ServiceImpl.class),显示声明导入该类，单元测试才能扫描到这个类，才可以使用@Autowired
      2. 不使用@TestConfiguration，单元测试不用@SpringBootTest，直接@Import(ServiceImpl.class)，就可以使用@Autowired
    ```
- @DataJpaTest
- @WebMvcTest
```text
层与层之间可以隔离测试
如果上层测试需要用到下层的依赖，就使用mock的方式构造一个依赖
比如测试DAO层可以使用@DataJpaTest注解；
测试controller层可以使用@WebMvcTest；
测试Service层可以使用@TestConfiguration把需要用到Bean依赖进来
```