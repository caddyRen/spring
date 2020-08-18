package indi.ikun.spring.basejava.codequality.chapter06;

/**
 * 88.用枚举实现工厂方法模式更简洁
 *
 * 工厂方法模式（Factory Method Pattern）
 * 是“创建对象的接口，让子类决定实例化哪一个类，并使一个类的实例化延迟到其子类”。
 *
 * 枚举优点
 * 1）避免错误调用的发生
 *  一般工厂方法模式中的生产方法（也就是createCar方法）可以接收三种类型的参数：
 *  类型参数（如我们的例子）、
 *  String参数（生产方法中判断String参数是需要生产什么产品）、
 *  int参数（根据int值判断需要生产什么类型的产品），
 *  这三种参数都是宽泛的数据类型，很容易产生错误（比如边界问题、null值问题），
 *  而且出现这类错误编译器还不会报警
 *  例如：
 *      Car car=CarFactory.createCar(Car.class);
 *  Car是一个接口，完全合乎createCar方法的要求，所以它在编译时不会报任何错误，
 *  但一运行起来就会报InstantiationException异常。
 *  而使用枚举类型的工厂方法模式就不存在该问题了，
 *  不需要传递任何参数，只需要选择好生产什么类型的产品即可
 * 2）性能好，使用便捷
 *  枚举类型的计算是以int类型的计算为基础的，这是最基本的操作，性能当然会快，
 *  至于使用便捷，注意看客户端的调用，代码的字面意思就是“汽车工厂，我要一辆别克汽车，赶快生产”
 * 3）降低类间耦合
 *  不管生产方法接收的是Class、String还是int的参数，
 *  都会成为客户端类的负担，
 *  这些类并不是客户端需要的，而是因为工厂方法的限制必须输入的，
 *  例如Class参数，对客户端main方法来说，它需要传递一个FordCar.class参数才能生产一辆福特汽车，
 *  除了在create方法中传递该参数外，业务类不需要改Car的实现类。
 *  这严重违背了迪米特原则（Law of Demeter，简称为LoD），
 *  也就是最少知识原则：一个对象应该对其他对象有最少的了解
 *  枚举类型的工厂方法就没有这种问题了，
 *  它只需要依赖工厂类就可以生产一辆符合接口的汽车，
 *  完全可以无视具体汽车类的存在
 *
 */
public class Dj {
    public static void main(String[] args) {
        Car car=CarFactory.createCar(FordCar.class);
        Car car2=CarFactoryEnum.BuickCar.create();
        Car car3=CarFactoryEnum1.BuickCar.create();

        car.display();
        car2.display();
        car3.display();
        System.err.println("以下是错误");
        Car car4=CarFactory.createCar(Car.class);
        car4.display();
    }
}
/**
 * 通过抽象方法生成产品
 */
enum CarFactoryEnum1{
    FordCar{
        @Override
        public Car create() {
            return new FordCar();
        }
    },BuickCar{
        @Override
        public Car create() {
            return new BuickCar();
        }
    };
    public abstract Car create();
}
/**
 *枚举非静态方法实现工厂方法模式
 */
enum CarFactoryEnum{
    FordCar,BuickCar;
    public Car create(){
        switch (this){
            case FordCar:
                return new FordCar();
            case BuickCar:
                return new BuickCar();
            default:
                throw new AssertionError("无效的参数");
        }
    }
}

/**
 * 经典工厂方法
 */
class CarFactory{
    public static Car createCar(Class<?extends Car> c){
        try{
            return (Car)c.newInstance();
        }catch (Exception  e){
            e.printStackTrace();
        }
        return null;
    }
}
interface Car{
    void display();
}
class FordCar implements Car{
    @Override
    public void display(){
        System.err.println("FordCar");
    }
}
class BuickCar implements Car{
    @Override
    public void display() {
        System.err.println("BuickCar");
    }
}