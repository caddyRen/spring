package indi.ikun.spring.demospringboot.api.service;

import indi.ikun.spring.demospringboot.api.service.impl.DemoServiceImpl;
import indi.ikun.spring.demospringboot.mybatis.dao.SysAppMapper;
import indi.ikun.spring.demospringboot.mybatis.po.SysApp;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@Slf4j
@Import(DemoServiceImpl.class)
public class DemoServiceTests {
    @MockBean
    private SysAppMapper sysAppMapper;

    @Autowired
    DemoService demoService;

    @Test
    public void test(){
        given(this.sysAppMapper.getByAppId2("xcgk")).willReturn(SysApp.builder().appName("主站").build());
        SysApp xcgk = demoService.getByAppId2("xcgk");
        log.info(xcgk.toString());
    }


}
