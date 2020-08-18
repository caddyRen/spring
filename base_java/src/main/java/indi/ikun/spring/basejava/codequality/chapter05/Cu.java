package indi.ikun.spring.basejava.codequality.chapter05;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 73.使用Comparator进行排序
 *注意： Comparable接口可以作为实现类的默认排序法，Comparator接口则是一个类的扩展排序工具
 *
 * JDK已经为我们提供了很多的排序算法
 *
 * 在Java中，要想给数据排序，有两种实现方式，
 * 一种是实现Comparable接口，
 * 一种是实现Comparator接口
 *
 * compareTo返回负数，表明当前值比对比值小，零表示相等，正数表明当前值比对比值大
 * 两个参数调换了一下位置，也就是compareTo的返回值与之前正好相反，
 * 再使用Collections.sort进行排序，顺序也就相反了，这样就实现了倒序
 *
 * 实现了Comparable接口的类表明自身是可比较的，有了比较才能进行排序；
 * 而Comparator接口是一个工具类接口，它的名字（比较器）也已经表明了它的作用：
 * 用作比较，它与原有类的逻辑没有关系，只是实现两个类的比较逻辑，
 * 从这方面来说，一个类可以有很多的比较器，只要有业务需求就可以产生比较器，有比较器就可以产生N多种排序，
 * 而Comparable接口的排序只能说是实现类的默认排序算法，
 * 一个类稳定、成熟后其compareTo方法基本不会改变，也就是说一个类只能有一个固定的、由compareTo方法提供的默认排序算法
 */
public class Cu {
    public static void main(String[] args) {
        List<EmployeeCu> list = new ArrayList<>(5);
        list.add(new EmployeeCu(1001, "张三", PositionCu.Boss));
        list.add(new EmployeeCu(1006, "赵七", PositionCu.Manager));
        list.add(new EmployeeCu(1003, "王五", PositionCu.Manager));
        list.add(new EmployeeCu(1002, "李四", PositionCu.Staff));
        list.add(new EmployeeCu(1005, "马六", PositionCu.Staff));
        //按照id排序调用的是EmployeeCu的compareTo排序算法
        System.err.println("按id排序");
        Collections.sort(list);
        for (EmployeeCu employeeCu : list) {
            System.err.println(employeeCu);
        }
        System.err.println("需求变更按职位排序");
        //需求变更按职位排序
        //普通实现类写法
        System.err.println("普通实现类写法");
        Collections.sort(list, new PositionComparator());
        for (EmployeeCu employeeCu : list) {
            System.err.println(employeeCu);
        }
        //匿名内部类写法倒序
        System.err.println("匿名内部类写法");
        Collections.sort(list, Collections.reverseOrder(new Comparator<EmployeeCu>() {
            @Override
            public int compare(EmployeeCu o1, EmployeeCu o2) {
                return o1.getPosition().compareTo(o2.getPosition());
            }
        }));
        for (EmployeeCu employeeCu : list) {
            System.err.println(employeeCu);
        }
        //lambda写法更高级写法comparing
        System.err.println("lambda写法更高级写法comparing");
        Collections.sort(list, Comparator.comparing(EmployeeCu::getPosition));
        for (EmployeeCu employeeCu : list) {
            System.err.println(employeeCu);
        }
        //lambda写法
        //先按照职位排序，职位相同再按照工号排序
        //先判断职位是否相等，相等的话再根据工号排序
        System.err.println("lambda:先按照职位排序，职位相同再按照工号排序");
        Collections.sort(list, (o1, o2) -> o1.getPosition().equals(o2.getPosition())?Integer.valueOf(o1.getId()).compareTo(o2.getId()): o1.getPosition().compareTo(o2.getPosition()));
        for (EmployeeCu employeeCu : list) {
            System.err.println(employeeCu);
        }

    }

}

enum PositionCu {
    Boss, Manager, Staff
}

//普通实现类写法
class PositionComparator implements Comparator<EmployeeCu> {
    @Override
    public int compare(EmployeeCu o1, EmployeeCu o2) {
        return o1.getPosition().compareTo(o2.getPosition());
    }
}


class EmployeeCu implements Comparable<EmployeeCu> {
    private int id;
    private String name;
    private PositionCu position;

    public EmployeeCu(int id, String name, PositionCu position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    @Override
    public int compareTo(EmployeeCu o) {
        return new CompareToBuilder().append(id, o.id).toComparison();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PositionCu getPosition() {
        return position;
    }

    public void setPosition(PositionCu position) {
        this.position = position;
    }
}

