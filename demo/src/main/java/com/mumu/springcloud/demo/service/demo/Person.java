package com.mumu.springcloud.demo.service.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @ClassName Person
 * @Description TODO
 * @Author caddyR
 * @Date 2019-07-02 15:02
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    public String name;
    public Integer age;
    public List list;
    public Map map;
    public Company company;


}
