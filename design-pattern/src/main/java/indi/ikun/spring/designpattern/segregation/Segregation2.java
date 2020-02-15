package indi.ikun.spring.designpattern.segregation;

public class Segregation2 {

    public static void main(String[] args) {
        A2 a2 = new A2();
        a2.depend1(new B2());
        a2.depend2(new B2());
        a2.depend3(new B2());
        C2 c2 = new C2();
        c2.depend1(new D2());
        c2.depend4(new D2());
        c2.depend5(new D2());
    }

}


interface Interface{
    void operation1();
}

interface Interface2{
    void operation2();
    void operation3();
}

interface Interface3{
    void operation4();
    void operation5();
}

class B2 implements Interface,Interface2{
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
}

class D2 implements Interface,Interface3{
    @Override
    public void operation1() {
        System.err.println("D实现operation1");
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
class A2 {
    public void depend1(Interface i){
        i.operation1();
    }

    public void depend2(Interface2 i){
        i.operation2();
    }
    public void depend3(Interface2 i){
        i.operation3();
    }

}

/**
 * @Description: c通过Interface1 依赖使用D，只用1,4,5方法
 * @Author caddy
 * @date 2020-02-04 14:36:38
 * @version 1.0
 */
class C2 {
    public void depend1(Interface i){
        i.operation1();
    }

    public void depend4(Interface3 i){
        i.operation4();
    }
    public void depend5(Interface3 i){
        i.operation5();
    }

}
