package indi.ikun.spring.basejava.codequality.chapter03;

/**
 * 43.避免对象的浅拷贝
 * 一个类实现了Cloneable接口就表示它具备了被拷贝的能力，
 * 如果再覆写clone()方法就会完全具备拷贝能力。
 * 拷贝是在内存中进行的，所以在性能方面比直接通过new生成对象要快很多，特别是在大对象的生成上，这会使性能的提升非常显著。
 * 但是对象拷贝也有一个比较容易忽略的问题：浅拷贝（Shadow Clone，也叫做影子拷贝）存在对象属性拷贝不彻底的问题
 *
 * Object提供了一个对象拷贝的默认方法，即下面代码中的super.clone方法，
 * 但是该方法是有缺陷的，它提供的是一种浅拷贝方式，也就是说它并不会把对象的所有属性全部拷贝一份，而是有选择性的拷贝
 * 1. 基本类型
 *      如果变量是基本类型，则拷贝其值，比如int、float等
 * 2. 对象
 *      如果变量是一个实例对象，则拷贝地址引用，也就是说此时新拷贝出的对象与原有对象共享该实例变量，不受访问权限的限制。这在Java中是很疯狂的，因为它突破了访问权限的定义：一个private修饰的变量，竟然可以被两个不同的实例对象访问，这让Java的访问权限体系情何以堪
 * 3. String字符串
 *      拷贝的也是一个地址，是个引用，但是在修改时，它会从字符串池（String Pool）中重新生成新的字符串，原有的字符串对象保持不变，在此处我们可以认为String是一个基本类型
 */
public class Bq {

    public static void main(String[] args) {
        PersonBq f=new PersonBq("你爹");
        PersonBq s1=new PersonBq("逆子",f);
        PersonBq s2=s1.clone();
        s2.setName("孽障");
        System.err.println(s1.getName()+s1.getFather().getName());
        System.err.println(s2.getName()+s2.getFather().getName());
        //逆子认干爹，
        s1.getFather().setName("干爹");
        System.err.println(s1.getName()+s1.getFather().getName());
        System.err.println(s2.getName()+s2.getFather().getName());
    }


}

class PersonBq implements Cloneable{
    private String name;
    private PersonBq father;

    public PersonBq(String name, PersonBq father) {
        this.name = name;
        this.father = father;
    }

    public PersonBq(String name) {
        this.name = name;
    }

    //拷贝的实现
    @Override
    public PersonBq clone(){
        PersonBq p=null;
       try{
        p=(PersonBq)super.clone();
        //加一行优化，即可解决该例子的浅拷贝问题，即拷贝时候引用类型生产一个新的对象，不用原来的引用
        p.setFather(new PersonBq(p.getFather().getName()));
       }catch (CloneNotSupportedException e){
           e.printStackTrace();
       }
       return p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonBq getFather() {
        return father;
    }

    public void setFather(PersonBq father) {
        this.father = father;
    }
}
