package indi.ikun.spring.basejava.codequality.chapter02;

import java.util.ArrayList;
import java.util.List;

/**
 * 26.提防包装类型的null值
 *
 * Java引入包装类型（Wrapper Types）是为了解决基本类型得实例化问题，以便让一个基本类型也能参与到面向对象的编程世界中。、
 * 而在Java5中泛型更是对基本类型说了“不”，如想把一个整型放到List中，就必须使用Integer包装类型
 *
 *
 * 包装对象和拆箱对象可以自由转换，但是要剔除null值，
 * null值并不能转化为基本类型。
 * 对于此类问题，我们谨记一点：包装类型参与运算时，要做null值校验
 */
public class Az {

    public static void main(String[] args) {
        List<Integer> list=new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(null);
        System.err.println(f(list));
    }


    public static int f(List<Integer> list){
        int count=0;
        /**
         * for循环中，隐含了一个拆箱过程，
         * 在此过程中包装类型Integer转换为了基本类型int。
         * 我们知道拆箱过程是通过调用包装对象的intValue方法来实现的，
         * 由于包装对象是null值，访问其intValue方法报空指针异常也就在所难免了。
         * 问题清楚了，修改也很简单，加入null值检查即可
         */
//        for(int i:list){
//            count+=i;
//        }

        //ok
        for(Integer i:list){
            count+=(i!=null)?i:0;
        }
        return count;
    }
}