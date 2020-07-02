package indi.ikun.spring.demospringboot.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//随机一个未占用的端口启动测试
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//以application设置的端口启动测试，如果没设置则会用8080端口
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//加载applicationContext正在使用的SpringApplication但是不提供任何servlet环境
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//加载WebApplicationContext并提供一个模拟servlet环境,使用此注释,不会启动嵌入式Servlcet容器,
// 如果servletAPI不在类路径中则此模式将透明地回退到创建常规非Web的ApplicationContext
//可以结合@AutoConfigureMockMvc使用MockMvc为应用程序测试
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Slf4j
public class DemoControllerTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void exampleTest() {
        String body = this.restTemplate.getForObject("/index2", String.class);
        assertThat(body).isEqualTo("Hello World");
    }
}
