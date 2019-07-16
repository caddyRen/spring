package com.mumu.springcloud.demo.config.listeners;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author renqiankun
 * @Description 监听上下文，可以初始化一些信息，比如从数据库获取系统需要的数据
 * @Date 2018/5/10
 * @Modified By
 */
@Slf4j
public class MyServletContextListerner implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("MyServletContextListerner init");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("MyServletContextListerner desroyed");
    }
}
