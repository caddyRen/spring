package org.bougainvillea.spring.security.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {
    private Boolean success;
    private Integer code;
    private String message;
    private Map<String,Object> data=new HashMap<>();

    private Result(){}

    public static Result ok(){
        Result result = new Result();
        result.success=true;
        result.code=2000;
        result.message="success";
        return result;
    }
    public static Result error(){
        Result result = new Result();
        result.success=false;
        result.code=2001;
        result.message="fail";
        return result;
    }
    public Result data(String key, Object obj){
       this.data.put(key,obj);
       return this;
    }
}
