package indi.ikun.spring.basejava.codequality.chapter05;


import java.util.Arrays;

/**
 * 61.若有必要，使用变长数组
 * Java中的数组是定长的，一旦经过初始化声明就不可改变长度，
 * 通过对数组扩容“婉转”地解决该问题
 */
public class Ci {
    public static void main(String[] args) {
        //一个班级最大60人
        StuCi[] classes = new StuCi[60];
        //偶尔一个班级可以容纳80人，扩容
        classes = expandCapacity(classes, 80);
    }

    /**
     * 数组扩容
     * 采用的是Arrays数组工具类的copyOf方法，产生了一个newLen长度的新数组，并把原有的值拷贝了进去，之后就可以对超长的元素进行赋值
     */
    public static <T> T[] expandCapacity(T[] datas, int newLen) {
        //不能为负值
        newLen = newLen < 0 ? 0 : newLen;
        //生成一个新数组，并拷贝原值
        return Arrays.copyOf(datas, newLen);
    }
}

class StuCi {

}
