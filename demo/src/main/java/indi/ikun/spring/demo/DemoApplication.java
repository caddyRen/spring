package indi.ikun.spring.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @ClassName DemoApplication
 * @Author caddyR
 * @Date 2019-06-14 11:24
 * @Version 1.0
 **/
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplicationBuilder builder=new SpringApplicationBuilder(DemoApplication.class);
        builder.bannerMode(Banner.Mode.OFF).run(args);
//        FilterRegistrationBean test=SpringUtil.getBean(FilterRegistrationBean.class);
//        int i=0;
    }
}
