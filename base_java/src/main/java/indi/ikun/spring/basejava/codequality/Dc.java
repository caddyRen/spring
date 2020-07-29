package indi.ikun.spring.basejava.codequality;

import java.util.*;

/**
 * 81.非稳定排序推荐a使用List
 *
 * Set与List的最大区别就是Set中的元素不可以重复（这个重复指的equals方法的返回值相等），其他方面则没有太大的区别了，
 * 在Set的实现类中有一个比较常用的类需要了解一下：
 * TreeSet，该类实现了类默认排序为升序的Set集合，
 * 如果插入一个元素，默认会按照升序排列（当然是根据Comparable接口的compareTo的返回值确定排序位置了）
 */
public class Dc {
    public static void main(String[] args) {
        SortedSet<PersonDc> set=new TreeSet<>();
        set.add(new PersonDc(180));
        set.add(new PersonDc(175));
        set.first().setHeight(185);
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
