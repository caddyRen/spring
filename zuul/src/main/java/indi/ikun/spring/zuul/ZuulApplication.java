package indi.ikun.spring.zuul;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @ClassName ProviderApplication
 * @Description 
 * @Author caddyR
 * @Date 2019-06-17 11:22
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@Slf4j
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class,args);
        log.trace("============trace");
        log.debug("============debug");
        log.info("============info");
        log.warn("============warn");
        log.error("============error");
    }

}
