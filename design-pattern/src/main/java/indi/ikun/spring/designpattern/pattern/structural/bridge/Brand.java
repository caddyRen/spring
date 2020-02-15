package indi.ikun.spring.designpattern.pattern.structural.bridge;

/**
 * @Description: 抽象出品牌类型
 * @Author caddy
 * @date 2020-02-12 13:50:35
 * @version 1.0
 */
public interface Brand {

    void open();
    void close();
    void call();

}

/**
 * @Description: 具体品牌类 扩展简单
 * @Author caddy
 * @date 2020-02-12 13:59:16
 * @version 1.0
 */
class Xiaomi implements Brand{
    @Override
    public void open() {
        System.err.println("xiaomi is open");
    }

    @Override
    public void close() {
        System.err.println("xiaomi is closed");
    }

    @Override
    public void call() {
        System.err.println("xiaomi is call");
    }
}


class Iphone implements Brand{
    @Override
    public void open() {
        System.err.println("iphone is open");
    }

    @Override
    public void close() {
        System.err.println("iphone is closed");
    }

    @Override
    public void call() {
        System.err.println("iphone is call");
    }
}
