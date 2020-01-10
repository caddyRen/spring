package indi.ikun.spring.h3bpm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
  *@TODO //h3bpm流程常量
  *@Author: renqiankun
  *@Date:2020-01-08 14:59
  **/
@Configuration
public class H3BPMConfigs {

    @Value("${h3bpm.ip}")
    public String ip;

    @Value("${h3bpm.port}")
    public String port;

    @Value("${h3bpm.system-code}")
    public String systemCode;

    @Value("${h3bpm.secret}")
    public String secret;

    @Value("${h3bpm.org-api}")
    public String orgAPI;

    @Value("${h3bpm.bpm-api}")
    public String bpmAPI;

    @Value("${h3bpm.sso-api}")
    public String ssoAPI;

    @Value("${h3bpm.api.get.get-flow}")
    public String getFlow;

    @Value("${h3bpm.api.get.get-userid}")
    public String getUserId;

    @Value("${h3bpm.api.get.get-todos}")
    public String getTodos;

    @Value("${h3bpm.api.post.get-bizobjectid}")
    public String getBizObjectId;

    @Value("${h3bpm.api.post.start-flow}")
    public String startFlow;

    @Value("${h3bpm.api.post.get-instanceid}")
    public String getInstanceId;

    @Value("${h3bpm.api.post.uploadfile}")
    public String uploadFile;

    @Value("${h3bpm.api.put.approve}")
    public String approve;

    @Value("${h3bpm.api.put.do-return}")
    public String doReturn;

    @Value("${h3bpm.api.put.inputform}")
    public String inputForm;

    @Value("${h3bpm.api.put.activities}")
    public String activities;



    private static H3BPMConfigs h3bpmConfigs;

    @PostConstruct
    void init() {
        h3bpmConfigs = this;
    }

    public static String getIp() {
        return h3bpmConfigs.ip;
    }

    public static String getPort() {
        return h3bpmConfigs.port;
    }

    public static String getSystemCode() {
        return h3bpmConfigs.systemCode;
    }

    public static String getSecret() {
        return h3bpmConfigs.secret;
    }

    public static String getOrgAPI() {
        return h3bpmConfigs.orgAPI;
    }

    public static String getBpmAPI() {
        return h3bpmConfigs.bpmAPI;
    }

    public static String getSsoAPI() {
        return h3bpmConfigs.ssoAPI;
    }

    public static String getGetFlow() {
        return h3bpmConfigs.getFlow;
    }

    public static String getGetUserId() {
        return h3bpmConfigs.getUserId;
    }

    public static String getGetTodos() {
        return h3bpmConfigs.getTodos;
    }

    public static String getGetBizObjectId() {
        return h3bpmConfigs.getBizObjectId;
    }

    public static String getStartFlow() {
        return h3bpmConfigs.startFlow;
    }

    public static String getGetInstanceId() {
        return h3bpmConfigs.getInstanceId;
    }

    public static String getUploadFile() {
        return h3bpmConfigs.uploadFile;
    }

    public static String getApprove() {
        return h3bpmConfigs.approve;
    }

    public static String getDoReturn() {
        return h3bpmConfigs.doReturn;
    }

    public static String getInputForm() {
        return h3bpmConfigs.inputForm;
    }

    public static String getActivities() {
        return h3bpmConfigs.activities;
    }
}
