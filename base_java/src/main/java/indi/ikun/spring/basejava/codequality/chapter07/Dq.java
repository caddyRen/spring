package indi.ikun.spring.basejava.codequality.chapter07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 95.强制声明泛型的实际类型
 *
 * 无法从代码中推断出泛型类型的情况下，即可强制声明泛型类型
 *
 * Arrays工具类有一个方法asList可以把一个变长参数或数组转变为列表，
 * 但是它有一个缺点：它所生成的List长度是不可改变的。
 * 如果期望生成的列表长度是可变，那就需要自己来写一个数组的工具类
 */
public class Dq {

    public static void main(String[] args) {
        /**
         *变量list1是一个常规用法，没有任何问题，
         * 泛型实际的参数类型是String，返回的结果也就是一个容纳String元素的List对象
         */
        List<String> list=ArrayUtilsDq.asList("A","B");
        display(list);
        /**
         *变量list2中容纳的是什么元素呢？
         * 我们无法从代码中推断出list2列表到底容纳的是什么元素
         * （因为它传递的参数是空，编译器也不知道泛型的实际参数类型是什么），
         * 不过，编译器会很“聪明”地推断出最顶层类Object就是其泛型类型
         */
        List list1=ArrayUtilsDq.asList();
        List<Integer> list11=ArrayUtilsDq.<Integer>asList();
        display(list1);
        /**
         *变量list3有两种类型的元素：整数类型和浮点类型，
         * 那它生成的List泛型化参数应该是什么呢？
         * 是Integer和Float的父类Number？
         * 你太高看编译器了，它不会如此推断的，
         * 当它发现多个元素的实际类型不一致时就会直接确认泛型类型是Object，
         * 而不会去追索元素类的公共父类是什么，
         * 但是对于list3，我们更期望它的泛型参数是Number，都是数字嘛！参照list2变量，代码修改如下
         */
        List list2=ArrayUtilsDq.asList(1,2,3.1);
        List<Number> list22=ArrayUtilsDq.<Number>asList(1,2,3.1);
        display(list2);
        display(list22);

    }
    public static void display(List list){
        for (int i=0;i<list.size();i++){
            System.err.println(list.get(i));
        }
    }
}
class ArrayUtilsDq{
    /**
     * 把一个变长参数变为列表，并且长度可变
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List<T> asList(T...t){
        List<T> list=new ArrayList<>();
        Collections.addAll(list,t);
        return list;
    }
}
