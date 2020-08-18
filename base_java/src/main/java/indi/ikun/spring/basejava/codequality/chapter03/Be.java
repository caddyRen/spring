package indi.ikun.spring.basejava.codequality.chapter03;


/**
 * 31.在接口中不要存在实现代码
 *
 * 接口中可以声明常量，声明抽象方法，也可以继承父接口，
 * 但就是不能有具体实现，
 * 因为接口是一种契约（Contract），是一种框架性协议，
 * 这表明它的实现类都是同一种类型，或者是具备相似特征的一个集合体。
 * 对于一般程序，接口确实没有任何实现，但是在那些特殊的程序中就例外了
 *
 * 接口是一个契约，不仅仅约束着实现者，同时也是一个保证，
 * 保证提供的服务（常量、方法）是稳定、可靠的，
 * 如果把实现代码写到接口中，那接口就绑定了可能变化的因素，
 * 这就会导致实现不再稳定和可靠，是随时都可能被抛弃、被更改、被重构的。
 * 所以，接口中虽然可以有实现，但应避免使用
 */
public class Be {

    public static void main(String[] args) {
        BeA.b.doSomething();
        BeA.b2.doSomething();
    }
}

interface BeA{
    BeB b=new BeB() {
        public void doSomething(){
            System.err.println("在接口中实现了BeB");
        }
    };
    //lambda
    BeB b2=()->System.err.println("在接口中实现了BeB");
}

interface BeB{
    void doSomething();
}
