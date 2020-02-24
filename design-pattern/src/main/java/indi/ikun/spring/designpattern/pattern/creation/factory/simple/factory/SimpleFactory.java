package indi.ikun.spring.designpattern.pattern.creation.factory.simple.factory;

import indi.ikun.spring.designpattern.pattern.creation.factory.no.pizza.CheesePizza;
import indi.ikun.spring.designpattern.pattern.creation.factory.no.pizza.GreekPizza;
import indi.ikun.spring.designpattern.pattern.creation.factory.no.pizza.Pizza;
import org.springframework.util.StringUtils;

/**
 * @Description: 简单工厂模式getPizza 静态工厂模式getPizza2 生产Pizza  ocp原则，单一职责原则
 * @Author caddy
 * @date 2020-02-10 11:33:30
 * @version 1.0
 *
 * 当需求变化，比如增加一个Pizza种类时候，只需要修改SimpleFactory;而Order不用修改
 * 符合ocp原则
 *
 * 简单工厂一般配合单例模式使用
 */
public class SimpleFactory {

    /**
     * @Description: 简单工厂模式getPizza
     * @Author caddy
     * @date 2020-02-10 11:46:20
     * @version 1.0
     */
    public void getPizza(String name){
        Pizza pizza=null;
        if(StringUtils.isEmpty(name)){
            System.err.println("order failure");
            return;
        }else if ("cheese".equals(name)){
            pizza= new CheesePizza();
        }else if("greek".equals(name)) {
            pizza= new GreekPizza();
        }else{
            System.err.println("order failure");
            return;
        }
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.bake();
    }

    /**
     * @Description: 静态工厂模式getPizza2
     * @Author caddy
     * @date 2020-02-10 11:46:10
     * @version 1.0
     */
    public static void getPizza2(String name){
        Pizza pizza=null;
        if(StringUtils.isEmpty(name)){
            System.err.println("order failure");
            return;
        }else if ("cheese".equals(name)){
            pizza= new CheesePizza();
        }else if("greek".equals(name)) {
            pizza= new GreekPizza();
        }else{
            System.err.println("order failure");
            return;
        }
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.bake();
    }

}
