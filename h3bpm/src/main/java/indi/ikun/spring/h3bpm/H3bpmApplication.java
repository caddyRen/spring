package indi.ikun.spring.h3bpm;

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
@MapperScan(basePackages ="indi.ikun.spring.h3bpm.mybatis.dao")
public class H3bpmApplication {
    public static void main(String[] args) {
        SpringApplication.run(H3bpmApplication.class, args);
    }
}
