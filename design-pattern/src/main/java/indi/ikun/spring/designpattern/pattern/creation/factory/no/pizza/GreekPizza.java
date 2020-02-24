package indi.ikun.spring.designpattern.pattern.creation.factory.no.pizza;

public class GreekPizza extends Pizza {

    @Override
    public void prepare() {
        setName("GreekPizza");
        System.err.println("prepare GreekPizza");
    }
}
