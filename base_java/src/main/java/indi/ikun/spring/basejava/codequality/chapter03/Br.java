package indi.ikun.spring.basejava.codequality.chapter03;

import java.io.*;

/**
 * 44.推荐使用序列化实现对象的拷贝
 * 通过序列化方式来处理，在内存中通过字节流的拷贝来实现，
 * 也就是把母对象写到一个字节流中，再从字节流中将其读出来，这样就可以重建一个新对象了，
 * 该新对象与母对象之间不存在引用共享的问题，也就相当于深拷贝了一个新对象、
 *
 * 采用序列化方式拷贝时还有一个更简单的办法，即使用Apache下的commons工具包中的SerializationUtils类，直接使用更加简洁方便
 */
public class Br {
    public static void main(String[] args) {
        PersonBr f=new PersonBr("你爹");
        PersonBr s1=new PersonBr("逆子",f);
        PersonBr s2=CloneUtils.clone(s1);
        s2.setName("孽障");
        System.err.println(s1.getName()+s1.getFather().getName());
        System.err.println(s2.getName()+s2.getFather().getName());
        //逆子认干爹，
        s1.getFather().setName("干爹");
        System.err.println(s1.getName()+s1.getFather().getName());
        System.err.println(s2.getName()+s2.getFather().getName());
    }
}


class PersonBr implements Serializable{
    private static final long serialVersionID=1111111111L;
    private String name;
    private PersonBr father;

    public PersonBr(String name) {
        this.name = name;
    }

    public PersonBr(String name, PersonBr father) {
        this.name = name;
        this.father = father;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonBr getFather() {
        return father;
    }

    public void setFather(PersonBr father) {
        this.father = father;
    }
}

/**
 * 此工具类要求被拷贝的对象必须实现Serializable接口
 * 注意
 * 1.对象的内部属性都是可序列化的
 *      如果有内部属性不可序列化，则会抛出序列化异常，这会让调试者很纳闷：生成一个对象怎么会出现序列化异常呢？从这一点来考虑，也需要把CloneUtils工具的异常进行细化处理
 * 2.注意方法和属性的特殊修饰符
 *      比如final、static变量的序列化问题会被引入到对象拷贝中来（参考12），这点需要特别注意，同时transient变量（瞬态变量，不进行序列化的变量）也会影响到拷贝的效果
 */
class CloneUtils{
    //拷贝一个对象
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T clone(T obj){
        //拷贝产生的对象
        T cloneobj=null;
        try{
            //读取对象字节数据
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            ObjectOutputStream oos=new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.close();
            //分配内存空间写入原始对象，生产新对象
            ByteArrayInputStream bais=new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois=new ObjectInputStream(bais);
            //返回新对象，并作类型转换
            cloneobj=(T)ois.readObject();
            ois.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return cloneobj;
    }
}