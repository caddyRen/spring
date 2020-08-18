package indi.ikun.spring.basejava.codequality.chapter04;


/**
 * 54.正确使用String、StringBuffer、StringBuilder
 * CharSequence接口有三个实现类与字符串有关：String、StringBuffer、StringBuilder，
 * 虽然它们都与字符串有关，但是其处理机制是不同的。
 * 1. String类是不可改变的量，也就是创建后就不能再修改了，
 *      比如创建了一个“abc”这样的字符串对象，那么它在内存中永远都会是“abc”这样具有固定表面值的一个对象，
 *      不能被修改，即使想通过String提供的方法来尝试修改，也是要么创建一个新的字符串对象，要么返回自己
 *      当采用str.substring(0)就不会创建新对象，JVM会从字符串池中返回str的引用，也就是自身的引用
 * 2. StringBuffer是一个可变字符序列，
 *      它与String一样，在内存中保存的都是一个有序的字符序列（char类型的数组），
 *      不同点是StringBuffer对象的值是可改变的
 * 3. StringBuilder与StringBuffer基本相同，都是可变字符序列，
 *      不同点是：StringBuffer是线程安全的，StringBuilder是线程不安全的，
 *      翻翻两者的源代码，就会发现在StringBuffer的方法前都有synchronized关键字，
 *      这也是StringBuffer在性能上远低于StringBuilder的原因
 *
 * 使用场景：
 *  1. String: 字符串不经常变化的场景中可以使用String类，例如常量的声明、少量的变量运算等
 *  2. StringBuffer: 频繁进行字符串的运算（如拼接、替换、删除等），并且运行在多线程的环境中，则可以考虑使用StringBuffer，例如XML解析、HTTP参数解析和封装等
 *  3. StringBuilder: 频繁进行字符串的运算（如拼接、替换、删除等），并且运行在单线程的环境中，则可以考虑使用StringBuilder，如SQL语句的拼装、JSON封装等
 */
public class Cb {
    public static void main(String[] args) {
        String str = "abc";
        String str1 = str.substring(1);
        String str2 = str.substring(0);
        System.err.println(str == str1);
        System.err.println(str == str2);

        /**
         * StringBuffer的对象，它的引用地址不变，但值在改变
         */
        StringBuffer sb=new StringBuffer("a");
        sb.append("b");

        /**
         * 字符串变量s初始化时是“a”对象的引用，
         * 经过加号计算后，s变量就修改为了“ab”的引用，
         * 但是初始化的“a”对象还是没有改变，只是变量s指向了新的引用地址
         */
        String s="a";//"a"对象
        s=s+"b";//“ab”对象


        /**
         * StringBuilder与StringBuffer基本相同，都是可变字符序列，不同点是：StringBuffer是线程安全的，StringBuilder是线程不安全的，
         * 翻翻两者的源代码，就会发现在StringBuffer的方法前都有synchronized关键字，
         * 这也是StringBuffer在性能上远低于StringBuilder的原因
         */
        StringBuilder stringBuilder=new StringBuilder("a");
        stringBuilder.append("c");
    }
}
