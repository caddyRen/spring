package indi.ikun.spring.demospringboot.mybatis.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JSONTestsPO {
    String name;
    String text;
}
