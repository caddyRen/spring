package indi.ikun.spring.h3bpm.api;

import indi.ikun.spring.h3bpm.mybatis.dao.SysAppMapper;
import indi.ikun.spring.h3bpm.mybatis.po.SysApp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    private final String PREFIX="pages/";

    @Autowired
    SysAppMapper sysAppMapper;

    @GetMapping("/")
    @Transactional(rollbackFor = Exception.class)
    public void index(){

        SysApp byAppId = sysAppMapper.getByAppId("xcgk");
        SysApp byAppId2 = sysAppMapper.getByAppId2("xcgk");
        sysAppMapper.insert(byAppId);


        System.err.println("welcome");
    }
    @GetMapping("/level1/{path}")
    public String level1(@PathVariable("path") String path){return PREFIX+"level1/"+path;}
    @GetMapping("/level2/{path}")
    public String level2(@PathVariable("path") String path){return PREFIX+"level1/"+path;}

    @GetMapping("/admin/{path}")
    public String admin(@PathVariable("path") String path){return PREFIX+"admin/"+path;}
    @GetMapping("/userlogin")
    public String login(){return PREFIX+"login";}



}
