package org.bougainvillea.spring.configclient.service.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName DemoController
 * @Author caddyR
 * @Date 2019-03-06 13:13
 * @Version 1.0
 **/
@RefreshScope
@RestController
public class DemoController {

//    @Autowired
//    DemoService demoService;

    @Value("${my.name}")
    String name;

    @GetMapping("/hello")
    public String test(Map<String,Object> map) throws ExecutionException, InterruptedException {
        //Future<String> future=demoService.demoAsync();
        //String s=future.get();
        //return s;
        //Future<String> future2=demoService.demoAsync();
        //demoService.demoAsync();
        //demoService.demoAsync();
        //return demoService.test(map).get("test").toString();
//        Map<String,Object> map1=demoService.test(map);
//        if(null!=map){
//            throw new RuntimeException("没有元素");
//        }
        return name;
    }
    /*
     *@Author caddyR
     *@Description //服务调用失败则会调用该方法，注意参数要和test的参数一致
     *@Date 2019-03-21 09:09
     *@Param [map]
     *@return java.lang.String
    **/
    public String processHystrix_GET(Map<String,Object> map){
        return new String("调用出错就会返回这条消息");

    }

}