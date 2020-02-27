package indi.ikun.spring.designpattern.pattern.structural.proxy.dynamic;

/**
 * proxy 代理模式
 */
public class Client {


    public static void main(String[] args) {

        //创建目标对象
        ITeacherDao target=new TeacherDao();
        //创建代理对象
        ITeacherDao proxyInstance =(ITeacherDao) new ProxyFactory(target).getProxyInstance();


        System.err.println(proxyInstance);
        // class com.sun.proxy.$Proxy0  $Proxy0表示内存中动态生存代理对象
        System.err.println(proxyInstance.getClass());
        proxyInstance.teach();
        System.err.println(proxyInstance.sayHello("caddy"));

    }
}
