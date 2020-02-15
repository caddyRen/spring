package indi.ikun.spring.designpattern.pattern.structural.adapter.objectadapter;

import indi.ikun.spring.designpattern.pattern.structural.adapter.classadapter.Volatage220V;
import indi.ikun.spring.designpattern.pattern.structural.adapter.classadapter.Volatage5V;

/**
 * @Description: 对象适配器 将被适配者转换为可以使用的样子
 * @Author caddy
 * @date 2020-02-11 16:06:14
 * @version 1.0
 */
public class VolatageAdapter implements Volatage5V {
    Volatage220V v;

    public VolatageAdapter(Volatage220V v) {
        this.v = v;
    }

    @Override
    public int outPut5V() {
        return v.output220v()/44;
    }
}
