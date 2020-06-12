package indi.ikun.spring.designpattern.pattern.behavior.strategy;

/**
 * 策略模式 strategy pattern
 * 将继承换成组合或者聚合
 * 定义算法族,分别封装,让他们可以互相替换,此模式让算法的变化独立与使用算法的用户
 * <p>
 * 把变化的代码从不变的代码中分离出来,
 * 针对接口编程,策略接口
 * 多用组合/聚合,少用继承--客户通过组合方式使用策略
 *
 * 对修改关闭,对扩展开放
 *
 * 策略过多,会导致类数目庞大
 */
public class Client {
    public static void main(String[] args) {
        WildDuck wildDuck = new WildDuck();
        wildDuck.disPlay();
        ToyDuck toyDuck = new ToyDuck();
        toyDuck.disPlay();
        PekingDuck pekingDuck = new PekingDuck();
        pekingDuck.disPlay();
        pekingDuck.setFly(new GoodFly());
        pekingDuck.setQuack(new NoQuack());
        pekingDuck.disPlay();

    }
}
