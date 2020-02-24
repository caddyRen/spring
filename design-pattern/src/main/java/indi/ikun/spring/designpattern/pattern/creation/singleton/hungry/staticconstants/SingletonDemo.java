package indi.ikun.spring.designpattern.pattern.creation.singleton.hungry.staticconstants;

/**
 * @Description:
 * @Author caddy
 * @date 2020-02-07 11:33:49
 * @version 1.0
 */
public class SingletonDemo {

    public static void main(String[] args) {
        Demo d1=Demo.getInstance();
        Demo d2=Demo.getInstance();

        System.err.println(d1==d2);
        System.err.println(d1.hashCode());
        System.err.println(d2.hashCode());
    }
}

/**
 * @Description: 单例模式 饿汉式 静态常量 例如： java.lang.Runtime
 * @Author caddy
 * @date 2020-02-07 11:44:15
 * @version 1.0
 */
class Demo{

    private Demo(){}

    private static Demo instance=new Demo();

    public static Demo getInstance() {
        return instance;
    }
}