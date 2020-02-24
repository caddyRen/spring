package indi.ikun.spring.designpattern.liskov;

/**
 * @Description: 里氏替换原则  降低继承的耦合度
 * @Author caddy
 * @date 2020-02-04 18:40:06
 * @version 1.0
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
