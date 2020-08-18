package indi.ikun.spring.basejava.codequality.chapter08;


import java.io.IOException;

/**
 * 114.不要在构造函数中抛出异常
 *
 * 在构造函数中不要抛出异常，尽量曲线救国
 *
 * Java的异常机制有三种:
 * 1)Error类及其子类表示的是错误，
 *      它是不需要程序员处理也不能处理的异常，比如VirtualMachineError虚拟机错误，ThreadDeath线程僵死等
 * 2)RuntimeException类及其子类表示的是非受检异常，是系统可能会抛出的异常，
 *      程序员可以去处理，也可以不处理，最经典就是NullPointerException空指针异常和IndexOutOfBoundsException越界异常
 * 3)Exception类及其子类（不包含非受检异常）表示的是受检异常，
 *      这是程序员必须处理的异常，不处理则程序不能通过编译，比如IOException表示I/O异常，SQLException表示数据库访问异常
 * 从Java语法上来说，完全可以在构造函数中抛出异常，三类异常都可以，
 * 但是从系统设计和开发的角度来分析，则尽量不要在构造函数中抛出异常
 * 1)构造函数抛出错误是程序员无法处理的
 *      在构造函数执行时，若发生了VirtualMachineError虚拟机错误，那就没招了，只能抛出，
 *      程序员不能预知此类错误的发生，也就不能捕捉处理
 * 2)构造函数不应该抛出非受检异常
 *      1）加重了上层代码编写者的负担
 *          捕捉这个RuntimeException异常吧，那谁来告诉我有这个异常呢？只有通过文档来约束了，
 *              一旦Person类的构造函数经过重构后再抛出其他非受检异常，那main方法不用修改也是可以通过测试的，
 *              但是这里就可能会产生隐藏的缺陷，而且还是很难重现的缺陷。
 *          不捕捉这个RuntimeException异常，这是我们通常的想法，
 *              既然已经写成了非受检异常，main方法的编码者完全可以不处理这个异常嘛，大不了不执行Person的方法！
 *              这是非常危险的，一旦产生异常，整个线程都不再继续执行，
 *              或者连接没有关闭，或者数据没有写入数据库，或者产生内存异常，这些都是会对整个系统产生影响
 *     2）后续代码不会执行
 *          main方法的实现者原本只是想把p对象的建立作为其代码逻辑的一部分，
 *          执行完seeMovie方法后还需要完成其他逻辑，
 *          但是因为没有对非受检异常进行捕捉，异常最终会抛出到JVM中，
 *          这会导致整个线程执行结束后，后面所有的代码都不会继续执行了，
 *          这就对业务逻辑产生了致命的影响
 * 3)构造函数尽可能不要抛出受检异常
 *      1）在构造函数中抛出受检异常的三个不利方面
 *          1）导致子类代码膨胀
 *              例子中子类的无参构造函数不能省略，
 *              原因是父类的无参构造函数抛出了IOException异常，
 *              子类的无参构造函数默认调用的是父类的构造函数，
 *              所以子类的无参构造也必须抛出IOException或其父类
 *          2）违背了里氏替换原则
 *              里氏替换原则是说“父类能出现的地方子类就可以出现，而且将父类替换为子类也不会产生任何异常”
 *              Java的构造函数允许子类的构造函数抛出更广泛的异常类,
 *              这正好与类方法的异常机制相反，(子类方法的异常类型必须是父类方法的子类型)。
 *              子类的方法可以抛出多个异常，但都必须是被覆写方法的子类型，
 *                  Sub类的method方法抛出的异常必须是Exception的子类或Exception类，
 *                  这是Java覆写的要求
 *              因为构造函数没有覆写的概念，只是构造函数间的引用调用而已，
 *              所以在构造函数中抛出受检异常会违背里氏替换原则，使我们的程序缺乏灵活性
 *          3）子类构造函数扩展受限
 *              子类存在的原因就是期望实现并扩展父类的逻辑，但是父类构造函数抛出异常却会让子类构造函数的灵活性大大降低
 *
 */
public class Ej {
    public static void main(String[] args) {
        PersonEj p = new PersonEj(10);//异常
        p.seeMovie();

        /**
         * 违法里氏替换原则
         * 期望把new Base()替换成new Sub()，而且代码能够正常编译和运行。
         * 非常可惜，编译通不过，
         * 原因是Sub的构造函数抛出了Exception异常，
         * 它比父类的构造函数抛出的异常范围要宽，
         * 必须增加新的catch块才能解决
         */
        try {
//            BaseEj b=new BaseEj();
            BaseEj b=new SubEj();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
//构造函数不应该抛出非受检异常
class PersonEj{
    public PersonEj(int _age) {
        if(_age<18){
            throw new RuntimeException("年龄必须大于18");
        }
    }
    public void seeMovie(){
        System.err.println("欣赏小黄片");
    }
}

/**
 * 3)构造函数尽可能不要抛出受检异常
 */
class BaseEj{
    //父类抛出IOException
    public BaseEj() throws IOException {
        throw new IOException();
    }
    //普通方法
    public void method() throws Exception{

    }
}
class SubEj extends BaseEj{
    //子类抛出Exception异常
    public SubEj() throws Exception {
    }

    //子类方法的异常类型必须是父类方法的子类型
    @Override
    public void method() throws IOException {

    }
}

/**
 * 3）子类构造函数扩展受限
 * 这段代码编译通不过，原因是构造函数Sub中没有把super()放在第一句话中，
 * 想把父类的异常重新包装后再抛出是不可行的
 * （当然，这里有很多种“曲线”的实现手段，比如重新定义一个方法，然
 * 后父子类的构造函数都调用该方法，那么子类构造函数就可以自由处理异常了），这是Java语法限制
 */
class SubEj1 extends BaseEj{
    //子类抛出Exception异常
    public SubEj1() throws Exception {
        /*try{
            //Call to 'super()' must be first statement in constructor body
            super();
        }catch (IOException e){
            throw e;
        }finally {
            //收尾
        }*/
    }
}