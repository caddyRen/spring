package indi.ikun.spring.basejava.codequality.chapter03;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 48.覆写equals方法必须覆写hashCode方法
 * 对象元素的hashCode它是一个对象的哈希码，
 * 是由Object类的本地方法生成的，
 * 确保每个对象有一个哈希码（这也是哈希算法的基本要求：
 * 任意输入k，通过一定算法f(k)，将其转换为非可逆的输出，
 * 对于两个输入k1和k2，要求若k1=k2，则必须f(k1)=f(k2)，
 * 但也允许k1≠k2，f(k1)=f(k2)的情况存在）
 *
 * HashCodeBuilder是org.apache.commons.lang.builder包下的一个哈希码生成工具，
 * （为什么不直接写hashCode方法？因为哈希码的生成有很多种算法，自己写麻烦，事儿又多，所以采用拿来主义是最好的方法）
 *
 */
public class Bv {
    public static void main(String[] args) {
        /**
         * HashMap的底层处理机制是以数组的方式保存Map条目（MapEntry），
         * 这其中的关键是这个数组下标的处理机制：
         * 依据传入元素hashCode方法的返回值决定其数组的下标，
         * 如果该数组位置上已经有了Map条目，
         *      与传入的键值相等则不处理，
         *      若不相等则覆盖；
         * 如果数组位置没有条目，则插入，并加入到Map条目的链表中。
         * 同理，检查键是否存在也是根据哈希码确定位置，然后遍历查找键值
         */
        Map<PersonBv,Object> map=new HashMap<PersonBv,Object>(){
            {
                put(new PersonBv("张三"),new Object());
            }
        };

        List<PersonBv>  list=new ArrayList<PersonBv>(){
            {
                add(new PersonBv("张三"));
            }
        };
        boolean b1=list.contains(new PersonBv("张三"));
        /**
         * 不重写hashCode方法，
         * 两个张三对象的hashCode方法返回值（也就是哈希码）肯定是不相同
         * 在HashMap的数组中也就找不到对应的Map条目，于是就返回了false
         */
        boolean b2=map.containsKey(new PersonBv("张三"));
        System.err.println(b1);
        System.err.println(b2);
    }
}
class PersonBv{
    private String name;

    public PersonBv(String _name){
        name=_name;
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(name).toHashCode();
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null&&obj.getClass()==this.getClass()){
            PersonBv p=(PersonBv)obj;
            if(p.getName()==null||name==null){
                return false;
            }else
                return name.equalsIgnoreCase(p.getName());

        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}