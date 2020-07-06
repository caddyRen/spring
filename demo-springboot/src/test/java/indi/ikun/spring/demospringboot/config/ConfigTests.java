package indi.ikun.spring.demospringboot.config;

import indi.ikun.spring.swagger.config.SwaggerConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 由于测试上下文框架缓存上下文，
 * 因此默认情况下禁用JMX以防止相同组件在同一域上注册。
 * 如果此类测试需要访问 MBeanServer，请考虑将其标记为脏
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.jmx.enable=true")
@DirtiesContext
public class ConfigTests {
    @Autowired
    SwaggerConfig swaggerConfig;

    @Test
    public void test(){
        System.err.println(swaggerConfig.getBasePackage());
    }
}
