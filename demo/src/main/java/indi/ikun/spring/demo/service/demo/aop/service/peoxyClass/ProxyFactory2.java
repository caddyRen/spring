package indi.ikun.spring.demo.service.demo.aop.service.peoxyClass;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName ProxyFactory2
 * @Description TODO
 * @Author caddyR
 * @Date 2019-08-29 15:27
 * @Version 1.0
 **/
public class ProxyFactory2 implements MethodInterceptor {

    private Object target;

    public ProxyFactory2(Object target) {
        this.target = target;
    }

    public Object getProxyInstance(){
        Enhancer en =new Enhancer();
        en.setSuperclass(target.getClass());
        en.setCallback(this);
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.err.println("开始事务");
        Object returnValue =method.invoke(target,objects);
        System.err.println("提交事务");
        return returnValue;
    }
}
