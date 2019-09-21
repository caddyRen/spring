package com.mumu.springcloud.demo.service.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mumu.springcloud.demo.service.demo.Company;
import com.mumu.springcloud.demo.service.demo.DefaultResultBean;
import com.mumu.springcloud.demo.service.demo.Person;
import com.mumu.springcloud.demo.service.demo.StatusCodeEnum;
import com.mumu.springcloud.demo.service.demo.aop.aspect.DemoAnnotation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author caddyR
 * @Date 2019-07-31 15:33
 * @Version 1.0
 **/
@RestController
@RequestMapping("test")
@Slf4j
public class TestController {

    @Autowired
    RestTemplate restTemplate;

    @DemoAnnotation
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
    @DemoAnnotation
    public DefaultResultBean<String> DefaultCreateMessage(HttpServletRequest request,HttpServletResponse response,@RequestBody Company messageVo) {
        Person person=Person.builder()
                .name("qiankun")
                .age(25)
                .company(messageVo).build();

        DefaultResultBean<String> bean = new DefaultResultBean<>();
        bean.setCode(StatusCodeEnum.SUCCESS.getCode());
        bean.setMsg("推送任务已提交, 消息会在稍候发送至内网或极光完成推送");
        bean.setData(person.toString());
        System.err.println(1);
        return bean;
    }

    @PostMapping("/start")
    public String start(HttpServletRequest httpServletRequest, HttpServletResponse response) {

        JSONArray jsonArray = new JSONArray();
        Enumeration paramNames = httpServletRequest.getParameterNames();
        while (paramNames.hasMoreElements()) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = (String) paramNames.nextElement();
            String value = httpServletRequest.getParameter(key);
            map.put("itemName", key);
            map.put("itemValue", value);
            jsonArray.add(map);
        }

        String url = "http://192.168.43.230:8088/Portal/bpm-api/workflows/20190917apitest";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("systemCode", "H3");
        requestMap.put("secret", "Authine");
        requestMap.put("finishStart", "true");
        requestMap.put("workflowCode", "20190917apitest");
        requestMap.put("userCode", "administrator");

        requestMap.put("paramValues", jsonArray);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestMap, headers);
        ResponseEntity<String> entity = restTemplate.postForEntity(url, request, String.class);
        //{"systemCode":"H3",
        // "secret":"Authine",
        // "workflowCode":"20190917apitest",
        // "userCode":"administrator",
        // "finishStart":"true",
        // "paramValues":[{"itemName":"id","itemValue":"002"},
        // {"itemName":"createdate","itemValue":"2019-09-17 11:11:11"},
        // {"itemName":"detail","itemValue":"测试哈"}]}
        String body = entity.getBody();
        JSONObject jsonObject = JSON.parseObject(body);
        if (null != jsonObject&&0 == (Integer) jsonObject.get("code")) {
            JSONObject jsonObject1=JSON.parseObject(jsonObject.get("data").toString());
//            commit(jsonObject1.getString("instanceId"));
        //提交
           return jsonObject.get("data").toString();
        } else {
            return "error";
        }
    }

    @GetMapping("/get1")
    public String get1(){
        String string="http://192.168.43.230:8088/Portal/bpm-api/workitems/unfinish/d68f8e44-eccd-426b-b3b9-94ffe8c34213?systemCode=H3&secret=Authine&startTime=2019-09-01&endTime=2019-09-30&startIndex=1&endIndex=1";
        ResponseEntity<String> entity=restTemplate.getForEntity(string,String.class);
        return entity.getBody();

    }
    @GetMapping("/get2")
    public String get2(){
        String string="http://192.168.43.230:8088/Portal/bpm-api/workitems/unfinish/2074f1d6-c8c5-4191-956b-0eb200af3f9d?systemCode=H3&secret=Authine&startTime=2019-09-01&endTime=2019-09-30&startIndex=1&endIndex=1";
        ResponseEntity<String> entity=restTemplate.getForEntity(string,String.class);
        return entity.getBody();
    }

    @PostMapping("/s1")
    public void s1(HttpServletRequest httpServletRequest, HttpServletResponse response){
        String userId="d68f8e44-eccd-426b-b3b9-94ffe8c34213";
        JSONArray jsonArray = new JSONArray();
        Enumeration paramNames = httpServletRequest.getParameterNames();
        while (paramNames.hasMoreElements()) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = (String) paramNames.nextElement();
            String value = httpServletRequest.getParameter(key);
            map.put("itemName", key);
            map.put("itemValue", value);
            jsonArray.add(map);
        }

        String url = "http://192.168.43.230:8088/Portal/bpm-api/itemvalues/spyj1";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("systemCode", "H3");
        requestMap.put("secret", "Authine");
        requestMap.put("bizObjectSchemaCode", "20190917apitest");
        requestMap.put("bizObjectId", "774f069a-b0ae-4149-bf45-af118695ad51");
        requestMap.put("userId", userId);

        requestMap.put("paramValues", jsonArray);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestMap, headers);
        restTemplate.put(url, request);

        commit(userId);
        //http://192.168.43.230:8088/Portal/bpm-api/itemvalues/spyj1
