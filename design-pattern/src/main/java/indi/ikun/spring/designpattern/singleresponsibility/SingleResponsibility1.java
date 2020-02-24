package indi.ikun.spring.designpattern.singleresponsibility;

/**
 * @Description: 单一职责原则
 * @Author caddy
 * @date 2020-02-04 13:37:12
 * @version 1.0
 */
public class SingleResponsibility1 {

    public static void main(String[] args) {
        Vehicle vehicle=new Vehicle();
        vehicle.run("摩托车");
        //不合理
        vehicle.run("飞机");
        vehicle.run("汽车");
    }
}

/**
 * @Description: 交通工具类
 * @Author caddy
 * @date 2020-02-04 13:38:41
 * @version 1.0
 */
class Vehicle{

    public void run( String vehicle){
        System.err.println(vehicle+"在公路上运行......");
    }
}
