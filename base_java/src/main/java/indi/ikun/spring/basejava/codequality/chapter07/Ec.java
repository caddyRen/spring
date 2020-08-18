package indi.ikun.spring.basejava.codequality.chapter07;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

/**
 * 107.使用反射增加装饰模式的普适性
 * 装饰模式（Decorator Pattern）的定义是
 * “动态地给一个对象添加一些额外的职责。就增加功能来说，装饰模式相比于生成子类更为灵活”，
 * 使用Java的动态代理也可以实现装饰模式的效果，
 * 而且其灵活性、适应性都会更强
 *
 * 此类代码是一个比较通用的装饰模式，
 * 只需要定义被装饰的类及装饰类即可，
 * 装饰行为由动态代理实现，
 * 实现了对装饰类和被装饰类的完全解耦，
 * 提供了系统的扩展性
 */
public class Ec {
    public static void main(String[] args) {
        AnimalEc Jerry = new RatEc();
        Jerry=new DecorateAnimalEc(Jerry,FlyFeatureEc.class);
        Jerry=new DecorateAnimalEc(Jerry,DigFeatureEc.class);
        Jerry.doStuff();
    }
}
//主角
interface AnimalEc{
    void doStuff();
}
class RatEc implements AnimalEc{
    @Override
    public void doStuff() {
        System.err.println("Jerry will play with Tom");
    }
}
//定义某种能力
interface FeatureEc{
    //加载特性
    void load();
}
//飞
class FlyFeatureEc implements FeatureEc{
    @Override
    public void load() {
        System.err.println("增加翅膀");
    }
}
//钻地
class DigFeatureEc implements FeatureEc{
    @Override
    public void load() {
        System.err.println("增加钻地能力");
    }
}
//将能力加到主角身上，即包装主角
class DecorateAnimalEc implements AnimalEc{
    //被包装的主角
    private AnimalEc animal;
    //使用哪一个包装器
    private Class<? extends FeatureEc> clz;
    public DecorateAnimalEc(AnimalEc animal, Class<? extends FeatureEc> clz) {
        this.animal = animal;
        this.clz = clz;
    }
    /**
     *一个装饰类型必然是抽象构建（Component）的子类型，
     * 它必须要实现doStuff，此处的doStuff方法委托给了动态代理执行，
     * 并且在动态代理的控制器Handler中还设置了决定装饰方式和行为的条件（即代码中InvocationHandler匿名类中的if判断语句），
     * 当然，此处也可以通过读取持久化数据的方式进行判断
     */
    @Override
    public void doStuff() {
        InvocationHandler handler=new InvocationHandler() {
            //具体包装行为
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object obj=null;
                //设置包装条件
                if(Modifier.isPublic(method.getModifiers())){
                    obj= method.invoke(clz.newInstance(),args);
                }
                animal.doStuff();
                return obj;
            }
        };
        //当前加载器
        ClassLoader cl=getClass().getClassLoader();
        //动态代理，由Handler决定如何包装
        FeatureEc proxy=(FeatureEc) Proxy.newProxyInstance(cl,clz.getInterfaces(),handler);
        proxy.load();
    }
}