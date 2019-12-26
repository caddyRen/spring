package indi.ikun.spring.demo.service.demo.aop.service.impl;

import indi.ikun.spring.demo.service.demo.aop.service.IUserServ;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author caddyR
 * @Date 2019-08-29 10:05
 * @Version 1.0
 **/
public class UserServiceImpl implements IUserServ {

    @Override
    public void save() {
        System.err.println("userServiceImpl");
    }
}
