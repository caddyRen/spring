package indi.ikun.spring.basejava.codequality.chapter05;

import java.util.ArrayList;
import java.util.List;

/**
 * 70.子列表只是原列表的一个视图
 *
 * 注意 subList产生的列表只是一个视图，所有的修改动作直接作用于原列表
 *
 * List接口提供了subList方法，其作用是返回一个列表的子列表
 * subList方法是由AbstractList实现的，它会根据是不是可以随机存取来提供不同的SubList实现方式，
 * 随机存储的使用频率比较高，而且RandomAccessSubList也是SubList子类，
 * 所以所有的操作都是由SubList类(AbstractList类内部)实现的（除了自身的SubList方法外）
 *
 * subList方法的实现原理：
 * 返回的SubList类也是AbstractList的子类，
 * 其所有的方法如get、set、add、remove等都是在原始列表上的操作，
 * 它自身并没有生成一个数组或是链表，
 * 也就是子列表只是原列表的一个视图（View），所有的修改动作都反映在了原列表
 */
public class Cr {
    public static void main(String[] args) {
        Cr cr = new Cr();

        List<String> c=new ArrayList<>();
        c.add("A");
        c.add("B");
        //构造一个包含c列表的字符串列表
        List<String> c1=new ArrayList<>(c);

        //subList生成与c相同的列表
        List<String> c2=c.subList(0,c.size());
        c2.add("C");
        System.err.println(c.equals(c1));//false
        System.err.println(c.equals(c2));//true
        System.err.println(c==c2);//false
        cr.displayList(c2);
        cr.displayList(c1);
        cr.displayList(c);
    }


    public void displayList(List<String> list){
        for(String string:list){
            System.err.println(string);
        }
    }

}





//扩展String 的subString
class SubStringCr{
    public static void main(String[] args) {
        String str="AB";
        String str1=new String(str);
        String str2=str.substring(0)+"C";
        System.err.println(str==str1);//false
        System.err.println(str.equals(str1));//true
        System.err.println(str==str2);//false
        System.err.println(str.equals(str2));//false
    }
}