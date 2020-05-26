package indi.ikun.spring.designpattern.pattern.behavior.visitor;

/**
 * 访问者模式
 *
 * 封装一些作用于某种数据结构的各元素的操作,它可以在不改变数据结构的前提下定义作用于这些元素的新的操作
 * 主要将数据结构与数据操作分离,解决数据结构和操作耦合性问题
 * 原理:在被访问的类里面加一个对外提供接待访问者的接口
 * 场景:需要对一个对象结构中的对象进行很多不同操作(这些操作彼此没有关联).同时避免让这些操作污染这些对象的类
 *
 *
 *符合单一职责原则,扩展性灵活性高
 * 报表UI拦截器过滤器等数据结构相对稳定的系统
 *
 * 具体Element对Visitor公布细节,Visitor关注了其他类的内部细节,违背了迪米特法则,会造成Element变更比较困难
 * 违背依赖倒转原则,Visitor依赖具体的Element---Man和Woman,而不直依赖抽象元素
 * 因此访问者模式适用于系统有比较稳定的数据结构,又有经常变化的功能需求
 */
public class Client {

    public static void main(String[] args) {
        ObjectStructure objectStructure=new ObjectStructure();


        objectStructure.attach(new Man());
        objectStructure.attach(new Woman());


        Success success=new Success();
        objectStructure.display(success);

        Fail fail = new Fail();
        objectStructure.display(fail);
    }

}
