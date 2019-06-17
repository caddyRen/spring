package com.mumu.springcloud.provider.service.demo.service;


import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * @ClassName DemoService
 * @Description TODO
 * @Author caddyR
 * @Date 2019-03-06 13:14
 * @Version 1.0
 **/
@Service
public class DemoService {

    public Map<String,Object> test(Map<String,Object> map){
        map.put("test",13459209374L);
        return map;
    }
    @Async()
    public Future<String> demoAsync(){
        Future<String> message;//异步方法返回值，如果方法内部出错，则会返回错误信息debug模式查看是异步处理的，但是会影响返回消息的速度

        System.err.println(String.format("async test Demo-----" + new Date().getTime()));
        try {
            Thread.sleep(5000);
            //int i=1/0;
            message=new AsyncResult<>("success");
        } catch (InterruptedException e) {
            message=new AsyncResult<>("异常信息"+e.getMessage()+Thread.currentThread().getName());
            //e.printStackTrace();
        }
        return message;
    }







}
