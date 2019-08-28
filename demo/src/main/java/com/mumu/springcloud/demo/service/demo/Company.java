package com.mumu.springcloud.demo.service.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @ClassName Company
 * @Description TODO
 * @Author caddyR
 * @Date 2019-07-02 15:14
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @ApiModelProperty(
            value="姓名",
            example = "caddy",
            required = true
    )
    public String name;
    @ApiModelProperty(
            value="位置",
            example = "gz",
            required = true
    )
    public String location;
    @JsonIgnore
    public List list;
    @JsonIgnore
    public Map  map;
}
