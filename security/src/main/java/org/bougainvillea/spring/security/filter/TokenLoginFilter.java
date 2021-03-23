package org.bougainvillea.spring.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bougainvillea.spring.security.entity.SecuUser;
import org.bougainvillea.spring.security.entity.User;
import org.bougainvillea.spring.security.secu.TokenManager;
import org.bougainvillea.spring.security.utils.ResponseUtil;
import org.bougainvillea.spring.security.utils.Result;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 认证过滤器
 * UsernamePasswordAuthenticationFilter
 * 1. 先判断提交方式是否为post
 * 父类 AbstractAuthenticationProcessingFilter
 * private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
 * 			throws IOException, ServletException {
 * 		if (!requiresAuthentication(request, response)) {
 * 			chain.doFilter(request, response);
 * 			return;
 *                }
 * 2.调用子类的方法进行身份认证，认证成功后吧认证信息封装到对象里面
 * try {
 * 			Authentication authenticationResult = attemptAuthentication(request, response);
 * 			if (authenticationResult == null) {
 * 				// return immediately as subclass has indicated that it hasn't completed
 * 				return;
 *                        }
 * 3. session策略处理
 * this.sessionStrategy.onAuthentication(authenticationResult, request, response);
 * 4.认证成功执行认证成功方法
 * // Authentication success
 * 			if (this.continueChainBeforeSuccessfulAuthentication) {
 * 				chain.doFilter(request, response);
 *                        }
 * 			successfulAuthentication(request, response, chain, authenticationResult);
 * 4.1认证失败执行认证失败方法
 * catch (InternalAuthenticationServiceException failed) {
 * 			this.logger.error("An internal error occurred while trying to authenticate the user.", failed);
 * 			unsuccessfulAuthentication(request, response, failed);
 *                }
 * 		catch (AuthenticationException ex) {
 * 			// Authentication failed
 * 			unsuccessfulAuthentication(request, response, ex);
 *        }
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    private TokenManager tokenManager;
    private AuthenticationManager authenticationManager;

    public TokenLoginFilter(TokenManager tokenManager, AuthenticationManager authenticationManager) {
        this.tokenManager = tokenManager;
        this.authenticationManager = authenticationManager;
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login","POST"));
    }
    /**
     * 获取表单提交用户名密码
     * 认证先调用此方法
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //获取表单提交数据
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            //下面操作会调用UserDetailsService 查数据库
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(),new ArrayList<>()));
        } catch (IOException e) {
            e.printStackTrace();
            throw  new RuntimeException();
        }
    }

    /**
     * 认证成功
     * @param authResult 认证用户信息
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //认证成功得到认证成功后用户信息
        SecuUser user=(SecuUser) authResult.getPrincipal();
        //根据用户名生成token
        String token = tokenManager.createToken(user.getCurrentUserInfo().getUsername());
        //可以存到缓存中，redis
        //返回token
        ResponseUtil.out(response,
                Result.ok().data("token",token));
    }

    /**
     * 认证失败
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ResponseUtil.out(response, Result.error());
    }
}
