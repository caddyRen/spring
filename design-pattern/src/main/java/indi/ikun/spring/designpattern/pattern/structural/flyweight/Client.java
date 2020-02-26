package indi.ikun.spring.designpattern.pattern.structural.flyweight;

/**
 * 享元模式 jdk源码参考Integer.valueOf()
 */
public class Client {


    public static void main(String[] args) {
        //jdk源码享元模式
        //i >= -128 && i <= Integer.IntegerCache.high
        //Integer.IntegerCache.high=127
        //当使用Integer.valueOf(m)声明Integer时
        //如果-128<=m<=127时，使用了享元模式，x和x1返回的是同一个对象，故x==x1为true
        Integer x=Integer.valueOf(127);
        Integer x1=Integer.valueOf(127);

        Integer y=new Integer(127);
        Integer y1=new Integer(127);

        System.err.println(x.equals(y));//equals比较的是值 故true
        System.err.println(x == y);//false
        System.err.println(x == x1);//true
        System.err.println(y == y1);//false
        System.err.println(x1 == y1);//false


        FlyWeightFactory factory=new FlyWeightFactory();
        factory.get("嘿嘿").use(new UnSharedConcreteFlyWeight("坤坤"));
        factory.get("嘿嘿").use(new UnSharedConcreteFlyWeight("乾乾"));
        System.err.println(factory.count())  ;

    }

}
