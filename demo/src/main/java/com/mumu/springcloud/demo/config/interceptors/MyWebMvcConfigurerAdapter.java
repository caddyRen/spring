package com.mumu.springcloud.demo.config.interceptors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author renqiankun
 * @Description
 * @Date 2018/5/2
 * @Modified By
 */
@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(testInterceptor()).addPathPatterns("/test/**");
        super.addInterceptors(registry);
    }

    @Bean
    public TestInterceptor testInterceptor(){
        return new TestInterceptor();
    }

}
