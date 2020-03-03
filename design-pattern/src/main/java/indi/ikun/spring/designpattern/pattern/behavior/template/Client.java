package indi.ikun.spring.designpattern.pattern.behavior.template;


/**
 * 模板方法模式
 *
 */
public class Client {

    public static void main(String[] args) {
        SoyaMilk redBeanSoyaMilk = new RedBeanSoyaMilk();
        redBeanSoyaMilk.make();

        SoyaMilk peanutSoyaMilk = new PeanutSoyaMilk();
        peanutSoyaMilk.make();

        SoyaMilk pureSoyaMilk = new PureSoyaMilk();
        pureSoyaMilk.make();
    }
}
