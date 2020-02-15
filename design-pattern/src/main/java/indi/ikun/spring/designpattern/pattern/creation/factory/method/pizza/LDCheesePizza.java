package indi.ikun.spring.designpattern.pattern.creation.factory.method.pizza;


import indi.ikun.spring.designpattern.pattern.creation.factory.no.pizza.Pizza;

/**
 * @Description: chesses pizza
 * @Author caddy
 * @date 2020-02-10 10:28:16
 * @version 1.0
 */
public class LDCheesePizza extends Pizza {

    @Override
    public void prepare() {
        setName("LDCheesePizza");
        System.err.println("prepare LDCheesePizza");
    }

}
