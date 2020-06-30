package indi.ikun.spring.demospringboot.api.service;


import indi.ikun.spring.demospringboot.mybatis.po.SysApp;

public interface DemoService {
    SysApp getByAppId2(String appID);
}
