package indi.ikun.spring.designpattern.segregation;
/**
 * @Description: 接口隔离
 * @Author caddy
 * @date 2020-02-05 17:01:43
 * @version 1.0
 */
public class Segregation1 {

    public static void main(String[] args) {
        A a = new A();
        a.depend1(new B());
        a.depend2(new B());
        a.depend3(new B());
        C c = new C();
        c.depend1(new D());
        c.depend4(new D());
        c.depend5(new D());
    }

}


interface Interface1{
    void operation1();
    void operation2();
    void operation3();
    void operation4();
    void operation5();
}

class B implements Interface1{
    @Override
    public void operation1() {
        System.err.println("B实现operation1");
    }

    @Override
    public void operation2() {
        System.err.println("B实现operation2");
    }

    @Override
    public void operation3() {
        System.err.println("B实现operation3");
    }

    @Override
    public void operation4() {
        System.err.println("B实现operation4");
    }

    @Override
    public void operation5() {
        System.err.println("B实现operation5");
    }
}

class D implements Interface1{
    @Override
    public void operation1() {
        System.err.println("D实现operation1");
    }

    @Override
    public void operation2() {
        System.err.println("D实现operation2");
    }

    @Override
    public void operation3() {
        System.err.println("D实现operation3");
    }

    @Override
    public void operation4() {
        System.err.println("D实现operation4");
    }

    @Override
    public void operation5() {
        System.err.println("D实现operation5");
    }
}
/**
 * @Description: A通过Interface1 依赖使用B，只用1,2,3方法
 * @Author caddy
 * @date 2020-02-04 14:36:38
 * @version 1.0
 */
class A {
    public void depend1(Interface1 i){
        i.operation1();
    }

    public void depend2(Interface1 i){
        i.operation2();
    }
    public void depend3(Interface1 i){
        i.operation3();
    }

}
/**
 * @Description: c通过Interface1 依赖使用D，只用1,4,5方法
 * @Author caddy
 * @date 2020-02-04 14:36:38
 * @version 1.0
 */
class C {
    public void depend1(Interface1 i){
        i.operation1();
    }

    public void depend4(Interface1 i){
        i.operation4();
    }
    public void depend5(Interface1 i){
        i.operation5();
    }

}
