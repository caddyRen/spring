package indi.ikun.spring.designpattern.pattern.structural.adapter.classadapter;

public class Client {

    public static void main(String[] args) {
        Phone p=new Phone();
        p.charging(new VolatageAdapter());
    }
}
