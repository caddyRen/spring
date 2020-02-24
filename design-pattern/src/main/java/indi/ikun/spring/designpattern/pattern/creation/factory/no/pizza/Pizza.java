package indi.ikun.spring.designpattern.pattern.creation.factory.no.pizza;


/**
 * @Description: pizza 制作过程
 * @Author caddy
 * @date 2020-02-10 10:26:53
 * @version 1.0
 */
public class Pizza {

    String name;

    public void setName(String name) {
        this.name = name;
    }

    public void prepare(){

    }

    public void bake(){
        System.err.println(name+" bake");
    }

    public void cut(){
        System.err.println(name+" cut");
    }

    public void box(){
        System.err.println(name+" box");
    }

}
