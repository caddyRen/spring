package com.mumu.springcloud.demo.config.filters;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author renqiankun
 * @Description
 *              servlet调用之前截获，可以检查request，修改request数据
 *              servlet调用之后截获，可以修改response头和response数据
 * @Date 2018/5/2
 * @Modified By
 */
@Slf4j
public class TestFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化过滤器....");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("过滤器 is running...");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        log.info("过滤器 destroy...");
    }
}
