package indi.ikun.spring.designpattern.pattern.structural.adapter.objectadapter;


/**
 * @Author caddy
 * @date 2020-02-11 16:06:59
 * @version 1.0
 */
public class Phone {

    public void charging(VolatageAdapter v) {
        if(v.outPut5V()==5){
            System.err.println("电压5v可以充电");
        }
    }
}
