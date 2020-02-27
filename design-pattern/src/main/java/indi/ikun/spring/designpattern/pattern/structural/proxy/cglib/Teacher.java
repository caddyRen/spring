package indi.ikun.spring.designpattern.pattern.structural.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 被代理类
 */
public class Teacher {

    public String teach(){
        System.err.println("Teacher is running");
        return "嘿嘿";
    }

    /**
     * 无法使用对象调用static方法
     */
    // public static String teach2(){
    //     System.err.println(" static Teacher is running");
    //     return "static";
    // }

    /**
     * final 无法被代理
     * @return
     */
    // public final String teach3(){
    //     System.err.println(" final Teacher is running");
    //     return "final";
    // }
}

/**
 * cglib 代理
 */
class ProxyFactory implements MethodInterceptor {

    //维护一个目标对象
    Object target;

    //构造器传入目标对象
    public ProxyFactory(Object target) {
        this.target = target;
    }

    //返回一个代理对象 target对象的代理对象
    public Object getProxyInstance(){
        //1. 创建一个工具类
        Enhancer enhancer = new Enhancer();
        //2. 设置父类
        enhancer.setSuperclass(target.getClass());
        //3. 设置回调函数
        enhancer.setCallback(this);
        //4. 创建子类对象，即代理对象
        return enhancer.create();
    }

    /**
     *
     * @param o 被代理类
     * @param method 被代理的方法，
     * @param objects 被代理的方法的参数
     * @param methodProxy
     * @return 返回的是代理模式修饰后的被代理方法返回值
     * @throws Throwable
     * 重写intercept方法，会调用目标对象的方法
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.err.println("cglib代理模式--前");
        Object invoke = method.invoke(target, objects);
        System.err.println("cglib代理模式--后");
        return invoke;
    }
}
