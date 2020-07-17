package indi.ikun.spring.zipkinclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
@EnableEurekaClient
public class ZipkinClientApplication {

    @Value("zipkin.host")
    private String percentage;

    @Value("spring.zipkin.base-url")
    private String url;
    @Value("eureka.instance.appname")
    private String url2;

    @RequestMapping("/2")
    public String home() {
        log.info("Handling home");
        return percentage+url+url2;
    }

    public static void main(String[] args) {
        SpringApplication.run(ZipkinClientApplication.class, args);
    }

}
