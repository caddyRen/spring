package indi.ikun.spring.demospringboot;

import indi.ikun.spring.demospringboot.mybatis.dao.SysAppMapper;
import indi.ikun.spring.demospringboot.mybatis.po.SysApp;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @TODO //启动类
 * @Author:renqiankun
 * @Date:2020-01-08 10:55
 **/
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
public class DemoSpringbootApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SysAppMapper sysAppMapper;

    @Autowired
    StringEncryptor stringEncryptor;

    @Test
    public void exampleTest() {
        String body = this.restTemplate.getForObject("/index2", String.class);
        assertThat(body).isEqualTo("Hello World");
    }
    @Test
    public void testExample(){
        SysApp user = this.sysAppMapper.getByAppId2("xcgk");
        System.err.println(user);
        assertThat(user.getAppName()).isEqualTo("广州地区现场可视化应用主站");
        assertThat(user.getAppShortName()).isEqualTo("主站");
    }
    @Test
    public void contextLoads() {
        System.err.println(stringEncryptor.decrypt("r1h11lrnWcgCxEnCcfyOtZ4mzrIjkX/hI27+6kFqTBM="));
    }
}
