package indi.ikun.spring.designpattern.pattern.creation.factory.no.pizza;

/**
 * @Description: chesses pizza
 * @Author caddy
 * @date 2020-02-10 10:28:16
 * @version 1.0
 */
public class CheesePizza extends Pizza {

    @Override
    public void prepare() {
        setName("CheesePizza");
        System.err.println("prepare CheesePizza");
    }

}
