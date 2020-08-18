package indi.ikun.spring.basejava.codequality.chapter05;

import java.util.*;

/**
 * 81.非稳定排序推荐a使用List
 *
 * 注意：SortedSet中的元素被修改后可能会影响其排序位置
 *
 * Set与List的最大区别就是Set中的元素不可以重复（这个重复指的equals方法的返回值相等），其他方面则没有太大的区别了，
 * 在Set的实现类中有一个比较常用的类需要了解一下：
 * TreeSet，该类实现了类默认排序为升序的Set集合，
 * 如果插入一个元素，默认会按照升序排列（当然是根据Comparable接口的compareTo的返回值确定排序位置了）
 *
 * SortedSet接口（TreeSet实现了该接口）只是定义了在给集合加入元素时将其进行排序，
 * 并不能保证元素修改后的排序结果，
 * 因此TreeSet适用于不变量的集合数据排序，比如String、Integer等类型，
 * 但不适用于可变量的排序，特别是不确定何时元素会发生变化的数据集合
 *
 * 解决重排序
 * 1.Set集合重排序 :重新生成Set对象
 * 2.彻底重构掉TreeSet，使用List解决问题
 *      之所以使用TreeSet是希望实现自动排序，即使修改也能自动排序，
 *      既然它无法实现，那就用List来代替，然后再使用Collections.sort()方法对List排序
 *
 * 建议
 *  对于不变量的排序，例如直接量（也就是8个基本类型）、String类型等，推荐使用TreeSet，
 *  对于可变量，例如我们自己写的类，可能会在逻辑处理中改变其排序关键值的，则建议使用List自行排序
 *
 * 保证集合中元素的唯一性，又要保证元素值修改后排序正确
 * List不能保证集合中的元素唯一，它是可以重复的，而Set能保证元素唯一，不重复。
 * 如果采用List解决排序问题，就需要自行解决元素重复问题（若要剔除也很简单，转变为HashSet，剔除后再转回来）。
 * 若采用TreeSet，则需要解决元素修改后的排序问题，孰是孰非，就需要根据具体的开发场景来决定了
 */
public class Dc {
    public static void main(String[] args) {
        SortedSet<PersonDc> set=new TreeSet<>();
        set.add(new PersonDc(180));
        set.add(new PersonDc(175));
        //修改元素不会重新排序
        set.first().setHeight(185);
        for (PersonDc p :
                set) {
            System.err.println(p.getHeight());
        }
    }

}

/**
 * TreeSet(SortedSet<E> s)这个构造函数只是原Set的浅拷贝，如果里面有相同的元素，是不会重新排序的
 */
class Dc1 {
    public static void main(String[] args) {
        SortedSet<PersonDc> set=new TreeSet<>();
        set.add(new PersonDc(180));
        set.add(new PersonDc(175));
        //修改元素不会重新排序
        set.first().setHeight(185);
        //重新排序
        set=new TreeSet<>(new ArrayList<>(set));
        //浅拷贝不重新排序
//        set=new TreeSet<>(set);

        for (PersonDc p :
                set) {
            System.err.println(p.getHeight());
        }
    }

}


class PersonDc implements Comparable<PersonDc>{
    private int height;

    @Override
    public int compareTo(PersonDc o) {
        return height-o.height;
    }

    public PersonDc(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
