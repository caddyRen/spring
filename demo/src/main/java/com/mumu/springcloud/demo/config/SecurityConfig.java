package com.mumu.springcloud.demo.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @ClassName SecurityConfig
 * @Description TODO
 * @Author caddyR
 * @Date 2019-06-14 11:30
 * @Version 1.0
 **/
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//public class SecurityConfig {
    /*
     *@Author caddyR
     *@Description //定义授权规则
     *@Date 2019-03-06 15:57
     *@Param [httpSecurity]
     *@return void
     **/
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        //super.configure(auth);
        /*
         *@Author caddyR
         *@Description //定制请求的授权规则
         * /level1/**请求必须要有vip1的权限
         * /manage/**请求需要有admin权限
         *
         *@Date 2019-03-06 15:56
         *@Param [httpSecurity]
         *@return void
         **/
        httpSecurity.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/manage/**","/admin/**").hasRole("admin");
        /*
         *@Author caddyR
         *@Description 开启自动配置的登陆功能，效果如果没有登陆，没有权限就会来到登陆页面
         * 1.转到/login来到spring security自己生成的登陆页
         * 2.重定向到/login?error表示登陆失败
         * 3.更多详细规定看文档
         * /login和/login?error是spring自己规定的，不需要配置，当然也可以自定义
         *@Date 2019-03-06 15:51
         *@Param [httpSecurity]
         *@return void
         **/
        //httpSecurity.formLogin();
        //自定义登录页controller
        //security 默认post表单请求是处理认证的；get请求是没登陆的页面跳转到登录页，
        /*登陆页面这样子写
         <div align="center">
            <form th:action="@{/login}" method="post">
                用户名：<input name="userName"/><br>
                密  码：<input name="password"/><br>
                <input type="submit" th:value="登陆">
            </form>
        </div>
        **/
        httpSecurity.formLogin()
                .usernameParameter("userName")
                .passwordParameter("password")
                .loginPage("/userlogin")
                .loginProcessingUrl("/login");

          /*登陆页面这样子写
         <div align="center">
            <form th:action="@{/userLogin}" method="post">
                用户名：<input name="userName"/><br>
                密  码：<input name="password"/><br>
                <input type="submit" th:value="登陆">
            </form>
        </div>
        **/
//        httpSecurity.formLogin()
//                .usernameParameter("userName")
//                .passwordParameter("password")
//                .loginPage("/userlogin");

        //开启自动配置的注销功能，注销成功后来到首页
        //访问/logout表示用户注销，清空session
        //注销成功会默认返回/login?logout页面；加上logoutSuccessUrl("/")会跳转到根页面
        httpSecurity.logout().logoutSuccessUrl("/");

        //开启记住我功能
        //登陆成功后将cookie发送给浏览器保存，以后访问页面会带上这个cookie，只要通过检查就可以实现免登陆
        //点击注销会删除cookie
        httpSecurity.rememberMe();
        //允许自己的页面引用
        httpSecurity.headers().frameOptions().sameOrigin();
        //解决post 403资源不可用问题error
        httpSecurity.csrf().disable();

    }
    /*
     *@Author caddyR
     *@Description //定义认证规则,设置角色，从而控制访问页面
     *@Date 2019-03-06 15:57
     *@Param
     *@return
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //在内存中定义用户名密码和角色，也可以在数据中定义，参考官方文档
        auth.inMemoryAuthentication()
                .withUser("mumu").password("0613").roles("admin","vip1")
                .and()
                .withUser("administrator").password("000000").roles("admin")
                .and()
                .withUser("renqiankun").password("000000").roles("vip1")
                .and()
                .withUser("caddy").password("000000").roles("vip2");
//        super.configure(auth);
    }
}
