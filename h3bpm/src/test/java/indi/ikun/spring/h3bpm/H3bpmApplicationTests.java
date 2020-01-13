package indi.ikun.spring.h3bpm;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import indi.ikun.spring.h3bpm.utils.H3bpmHttpUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class H3bpmApplicationTests {

    @Autowired
    H3bpmHttpUtils h3bpmHttpUtils;

    @Test
    public void testRestTemplate() {
        h3bpmHttpUtils.getFlow("ProjectLeader");
    }

    @Test
    public void test() {
        //        String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKD24OhWZ3FvaqHrKQXjDE6m+ujGeEE4UWf8Z/dwiwQsDIIEW3vqkf7mv1IAxYqBxnVXaM35jS3lgUgCaylN0n0ZcEo6YaQbdYpWVngV50FRA62MQo7nbFGX9KcYk9gNEmMtklUjr1tORFk+dph4kIFUY5ql+JcVSUeiCBQCX14lAgMBAAECgYBXb8QccVMfu3YhHqzaFcvJrcr+R1FrnDwcUG/paakaSxNATT2kwncsEAw9b03fPz+e3iyANt8J33GqvCWZ+jwr+WpzT5VzeEce2fyT9xt3/AwXd3LBS1hJRRUeK6vvTKHP8K4khxUrtqlCkAWw97oxwHQ3uXub8QpWZe/6Lo3owQJBAOv7LoPOfQXLImyUKzJ2vcH1Rl5b8LND8O8GjsQCYSuClpxToWo88hp2UVgYG6iL2lJlcA8ukXHm8+agCAFSoFsCQQCuno1RCEIGLAXgll0VuR7yFx8+ZTtiV2cBw9iVYyIF6VZjAZHcPTwhXCyJOrKta8ojbmcCkqQYPGuL9RrbXkN/AkEA2UO7EMmlXBcpi5RjrSOcFB6x+iBmHXaZNkjwWtDiDABAkI+DFuAj+28h95zCokyY5rHUGXZDnP4JYzXGCQm87wJAM/QyoZhuZH22pfK28V6p6WedwSfHemP83taRWykql02siTqPVlLV0CsniRDw1o5Kjy4q8eYFcryPVw3vFrO+KwJBAJoUMSUF1L906VXiwVAeoGXUgZkPyeSg4EaGQyD5xm+1vmGPgcUMCC9FldNC8m3oLeBtBh2qAKmB94KLqnmzzV8=";
        //        String data="hJMzahGDVJEVhPOI5t8TwPvWTJqBmQzfwarzqhHw/o5TNeNA6hOWzodSZMRSctiO6EvfQG5KohaeD9VzGGDMcdnZOtAPmm3bVuuGQCth715Gu1LjlUc2KEAhAekJfW7dbVkDIfdmVoaP67vCvrYFKjIBw62mVd4yf0zwGkUZQeI=";
        String Code = "xmsgywlc";
        String projectLeader = "ProjectLeader";
        String projectManager = "ProjectManager";
        String projectSiteLeader = "ProjectSiteLeader";
        String jianli = "Supervisor";
        String banzu = "TeamAcceptanceLeader";

        Map<String, String> map = new HashMap<>();
        map.put("project_name", "2019年越秀局低压线路维修");
        map.put("project_code", "0820000MS62181011");
        map.put("project_attr", "日常维修");
        map.put("fix_attr", "生产修理");
        map.put("project_type", "生产场所、设施维修");
        map.put("leading_unit", "广州供电局有限公司 广州越秀供电局");
        map.put("apply_dept", "广州越秀供电局");
        map.put("project_leader", projectLeader);
        map.put("voltage_level", "10kV");
        map.put("build_time", "2019年01月至2019年12月");
        map.put("cost", "11700000（万元）");
        map.put("anquanmao", "200");
        map.put("denggaoban", "200");
        map.put("jiaokou", "20");
        map.put("anquandai", "40");
        map.put("scszkq", "40");
        map.put("work_com", "中区公司");
        map.put("project_manager", projectManager);
        map.put("pd_date", "2020-01-08");
        map.put("defect_info", "0820000MS62181011");
        try {
            //            System.err.println(H3bpmHttpUtils.decrypt(data, RSAUtil.getPrivateKey(privateKey)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //1.发起流程，此时设置好需要设置好的参与者：项目负责人userCode（对应：项目负责人审批），项目经理（对应：接收派单，申请结项）
        JSONObject j1 = h3bpmHttpUtils.startFlow(Code, projectLeader, map);
        //测试异步
        System.err.println("2333");
        String instanceId = j1.getJSONObject("data").getString("instanceId");

        //2.项目经理接收派单，先设置现场负责人（现场施工参与者）再审批通过
        String projectSiteLeaderId = h3bpmHttpUtils.getUserId(projectSiteLeader).getString("data");
        JSONObject j2 = h3bpmHttpUtils.setItemValue(instanceId, projectSiteLeaderId, "project_site_leader");
        //设置需要的表单值
        Map<String, String> m2 = new HashMap<>();
        m2.put("anquanmao", "100");
        m2.put("denggaoban", "100");
        m2.put("jiaokou", "20");
        m2.put("anquandai", "60");
        m2.put("scszkq", "60");
        m2.put("work_object", "创展#1开关房");
        m2.put("plan_end_time", "2020-01-15");
        JSONObject j222 = h3bpmHttpUtils.setItemValue(instanceId, m2);
        //获取待办，获取workItemId
        JSONObject j22 = h3bpmHttpUtils.getUnFinishWork(projectManager);
        JSONArray uFinishWork = j22.getJSONArray("data");
        for (int i = 0; i < uFinishWork.size(); i++) {
            if ("项目施工业务流程".equals(uFinishWork.getJSONObject(i).getString("WorkflowName")) && "接收派单".equals(uFinishWork.getJSONObject(i).getString("DisplayName"))) {
                h3bpmHttpUtils.sptg(projectManager, uFinishWork.getJSONObject(i).getString("ObjectID"));
            }
        }

        //3.现场负责人，现场施工直接审批通过
        //设置需要的表单值
        //        Map<String,String> m3=new HashMap<>();
        //        m3.put("ldpz", "ldpz.jpg");
        //        m3.put("clpz", "clpz.jpg");
        //        m3.put("wzpz", "wzpz.jpg");
        //        m3.put("zyqpz", "zyqpz.jpg");
        //        m3.put("zyhpz", "zyhpz.jpg");
        //        m3.put("sgqk", "sgqk.jpg");
        //        JSONObject j33=h3bpmHttpUtils.setItemValue(instanceId,m3);
        //获取待办，获取workItemId
        JSONObject j3 = h3bpmHttpUtils.getUnFinishWork(projectSiteLeader);
        JSONArray uFinishWork3 = j3.getJSONArray("data");
        for (int i = 0; i < uFinishWork3.size(); i++) {
            if ("项目施工业务流程".equals(uFinishWork3.getJSONObject(i).getString("WorkflowName")) && "现场施工".equals(uFinishWork3.getJSONObject(i).getString("DisplayName"))) {
                h3bpmHttpUtils.sptg(projectSiteLeader, uFinishWork3.getJSONObject(i).getString("ObjectID"));
            }
        }

        //4.项目经理申请结项：先设置监理审批人，再审批通过
        String jianliId = h3bpmHttpUtils.getUserId(jianli).getString("data");
        JSONObject j4 = h3bpmHttpUtils.setItemValue(instanceId, jianliId, "jianli");
        //设置需要的表单值
        Map<String, String> m4 = new HashMap<>();
        m4.put("visa_code", "20200107-001");
        m4.put("work_note", "剑气纵横三万里，一剑光寒十九州");
        JSONObject j444 = h3bpmHttpUtils.setItemValue(instanceId, m4);
        //获取待办，获取workItemId
        JSONObject j44 = h3bpmHttpUtils.getUnFinishWork(projectManager);
        JSONArray uFinishWork4 = j44.getJSONArray("data");
        for (int i = 0; i < uFinishWork4.size(); i++) {
            if ("项目施工业务流程".equals(uFinishWork4.getJSONObject(i).getString("WorkflowName")) && "申请结项".equals(uFinishWork4.getJSONObject(i).getString("DisplayName"))) {
                h3bpmHttpUtils.sptg(projectManager, uFinishWork4.getJSONObject(i).getString("ObjectID"));
            }
        }

        //5.监理审理：先设置班组验收，再审批通过
        String banzuId = h3bpmHttpUtils.getUserId(banzu).getString("data");
        JSONObject j5 = h3bpmHttpUtils.setItemValue(instanceId, banzuId, "banzu");
        //        JSONObject j555=h3bpmHttpUtils.setItemValue(instanceId,"ojbk","spyj_jianli");
        //获取待办，获取workItemId
        JSONObject j55 = h3bpmHttpUtils.getUnFinishWork(jianli);
        JSONArray uFinishWork5 = j55.getJSONArray("data");
        for (int i = 0; i < uFinishWork5.size(); i++) {
            if ("项目施工业务流程".equals(uFinishWork5.getJSONObject(i).getString("WorkflowName")) && "监理审批".equals(uFinishWork5.getJSONObject(i).getString("DisplayName"))) {
                h3bpmHttpUtils.sptg(jianli, uFinishWork5.getJSONObject(i).getString("ObjectID"));
            }
        }
        //        JSONObject j55=h3bpmHttpUtils.setItemValues(instanceId,projectManager,null);
        //6.班组验收，直接审批通过
        //        JSONObject j66=h3bpmHttpUtils.setItemValue(instanceId,"ojbk","spyj_bzsp");
        //获取待办，获取workItemId
        JSONObject j6 = h3bpmHttpUtils.getUnFinishWork(banzu);
        JSONArray uFinishWork6 = j6.getJSONArray("data");
        for (int i = 0; i < uFinishWork6.size(); i++) {
            if ("项目施工业务流程".equals(uFinishWork6.getJSONObject(i).getString("WorkflowName")) && "班组验收负责人审批".equals(uFinishWork6.getJSONObject(i).getString("DisplayName"))) {
                h3bpmHttpUtils.sptg(banzu, uFinishWork6.getJSONObject(i).getString("ObjectID"));
            }
        }
        //7.项目负责人审批通过，
        //        JSONObject j77=h3bpmHttpUtils.setItemValue(instanceId,"ojbk","spyj_fzr");
        JSONObject j7 = h3bpmHttpUtils.getUnFinishWork(projectLeader);
        JSONArray uFinishWork7 = j7.getJSONArray("data");
        for (int i = 0; i < uFinishWork7.size(); i++) {
            if ("项目施工业务流程".equals(uFinishWork7.getJSONObject(i).getString("WorkflowName")) && "项目负责人审批".equals(uFinishWork7.getJSONObject(i).getString("DisplayName"))) {
                h3bpmHttpUtils.sptg(projectLeader, uFinishWork7.getJSONObject(i).getString("ObjectID"));
            }
        }

    }

}
