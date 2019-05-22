package com.mumu.springcloud.eurekaserver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *@Author caddyR
 *@Description 服务注册中心  @EnableEurekaServer//注册中心开启注解
 *@Date 2019-03-06 11:40
 *@Param
 *@return
**/
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
