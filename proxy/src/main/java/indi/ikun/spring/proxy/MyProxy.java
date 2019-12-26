package indi.ikun.spring.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author renqiankun
 */
@SpringBootApplication
@ServletComponentScan
public class MyProxy {
    public static void main(String[] args) {
        SpringApplication.run(MyProxy.class, args);
    }
}
