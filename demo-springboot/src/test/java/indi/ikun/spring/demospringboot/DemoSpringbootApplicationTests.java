package indi.ikun.spring.demospringboot;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
  *@Author:renqiankun
  *@Date:2020-01-08 10:55
  **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoSpringbootApplicationTests {

    @Test
    public void test(){
        log.info("111");
    }
}
