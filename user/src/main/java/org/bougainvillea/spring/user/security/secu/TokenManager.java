package org.bougainvillea.spring.user.security.secu;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 生成token
 * 根据token获取用户
 *
 * 无需删除token 前端不携带即可
 * 过期token验证不通过
 */
@Component
public class TokenManager {

    //token有效时长
    private long tokenExpiration=24*60*60*1000;
    //编码密钥
    private String tokenSignKey="123456";

     //根据用户名生成token
    public String createToken(String userName){
        String token= Jwts.builder().setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis()+tokenExpiration))
                .signWith(SignatureAlgorithm.HS256,tokenSignKey)
                .compressWith(CompressionCodecs.GZIP).compact();
        return token;
    }

    //根据token获取用户信息
    public String getUserFromToken(String token){
        String user=Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getSubject();
        return user;
    }

}
