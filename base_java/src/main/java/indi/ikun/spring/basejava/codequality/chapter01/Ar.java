package indi.ikun.spring.basejava.codequality.chapter01;


import java.util.Date;

/**
 * 18.避免instanceof非预期结果
 * instanceof是一个简单的二元操作符，它是用来判断一个对象是否是一个类实例
 * 其操作类似于>=,==非常简单
 */
public class Ar {
    public static void main(String[] args) {
        //String 对象是否是Object实例
        //"String"是一个字符串，字符串继承了Object 故true
        boolean b1 = "String" instanceof Object;
        System.err.println(b1);
        //String对象是否是String的实例
        //一个类的对象是这个类（父类）的实例 故true
        boolean b2 = new String() instanceof String;
        System.err.println(b2);
        //Object对象是否是String的实例
        //Object是父类，其对象当然不是String类的实例了，只要instanceof关键字左右两个操作数有继承或实现关系，就可以编译通过，false
        boolean b3 = new Object() instanceof String;
        System.err.println(b3);
        //拆箱类型是否时候装箱类型的实例
        //编译错误，'A'是一个char类型（基本类型），不是一个对象，instanceof只能用于对象的判断，不能用于基本类型的判断
//       boolean b4= 'A' instanceof Character;
//        System.err.println(b4);
        //空对象是否是String的实例
        //instanceof规则，左操作数是null，结果就直接返回false，不再运算右操作数是什么类，即在使用instanceof操作符时，不用关心被判断的类（左操作数）是否为null，注意这与equals，toString方法不同。
        boolean b5 = null instanceof String;
        System.err.println(b5);
        //类型转换后的空对象是否是String的实例
        //(String)null仍为null，null是一个万用类型，也可以说它没有类型，即使左类型转换还是null，false
        boolean b6 = (String) null instanceof String;
        System.err.println(b6);
        //Date对象是否是String的实例
        //编译错误，Date类和String类没有继承或实现关系
//       boolean b7=new Date() instanceof String;
//        System.err.println(b7);
        //在泛型中判断String对象是否是Date的实例
        //返回false，Java泛型是为编码服务的，在编译成字节码时，T已经是Object类型了，传递的实参时String类型，也就是说T的表面类型时Object，实际类型时String，
        //t instanceof Date等价于Object instanceof Date 故返回false
        boolean b8 = new GenericClass<String>().isDateInstance("");
        System.err.println(b8);

    }
}

class GenericClass<T> {
    public boolean isDateInstance(T t) {
        return t instanceof Date;
    }
}