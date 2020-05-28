package indi.ikun.spring.h3bpm.api;

import indi.ikun.spring.h3bpm.mybatis.dao.SysAppMapper;
import indi.ikun.spring.h3bpm.mybatis.po.SysApp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName DemoController
 * @Description TODO
 * @Author caddyR
 * @Date 2019-06-14 11:31
 * @Version 1.0
 **/
@Controller
@Slf4j
public class DemoController {

    @Autowired
    SysAppMapper sysAppMapper;

    @GetMapping("/dem0")
    @Transactional(rollbackFor = Exception.class)
    public SysApp index(){

        SysApp byAppId = sysAppMapper.getByAppId("xcgk");
        SysApp byAppId2 = sysAppMapper.getByAppId2("xcgk");
        sysAppMapper.insert(byAppId);
        int i=1/0;
        return byAppId2;
    }


}
