package indi.ikun.spring.designpattern.pattern.structural.proxy.cglib;

/**
 * proxy 代理模式
 */
public class Client {


    public static void main(String[] args) {
        Teacher teacher=new Teacher();
        ProxyFactory proxyFactory=new ProxyFactory(teacher);
        Teacher proxyInstance = (Teacher)proxyFactory.getProxyInstance();

        // System.err.println(proxyInstance);
        System.err.println(proxyInstance.getClass());
        System.err.println(proxyInstance.teach());
        // System.err.println(proxyInstance.teach2());
        // System.err.println(proxyInstance.teach3());
    }
}
