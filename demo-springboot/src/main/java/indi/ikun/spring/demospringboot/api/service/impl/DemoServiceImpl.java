package indi.ikun.spring.demospringboot.api.service.impl;

import indi.ikun.spring.demospringboot.api.service.DemoService;
import indi.ikun.spring.demospringboot.mybatis.dao.SysAppMapper;
import indi.ikun.spring.demospringboot.mybatis.po.SysApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
//@TestConfiguration 此注解加上，单元测试不会扫描装载这个类，需要通过@Import将这个类显示滴导入单元测试
public class DemoServiceImpl implements DemoService {


    private SysAppMapper sysAppMapper;

    @Autowired
    public DemoServiceImpl(SysAppMapper sysAppMapper) {
        this.sysAppMapper = sysAppMapper;
        Assert.notNull(sysAppMapper,"sysAppMapper must be not null");
    }

    @Override
    public SysApp getByAppId2(String appID) {
        return sysAppMapper.getByAppId2(appID);
    }
}
