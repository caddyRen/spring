package org.bougainvillea.spring.security.secu;

import org.bougainvillea.spring.commons.utils.Result;
import org.bougainvillea.spring.security.utils.ResponseUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登出移除token
 */
public class TokenLogoutHandler implements LogoutHandler {

    private TokenManager tokenManager;

    public TokenLogoutHandler(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        //从header里面获取token
        String token=request.getHeader("token");
        //token不为空，移除token，从redis删除
        if(token!=null){
            //移除token
            String username=tokenManager.getUserFromToken(token);
            //redis移除用户token
        }
        ResponseUtil.out(response, Result.ok());
    }
}
