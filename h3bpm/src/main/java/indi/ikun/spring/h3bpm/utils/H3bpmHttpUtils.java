package indi.ikun.spring.h3bpm.utils;

import java.io.ByteArrayOutputStream;
import java.security.PrivateKey;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import indi.ikun.spring.h3bpm.config.H3BPMConfigs;
import lombok.extern.slf4j.Slf4j;

/**
 * @TODO //项目施工业务流程 1.发起流程，此时设置好需要设置好的参与者：项目负责人（对应：项目负责人审批），项目经理（对应：接收派单，申请结项） 2.项目经理接收派单，先设置现场负责人（现场施工参与者）再审批通过 3.现场负责人，现场施工直接审批通过 4.项目经理申请结项：先设置监理审批人，再审批通过 5.监理审理：先设置班组验收，再审批通过 6.班组验收，直接审批通过 7.项目负责人审批通过，
 * @Author: renqiankun
 * @Date: 2020-01-08 15:14
 **/
@Component
@Slf4j
public class H3bpmHttpUtils {

    @Autowired
    RestTemplate restTemplate;

    String orgAPI = "http://" + H3BPMConfigs.getIp() + ":" + H3BPMConfigs.getPort() + H3BPMConfigs.getOrgAPI();
    String bpmAPI = "http://" + H3BPMConfigs.getIp() + ":" + H3BPMConfigs.getPort() + H3BPMConfigs.getBpmAPI();
    String ssoAPI = "http://" + H3BPMConfigs.getIp() + ":" + H3BPMConfigs.getPort() + H3BPMConfigs.getSsoAPI();

    String Code = "xmsgywlc";
    String Administrator = "18f923a7-5a5e-426d-94ae-a55ad1a4b239";

    private Map<String, String> xmsgywlcMap(int a) {
        Map<String, String> map = new HashMap<>();
        switch (a) {
            case 1:
                map.put("project_name", "2020年越秀局低压线路维修");
                map.put("project_code", "0820000MS62181011");
                map.put("project_attr", "日常维修");
                map.put("fix_attr", "生产修理");
                map.put("project_type", "生产场所、设施维修");
                map.put("leading_unit", "广州供电局有限公司 广州越秀供电局");
                map.put("apply_dept", "广州越秀供电局");
                map.put("project_leader", "项目负责人");
                map.put("voltage_level", "10kV");
                map.put("begin_date", "2020-01-07");
                map.put("end_date", "2020-01-15");
                map.put("cost", "11700000（万元）");
                map.put("work_com", "中区公司");
                map.put("project_manager", "项目经理");
                map.put("pd_date", "2020-01-07");
                map.put("anquanmao", "200");
                map.put("denggaoban", "200");
                map.put("jiaokou", "20");
                map.put("anquandai", "40");
                map.put("scszkq", "40");
                map.put("defect_info", "0820000MS62181011");
                return map;
            case 2:
                map.put("anquanmao", "200");
                map.put("denggaoban", "200");
                map.put("jiaokou", "20");
                map.put("anquandai", "40");
                map.put("scszkq", "40");
                map.put("work_object", "创展#1开关房");
                map.put("project_site_eader", "施工现场负责人");
                map.put("plan_end_time", "2020-01-15");
                map.put("visa_code", "20200107-001");
                map.put("work_note", "剑气纵横三万里，一剑光寒十九州");
                map.put("ldpz", "ldpz.jpg");
                map.put("clpz", "clpz.jpg");
                map.put("wzpz", "wzpz.jpg");
                map.put("zyqpz", "zyqpz.jpg");
                map.put("zyhpz", "zyhpz.jpg");
                map.put("sgqk", "sgqk.jpg");
                map.put("spyj_jianli", "spyjJianli.jpg");
                map.put("spyj_bzsp", "spyjBzsp.jpg");
                map.put("spyj_fzr", "同意");
                return map;
            default:
                map.put("project_name", "2020年越秀局低压线路维修");
                map.put("project_code", "0820000MS62181011");
                map.put("project_attr", "日常维修");
                map.put("fix_attr", "生产修理");
                map.put("project_type", "生产场所、设施维修");
                map.put("leading_unit", "广州供电局有限公司 广州越秀供电局");
                map.put("apply_dept", "广州越秀供电局");
                map.put("project_leader", "项目负责人");
                map.put("voltage_level", "10kV");
                map.put("begin_date", "2020-01-07");
                map.put("end_date", "2020-01-15");
                map.put("cost", "11700000（万元）");
                map.put("work_com", "中区公司");
                map.put("project_manager", "项目经理");
                map.put("pd_date", "2020-01-07");
                map.put("anquanmao", "200");
                map.put("denggaoban", "200");
                map.put("jiaokou", "20");
                map.put("anquandai", "40");
                map.put("scszkq", "40");
                map.put("work_object", "创展#1开关房");
                map.put("project_site_eader", "施工现场负责人");
                map.put("plan_end_time", "2020-01-15");
                map.put("defect_info", "0820000MS62181011");
                map.put("visa_code", "20200107-001");
                map.put("work_note", "剑气纵横三万里，一剑光寒十九州");
                map.put("ldpz", "ldpz.jpg");
                map.put("clpz", "clpz.jpg");
                map.put("wzpz", "wzpz.jpg");
                map.put("zyqpz", "zyqpz.jpg");
                map.put("zyhpz", "zyhpz.jpg");
                map.put("sgqk", "sgqk.jpg");
                map.put("spyj_jianli", "spyjJianli.jpg");
                map.put("spyj_bzsp", "spyjBzsp.jpg");
                map.put("spyj_fzr", "同意");
                return map;
        }
    }

