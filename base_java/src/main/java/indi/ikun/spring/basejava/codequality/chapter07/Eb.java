package indi.ikun.spring.basejava.codequality.chapter07;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 106.动态代理可以使代理模式更加灵活
 *
 * Java的反射框架提供了动态代理（Dynamic Proxy）机制，
 * 允许在运行期对目标类生成代理，避免重复开发。
 * 一个静态代理是通过代理主题角色（Proxy）和具体主题角色（Real Subject）共同实现抽象主题角色（Subject）的逻辑，
 * 只是代理主题角色把相关的执行逻辑委托给了具体主题角色而已
 */
public class Eb {
    public static void main(String[] args) throws ClassNotFoundException {
        /**
         * 静态代理
         */
        //静态代理类
        ProxyEb proxy= new ProxyEb();
        //执行具体主题角色方法
        proxy.request();

        /**
         * 动态代理
         * 实现了不用显式创建代理类即实现代理的功能，
         * 例如可以在被代理角色执行前进行权限判断，或者执行后进行数据校验
         * 动态代理很容易实现通用的代理类，
         *
         * 只要在InvocationHandler的invoke方法中读取持久化数据即可实现，而且还能实现动态切入的效果，
         * 这也是AOP（Aspect Oriented Programming）编程理念
         */
        //具体主题角色，被代理类
        SubjectEb subjectEb=new RealSubjectEb();
        //代理实例的处理Handler
        InvocationHandler handler=new SubjectHandler(subjectEb);
        //当前加载器
        ClassLoader c1=subjectEb.getClass().getClassLoader();
        //动态代理
        SubjectEb proxy1=(SubjectEb) Proxy.newProxyInstance(c1,subjectEb.getClass().getInterfaces(),handler);
        //执行具体主题角色方法
        proxy1.request();
    }
}

/**
 * 抽象主题角色
 */
interface SubjectEb{
    //定义一个方法
    public void request();
}
/**
 * 具体主题角色
 */
class RealSubjectEb implements SubjectEb{
    @Override
    public void request() {
        System.err.println("具体主题角色");
    }
}
//*************静态代理******************start
/**
 * 代理主题角色
 */
class ProxyEb implements SubjectEb{
    //要代理哪个实现类
    private SubjectEb subject=null;
    //默认被代理者
    public ProxyEb() {
        subject = new RealSubjectEb();
    }
    //构造函数传递被代理者
    public ProxyEb(SubjectEb subject) {
        this.subject = subject;
    }
    private void before(){
        System.err.println("before");
    }
    private void after(){
        System.err.println("after");
    }

    @Override
    public void request() {
        before();
        subject.request();
        after();
    }
}
//*************静态代理******************end
/**
 * Java还提供了java.lang.reflect.Proxy用于实现动态代理：
 * 只要提供一个抽象主题角色和具体主题角色，就可以动态实现其逻辑的
 */
//*************动态代理******************start

//*************动态代理******************end
class SubjectHandler implements InvocationHandler{
    //被代理对象
    private SubjectEb subject;

    public SubjectHandler(SubjectEb subject) {
        this.subject = subject;
    }
    private void before(){
        System.err.println("动态before");
    }
    private void after(){
        System.err.println("动态after");
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object obj=method.invoke(subject,args);
        after();
        return obj;
    }
}