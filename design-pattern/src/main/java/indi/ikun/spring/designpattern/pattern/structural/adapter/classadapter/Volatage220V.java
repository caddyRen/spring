package indi.ikun.spring.designpattern.pattern.structural.adapter.classadapter;

/**
 * @Description: 被适配者
 * @Author caddy
 * @date 2020-02-11 16:06:38
 * @version 1.0
 */
public class Volatage220V {

    public int output220v(){
        System.err.println("输出电压220v");
        return 220;
    }
}
