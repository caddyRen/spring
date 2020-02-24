package indi.ikun.spring.designpattern.pattern.creation.factory.no.order;

import indi.ikun.spring.designpattern.pattern.creation.factory.no.pizza.CheesePizza;
import indi.ikun.spring.designpattern.pattern.creation.factory.no.pizza.GreekPizza;
import indi.ikun.spring.designpattern.pattern.creation.factory.no.pizza.Pizza;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Description: 订购pizza
 * @Author caddy
 * @date 2020-02-10 10:32:02
 * @version 1.0
 */
public class Order {

    public static void main(String[] args) {
        Pizza pizza=null;
        String name="";
        do{
            name=getName();
            if(StringUtils.isEmpty(name)){
                break;
            }else if("cheese".equals(name)){
                pizza=new CheesePizza();
            }else if("greek".equals(name)){
                pizza=new GreekPizza();
            }else{
                break;
            }
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.bake();
        }while (true);

    }

    public static String getName(){
        BufferedReader string=new BufferedReader(new InputStreamReader(System.in));
        System.err.println("wait input...");
        String name= null;
        try {
            name = string.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }
}
