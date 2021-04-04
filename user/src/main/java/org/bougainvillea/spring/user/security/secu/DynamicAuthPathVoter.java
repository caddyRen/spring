package org.bougainvillea.spring.user.security.secu;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import java.util.*;

/**
 * 动态权限配置
 *  用于投票
 *  int ACCESS_GRANTED = 1; 允许访问
 * 	int ACCESS_ABSTAIN = 0; 弃权
 * 	int ACCESS_DENIED = -1; 拒绝访问
 * @author caddy
 */
public class DynamicAuthPathVoter implements AccessDecisionVoter<Object> {

    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }

    public int vote(Authentication authentication, Object object,
                    Collection<ConfigAttribute> attributes) {
        if (authentication == null) {
            return ACCESS_DENIED;
        }

        //获取当前请求路径
        FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();
        int result = ACCESS_DENIED;
        //当前用户可访问的路径列表
        Collection<? extends GrantedAuthority> authorities = extractAuthorities(authentication);
        //遍历判断当前路径是否为用户可访问路径
        for (GrantedAuthority authority : authorities){
            if (url.equals(authority.getAuthority())) {
                result= ACCESS_GRANTED;
                break;
            }
        }
        return result;
    }

    Collection<? extends GrantedAuthority> extractAuthorities(
            Authentication authentication) {
        return authentication.getAuthorities();
    }
}
