package com.mumu.springcloud.demo.service.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.mumu.springcloud.demo.service.demo.Company;
import com.mumu.springcloud.demo.service.demo.DefaultResultBean;
import com.mumu.springcloud.demo.service.demo.Person;
import com.mumu.springcloud.demo.service.demo.StatusCodeEnum;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author caddyR
 * @Date 2019-07-31 15:33
 * @Version 1.0
 **/
@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping(value = "getToken", method = RequestMethod.POST)
    public String getToken(HttpServletRequest request, HttpServletResponse response,
     @RequestParam() String ciphertext
    ) {
        return "hello";
    }

    @ApiOperation(value = "推送通知接口", notes = "提交相关信息，等待推送")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Authorization",
                    value = "Authorization token",
                    paramType = "header",
                    dataType = "string",
                    required = true,
                    defaultValue = "Bearer eyJhbGciOiJIUzUxMiJ9"
            )
    })
    @PostMapping(value = "/createMessage",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DefaultResultBean<String> DefaultCreateMessage(@RequestBody Company messageVo) {
        Person person=Person.builder()
                .name("qiankun")
                .age(25)
                .company(messageVo).build();

        DefaultResultBean<String> bean = new DefaultResultBean<>();
        bean.setCode(StatusCodeEnum.SUCCESS.getCode());
        bean.setMsg("推送任务已提交, 消息会在稍候发送至内网或极光完成推送");
        bean.setData(person.toString());
        return bean;
    }
}
