package org.bougainvillea.spring.user.security.config;


import org.bougainvillea.spring.redisdepency.utils.RedisUtils;
import org.bougainvillea.spring.user.security.filter.TokenAuthenticationFilter;
import org.bougainvillea.spring.user.security.filter.TokenLoginFilter;
import org.bougainvillea.spring.user.security.secu.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.Arrays;
import java.util.List;

/**
 * 全局配置
 */
@Configuration
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private TokenManager tokenManager;
    private DefaultPasswordEncoder defaultPasswordEncoder;
    private UserDetailsService userDetailsService;
    private RedisUtils redisUtils;

    @Autowired
    public TokenWebSecurityConfig(TokenManager tokenManager, DefaultPasswordEncoder defaultPasswordEncoder, UserDetailsService userDetailsService, RedisUtils redisUtils) {
        this.tokenManager = tokenManager;
        this.defaultPasswordEncoder = defaultPasswordEncoder;
        this.userDetailsService = userDetailsService;
        this.redisUtils = redisUtils;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(new UnAuthEntryPoint())//没有权限访问
                .and().csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated().accessDecisionManager(accessDecisionManager())
                .and().logout().logoutUrl("/index/logout")//退出路径
                .addLogoutHandler(new TokenLogoutHandler(tokenManager))
                .and()
                .addFilter(new TokenLoginFilter(tokenManager,authenticationManager(),redisUtils))//认证过滤器
                .addFilter(new TokenAuthenticationFilter(authenticationManager(),tokenManager,redisUtils))//授权过滤器
                .httpBasic();
    }

    /**
     * 调用userDetailService和密码处理
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }

    /**
     * 不进行认证的路径可以直接访问
     */
    @Override
    public void configure(WebSecurity web) {
        String[] paths={
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/v2/api-docs",
                "/webjars/**",
                "/admin/**"
        };
        web.ignoring().antMatchers(paths);
    }

    /**
     * 投票器
     * ConsensusBased uses a consensus-based approach 少数服从多数，忽略弃权
     * AffirmativeBased grants access if any AccessDecisionVoter returns an affirmative response 一票通过
     * UnanimousBased  that requires all voters to abstain or grant access 一票反对
     * @return
     */
    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> decisionVoters = Arrays.asList(
                new WebExpressionVoter(),
//                new RoleVoter(),
//                new AuthenticatedVoter(),
                new DynamicAuthPathVoter()
        );
        return new UnanimousBased(decisionVoters);
    }
}
