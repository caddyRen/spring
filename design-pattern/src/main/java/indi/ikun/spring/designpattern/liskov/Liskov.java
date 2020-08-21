package indi.ikun.spring.designpattern.liskov;

/**
 * 里氏替换原则(Liskov Substitution Principle LSP)面向对象设计的基本原则之一。
 * 里氏替换原则中说，任何基类可以出现的地方，子类一定可以出现。
 * LSP是继承复用的基石，只有当衍生类可以替换掉基类，软件单位的功能不受到影响时，基类才能真正被复用，而衍生类也能够在基类的基础上增加新的行为
 *
 * A和B耦合度高
 */
public class Liskov {

    public static void main(String[] args) {
        A a = new A();
        System.err.println(a.func1(2,1));
        B b = new B();
        System.err.println(b.func1(2,1));
        System.err.println(b.func2(2,1));
    }

}

class A {
    public int func1( int a,int b){
        return a+b;
    }
}

class B extends A{

    @Override
    public int func1(int a, int b){
        return a-b;
    }

    public int func2(int a,int b){
        return func1(a,b)+9;
    }

}
