package indi.ikun.spring.basejava.codequality.chapter07;

import java.util.ArrayList;
import java.util.List;

/**
 * 93.Java的泛型是类型擦除的
 *
 * Java泛型（Generic）的引入加强了参数类型的安全性，减少了类型的转换，
 * 与C++中的模板（Templates）比较类似，
 * 但是有一点不同的是：
 *      Java的泛型在编译期有效，
 *      在运行期被删除，
 *      也就是说所有的泛型参数类型在编译后都会被清除掉
 *
 * Java泛型擦除，编译后所有的泛型类型都会做相应的转化
 *      List<String>、List<Integer>、List<T>擦除后的类型为List
 *      List<String>[]擦除后的类型为List[]
 *      List<? extends E>、List<? super E>擦除后的类型为List<E>
 *      List<T extends Serializable&Cloneable>擦除后为List<Serializable>
 * Java编译后的字节码中已经没有泛型的任何信息了，
 * 也就是说一个泛型类和一个普通类在经过编译后都指向了同一字节码，
 * 比如Foo<T>类，经过编译后将只有一份Foo.class类，
 * 不管是Foo<String>还是Foo<Integer>引用的都是同一字节码Foo.class
 * 原因：
 *      1.避免JVM的大换血。C++的泛型生命期延续到了运行期，
 *          而Java是在编译器擦除掉的，
 *          如果JVM也把泛型类型延续到运行期，那么JVM就需要进行大量的重构工作了
 *      2.版本兼容。
 *          在编译期擦除可以更好地支持原生类型（Raw Type），
 *          在Java 1.5或1.6平台上，即使声明一个List这样的原生类型也是可以正常编译通过的，只是会产生警告信息而已
 *问题：
 *  1）泛型的class对象是相同的
 *      每个类都有一个class属性，泛型化不会改变class属性的返回值
 *  2）泛型数组初始化时不能声明泛型类型
 *      可以声明一个带有泛型参数的数组，
 *      但是不能初始化该数组，
 *      因为执行了类型擦除操作，List<Object>[]与List<String>[]就是同一回事了，编译器拒绝如此声明
 *  3）instanceof不允许存在泛型参数
 *      泛型类型被擦除不能通过编译
 */
public class Do {
    public static void main(String[] args) {
        //使用泛型
        List<String> list=new ArrayList<>();
        list.add("abc");
        String str=list.get(0);
        //编译器擦除处理后，上面的代码与下面的是一致的
        /*
        List list1=new ArrayList();
        list1.add("abc");
        String str2=(String)list1.get(0);
        */


        System.err.println("泛型的class对象是相同的");
        List<String> ls=new ArrayList<>();
        List<Integer> li=new ArrayList<>();
        System.err.println(li.getClass()==ls.getClass());
        System.err.println("泛型数组初始化时不能声明泛型类型");
        List<String>[] listArray=null;
//        listArray=new List<String>[];//编译不通过
        //泛型类型被擦除不能通过编译
        System.err.println("instanceof不允许存在泛型参数");
        List<String> l3=new ArrayList<>();
//        System.err.println(list instanceof List<String>);//编译不通过
    }
}



class FooDo{
    //arrayMethod接收数组参数，并进行重载
    public void arrayMethod(String[] strArray){

    }
    public void arrayMethod(Integer[] strArray){

    }
    //listMethod接收泛型list参数并进行重载
    // 不能编译
    //listMethod(List<Integer>)和listMethod(List<String>)具有相同疑符
    //方法签名重复
    //listMethod(List<Integer>)方法在编译时擦除类型后的方法是listMethod(List<E>)
    //listMethod(List<String>)方法在编译时擦除类型后的方法是listMethod(List<E>)
    //此问题是Java泛型擦除引起的问题：在编译后所有的泛型类型都会做相应的转化
    public void listMethod(List<String> strList){

    }
//    public void listMethod(List<Integer> intList){
//
//    }


}

