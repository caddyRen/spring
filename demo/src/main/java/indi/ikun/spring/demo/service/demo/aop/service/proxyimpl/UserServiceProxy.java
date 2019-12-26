package indi.ikun.spring.demo.service.demo.aop.service.proxyimpl;

import indi.ikun.spring.demo.service.demo.aop.service.IUserServ;

/**
 * @ClassName UserServiceProxy
 * @Description TODO
 * @Author caddyR
 * @Date 2019-08-29 10:06
 * @Version 1.0
 **/
public class UserServiceProxy implements IUserServ {

    private IUserServ target;

    public UserServiceProxy(IUserServ iUserServ){
        this.target=iUserServ;
    }

    @Override
    public void save() {
        System.err.println("start...");
        target.save();
        System.err.println("end...");

    }
}