//        {"systemCode":"H3",
//                "secret":"Authine",
//                "userId":"2074f1d6-c8c5-4191-956b-0eb200af3f9d",
//                "bizObjectSchemaCode":"20190917apitest",
//                "bizObjectId":"65c31d2a-66d8-4340-bdb3-b145dba27efb",
//                "keyName":"spyj2","keyValue":"2333"}

    }
    @PostMapping("/s2")
    public void s2(HttpServletRequest httpServletRequest, HttpServletResponse response){
        String userId="2074f1d6-c8c5-4191-956b-0eb200af3f9d";
        JSONArray jsonArray = new JSONArray();
        Enumeration paramNames = httpServletRequest.getParameterNames();
        while (paramNames.hasMoreElements()) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = (String) paramNames.nextElement();
            String value = httpServletRequest.getParameter(key);
            map.put("itemName", key);
            map.put("itemValue", value);
            jsonArray.add(map);
        }

        String url = "http://192.168.43.230:8088/Portal/bpm-api/itemvalues/spyj2";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("systemCode", "H3");
        requestMap.put("secret", "Authine");
        requestMap.put("bizObjectSchemaCode", "20190917apitest");
        requestMap.put("bizObjectId", "774f069a-b0ae-4149-bf45-af118695ad51");
        requestMap.put("userId", userId);

        requestMap.put("paramValues", jsonArray);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestMap, headers);
        restTemplate.put(url, request);

        commit(userId);
//        {"systemCode":"H3",
//                "secret":"Authine",
//                "userId":"2074f1d6-c8c5-4191-956b-0eb200af3f9d",
//                "bizObjectSchemaCode":"20190917apitest",
//                "bizObjectId":"65c31d2a-66d8-4340-bdb3-b145dba27efb",
//                "keyName":"spyj2","keyValue":"2333"}

    }

    private void commit(String userid){
        String string="http://192.168.43.230:8088/Portal/bpm-api/workitems/unfinish/"+userid+"?systemCode=H3&secret=Authine&startTime=2019-09-01&endTime=2019-09-30&startIndex=1&endIndex=1";
        ResponseEntity<String> entity=restTemplate.getForEntity(string,String.class);
        String body = entity.getBody();
        JSONObject json=JSON.parseObject(body);
        JSONArray jsonArray=json.getJSONArray("data");
        String workItemId="";
        if (null != jsonArray&&jsonArray.size()>0) {
            JSONObject jsonObject=jsonArray.getJSONObject(0);
            workItemId=(String) jsonObject.get("ObjectID");
        }

        String url = "http://192.168.43.230:8088/Portal/bpm-api/workitems/submit/"+workItemId;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("systemCode", "H3");
        requestMap.put("secret", "Authine");
        requestMap.put("workItemId", workItemId);
        requestMap.put("commentText", "审批1");
        requestMap.put("userId", userid);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestMap, headers);
        restTemplate.put(url, request);

    }
}
