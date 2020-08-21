package indi.ikun.spring.demospringboot.api;

import indi.ikun.spring.demospringboot.mybatis.dao.SysAppMapper;
import indi.ikun.spring.demospringboot.mybatis.po.SysApp;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DemoController
 * @Author caddyR
 * @Date 2019-06-14 11:31
 * @Version 1.0
 **/
@RestController
@Api(tags = "APIDemo")
@Slf4j
public class DemoController {

    @Autowired
    SysAppMapper sysAppMapper;


    @Autowired
    private ApplicationContext applicationContext;

    @PostMapping("/stop")
    public String stop() {
        // 加上自己的权限验证
        SpringApplication.exit(applicationContext);
        return "ok";
    }


    @GetMapping("/dem0")
    @Transactional(rollbackFor = Exception.class)
    public SysApp index(){

        SysApp byAppId = sysAppMapper.getByAppId("xcgk");
        SysApp byAppId2 = sysAppMapper.getByAppId2("xcgk");
        sysAppMapper.insert(byAppId);
        int i=1/0;
        return byAppId2;
    }

    @GetMapping("/index2")
    public String index2(){
        return "Hello World";
    }


}
