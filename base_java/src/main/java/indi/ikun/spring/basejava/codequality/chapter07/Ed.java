package indi.ikun.spring.basejava.codequality.chapter07;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 108.反射让模板方法模式更强大
 *
 * 决定使用模板方法模式时，请尝试使用反射方式实现，它会让你的程序更灵活、更强大
 *
 * 模板方法模式（Template Method Pattern）的定义是：
 * 定义一个操作中的算法骨架，
 * 将一些步骤延迟到子类中，
 * 使子类不改变一个算法的结构即可重定义该算法的某些特定步骤。
 * 简单地说，
 * 就是父类定义抽象模板作为骨架，
 * 其中包括基本方法（是由子类实现的方法，并且在模板方法被调用）
 * 和模板方法（实现对基本方法的调度，完成固定的逻辑），
 * 它使用了简单的继承和覆写机制
 *
 */
public class Ed {
    public static void main(String[] args) throws Exception {
        AbsPopulator a=new UserPopulator();
        a.dataInitialing();

        //反射
        AbsPopulator1 a1=new UserPopulator1();
        a1.dataInitialing();
    }

}

/**
 * 抽象模板类AbsPopulator，
 * 它负责数据初始化，
 * 但是具体要初始化哪些数据则是由doInit方法决定的，
 * 这是一个抽象方法，子类必须实现
 */
abstract class AbsPopulator{
    //模板方法
    public final void dataInitialing()throws Exception{
        //调用基本方法
        doInit();
    }
    //基本方法
    protected abstract void doInit();
}

/**
 * 系统在启动时，查找所有的AbsPopulator实现类，
 * 然后dataInitialing实现数据的初始化。
 * 如果是使用Spring作为IoC容器的项目，
 * 直接在dataInitialing方法上加上@PostConstruct注解，
 * Spring容器启动完毕后会自动运行dataInitialing方法
 *
 *
 * 初始化一张User表需要非常多的操作，
 * 比如先建表，然后筛选数据，之后插入，最后校验，
 * 如果把这些都放到一个doInit方法里会非常庞大（即使提炼出多个方法承担不同的职责，代码的可读性依然很差）
 */
class UserPopulator extends AbsPopulator{
    @Override
    protected void doInit() {
        System.err.println("初始化信息");
    }
}
//************************反射优化
/**
 * 在一般的模板方法模式中，抽象模板（这里是AbsPopulator类）需要定义一系列的基本方法，
 * 一般都是protected访问级别的，并且是抽象方法，
 * 这标志着子类必须实现这些基本方法，这对子类来说既是一个约束也是一个负担。
 * 但是使用了反射后，不需要定义任何抽象方法，
 * 只需定义一个基本方法鉴别器（例子中isInitDataMethod）即可加载符合规则的基本方法。
 * 鉴别器在此处的作用是鉴别子类方法中哪些是基本方法，
 * 模板方法（例子中的dataInitialing）则根据基本方法鉴别器返回的结果通过反射执行相应的方法
 */
abstract class AbsPopulator1{
    //模板方法
    public final void dataInitialing()throws Exception{
        //获取所有public方法
        Method[] methods=getClass().getMethods();
        for (Method m:methods){
            //判断是否是数据初始化方法
            if(isInitDataMethod(m)){
                m.invoke(this);
            }
        }
    }
    private boolean isInitDataMethod(Method m){
        return m.getName().startsWith("init")//init开始
                && Modifier.isPublic(m.getModifiers())//公开方法
                &&m.getReturnType().equals(Void.TYPE)//返回值
                &&!m.isVarArgs()//输入参数为为空
                &&!Modifier.isAbstract(m.getModifiers());//不能是抽象方法
    }
}

/**
 * UserPopulator类中的方法只要符合基本方法鉴别器条件即会被模板方法调用，
 * 方法的数据量也不再受父类的约束，
 * 实现了子类灵活定义基本方法、父类批量调用的功能，并且缩减了子类的代码量
 */
class UserPopulator1 extends AbsPopulator1{
    public void initUser(){
        System.err.println("user");
    }
    public void initPassword(){
        System.err.println("password");
    }
    public void initJobs(){
        System.err.println("job");
    }
}