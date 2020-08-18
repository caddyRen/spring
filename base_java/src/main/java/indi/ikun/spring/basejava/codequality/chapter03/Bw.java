package indi.ikun.spring.basejava.codequality.chapter03;

/**
 * 49.推荐覆写toString方法
 * Java提供的默认toString方法不友好，打印出来看不懂:类名+@+hashCode
 * println的实现机制：
 *      如果是一个原始类型就直接打印，
 *      如果是一个类类型，则打印出其toString方法的返回值
 * 当Bean的属性较多时，自己实现就不可取了，
 * 不过可以使用apache的commons工具包中的ToStringBuilder类，简洁、实用又方便
 */
public class Bw {
    public static void main(String[] args) {
        System.err.println(new PersonBw("二狗"));
    }
}
class PersonBw{
    private String name;
    public PersonBw(String _name){
        name=_name;
    }
    public String toString(){
        return String.format("%s.name=%s",this.getClass(),name);
    }

}