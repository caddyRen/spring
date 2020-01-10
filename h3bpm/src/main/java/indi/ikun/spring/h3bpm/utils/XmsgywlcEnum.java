package indi.ikun.spring.h3bpm.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
  *@TODO //项目施工业务流程
  *@Author:renqiankun
  *@Date:2020-01-08 19:21
  **/
@Getter
@ToString
@AllArgsConstructor
public enum XmsgywlcEnum {

    projectName("projectName", "2020年越秀局低压线路维修"),
    projectCode("projectCode", "0820000MS62181011"),
    projectAttr("projectAttr", "日常维修"),
    fixAttr("fixAttr", "生产修理"),
    projectType("projectType", "生产场所、设施维修"),
    leadingUnit("leadingUnit", "广州供电局有限公司 广州越秀供电局"),
    applyDept("applyDept", "广州越秀供电局"),
    projectLeader("projectLeader", "项目负责人"),
    voltageLevel("voltageLevel", "10kV"),
    beginDate("beginDate", "2020-01-07"),
    endDate("endDate", "2020-01-15"),
    cost("cost", "11700000（万元）"),
    workCom("workCom", "中区公司"),
    projectManager("projectManager", "项目经理"),
    pdDate("pdDate", "2020-01-07"),
    anquanmao("anquanmao", "200"),
    denggaoban("denggaoban", "200"),
    jiaokou("jiaokou", "20"),
    anquandai("anquandai", "40"),
    scszkq("scszkq", "40"),
    workObject("workObject", "创展#1开关房"),
    projectSiteLeader("projectSiteLeader", "施工现场负责人"),
    planEndTime("planEndTime", "2020-01-15"),
    defectInfo("defectInfo", "0820000MS62181011"),
    visaCode("visaCode", "20200107-001"),
    workNote("workNote", "剑气纵横三万里，一剑光寒十九州"),
    ldpz("ldpz", "ldpz.jpg"),
    clpz("clpz", "clpz.jpg"),
    wzpz("wzpz", "wzpz.jpg"),
    zyqpz("zyqpz", "zyqpz.jpg"),
    zyhpz("zyhpz", "zyhpz.jpg"),
    sgqk("sgqk", "sgqk.jpg"),
    spyjJianli("spyjJianli", "spyjJianli.jpg"),
    spyjBzsp("spyjBzsp", "spyjBzsp.jpg"),
    spyjFzr("spyjFzr", "同意");

    private String key;
    private String defaultValue;
}
