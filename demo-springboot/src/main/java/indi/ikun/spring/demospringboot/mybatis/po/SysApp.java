package indi.ikun.spring.demospringboot.mybatis.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_app")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysApp {

    /**
     * comment  主键id
     */
    @Id
    @KeySql(sql = "select REPLACE(uuid(),'-','')", order = ORDER.BEFORE)
    @Column(name = "id")
    private String id;
    /**
     * comment  appId
     */
    @Column(name = "app_id")
    private String appId;
    /**
     * comment  appSecret
     */
    @Column(name = "app_secret")
    private String appSecret;

    /**
     * comment  系统全称
     */
    @Column(name = "app_name")
    private String appName;

    /**
     * comment  系统简称
     */
    @Column(name = "app_short_name")
    private String appShortName;

    /**
     * comment  联系人
     */
    @Column(name = "linkman")
    private String linkman;
    /**
     * comment  联系人电话
     */
    @Column(name = "phone")
    private String phone;
    /**
     * comment  邮箱
     */
    @Column(name = "email")
    private String email;
    /**
     * comment  备注
     */
    @Column(name = "remark")
    private String remark;

}