    // 私钥用于解密接口返回数据
    private String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKD24OhWZ3FvaqHrKQXjDE6m+ujGeEE4UWf8Z/dwiwQsDIIEW3vqkf7mv1IAxYqBxnVXaM35jS3lgUgCaylN0n0ZcEo6YaQbdYpWVngV50FRA62MQo7nbFGX9KcYk9gNEmMtklUjr1tORFk+dph4kIFUY5ql+JcVSUeiCBQCX14lAgMBAAECgYBXb8QccVMfu3YhHqzaFcvJrcr+R1FrnDwcUG/paakaSxNATT2kwncsEAw9b03fPz+e3iyANt8J33GqvCWZ+jwr+WpzT5VzeEce2fyT9xt3/AwXd3LBS1hJRRUeK6vvTKHP8K4khxUrtqlCkAWw97oxwHQ3uXub8QpWZe/6Lo3owQJBAOv7LoPOfQXLImyUKzJ2vcH1Rl5b8LND8O8GjsQCYSuClpxToWo88hp2UVgYG6iL2lJlcA8ukXHm8+agCAFSoFsCQQCuno1RCEIGLAXgll0VuR7yFx8+ZTtiV2cBw9iVYyIF6VZjAZHcPTwhXCyJOrKta8ojbmcCkqQYPGuL9RrbXkN/AkEA2UO7EMmlXBcpi5RjrSOcFB6x+iBmHXaZNkjwWtDiDABAkI+DFuAj+28h95zCokyY5rHUGXZDnP4JYzXGCQm87wJAM/QyoZhuZH22pfK28V6p6WedwSfHemP83taRWykql02siTqPVlLV0CsniRDw1o5Kjy4q8eYFcryPVw3vFrO+KwJBAJoUMSUF1L906VXiwVAeoGXUgZkPyeSg4EaGQyD5xm+1vmGPgcUMCC9FldNC8m3oLeBtBh2qAKmB94KLqnmzzV8=";

