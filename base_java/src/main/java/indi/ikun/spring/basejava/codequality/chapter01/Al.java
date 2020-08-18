package indi.ikun.spring.basejava.codequality.chapter01;


import java.io.*;

/**
 * 12.避免用序列化类在构造函数中为不变量赋值
 *
 * 带final标识的属性是不变量，也就是说只能赋值一次，不能重复赋值
 *
 * 1.  final属性是一个直接量，反序列化时会重新计算，final属性会变成凯奇，
 *      使用v1版本序列化：public final String name="尼古拉斯";
 *      使用v2版本进行反序列化：public final String name="凯奇";
 * 2. 反序列化时构造函数不会执行
 *      所以构造函数赋值时，修改成V2版本反序列化时，name是不会变的
 *      JVM从数据流种获取一个Object对象，然后根据数据流中的类文件描述信息查看(在序列化时，保存到磁盘的对象文件中包含了类描述信息，注意是类描述信息，不是类)
 *      发现final变量name，需要重新计算，于是引用Al类中的name值，而此时JVM发现name没有赋值，不能引用，于是不再初始化，保持原值，所以结果仍未v1版本。
 *
 * 场景介绍
 *  桌面应用，C/S结构24小时在线，升级的类中有一个final变量是构造函数赋值，而且新旧版本发生变化
 *  则应用请求热切的过程中，很可能出现反序列化生产的final变量值与新产生的实例值不相同的情况
 *
 */
public class Al implements Serializable {

    private static final long serialVersionID = 123L;

    //1.直接量
//    public final String name="尼古拉斯";//v1
//    public final String name="凯奇";//v2


    //2.构造函数赋值
    public final String name;

    //v1
//    public Al() {
//        name = "尼古拉斯";
//    }

    //v2
    public Al() {
        name = "凯奇";
    }

    public String getName() {
        return name;
    }

}

class AlTest {
    //序列化
    public static void main(String[] args) {
        SerializationUtils.writeObject(new Al());
    }
}

class AlTest2 {
    //反序列化
    public static void main(String[] args) {
        Al al = (Al) SerializationUtils.readObject();
        System.err.println(al.getName());
    }
}