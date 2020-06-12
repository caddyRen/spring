package indi.ikun.spring.designpattern.pattern.behavior.strategy;
/**
 * 算法
 */
public interface Fly {

    void fly();
}


class NoFly implements Fly{
    @Override
    public void fly() {
        System.err.println("不会飞");
    }
}

class GoodFly implements Fly{
    @Override
    public void fly() {
        System.err.println("善于飞行");
    }
}

class BadFly implements Fly{
    @Override
    public void fly() {
        System.err.println("飞行技术一般");
    }
}