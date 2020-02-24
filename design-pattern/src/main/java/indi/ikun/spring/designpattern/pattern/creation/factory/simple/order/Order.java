package indi.ikun.spring.designpattern.pattern.creation.factory.simple.order;

import indi.ikun.spring.designpattern.pattern.creation.factory.simple.factory.SimpleFactory;

import static indi.ikun.spring.designpattern.pattern.creation.factory.no.order.Order.getName;

/**
 * @Description: 订购pizza
 * @Author caddy
 * @date 2020-02-10 10:32:02
 * @version 1.0
 */
public class Order {

    public static void main(String[] args) {
        SimpleFactory s=new SimpleFactory();
        do{
//            s.getPizza(getName());
            SimpleFactory.getPizza2(getName());
        }while (true);
    }
}
