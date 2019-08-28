package com.mumu.springcloud.demo.service.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName DemoController
 * @Description TODO
 * @Author caddyR
 * @Date 2019-06-14 11:31
 * @Version 1.0
 **/
@Controller
public class DemoController {

    private final String PREFIX="pages/";

    @GetMapping("/")
    public String index(){return "welcome";}
    @GetMapping("/level1/{path}")
    public String level1(@PathVariable("path") String path){return PREFIX+"level1/"+path;}
    @GetMapping("/admin/{path}")
    public String admin(@PathVariable("path") String path){return PREFIX+"admin/"+path;}
    @GetMapping("/userlogin")
    public String login(){return PREFIX+"login";}
}
