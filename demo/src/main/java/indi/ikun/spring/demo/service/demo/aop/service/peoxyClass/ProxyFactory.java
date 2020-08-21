package indi.ikun.spring.demo.service.demo.aop.service.peoxyClass;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName ProxyFactory
 * @Author caddyR
 * @Date 2019-08-29 15:15
 * @Version 1.0
 **/
public class ProxyFactory {

    //维护目标对象
    private Object target;

    public ProxyFactory(Object target){
        this.target=target;
    }

    //给目标对象创建一个代理对象
    public Object getProxyInstance(){
        //工具类
        Enhancer en=new Enhancer();
        //设置父类
        en.setSuperclass(target.getClass());
        //设置回调函数
        en.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.err.println("开始");
                //执行目标的方法
                Object returenValue=method.invoke(target,objects);
                System.err.println("end");
                return returenValue;
            }
        });
        //创建代理对象
        return en.create();
    }

}
