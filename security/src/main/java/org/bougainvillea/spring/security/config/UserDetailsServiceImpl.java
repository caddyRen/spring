package org.bougainvillea.spring.security.config;

import org.bougainvillea.spring.commons.entity.User;
import org.bougainvillea.spring.security.entity.SecuUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 自定义用户密码
 */
@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. 查询用户
        User user = User.builder()
                .username(username)
                .password("202cb962ac59075b964b07152d234b70")
                .build();
        if (user == null) {
            //这里找不到必须抛异常
            throw new UsernameNotFoundException("User " + username + " was not found in db");
        }
        // 2. 查询角色
        //用户，角色，权限
        //3. 查询权限
        List<String> permissionValueList= Arrays.asList("user");

        SecuUser secuUser=new SecuUser(user);
        secuUser.setPermissionValueList(permissionValueList);

        return secuUser;

    }
}
