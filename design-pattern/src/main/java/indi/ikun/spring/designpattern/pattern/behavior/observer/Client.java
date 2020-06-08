package indi.ikun.spring.designpattern.pattern.behavior.observer;

/**
 * 观察者模式,ocp原则
 * 对象之间多对一依赖的一种方案,被依赖对象为Subject,依赖对象为Observer,
 * Subject通知Observer
 *
 * 以集合方式管理Observer,注册,移除,通知
 * 增加具体Observer,不需要修改核心Subject实现类(WeatherData)
 * 遵循OCP原则
 */
public class Client {
    public static void main(String[] args) {
        WeatherData weatherData=new WeatherData();
        Test1 test1 = new Test1();
        Test2 test2 = new Test2();

        weatherData.setData("12345");
        weatherData.registerObserver(test1);
        weatherData.registerObserver(test2);
        weatherData.notifyObserver();

    }
}
