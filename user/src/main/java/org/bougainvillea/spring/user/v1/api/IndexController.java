package org.bougainvillea.spring.user.v1.api;

import org.bougainvillea.spring.commons.utils.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/index")
public class IndexController {

    @GetMapping("info")
    public Result info(){
        //获取当前登陆用户
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        return Result.ok().data("username",username);
    }


    @GetMapping("menu")
    public Result menu(){
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        List<String> permissionList= Arrays.asList("a","b");
        return Result.ok().data("permissionList",permissionList);
    }

    @PostMapping("logout")
    public Result logout(){
        return Result.ok();
    }

}
