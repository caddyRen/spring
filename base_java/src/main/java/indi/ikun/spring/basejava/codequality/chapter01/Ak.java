package indi.ikun.spring.basejava.codequality.chapter01;


import java.io.*;

/**
 * 11.养成良好习惯，显式声明UID
 *  类实现Serializable接口的目的是为了可持久化
 *  网络传输，本地存储，为系统的 分布 和 异构 部署提供先决支持条件
 *  UID：
 *      1.显示声明
 *          private static final long serialVersionUID=66666L;
 *      2.隐式声明：
 *          即不声明，编译器在编译的时候生成，
 *          依据是包名，类名，继承关系、非私有方法和属性，以及参数、返回值等诸多因子
 *          计算得出的极度复杂，基本上计算出来的值是唯一的
 * 问题：
 *  1.分布式下版本问题：
 *      隐式声明UID；
 *          生产者端和消费者端的Ak类有差异，
 *          比如生产者中的Ak增加了一个年龄属性，消费者端没有增加该属性，
 *          反序列化会报InvalidClassException异常
 *          因为序列化和反序列化所对应的类版本发生了变化，JVM不能把数据流转换为实例对象
 *          JVM通过SerialVersionUID，也叫做流标识符（Stream Unique Identifier）判断类是否变化
 *          相同则无变化，不同则抛出InvalidClassException异常
 *          这个机制可以保证一个对象即使在网络或磁盘中“滚过”一次，仍能做到“出淤泥而不染”，完美地实现类的一致性
 *      显示声明UID可以欺骗JVM：
 *          假如Ak类变动不大，通过显示声明SerialVersionUID欺骗JVM，版本不变，可以实现类的向上兼容，
 *          提高代码的健壮性
 *
 * 显式声明serialVersionUID可以避免对象不一致，但尽量不要以这种方式向JVM“撒谎”
 */
public class Ak implements Serializable {

    private static final long serialVersionUID=66666L;

    private String name;
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

//将java 对象序列化，保存到本地
//将属性age相关屏蔽
class Producer{

    public static void main(String args[]){
        Ak ak = new Ak();
        ak.setName("胜天半子");
        SerializationUtils.writeObject(ak);
    }
}

//读取java文件，反序了化java对象
//将属性age相关 取消屏蔽
class Consumer{
    public static void main(String[] args) {
        Ak ak=(Ak)SerializationUtils.readObject();
        System.err.println(ak.getName());
    }
}


/**
 * 读写java类
 */
class SerializationUtils{
    //win
    private static String FILE_NAME="d:/obj.bin";
    //arch
//    private static String FILE_NAME="d:/obj.bin";

    //序列化
    public static void writeObject(Serializable s){
        try {
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            oos.writeObject(s);
            oos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //反序列化 把一个对象从内存块转化为可传输的数据流，通过网络发送到消息消费者那里，进行反序列化，生成实例对象
    public static Object readObject(){
        Object obj=null;
        try {

        ObjectInput input=new ObjectInputStream(new FileInputStream(FILE_NAME));
        obj=input.readObject();
        input.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }
}