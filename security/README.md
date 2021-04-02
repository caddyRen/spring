# spring security

# Help

## 本质是一个过滤器链，十多个过滤器

- FilterSecurityInterceptor 方法级的权限过滤器，基本位于过滤链的最低部
- ExceptionTranslationFilter 异常过滤器用来处理在认证授权过程中抛出的异常
- UsernamePasswordAuthenticationFilter 对/login的POST请求做拦截，获取并验证表单中的用户名密码

## 过滤器加载 Springboot对SpringSecurity进行了自动化配置

1. DelegatingFilterProxy的doFilter()调用initDelegate(),initDelegate()内获取一个FilterChainProxy
2. FilterChainProxy的doFilterInternal()内有List<Filter> filters保存过滤器链

## 重要接口 自定义
### UserDetailService
1. 默认配置时，帐号密码都是SpringSecurity定义生成的，而在实际项目中帐号和密码都是从数据库中查询出来的，可以通过此接口自定义认证逻辑
2. 查询逻辑在此接口实现类中编写
3. 获取用户名密码，继承UsernamePasswordAuthenticationFilter，重写三个方法
4. 创建类实现UserDetailService编写查询数据逻辑返回org.springframework.security.core.userdetails.User对象
### PasswordEncoder
1. 数据加密接口 用于返回User对象里面密码加密

## CSRF/XSRF Cross site request forgery 也称 one-click attack或者 session riding
- 一种挟持用户当前已登陆的web应用程序上执行非本意的操作的攻击
- 与XSS比较，XSS是利用用户对指定网站的信任
- CSRF 利用的是网站对用户网页浏览器的信任
- 攻击者通过技术手段欺骗用户浏览器去访问一个用户认证过的网站并运行一些操作（发邮件发消息转帐购买等）
  浏览器曾将认证过，所以被访问的网站会认为是真正的用户操作
- 简单的身份验证只能保证请求发自某个用户的浏览器，却不能保证请求本身是用户自愿发出的
- SpringSecurity4.0开始默认开启CSRF防护，针对PATCH、POST、PUT、DELETE方法进行防护

```curl
gateway

curl -H 'Content-Type:application/json' -X POST -d '{"username":"caddy","password":"123"}'  http://127.0.0.1:9997/login
curl -H 'token:eyJhbGciOiJIUzI1NiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJSSk5MSalU0lFKrShQsjI0MzSzNLIwMDesBQBghJkSIAAAAA.OYlQtSLFhq0epfXoybQS4k_7pHOvOld31qbcYQBMfaQ' -X GET  http://127.0.0.1:9997/index/info


curl -H 'Content-Type:application/json' -X POST -d '{"username":"caddy","password":"123"}'  http://127.0.0.1:8086/login
curl -H 'token:eyJhbGciOiJIUzI1NiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJSSk5MSalU0lFKrShQsjI0MzQzNTc3MzCqBQDs8q0MIAAAAA.yUOI5Lig0rJyRwEFVgn5E9xI-bPvAC1WqywVEhQQDVc' -X GET  http://127.0.0.1:8086/index/info
curl -H 'token:eyJhbGciOiJIUzI1NiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJSSk5MSalU0lFKrShQsjI0MzQwM7S0NDSvBQCuMpW2IAAAAA.fm2X2vj5qtp3mz5jWmiaFOmsn_bLsb9nJmpOvxJWI8Y' -X GET  http://127.0.0.1:8086/index/menu
```
## 使用说明
1. 引入security模块
```groovy
implementation(project(":security"))
```
2. 启动类开启注入security相关类
```java
@SpringBootApplication(scanBasePackages = "org.bougainvillea.spring")
```
3. 自定义 UserDetailService
```java
package org.bougainvillea.spring.user.v1.service.impl;

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

```


