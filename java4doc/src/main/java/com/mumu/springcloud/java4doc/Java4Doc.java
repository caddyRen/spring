package com.mumu.springcloud.java4doc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Java4Doc {
    public static void main(String[] args) {
        SpringApplication.run(Java4Doc.class, args);
    }
}
