package indi.ikun.spring.basejava.codequality;



/**
 * 29.优先选择基本类型
 * 包装类型是一个类，它提供了诸如构造方法、类型转换、比较等非常实用的功能，
 * 在Java 5之后实现了与基本类型之间的自动转换，在开发中包装类型已经随处可见，
 * 但无论是从安全性、性能方面来说，还是从稳定性方面来说，基本类型都是首选方案
 *
 *自动装箱有一个重要的原则：
 *      基本类型可以先加宽，再转变成宽类型的包装类型，但不能直接转变成宽类型的包装类型
 */
public class Bc {

    public static void main(String[] args) {
        Bc bc = new Bc();
        int i=140;
        //分别传递int类型，和Integer类型
        //int可以加宽转变成long，然后再转变成Long对象，但不能直接转变成包装类型Long
        bc.f(i);
        /**
         * i通过valueOf方法包装成一个Integer对象
         * 由于没有f(Integer i)方法，编译器“聪明”地把Integer对象转换成int
         * int自动拓宽为long，编译结束。
         */
        bc.f(Integer.valueOf(i));


        BcF bcF = new BcF();
        //i是一个int类型，不能自动转变为Long型
//        bcF.f(i);
    }

    public void f(long a){
        System.err.println("基本类型");
    }

    public void f(Long a){
        System.err.println("包装类型的方法被调用");
    }
}

class BcF{
    public void f(Long a){
        System.err.println("包装类型的方法被调用");
    }
}

