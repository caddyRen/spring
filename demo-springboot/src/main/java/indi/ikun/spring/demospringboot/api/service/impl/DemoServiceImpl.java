package indi.ikun.spring.demospringboot.api.service.impl;

import indi.ikun.spring.demospringboot.api.service.DemoService;
import indi.ikun.spring.demospringboot.mybatis.dao.SysAppMapper;
import indi.ikun.spring.demospringboot.mybatis.po.SysApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@TestConfiguration
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
