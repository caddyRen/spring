package indi.ikun.spring.basejava.codequality.chapter05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 72.生成子列表后不要再操作原列表
 *
 * subList的其他会检测修改计数器的方法，例如size、set、get、add等方法，
 * 若生成子列表后，再修改原列表，这些方法也会抛出ConcurrentModificationException异常
 *
 * 对于子列表操作，因为视图是动态生成的，生成子列表后再操作原列表，必然会导致“视图”的不稳定，
 * 最有效的办法就是通过Collections.unmodifiableList方法设置列表为只读状态
 *
 *
 * 数据库的一张表可以有很多视图，
 * List也可以有多个视图，也就是可以有多个子列表，
 * 但问题是只要生成的子列表多于一个，
 * 则任何一个子列表就都不能修改了，否则就会抛出ConcurrentModificationException异常
 *
 */
public class Ct {
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        List<String> sub=list.subList(0,2);
        //设置源列表为只读状态
        //修改源表会抛异常
        //Exception in thread "main" java.lang.UnsupportedOperationException
        list= Collections.unmodifiableList(list);

        System.err.println(sub.size());
        list.add("D");
        System.err.println(list.size());
        /**
         * Exception in thread "main" java.util.ConcurrentModificationException
         * subList取出的列表是原列表的一个视图，
         * 原数据集（代码中的list变量）修改了，
         * 但是subList取出的子列表不会重新生成一个新列表（这点与数据库视图是不相同的），
         * 后面在对子列表继续操作时，就会检测到修改计数器与预期的不相同，于是就抛出了并发修改异常
         */
        System.err.println(sub.size());
    }

}
