package indi.ikun.spring.designpattern.pattern.behavior.state;

/**
 * 状态模式 state pattern
 * 解决对象在多种状态转换时,需要对外输出不同的行为
 * 状态和行为一一对应,状态之间可以相互转换
 * 当一个对象的内在状态改变时,允许改变其行为,这个对象看起来像是改变了类
 *
 * 符合ocp原则
 *
 * 代码可读性强,避免出现很多if-else
 *
 * 会产生很多类,每个状态对应一个类,增加维护难度
 *
 * 当一个事件或者对象有很多种状态,状态之间会相互转换,对不同的状态要求有不同的行为的时候,可以考虑使用状态模式
 *
 *
 */
public class Client {

    public static void main(String[] args) {

        Activity activity = new Activity(1);

        for(int i=0;i<30;i++){
            System.err.println("第"+i+"次抽奖");
            activity.debuctMoney();
            activity.raffle();
        }
    }
}
