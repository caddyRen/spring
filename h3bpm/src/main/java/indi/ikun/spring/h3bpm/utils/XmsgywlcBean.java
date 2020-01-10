package indi.ikun.spring.h3bpm.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class XmsgywlcBean {
    public String projectName;
    public String projectCode;
    public String projectAttr;
    public String fixAttr;
    public String projectType;
    public String leadingUnit;
    public String applyDept;
    public String projectLeader;
    public String voltageLevel;
    public Date beginDate;
    public Date endDate;
    public String cost;
    public String workCom;
    public String projectManager;
    public Date pdDate;
    public int anquanmao;
    public int denggaoban;
    public int jiaokou;
    public int anquandai;
    public int scszkq;
    public String workObject;
    public String projectSiteLeader;
    public Date planEndTime;
    public String defectInfo;
    public String visaCode;
    public String workNote;
    public String ldpz;
    public String clpz;
    public String wzpz;
    public String zyqpz;
    public String zyhpz;
    public String sgqk;
    public String spyjJianli;
    public String spyjBzsp;
    public String spyjFzr;
}
