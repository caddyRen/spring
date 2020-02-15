package indi.ikun.spring.designpattern.pattern.structural.adapter.classadapter;

/**
 * @Description: 类适配器 将被适配者转换为可以使用的样子
 * @Author caddy
 * @date 2020-02-11 16:06:14
 * @version 1.0
 */
public class VolatageAdapter extends Volatage220V implements Volatage5V {

    @Override
    public int outPut5V() {
        return super.output220v()/44;
    }
}
