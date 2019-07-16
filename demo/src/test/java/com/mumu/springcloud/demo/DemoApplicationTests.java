package com.mumu.springcloud.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.mumu.springcloud.demo.service.demo.Company;
import com.mumu.springcloud.demo.service.demo.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;
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
