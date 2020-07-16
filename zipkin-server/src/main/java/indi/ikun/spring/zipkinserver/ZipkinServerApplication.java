package indi.ikun.spring.zipkinserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zipkin.server.internal.EnableZipkinServer;

@SpringBootApplication
@RestController
@Slf4j
@EnableEurekaClient
@EnableZipkinServer
public class ZipkinServerApplication {


    @RequestMapping("/2")
    public String home() {
        log.info("Handling home");
        return "Hello World";
    }

    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerApplication.class, args);
    }

}
