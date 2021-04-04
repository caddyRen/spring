package org.bougainvillea.spring.user.security.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bougainvillea.spring.commons.utils.Result;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {

    private ResponseUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void out(HttpServletResponse response, Result result){
        ObjectMapper mapper=new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=UTF-8");
//        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try{
            mapper.writeValue(response.getWriter(),result);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
