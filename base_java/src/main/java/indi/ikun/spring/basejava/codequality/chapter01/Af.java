package indi.ikun.spring.basejava.codequality.chapter01;

/**
 * 6.覆写变长方法也循规蹈矩
 * 子类覆写父类中的方法，可以修正bug，也可以提供扩展的业务功能支持，同时还符合ocp原则（open Closed Principle）
 * 覆写必须满足的条件
 * 1.重写方法不能缩小访问权限
 * 2.参数列表必须与被重写方法相同
 *      1.参数数量相同
 *      2.类型相同
 *      3.顺序相同
 *      4.显示形式
 * 3.返回类型必须与被重写方法的相同或者是其子类
 * 4.重写方法不能抛出新的异常，或者抛出超出父类异常范围的异常，但是可以抛出更少，更有限的异常，或者不抛出异常
 */
public class Af {
    public static void main(String[] args) {
        //向上转型
        //subAfBase对象把子类SubAfBase对象做了向上转型，形参列表是由父类决定的，即变长数组
        // 编译时subAfBase.fun(100,50)中的50会被编译成{50}数组，再由子类执行，
        AfBase subAfBase = new SubAfBase();
        subAfBase.fun(100,50);

        //不转型 有问题的覆写方式，子类参数类型是int[] 故编译错误
        //对比向上转型，编译器不会把50做类型转换，而是编译直接报错
        SubAfBase subAfBase2=new SubAfBase();
//        subAfBase2.fun(100,50);

        //不转型，正确的覆写方式
        SubAfBase1 subAfBase1=new SubAfBase1();
        subAfBase1.fun(100,50);

    }
}
class AfBase{
    void fun(int price,int... discounts){
        System.err.println("123");
    }
}

//覆写方法参数显示形式已父类方法不一致，向上转型
class SubAfBase extends AfBase{
    @Override
    void fun(int price, int[] discounts) {
        System.err.println("123-123");
    }
}

class SubAfBase1 extends AfBase{
    @Override
    void fun(int price, int... discounts) {
        System.err.println("123-234");
    }
}