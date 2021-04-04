package org.bougainvillea.spring.user.security.service.impl;

import org.bougainvillea.spring.commons.entity.User;
import org.bougainvillea.spring.user.security.entity.SecuUser;
import org.bougainvillea.spring.user.v1.dao.SecuUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 自定义用户密码
 */
@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private SecuUserDao secuUserDao;

    @Autowired
    public UserDetailsServiceImpl(SecuUserDao secuUserDao) {
        Assert.notNull(secuUserDao,"secuUserDao must be not null");
        this.secuUserDao = secuUserDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. 查询用户
        int userId = secuUserDao.getUserByName(username);

        if (userId == 0) {
            //这里找不到必须抛异常
            throw new UsernameNotFoundException("User " + username + " was not found in db");
        }

        User user = User.builder()
                .username(username)
                .password("202cb962ac59075b964b07152d234b70")//123
                .build();

//        User user = User.builder()
//                .username(username)
//                .password("202cb962ac59075b964b07152d234b70")
//                .build();
        // 2. 查询角色
        List<String> roleList=secuUserDao.getRolesByName(username);

        //用户，角色，权限
        //3. 查询权限
        List<String> permissionValueList= secuUserDao.getPermsByName(username);
        SecuUser secuUser=new SecuUser(user);
        secuUser.setCurrentUserInfo(user);
        secuUser.setPermissionValueList(permissionValueList);
        return secuUser;

    }
}
