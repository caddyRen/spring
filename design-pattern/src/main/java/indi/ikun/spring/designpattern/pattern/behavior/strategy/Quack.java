package indi.ikun.spring.designpattern.pattern.behavior.strategy;

/**
 * 算法
 */
public interface Quack {
    void quack();
}

class GaGa implements Quack{
    @Override
    public void quack() {
        System.err.println("嘎嘎叫");
    }
}

class JiJi implements Quack{
    @Override
    public void quack() {
        System.err.println("唧唧叫");
    }
}

class NoQuack implements Quack{
    @Override
    public void quack() {
        System.err.println("不会叫");
    }
}

