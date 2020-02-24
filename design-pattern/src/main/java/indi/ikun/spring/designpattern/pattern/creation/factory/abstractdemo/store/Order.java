package indi.ikun.spring.designpattern.pattern.creation.factory.abstractdemo.store;

import indi.ikun.spring.designpattern.pattern.creation.factory.abstractdemo.factory.AbsFactory;
import org.springframework.util.StringUtils;

import static indi.ikun.spring.designpattern.pattern.creation.factory.no.order.Order.getName;

public class Order {

    AbsFactory absFactory;

    public Order(AbsFactory abs) {
        this.absFactory = abs;
        do{
            System.err.println("输入pizza name");
            String name=getName();
            if(StringUtils.isEmpty(name)){
                break;
            }else {
                absFactory.createPizza(name);
            }
        }while (true);
    }
}
