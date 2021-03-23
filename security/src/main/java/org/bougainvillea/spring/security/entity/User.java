package org.bougainvillea.spring.security.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
    private static final long serialVersionUID=1L;
    private String username;
    private String password;
    private String nickName;//昵称
    private String salt;//用户头像
    private String token;//用户签名

}
