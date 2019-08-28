package com.mumu.springcloud.demo.config.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author renqiankun
 * @Description 手动注入TestFilter过滤器，
 * @Date 2018/5/2
 * @Modified By
 */
@Configuration
public class TestFilterConfig {
    @Bean
    public FilterRegistrationBean testFilterRegistration(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        TestFilter testFilter=new TestFilter();
        filterRegistrationBean.setFilter(testFilter);
        List<String> urlPatterns=new ArrayList<>();
        //过滤带特定字符的url
        urlPatterns.add("/test/*");
        filterRegistrationBean.setUrlPatterns(urlPatterns);
        filterRegistrationBean.setName("apiFilter");
        filterRegistrationBean.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
        return filterRegistrationBean;
    }
}
