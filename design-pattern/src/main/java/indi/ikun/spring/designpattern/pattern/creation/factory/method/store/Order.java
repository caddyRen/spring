package indi.ikun.spring.designpattern.pattern.creation.factory.method.store;

import indi.ikun.spring.designpattern.pattern.creation.factory.method.order.OrderFactory;
import org.springframework.util.StringUtils;

import static indi.ikun.spring.designpattern.pattern.creation.factory.no.order.Order.getName;

public class Order {

    OrderFactory orderFactory;

    public Order(OrderFactory abs) {
        this.orderFactory = abs;
        do{
            System.err.println("输入pizza name");
            String name=getName();
            if(StringUtils.isEmpty(name)){
                break;
            }else {
                orderFactory.createPizza(name);
            }
        }while (true);
    }
}
