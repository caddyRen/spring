package indi.ikun.spring.basejava.codequality.chapter03;

import java.util.ArrayList;
import java.util.List;

/**
 * 39.使用匿名类的构造函数
 *
 * 匿名函数虽然没有名字，但也是可以有构造函数的，它用构造函数块来代替，
 * 那下面的3个输出就很清楚了：虽然父类相同，但是类还是不同的
 */
public class Bm {

    public static void main(String[] args) {
        List l1=new ArrayList();
        List l2=new ArrayList(){};
        List l3=new ArrayList(){{}};

        System.err.println(l1.getClass()==l2.getClass());
        System.err.println(l1.getClass()==l3.getClass());
        System.err.println(l2.getClass()==l3.getClass());

        /**
         * l2代表的是一个匿名类的声明和赋值，它定义了一个继承于ArrayList的匿名类，只是没有任何的覆写方法而已
         */
        class SubBm extends ArrayList{}
        List l22=new SubBm(){};

        /**
         * l3代表的是一个匿名类的声明和赋值，它定义了一个继承于ArrayList的匿名类，只是没有任何的覆写方法而已
         * 与l2相比多了一个初始化块而已，起到构造函数的功能。
         * 一个类肯定有一个构造函数，且构造函数的名称和类名相同，
         * 那问题来了：匿名类的构造函数是什么呢？它没有名字呀！
         * 很显然，初始化块就是它的构造函数。当然，一个类中的构造函数块可以是多个，也就是说可以出现如下代码：
         * List l3=new ArrayList(){{}{}{}{}{}{}{}};
         */
        class SubBm3 extends ArrayList{}
        List l33=new SubBm3(){
            {
                //静态代码块
            }
        };

    }


}
