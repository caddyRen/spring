package indi.ikun.spring.basejava.codequality.chapter05;

import java.util.ArrayList;
import java.util.Vector;

/**
 * 69.列表相等只需关心元素数据
 *  注意：  判断集合是否相等时只须关注元素是否相等即可
 *
 * 只要实现了List接口，不关心List的具体实现类。
 * 只要所有的元素相等，并且长度也相等   就表明两个List是相等的，
 * 与具体的容量类型无关。上面的例子中虽然一个是ArrayList，一个是Vector，只要里面的元素相等，那结果就是相等
 *
 * 其他的集合类型，如Set、Map等与此相同，也是只关心集合元素，不用考虑集合类型。
 */
public class Cq {
    public static void main(String[] args) {
        ArrayList<String> strs=new ArrayList<>();
        strs.add("A");
        Vector<String> str2=new Vector<>();
        str2.add("A");
        /**
         * ArrayList和 Vector两者都是列表（List），都实现了List接口，也都继承了AbastractList抽象类
         * equals方法是在AbstractList中定义的
         * public boolean equals(Object o) {
         *         if (o == this)
         *             return true;
         *         //是否是List列表，只要实现List接口即可
         *         if (!(o instanceof List))
         *             return false;
         *         //迭代器访问list所有元素
         *         ListIterator<E> e1 = listIterator();
         *         ListIterator<?> e2 = ((List<?>) o).listIterator();
         *         //遍历两个list元素
         *         while (e1.hasNext() && e2.hasNext()) {
         *             E o1 = e1.next();
         *             Object o2 = e2.next();
         *             //只要存在着不相等的就退出
         *             if (!(o1==null ? o2==null : o1.equals(o2)))
         *                 return false;
         *         }
         *         //长度是否也相等
         *         return !(e1.hasNext() || e2.hasNext());
         *     }
         */
        System.err.println(strs.equals(str2));//true
    }
}
