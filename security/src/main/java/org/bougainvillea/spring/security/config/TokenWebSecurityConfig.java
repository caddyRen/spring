package org.bougainvillea.spring.security.config;

import org.bougainvillea.spring.security.filter.TokenAuthenticationFilter;
import org.bougainvillea.spring.security.filter.TokenLoginFilter;
import org.bougainvillea.spring.security.secu.DefaultPasswordEncoder;
import org.bougainvillea.spring.security.secu.TokenLogoutHandler;
import org.bougainvillea.spring.security.secu.TokenManager;
import org.bougainvillea.spring.security.secu.UnAuthEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private TokenManager tokenManager;
    private DefaultPasswordEncoder defaultPasswordEncoder;
    private UserDetailsService userDetailsService;

    @Autowired
    public TokenWebSecurityConfig(TokenManager tokenManager, DefaultPasswordEncoder defaultPasswordEncoder, UserDetailsService userDetailsService) {
        this.tokenManager = tokenManager;
        this.defaultPasswordEncoder = defaultPasswordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(new UnAuthEntryPoint())
                .and().csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().logout().logoutUrl("/index/logout")
                .addLogoutHandler(new TokenLogoutHandler(tokenManager))
                .and()
                .addFilter(new TokenLoginFilter(tokenManager,authenticationManager()))
                .addFilter(new TokenAuthenticationFilter(authenticationManager(),tokenManager))
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
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        String[] paths={
                "/index/menu",
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/v2/api-docs",
                "/webjars/**",
                "/666/**"
        };
        web.ignoring().antMatchers(paths);
    }
}
