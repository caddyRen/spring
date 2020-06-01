package indi.ikun.spring.designpattern.pattern.behavior.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 迭代器接口,jdk已提供
 * 1.提供统一的方法遍历对象,不用考虑聚合(集合存放,数组存放)的类型,
 * 2.隐藏聚合的内部结构,客户端要遍历聚合的时候取迭代器,不用知道具体是聚合方式
 * 3.单一职责原则,管理对象聚合,遍历对象聚合的责任分开,集合改变,只影响聚合对象;遍历方式改变,只影响迭代器
 * 4.用于相似对象的暂时,遍历一组相同对象时使用,适合使用迭代器模式
 * 缺陷:每个聚合对象都要一个迭代器,会生成多个迭代器不好管理类
 */
public interface MyIterator {
    boolean hasNext();

    <E> E next();

    void remove();

}

/**
 * 一种实现,管理迭代
 * Computer 遍历数组形式
 */
class ComputerIterator implements Iterator {

    Department[] departments;
    int position = 0;

    public ComputerIterator(Department[] departments) {
        this.departments = departments;
    }

    @Override
    public boolean hasNext() {
        if (position >= departments.length || departments[position] == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Object next() {
        Department department = departments[position];
        position += 1;
        return department;
    }

    @Override
    public void remove() {

    }
}


/**
 * 一种实现,管理迭代
 * Info 遍历集合形式
 */
class InfoIterator implements Iterator {

    List<Department> departmentList;

    int index = -1;

    public InfoIterator(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    @Override
    public boolean hasNext() {
        if (index >= departmentList.size() - 1) {
            return false;
        } else {
            index += 1;
            return true;
        }
    }

    @Override
    public Object next() {
        return departmentList.get(index);
    }

    @Override
    public void remove() {
        //空实现
    }
}


/**
 * 统一聚合接口,将客户端与具体聚合解耦, 数据层
 */
interface College {
    String getName();

    void addDepartment(String name, String desc);

    Iterator createIterator();
}

/**
 * 具体聚合持有对象集合,提供方法,返回一个可以正确遍历集合的迭代器,
 */
class Computer implements College {

    Department[] departments;
    int numOfDepartment = 0;

    public Computer() {
        departments=new Department[4];
        addDepartment("java", "java 专业");
        addDepartment("python", "python 专业");
        addDepartment("js", "js 专业");
        addDepartment("运维", "运维 专业");

    }

    @Override
    public String getName() {
        return "计算机学院";
    }

    @Override
    public void addDepartment(String name, String desc) {
        departments[numOfDepartment] = new Department(name, desc);
        numOfDepartment += 1;
    }

    @Override
    public Iterator createIterator() {
        return new ComputerIterator(departments);
    }
}

/**
 * 具体聚合持有对象集合,提供方法,返回一个可以正确遍历集合的迭代器,
 */
class Info implements College {

    List<Department> departmentList;

    public Info() {
        departmentList=new ArrayList<>();
        addDepartment("java", "java 信息专业");
        addDepartment("python", "python 信息专业");
        addDepartment("js", "js 信息专业");
        addDepartment("运维", "运维 信息专业");

    }

    @Override
    public String getName() {
        return "信息学院";
    }

    @Override
    public void addDepartment(String name, String desc) {
        departmentList.add(new Department(name, desc));
    }

    @Override
    public Iterator createIterator() {
        return new InfoIterator(departmentList);
    }
}

class OutPutImpl {
    List<College> collegeList;

    public OutPutImpl(List<College> collegeList) {
        this.collegeList = collegeList;
    }

    public void printCollege() {
        for (College college : collegeList) {
            System.err.println(college.getName());
            printDepartment(college.createIterator());
        }

    }


    public void printDepartment(Iterator iterator) {
        while (iterator.hasNext()) {
            Department next = (Department) iterator.next();
            System.err.println(next.getDesc());
        }
    }


}

