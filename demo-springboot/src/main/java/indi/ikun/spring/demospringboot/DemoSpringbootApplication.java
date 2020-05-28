package indi.ikun.spring.demospringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;

/**
  *@TODO //启动类
  *@Author:renqiankun
  *@Date:2020-01-08 10:55
  **/
@SpringBootApplication
@EnableAsync
@MapperScan(basePackages ="indi.ikun.spring.demospringboot.mybatis.dao")
public class DemoSpringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoSpringbootApplication.class, args);
    }
}
