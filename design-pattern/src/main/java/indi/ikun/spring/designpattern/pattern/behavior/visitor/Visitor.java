package indi.ikun.spring.designpattern.pattern.behavior.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象访问者,
 * 为该对象结构中的ConcreateElement的每一个类声明一个visit操作
 */
public abstract class Visitor {

    public abstract void manVisitor(Man man);

    public abstract void womanVisitor(Woman woman);

}

/**
 * 具体元素实现accept方法
 */
class Success extends Visitor {
    @Override
    public void manVisitor(Man man) {
        System.err.println("男的success");
    }

    @Override
    public void womanVisitor(Woman woman) {
        System.err.println("女的success");
    }
}

/**
 * 具体元素实现accept方法
 */
class Fail extends Visitor {
    @Override
    public void manVisitor(Man man) {
        System.err.println("男的fail");
    }

    @Override
    public void womanVisitor(Woman woman) {
        System.err.println("女的fail");
    }
}

/**
 * 接收一个访问者对象
 */
abstract class Element {

    abstract void accept(Visitor visitor);
}


/**
 * 具体访问值,实现每个有Visitor声明的操作,是每个操作实现的部分
 *
 * 双分派---为了解耦
 * 首先在客户端程序,将  具体状态  作为参数传递到Woman中---第一次反派
 * 然后Woman调用作为参数的具体方法manVisitor,同时将自己作为参数传入---第二次反派
 *
 * 不管类怎么变化,我们都能找到期望的方法运行
 * 双分派意味着得到执行的操作取决于请求的种类和两个接受者的类型
 *
 * 如果需要新增一个Visitor子类,Man和Woman不用做任何修改
 * 客户端可直接调用---ocp,解耦
 *
 *
 */
class Man extends Element {
    @Override
    void accept(Visitor visitor) {
        visitor.manVisitor(this);
    }
}


class Woman extends Element {
    @Override
    void accept(Visitor visitor) {
        visitor.womanVisitor(this);
    }
}



/**
 * 枚举它的元素,提供一个高层借口允许访问者访问元素
 */
class ObjectStructure {

   List<Element> elements=new ArrayList<>();

   public void attach(Element visitor){
       elements.add(visitor);
   }

    public void detach(Element visitor){
        elements.remove(visitor);
   }

    public void display(Visitor visitor){
       for(Element element:elements){
           element.accept(visitor);
       }
   }


}



