package indi.ikun.spring.designpattern.pattern.behavior.mediator;

/**
 * 中介模式 Mediator Pattern
 * 用一个中介对象封装一系列对象交互
 * 中介对象使各个对象不需要显示地相互引用,从而使其耦合松散,而且可以独立地改变他们之间的交互
 * mvc模式,Controller是Model和View的中介,前后端交互的中介
 *
 *
 * 多个类相互耦合,形成网状结构,使用中介模式,可以分离解耦成星状结构
 * 减少类依赖,降低耦合,符合迪米特法则
 * 中介承担较多责任,中介者出问题,整个系统都会受到影响
 * 设计不当中介对象容易过于复杂
 *
 */
public class Client {


    public static void main(String[] args) {

        Mediator mediator=new ConcreteMediator();
        Alarm alarm=new Alarm(mediator,"alarm");
        TV tv=new TV(mediator,"TV");
        CoffeeMachine coffeeMachine=new CoffeeMachine(mediator,"CoffeeMachine");
        Curtains curtains=new Curtains(mediator,"Curtains");
        alarm.SendMessage(0);
        tv.SendMessage(0);
        coffeeMachine.SendMessage(1);
        tv.SendMessage(1);
    }




}
