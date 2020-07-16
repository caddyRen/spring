package indi.ikun.spring.zipkinclient;

import lombok.extern.slf4j.Slf4j;
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


    @RequestMapping("/2")
    public String home() {
        log.info("Handling home");
        return "Hello World";
    }

    public static void main(String[] args) {
        SpringApplication.run(ZipkinClientApplication.class, args);
    }

}
