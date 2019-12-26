package indi.ikun.spring.demo.config.ApplicationRunner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

/**
 * @author renqiankun
 * @Description
 * @Date  2018/5/2
 * @Modified By
 */
@Order(value = 4)
@Slf4j
public class TestApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("ApplicationRunner...");
    }
}
