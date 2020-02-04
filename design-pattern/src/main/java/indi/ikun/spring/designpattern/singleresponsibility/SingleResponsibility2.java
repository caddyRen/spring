package indi.ikun.spring.designpattern.singleresponsibility;

/**
 * @Description: 单一职责原则
 * @Author caddy
 * @date 2020-02-04 13:37:12
 * @version 1.0
 */
public class SingleResponsibility2 {

    public static void main(String[] args) {
        RoadVehicle roadvehicle=new RoadVehicle();
        AirVehicle airvehicle=new AirVehicle();
        WaterVehicle watervehicle=new WaterVehicle();
        roadvehicle.run("摩托车");
        airvehicle.run("飞机");
        watervehicle.run("轮船");
    }
}

/**
 * @Description: 交通工具类
 * @Author caddy
 * @date 2020-02-04 13:38:41
 * @version 1.0
 * 方案2
 * 1.遵守单一职责原则
 * 2.但是这样做的改动很大，即分解类，又要修改客户端代码
 * 3.改进直接修改Vehicle类改动代码较少=>方案3
 */
class RoadVehicle{
    public void run( String vehicle){
        System.err.println(vehicle+"在公路上运行......");
    }
}
class AirVehicle{
    public void run( String vehicle){
        System.err.println(vehicle+"在天空运行......");
    }
}
class WaterVehicle{
    public void run( String vehicle){
        System.err.println(vehicle+"在水中运行......");
    }
}
