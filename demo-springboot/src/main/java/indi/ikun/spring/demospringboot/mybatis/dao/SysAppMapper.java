package indi.ikun.spring.demospringboot.mybatis.dao;

import indi.ikun.spring.demospringboot.mybatis.po.SysApp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

public interface SysAppMapper extends BaseMapper<SysApp> {

    @Select("SELECT * FROM sys_app where app_id = #{appId}")
    SysApp getByAppId(@Param("appId") String appId);
    SysApp getByAppId2(@Param("appId") String appId);
}