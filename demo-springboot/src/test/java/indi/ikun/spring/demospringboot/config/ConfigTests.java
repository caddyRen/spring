package indi.ikun.spring.demospringboot.config;

import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
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
    SwaggerConfig swaggerConfigl;

    @Test
    public void test(){
        System.err.println(swaggerConfigl.basePackage);
    }
}
