package indi.ikun.spring.designpattern.pattern.structural.decorator;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Description: 递归
 * @Author caddy
 * @date 2020-02-12 18:24:23
 * @version 1.0
 */
public class Client {

    public static void main(String[] args) {
        Drink drink=new Decaf();
        System.err.println(drink.getDes());
        System.err.println(drink.cost());

        //包装一次Milk
        drink=new Milk(drink);
        System.err.println(drink.getDes());
        System.err.println(drink.cost());
        //再包装一次Milk
        drink=new Milk(drink);
        System.err.println(drink.getDes());
        System.err.println(drink.cost());


        drink=new Chocolate(drink);
        System.err.println(drink.getDes());
        System.err.println(drink.cost());


        Drink drink2=new LongBlack();
        System.err.println(drink2.getDes());
        System.err.println(drink2.cost());

        /**
         * 装饰者模式 IO源码解析
         *
         * InputStream 抽象基类，Drink
         * FilterInputStream  装饰者  Decorator
         * DataInputStream  具体装饰者  Milk
         * FileInputStream  被装饰者
         */
        DataInputStream dis=null;
        try {
            dis =new DataInputStream(new FileInputStream("/test.txt"));
            System.err.println(dis.read());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(dis!=null){
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
