package org.bougainvillea.spring.user.v1.dao;


import java.util.List;

public interface SecuUserDao {

    int getUserByName(String username);
    List<String> getRolesByName(String username);
    List<String> getPermsByName(String username);
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(SecuUser record);
//
//    int insertSelective(SecuUser record);
//
//    SecuUser selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(SecuUser record);
//
//    int updateByPrimaryKey(SecuUser record);
}