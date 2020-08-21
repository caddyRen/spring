package indi.ikun.spring.designpattern.pattern.structural.bridge;

/**
 * @Author caddy
 * @date 2020-02-12 14:13:50
 * @version 1.0
 */
public class Client {

    public static void main(String[] args) {
        Phone p=new UpRightPhone(new Xiaomi());
        p.open();
        p.call();
        p.close();
        System.err.println("----");
        p=new UpRightPhone(new Iphone());
        p.open();
        p.call();
        p.close();
        System.err.println("----");
        p=new FoldedPhone(new Iphone());
        p.open();
        p.call();
        p.close();
        System.err.println("----");
        p=new FoldedPhone(new Xiaomi());
        p.open();
        p.call();
        p.close();

    }

}
