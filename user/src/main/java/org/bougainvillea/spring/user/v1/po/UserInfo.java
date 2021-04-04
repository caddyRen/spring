package org.bougainvillea.spring.user.v1.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo implements Serializable {
    private static final long serialVersionUID=1L;
    int id;
    String name;
    List<String> role;
    List<String> permission;
}
