package indi.ikun.spring.demospringboot;

import indi.ikun.spring.demospringboot.mybatis.dao.SysAppMapper;
import indi.ikun.spring.demospringboot.mybatis.po.SysApp;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
  *@TODO //启动类
  *@Author:renqiankun
  *@Date:2020-01-08 10:55
  **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoSpringbootApplicationTests {

    @Autowired
    SysAppMapper sysAppMapper;

    @Test
    public void test(){
        SysApp xcgk = sysAppMapper.getByAppId2("xcgk");
        log.info(xcgk.toString());
    }
}
