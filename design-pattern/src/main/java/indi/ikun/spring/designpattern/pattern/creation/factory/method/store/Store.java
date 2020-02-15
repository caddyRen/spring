package indi.ikun.spring.designpattern.pattern.creation.factory.method.store;


import indi.ikun.spring.designpattern.pattern.creation.factory.method.order.BJOrderFactory;
import indi.ikun.spring.designpattern.pattern.creation.factory.method.order.LDOrderFactory;

import static indi.ikun.spring.designpattern.pattern.creation.factory.no.order.Order.getName;

public class Store {

    public static void main(String[] args) {
        do{
            System.err.println("输入bj or ld");
            String name=getName();
            if("bj".equals(name)){
                new Order(new BJOrderFactory());
            }else if("ld".equals(name)){
                new Order(new LDOrderFactory());
            }else{
              break;
            }
        }while (true);
    }
}
