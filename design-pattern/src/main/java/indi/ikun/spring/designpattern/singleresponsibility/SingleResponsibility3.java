package indi.ikun.spring.designpattern.singleresponsibility;

/**
 * @Description: 单一职责原则
 * @Author caddy
 * @date 2020-02-04 13:37:12
 * @version 1.0
 */
public class SingleResponsibility3 {

    public static void main(String[] args) {
        Vehicle2 vehicle=new Vehicle2();
        vehicle.run("摩托车");
        vehicle.runAir("飞机");
        vehicle.runWater("轮船");
    }
}

/**
 * @Description: 交通工具类
 * @Author caddy
 * @date 2020-02-04 13:38:41
 * @version 1.0
 * 方式3
 * 1.这种方式没有对原来的类做大的修改，只是增加方法
 * 2.没有在类级别遵守单一职责原则，在方法级别遵守单一职责原则
 */
class Vehicle2{

    public void run( String vehicle){
        System.err.println(vehicle+"在公路上运行......");
    }
    public void runAir( String vehicle){
        System.err.println(vehicle+"在天空上运行......");
    }
    public void runWater( String vehicle){
        System.err.println(vehicle+"在水中运行......");
    }
}
