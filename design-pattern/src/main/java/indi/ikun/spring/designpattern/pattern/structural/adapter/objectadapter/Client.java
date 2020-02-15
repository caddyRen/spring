package indi.ikun.spring.designpattern.pattern.structural.adapter.objectadapter;

import indi.ikun.spring.designpattern.pattern.structural.adapter.classadapter.Volatage220V;

public class Client {

    public static void main(String[] args) {
        Phone p=new Phone();
        p.charging(new VolatageAdapter(new Volatage220V()));
    }
}
