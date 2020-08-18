package indi.ikun.spring.basejava.codequality.chapter05;

import java.util.Arrays;
import java.util.List;

/**
 * 66.asList方法产生的List对象不可更改
 * 注意：除非非常自信该List只用于读操作，否则不可以使用asList初始化List
 *
 * asList 直接new一个静态私有内部类ArrayList
 *      private static class ArrayList<E> extends AbstractList<E>
 * 此ArrayList非java.util.ArrayList，而是Arrays工具类的一个内置类
 * 这里的ArrayList是一个静态私有内部类，除了Arrays能访问外，其他类都不能访问。
 * 这个类没有提供add方法，那肯定是父类AbstractList提供（父类确实提供了，但没有提供具体的实现直接抛出异常）
 *      public void add(int index, E element) {
 *             throw new UnsupportedOperationException();
 *      }
 * ArrayList静态内部类，它没有实现List.add和List.remove方法
 *
 * 也就是说asList返回的是一个长度不可变的列表，
 * 数组是多长，转换成的列表也就是多长，
 * 换句话说此处的列表只是数组的一个外壳，不再保持列表动态变长的特性
 */
public class Cn {
    public static void main(String[] args) {
        Week[] workDays = {
                Week.Mon, Week.Tue, Week.Wed, Week.Thu, Week.Fri
        };
        List<Week> list = Arrays.asList(workDays);
        list.add(Week.Sat);
        //Exception in thread "main" java.lang.UnsupportedOperationException
        //一句话完成了列表的定义和初始化，看似很便捷，却深藏着重大隐患----列表长度无法修改
        List<String> names=Arrays.asList("李","2","3");
    }
}

enum Week {
    Sun, Mon, Tue, Wed, Thu, Fri, Sat
}