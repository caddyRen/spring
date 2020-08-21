package indi.ikun.spring.designpattern.pattern.structural.adapter.classadapter;

/**
 * @Author caddy
 * @date 2020-02-12 10:58:35
 * @version 1.0
 */
public class Phone {

    public void charging(VolatageAdapter v) {
        if(v.outPut5V()==5){
            System.err.println("电压5v可以充电");
        }
    }
}
