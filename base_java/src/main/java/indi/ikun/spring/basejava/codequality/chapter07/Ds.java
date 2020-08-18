package indi.ikun.spring.basejava.codequality.chapter07;

import java.util.ArrayList;
import java.util.List;

/**
 * 97.警惕泛型是不能协变和逆变的
 *
 * 注意：Java的泛型是不支持协变和逆变的，只是能够实现协变和逆变
 *
 * 协变（covariance）和逆变（contravariance）？Wiki上是这样定义的：
 * Within the type system of a programming language,
 * covariance and contravariance refers to the ordering of types from narrower to widerand their interchangeability or equivalence in certain situations (such asparameters, generics, and return types).
 * 在编程语言的类型框架中，
 * 协变和逆变是指宽类型和窄类型在某种情况下（如参数、泛型、返回值）替换或交换的特性，
 * 简单地说，协变是用一个窄类型替换宽类型，
 * 而逆变则是用宽类型覆盖窄类型。
 * 其实，在Java中协变和逆变我们已经用了很久了，只是我们没发觉而已
 *
 * 1）泛型不支持协变
 *  ArrayList是List的子类型，（ 不支持协变）
 *  Integer是Number的子类型，（支持协变）
 *  里氏替换原则（Liskov Substitution Principle）在此处行不通了，
 *  原因就是Java为了保证运行期的安全性，
 *  必须保证泛型参数类型是固定的，
 *  所以不允许一个泛型参数可以同时包含两种类型，
 *  即使是父子类关系也不行
 *  泛型不支持协变，但可以使用通配符（Wildcard）模拟协变
 * 2）泛型不支持逆变
 *  Java虽然可以允许逆变存在，但在对类型赋值上是不允许逆变的，
 *  不能把一个父类实例对象赋值给一个子类类型变量，
 *  泛型自然也不允许此种情况发生了，
 *  但是它可以使用super关键字来模拟实现
 *
 * 泛型既不支持协变也不支持逆变，带有泛型参数的子类型定义与我们经常使用的类类型也不相同
 * Integer是Number的子类型
 * ArrayList<Integer>是List<Integer>的子类型
 * Integer[]是Number[]的子类型
 * List<Integer>不是List<Number>的子类型
 * List<Integer>不是List<? extends Integer>的子类型
 * List<Integer>不是List<? super Integer>的子类型
 */
public class Ds {

    public static void main(String[] args) {
        /**
         * baseDs变量发生协变，
         * base变量是Base类型，它是父类，而其赋值却是子类实例，也就是用窄类型覆盖了宽类型
         * 这也叫多态（Polymorphism），两者同含义
         */
        BaseDs baseDs=new SubDs();

        //数组支持协变
        Number[] n=new Integer[10];
        //泛型不支持协变
//        List<Number> ln=new ArrayList<Integer>();//编译不通过
        //泛型不支持协变，但可以使用通配符（Wildcard）模拟协变
        //“? extends Number”表示的意思是，允许Number所有的子类（包括自身）作为泛型参数类型，
        // 但在运行期只能是一个具体类型，或者是Integer类型，或者是Double类型，或者是Number类型，
        // 也就是说通配符只是在编码期有效，运行期则必须是一个确定类型
        List<? extends Number> ln1=new ArrayList<Integer>();

        //泛型不支持逆变
        //但是可以使用super关键字来模拟实现
        //? super Integer”的意思是可以把所有Integer父类型（自身、父类或接口）作为泛型参数，
        // 这里看着就像是把一个Number类型的ArrayList赋值给了Integer类型的List，
        // 其外观类似于使用一个宽类型覆盖一个窄类型，它模拟了逆变的实现
        List<? super Integer> li=new ArrayList<Number>();
    }

}
class BaseDs{
    public Number doStuff(){
        return 0;
    }

    public void doStuff1(Integer i){
    }
}
class SubDs extends BaseDs{
    /**
     * 子类的doStuff方法返回值的类型比父类方法要窄
     * 此时doStuff方法就是一个协变方法，
     * 同时根据Java的覆写定义来看，这又属于覆写
     */
    @Override
    public Integer doStuff() {
        return 0;
    }

    /**
     * 子类的doStuff方法的参数类型比父类要宽，此时就是一个逆变方法，
     * 子类扩大了父类方法的输入参数，
     * 但根据覆写定义来看，doStuff不属于覆写，只是重载而已。
     * 由于此时的doStuff方法已经与父类没有任何关系了，
     * 只是子类独立扩展出的一个行为，
     * 所以是否声明为doStuff方法名意义不大，
     * 逆变已经不具有特别的意义
     */
    public void doStuff1(Number i) {
    }
}