    /**
     * @TODO //
     * @Author:renqiankun
     * @Date:2020-01-08 17:01
     * @param: xmsgywlc
     * @return: 返回有instanceId
     **/
    public JSONObject startFlow(String Code, String userCode, Map<String, String> map) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        StringBuffer url = new StringBuffer().append(bpmAPI);
        url.append(H3BPMConfigs.getStartFlow());
        url.append("/" + Code);
        log.info("======>发起请求：" + url.toString());
        JSONObject params = new JSONObject();
        params.put("systemCode", H3BPMConfigs.getSystemCode());
        params.put("secret", H3BPMConfigs.getSecret());
        params.put("workflowCode", Code);
        params.put("userCode", userCode);
        params.put("finishStart", "true");
        JSONArray jsonArray = new JSONArray();
        for (String str : xmsgywlcMap(1).keySet()) {
            JSONObject paramValues = new JSONObject();
            paramValues.put("itemName", str);
            for (String str2 : map.keySet()) {
                if (str.equals(str2)) {
                    try {
                        if (str2.equals("beginDate") || str2.equals("endDate") || str2.equals("pdDate") || str2.equals("planEndTime")) {
                            paramValues.put("itemValue", format.parse(map.get(str2)));
                            break;
                        } else {
                            paramValues.put("itemValue", map.get(str2));
                            break;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
            jsonArray.add(paramValues);
        }
        params.put("paramValues", jsonArray);
        log.info("======>请求参数：" + params.toJSONString());
        return getPostPutResponse(url.toString(), HttpMethod.POST, MediaType.APPLICATION_JSON_UTF8, params.toJSONString(), String.class);
    }

    /**
     * @TODO //当前节点审批通过
     * @Author:renqiankun
     * @Date:2020-01-09 17:38
     * @param:
     * @return:
     **/
    public JSONObject sptg(String userName, String workItemId) {
        String userId = getUserId(userName).getString("data");
        StringBuffer url = new StringBuffer().append(bpmAPI);
        url.append(H3BPMConfigs.getApprove());
        url.append("/" + workItemId);
        log.info("======>发起请求：" + url.toString());
        JSONObject params = new JSONObject();
        params.put("systemCode", H3BPMConfigs.getSystemCode());
        params.put("secret", H3BPMConfigs.getSecret());
        params.put("userId", userId);
        params.put("workItemId", workItemId);// 指定参与者
        params.put("commentText", "同意");// 指定参与者
        log.info("======>请求参数：" + params.toJSONString());
        return getPostPutResponse(url.toString(), HttpMethod.PUT, MediaType.APPLICATION_JSON_UTF8, params.toJSONString(), String.class);
    }

    /**
     * @TODO // 设置单个流程数据项值
     * @Author:renqiankun
     * @Date:2020-01-09 10:52
     * @param: 指定指定节点参与者
     * @return: { "systemCode": "H3", "secret": "Authine", "userId": "18f923a7-5a5e-426d-94ae-a55ad1a4b239", "bizObjectSchemaCode": "TD", "bizObjectId": "52491396-d2cc-423e-8526-62df1ad1eae6", "keyName": "message", "keyValue": "Hello, H3 BPM!" }
     **/
    public JSONObject setItemValue(String instanceId, String keyValue, String keyName) {
        StringBuffer url = new StringBuffer().append(bpmAPI);
        url.append(H3BPMConfigs.getInputForm());
        url.append("/" + keyName);
        log.info("======>发起请求：" + url.toString());
        JSONObject params = new JSONObject();
        params.put("systemCode", H3BPMConfigs.getSystemCode());
        params.put("secret", H3BPMConfigs.getSecret());
        params.put("userId", "18f923a7-5a5e-426d-94ae-a55ad1a4b239");
        params.put("keyName", keyName);// 指定参与者
        params.put("keyValue", keyValue);// 指定参与者
        params.put("bizObjectSchemaCode", Code);
        // 获取bizObjectId
        String bizObjectId = getBizObjectId(instanceId).getString("data");
        params.put("bizObjectId", bizObjectId);
        log.info("======>请求参数：" + params.toJSONString());
        return getPostPutResponse(url.toString(), HttpMethod.PUT, MediaType.APPLICATION_JSON_UTF8, params.toJSONString(), String.class);
    }

    /**
     * @TODO // 设置多个流程数据项值
     * @Author:renqiankun
     * @Date:2020-01-09 10:52
     * @param: 指定指定节点参与者
     * @return: { "systemCode": "H3", "secret": "Authine", "userId": "18f923a7-5a5e-426d-94ae-a55ad1a4b239", "bizObjectSchemaCode": "TD", "bizObjectId": "52491396-d2cc-423e-8526-62df1ad1eae6", "keyName": "message", "keyValue": "Hello, H3 BPM!" }
     **/
    public JSONObject setItemValue(String instanceId, Map<String, String> map) {
        StringBuffer url = new StringBuffer().append(bpmAPI);
        url.append(H3BPMConfigs.getInputForm());
        log.info("======>发起请求：" + url.toString());
        JSONObject params = new JSONObject();
        params.put("systemCode", H3BPMConfigs.getSystemCode());
        params.put("secret", H3BPMConfigs.getSecret());
        params.put("userId", "18f923a7-5a5e-426d-94ae-a55ad1a4b239");
        JSONArray keyAndValue = new JSONArray();
        for (String str : map.keySet()) {
            JSONObject param = new JSONObject();
            param.put("itemName", str);// 指定参与者
            param.put("itemValue", map.get(str));// 指定参与者
            keyAndValue.add(param);
        }
        params.put("bizObjectSchemaCode", Code);
        params.put("keyValues", keyAndValue);
        // 获取bizObjectId
        String bizObjectId = getBizObjectId(instanceId).getString("data");
        params.put("bizObjectId", bizObjectId);
        log.info("======>请求参数：" + params.toJSONString());
        return getPostPutResponse(url.toString(), HttpMethod.PUT, MediaType.APPLICATION_JSON_UTF8, params.toJSONString(), String.class);
    }

    /**
     * @TODO //
     * @Author:renqiankun
     * @Date:2020-01-08 17:01
     * @param: xmsgywlc
     * @return: 返回有instanceId
     **/
    public JSONObject getBizObjectId(String instanceId) {
        StringBuffer url = new StringBuffer().append(bpmAPI);
        url.append(H3BPMConfigs.getGetBizObjectId());
        url.append("/" + instanceId);
        log.info("======>发起请求：" + url.toString());
        JSONObject params = new JSONObject();
        params.put("systemCode", H3BPMConfigs.getSystemCode());
        params.put("secret", H3BPMConfigs.getSecret());
        params.put("instanceId", instanceId);
        log.info("======>请求参数：" + params.toJSONString());
        return getPostPutResponse(url.toString(), HttpMethod.POST, MediaType.APPLICATION_JSON_UTF8, params.toJSONString(), String.class);
    }

    /**
     * @TODO //根据h3bpm用户名获取待办
     * @Author:renqiankun
     * @Date:2020-01-08 15:23
     * @param: userName h3系统用户名，4a
     * @return: 返回当前用户id usserid?systemCode=H3&secret=Authine&startTime=2019-01-21&endTime=2019-02-01&startIndex=1&endIndex=1
     **/
    public JSONObject getUnFinishWork(String userName) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        StringBuffer url = new StringBuffer().append(bpmAPI);
        String userID = getUserId(userName).getString("data");
        url.append(H3BPMConfigs.getGetTodos());
        url.append("/" + userID);
        url.append("?systemCode=" + H3BPMConfigs.getSystemCode());
        url.append("&secret=" + H3BPMConfigs.getSecret());
        url.append("&startTime=2020-01-01" + H3BPMConfigs.getSecret());
        url.append("&endTime=" + format.format(new Date()));
        url.append("&startIndex=1&endIndex=1");
        log.info("======>发起请求：" + url.toString());
        return getH3BPMData(restTemplate.getForObject(url.toString(), String.class));
    }

    /**
     * @TODO //根据h3bpm用户名获取用户id
     * @Author:renqiankun
     * @Date:2020-01-08 15:23
     * @param: userName  h3系统用户名，4a
     * @return: 返回当前用户id
     **/
    public JSONObject getUserId(String userName) {
        StringBuffer url = new StringBuffer().append(orgAPI);
        url.append(H3BPMConfigs.getGetUserId());
        url.append("?userCode=" + userName);
        log.info("======>发起请求：" + url.toString());
        return getH3BPMData(restTemplate.getForObject(url.toString(), String.class));
    }

    /**
     * @TODO: 获取可发起流程
     * @author: renqiankun
     * @Description: 根据h3系统用户名获取可发起流程
     * @date: 2020-01-10 14:50:44
     * @param userName
     * @return
     */
    public JSONObject getFlow(String userName) {
        StringBuffer url = new StringBuffer().append(bpmAPI);
        url.append(H3BPMConfigs.getGetFlow());
        url.append("/" + userName + "?");
        url.append("systemCode=" + H3BPMConfigs.getSystemCode());
        url.append("&secret=" + H3BPMConfigs.getSecret());
        url.append("&showFavorite=false&isMobile=false");
        url.append("&userCode=" + userName);
        log.info("======>发起请求：" + url.toString());
        return getH3BPMData(restTemplate.getForObject(url.toString(), String.class));
    }

    /**
     * @TODO: 获取post put请求的返回结果
     * @author: renqiankun
     * @Description: 抽取post put请求
     * @date: 2020-01-10 16:08:00
     * @return
     */
    public <T> JSONObject getPostPutResponse(String url, HttpMethod httpMethod, MediaType mediaType, String params, Class<T> responseType) {
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, httpMethod, getHttpEntityJSON(mediaType, params), responseType);
        if (responseEntity.hasBody()) {
            log.info("======>h3bpm接口数据 status code: " + responseEntity.getStatusCodeValue());
            log.info("======>h3bpm接口数据 Headers:" + responseEntity.getHeaders());
            return getH3BPMData(responseEntity.getBody());
        } else {
            throw new RuntimeException("======>H3BPM接口异常");
        }
    }

    /**
     * @TODO: 获取h3系统API返回data
     * @author: renqiankun
     * @Description: 传入h3系统API返回的密文，解密并用自定义json格式封装data
     * @date: 2020-01-10 14:49:05
     * @param h3bpmResult
     * @return
     */
    public <T> JSONObject getH3BPMData(T h3bpmResult) {
        JSONObject jsonObject = getNewJSONObject();
        JSONObject result;
        try {
            result = JSON.parseObject(H3bpmHttpUtils.decrypt(String.valueOf(h3bpmResult), RSAUtil.getPrivateKey(privateKey)));
            log.info("======>h3bpm返回结果解密后内容：" + result.toJSONString());
            // 获取Code，DisplayName，ObjectID
            if (0 == result.getInteger("code")) {
                // httpCode
                jsonObject.put("code", 0);
                // response code
                jsonObject.put("responseCode", 0);
                // response msg
                jsonObject.put("responseMSG", result.get("msg"));
                jsonObject.put("data", result.get("data"));
                log.info("======>返回结果：" + jsonObject);
            }
        } catch (Exception e) {
            log.error("======>处理h3bpm结果出现异常：" + e.getMessage());
        } finally {
            return jsonObject;
        }
    }

    /**
     * @TODO // HttpEntity<String>
     * @Author:renqiankun
     * @Date:2019-12-10 18:18
     * @param:
     * @return:
     **/
    private HttpEntity<String> getHttpEntityJSON(MediaType mediaType, String json) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        return entity;
    }

    /**
     * @TODO: 设置请求头
     * @author: renqiankun
     * @Description: postform表单请求头
     * @date: 2020-01-10 15:23:28
     * @param mediaType
     * @param params
     * @return
     */
    private HttpEntity<MultiValueMap<String, Object>> getHttpEntity(MediaType mediaType, MultiValueMap<String, Object> params) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(params, headers);
        return entity;
    }

    /**
     * @TODO // 预设返回值
     * @Author:renqiankun
     * @Date:2019-12-10 18:19
     * @param:
     * @return:
     **/
    private JSONObject getNewJSONObject() {
        JSONObject result = new JSONObject();
        // httpCode
        result.put("code", 500);
        // response code
        result.put("responseCode", -1);
        // response msg
        result.put("responseMSG", "Connection timed out");
        return result;
    }

    /**
     * RSA解密
     * @param data待解密数据
     * @param privateKey私钥
     * @return
     */
    public static String decrypt(String data, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] dataBytes = Base64.decodeBase64(data);
        int inputLen = dataBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offset > 0) {
            if (inputLen - offset > RSAUtil.MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(dataBytes, offset, RSAUtil.MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * RSAUtil.MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        // 解密后的内容
        return new String(decryptedData, "UTF-8");
    }
}
