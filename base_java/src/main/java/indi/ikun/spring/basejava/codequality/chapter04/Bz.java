package indi.ikun.spring.basejava.codequality.chapter04;


/**
 * 52.推荐使用String直接量赋值
 * 一般对象都是通过new关键字生成的，
 * 但是String还有第二种生成方式，
 * 也就是我们经常使用的直接声明方式，比如Str str = "a"，
 * 即是通过直接量“a”进行赋值的。
 * 对于String对象来说，这种方式是极力推荐的，
 * 但不建议使用newString("a")的方式赋值
 *
 *
 * Java为了避免在一个系统中大量产生String对象（为什么会大量产生？因为String字符串是程序中最经常使用的类型），
 * 设计了一个字符串池（也有叫做字符串常量池，String Pool或String Constant Pool 或 StringLiteral Pool），
 * 在字符串池中所容纳的都是String字符串对象，它的创建机制是这样的：
 *      创建一个字符串时，首先检查池中是否有字面值相等的字符串，
 *      如果有，则不再创建，直接返回池中该对象的引用，
 *      若没有则创建之，然后放到池中，并返回新建对象的引用，
 *      这个池和我们平常所说的池概念非常相似。
 * 直接声明一个String对象是不检查字符串池的，也不会把对象放到池中，那当然“==”为false
 * intern会检查当前的对象在对象池中是否有字面值相同的引用对象，
 *      如果有则返回池中对象，
 *      如果没有则放置到对象池中，并返回当前对象
 * 线程安全：
 * String类是一个不可变（Immutable）对象其实有两层意思：
 *      一是String类是final类，不可继承，不可能产生一个String的子类；
 *      二是在String类提供的所有方法中，如果有String返回值，就会新建一个String对象，不对原对象进行修改，这也就保证了原对象是不可改变的。
 *
 * Java的每个对象都保存在堆内存中，
 * 但是字符串池非常特殊，它在编译期已经决定了其存在JVM的常量池（Constant Pool），
 * 垃圾回收器是不会对它进行回收的
 */
public final class Bz {
    public static void main(String[] args) {
        /**
         * 对于此例子来说，就是在创建第一个“中国”字符串时，先检查字符串池中有没有该对象，发现没有，于是就创建了“中国”这个字符串并放到池中，
         * 待再创建str2字符串时，由于池中已经有了该字符串，于是就直接返回了该对象的引用，
         * 此时，str1和str2指向的是同一个地址，所以使用“==”来判断那当然是相等的了
         */
        String str1 = "中国";
        String str2 = "中国";

        //直接声明一个String对象是不检查字符串池的，也不会把对象放到池中，那当然“==”为false
        String str3 = new String("中国");
        /**
         * intern会检查当前的对象在对象池中是否有字面值相同的引用对象，
         *  如果有则返回   池中对象，
         *  如果没有则放置到对象池中，并返回当前对象
         */
        String str4 = str3.intern();//返回了池中对象
        //两个直接量引用地址是否相等
        boolean b1=(str1==str2);
        //直接量和对象引用地址是否相等
        boolean b2=(str1==str3);
        //经过intern处理后的对象与直接量的引用地址是否相等
        boolean b3=(str1==str4);
        System.err.println(b1);
        System.err.println(b2);
        System.err.println(b3);
    }
}
