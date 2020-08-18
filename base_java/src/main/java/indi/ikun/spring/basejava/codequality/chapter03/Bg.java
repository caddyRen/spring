package indi.ikun.spring.basejava.codequality.chapter03;

/**
 * 33.不要覆写静态方法
 * 在Java中可以通过覆写（Override）来增强或减弱父类的方法和行为，
 * 但覆写是针对非静态方法（也叫做实例方法，只有生成实例才能调用的方法）的，不能针对静态方法（static修饰的方法，也叫做类方法）
 *
 *一个实例对象有两个类型：表面类型（Apparent Type）和实际类型（ActualType），
 * 表面类型是声明时的类型，
 * 实际类型是对象产生时的类型，
 * 比如我们例子，变量bgA的表面类型是BgA，实际类型是BgAa。
 * 对于非静态方法，它是根据对象的实际类型(BgAa)来执行的，也就是执行了BgAa类中的doAnything方法。
 * 而对于静态方法来说就比较特殊了，
 *      首先静态方法不依赖实例对象，它是通过类名访问的；
 *      其次，可以通过对象访问静态方法，
 *      如果是通过对象调用静态方法，JVM则会通过对象的表面类型(BgA)查找到静态方法的入口，继而执行之。
 *      bgA.doSomething()打印出“我是父类静态方法”
 *
 * 在子类中构建与父类相同的方法名、输入参数、输出参数、访问权限（权限可以扩大），
 * 并且父类、子类都是静态方法，此种行为叫做隐藏（Hide），它与覆写有两点不同
 *      1.表现形式不同。隐藏用于静态方法，覆写用于非静态方法。在代码上的表现是：@Override注解可以用于覆写，不能用于隐藏
 *      2.职责不同。隐藏的目的是为了抛弃父类静态方法，重现子类方法，
 *          例如我们的例子，Sub.doSomething的出现是为了遮盖父类的Base.doSomething方法，
 *          也就是期望父类的静态方法不要破坏子类的业务行为；
 *          而覆写则是将父类的行为增强或减弱，延续父类的职责
 */
public class Bg {

    public static void main(String[] args) {
        BgA bgA=new BgAa();
        bgA.doAnything();
        bgA.doSomething();//执行父类
        BgA.doSomething();//执行父类
        BgAa.doSomething();//执行子类
    }
}
class BgA{
    public static void doSomething(){
        System.err.println("我是父类静态方法");
    }

    public void doAnything(){
        System.err.println("我是父类非静态方法");
    }

}

class BgAa extends BgA{

    public static void doSomething(){
        System.err.println("我是子类静态方法");
    }

    @Override
    public void doAnything() {
        System.err.println("子类非静态方法");
    }
}
