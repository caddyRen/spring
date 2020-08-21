package indi.ikun.spring.demospringboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;

/**
 *@author renqiankun
 *@date 2020-01-08 10:55
 */
@SpringBootApplication(scanBasePackages ="indi.ikun.spring" )
@EnableAsync
@MapperScan(basePackages ="indi.ikun.spring.demospringboot.mybatis.dao")
@Slf4j
public class DemoSpringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoSpringbootApplication.class, args);
        log.trace("============trace");
        log.debug("============debug");
        log.info("============info");
        log.warn("============warn");
        log.error("============error");
    }
}
