package org.bougainvillea.spring.security.filter;

import org.bougainvillea.spring.security.secu.TokenManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 授权过滤器
 *
 * 从header取token
 * 从token获取用户名
 * 根据用户名授权
 *
 *
 */
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    private TokenManager tokenManager;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager, TokenManager tokenManager) {
        super(authenticationManager);
        this.tokenManager=tokenManager;
    }

    /**
     *
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //获取认证成功用户信息
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        //判断权限信息，放到权限上下文中
        if (authentication!=null){
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request,response);
    }


    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
        String token= request.getHeader("token");
        if(token!=null){
            String username=tokenManager.getUserFromToken(token);
            //获取权限列表 从redis或者数据库
            List<String> permissionValueList=new ArrayList<>();
            //List转Collection<? extends GrantedAuthority> authorities
            Collection<GrantedAuthority> authorities=new ArrayList<>();
            for (String permissionValue:permissionValueList){
                SimpleGrantedAuthority auth=new SimpleGrantedAuthority(permissionValue);
                authorities.add(auth);
            }
            return new UsernamePasswordAuthenticationToken(username,token,authorities);
        }
        return null;
    }
}
