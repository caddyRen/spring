package indi.ikun.spring.demo.service.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @ClassName Person
 * @Description TODO
 * @Author caddyR
 * @Date 2019-07-02 15:02
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @ApiModelProperty(
            value="姓名",
            example = "qk",
            required = true
    )
    public String name;
    @ApiModelProperty(
            value="年龄",
            example = "18",
            required = true
    )
    public Integer age;
    @JsonIgnore
    public List list;
    @JsonIgnore
    public Map map;
    @ApiModelProperty(
            value="极光appKey",
            example = "gaghugogidnjusieahsi",
            required = true
    )
    public Company company;


}
