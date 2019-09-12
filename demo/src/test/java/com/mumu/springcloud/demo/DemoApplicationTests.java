package com.mumu.springcloud.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.mumu.springcloud.demo.config.cyclicdpns.StudentA;
import com.mumu.springcloud.demo.config.cyclicdpns.StudentB;
import com.mumu.springcloud.demo.config.cyclicdpns.StudentC;
import com.mumu.springcloud.demo.service.demo.Company;
import com.mumu.springcloud.demo.service.demo.Person;
import com.mumu.springcloud.demo.service.demo.aop.service.IUserServ;
import com.mumu.springcloud.demo.service.demo.aop.service.impl.UserServiceImpl;
import com.mumu.springcloud.demo.service.demo.aop.service.peoxyClass.ProxyFactory;
import com.mumu.springcloud.demo.service.demo.aop.service.peoxyClass.ProxyFactory2;
import com.mumu.springcloud.demo.service.demo.aop.service.proxyimpl.UserServiceProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DemoApplicationTests
 * @Description TODO
 * @Author caddyR
 * @Date 2019-06-14 11:26
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Bean
    private StudentA getA(){
        return new StudentA();
    }
    @Bean
    private StudentB getB(){
        return new StudentB();
    }
    @Bean
    private StudentC getC(){
        return new StudentC();
    }

    @Autowired
    ApplicationContext context;

    @Test
    public void testCyclicdpns(){
        StudentA studentA=context.getBean(StudentA.class);
        studentA.print();
        StudentB studentB=context.getBean(StudentB.class);
        studentB.print();
        StudentC studentC=context.getBean(StudentC.class);
        studentC.print();

    }

    UserServiceImpl userService=new UserServiceImpl();
    @Test
    public void testDynamicProxy(){
        IUserServ proxy=(IUserServ) Proxy.newProxyInstance(
                userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object invoke=method.invoke(userService,args);
                        return null;
                    }
                }
        );
        proxy.save();

    }

    @Test
    public void testCglib(){
        UserServiceImpl userService=new UserServiceImpl();

        UserServiceImpl proxy=(UserServiceImpl)new ProxyFactory(userService).getProxyInstance();

        proxy.save();
    }

    @Test
    public void testCglib2(){
        UserServiceImpl userService=new UserServiceImpl();

        UserServiceImpl proxy=(UserServiceImpl)new ProxyFactory2(userService).getProxyInstance();

        proxy.save();
    }

    @Test
    public void testStaticProxy(){
        UserServiceImpl userService=new UserServiceImpl();
        UserServiceProxy userServiceProxy=new UserServiceProxy(userService);
        userServiceProxy.save();
    }


    @Test
    public void testJSON(){
        List<String> list=new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        Map<String,Object> map=new HashMap<>();
        map.put("a",12);
        map.put("b",true);
        map.put("c","caddy");
        map.put("d",12.1);
        Company company= Company.builder()
                .location("earth")
                .name("grdgdj")
                .list(list)
                .map(map)
                .build();
        Person person= Person.builder()
                .age(10)
                .name("caddy")
                .list(list)
                .map(map)
                .company(company)
                .build();
        String string=JSON.toJSONString(person);
//        System.err.println(string);
        Person person1= JSON.parseObject(string,new TypeReference<Person>(){});
        System.err.println(person1);

    }
}
