package indi.ikun.spring.designpattern.pattern.behavior.memento;

/**
 * 备忘录模式  memento pattern
 * 不破坏封装性的前提下,捕获一个对象的内部状态,
 * 并在该对象之外保存这个状态,
 * 后续可以恢复到原先的保存的状态
 * <p>
 * 实现信息封装,不需要关心状态的保存细节
 * <p>
 * 数据库事务管理
 * 备忘录加原型模式配和节约内存
 */
public class Client {

    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        originator.setState("first");
        caretaker.add(originator.saveStateMement());
        originator.setState("second");
        caretaker.add(originator.saveStateMement());
        originator.setState("third");
        caretaker.add(originator.saveStateMement());
        System.err.println("当前状态" + originator.getState());

        //回滚到第一个状态
        originator.getStateMement(caretaker.get(0));
        System.err.println("当前状态" + originator.getState());

    }


}
