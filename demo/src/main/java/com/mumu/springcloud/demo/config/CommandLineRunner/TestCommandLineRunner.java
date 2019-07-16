package com.mumu.springcloud.demo.config.CommandLineRunner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author renqiankun
 * @Description
 * @Date 2018/5/2
 * @Modified By
 */
@Component//添加进IOC容器
@Order(value = 1)//设置启动优先级，越小优先级越高
@Slf4j
public class TestCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

    }
}
