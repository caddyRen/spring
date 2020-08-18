package indi.ikun.spring.basejava.codequality.chapter01;

import java.io.Serializable;

/**
 * 13.避免为final变量复杂赋值
 *  为final变量赋值还有一种方法：通过方法赋值，即直接在声明时通过方法返回值赋值，
 *
 *  final会被被重新赋值，其中的”值“指的是简单对象。
 *  简单对象包括：8个基本类型，
 *  以及数组、字符串（字符串情况很复杂，不通过new关键字生成String对象的情况下，final变量的赋值与基本类型相同）
 *  但是不能方法赋值
 *
 *  原理：
 *      保存在磁盘上（或网络传输）的对象文件包括两部分；
 *         1.类描述信息
 *              包路径、继承关系、访问权限、变量描述、变量访问权限、方法签名、返回值、变量的关联信息
 *              注意：不是class文件的翻版，它不记录方法、构造函数、static变量等的具体实现
 *              保存类描述，是为了反序列化
 *         2.非瞬态（transient关键字）和 非静态（static关键字）的实例变量值
 *              值：如果是一个基本类型，就直接保存下来
 *              如果是复杂对象，连该对象和关联类信息一起保存，并且持续递归下去（关联的类必须实现Serializable接口，否则会出异常）
 *              递归到最后其实还是基本数据类型的保存
 *         综上两点，一个持久化后的对象文件会比一个class类文件大很多
 *  总结
 *      反序列化时final变量在一下情况不会被重新赋值
 *          1.通过构造函数为final变量赋值
 *          2.通过方法返回值为final变量赋值
 *          3.final修饰的属性不是基本类型
 *
 *
 */
public class Am implements Serializable {

    private static final long serialVersionID = 123L;

    //通过方法赋值
    public final String name=setName();

    public String setName() {
//        return "尼古拉斯";//v1
        return "凯奇";//v2
    }

    public String getName() {
        return name;
    }
}
class AmTest {
    //序列化
    public static void main(String[] args) {
        SerializationUtils.writeObject(new Am());
    }
}

class AmTest2 {
    //反序列化
    public static void main(String[] args) {
        Am am = (Am) SerializationUtils.readObject();
        System.err.println(am.getName());
    }
}
