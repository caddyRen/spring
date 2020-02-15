package indi.ikun.spring.designpattern.pattern.creation.builder;

import indi.ikun.spring.designpattern.pattern.creation.builder.builder.CommonHouseBuilder;
import indi.ikun.spring.designpattern.pattern.creation.builder.builder.HighHouseBuilder;
import indi.ikun.spring.designpattern.pattern.creation.builder.director.HouseBuildDirect;

public class Client {

    public static void main(String[] args) {
        HouseBuildDirect h=new HouseBuildDirect(new CommonHouseBuilder());
        System.err.println(h.build());
        h.setHouseBuilder(new HighHouseBuilder());
        System.err.println(h.build());

        //建造者模式jdk源码
        StringBuilder sb=new StringBuilder("hello");
        System.err.println(sb);
        /*
        Appendable接口定义了多个append方法     为抽象建造者，定义了抽象方法
        AbstractStringBuilder 实现Appendable   为建造者，但是不能实例化
        StringBuilder 继承AbstractStringBuilder
        StringBuilder 即是指挥者，又是建造者，建造方法append是AbstractStringBuilder实现
         */



    }
}
