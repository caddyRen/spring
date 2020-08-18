package indi.ikun.spring.basejava.codequality.chapter02;

/**
 * 27.谨慎包装类型的大小比较
 *
 * 只要是两个对象之间的比较就应该采用相应的方法，而不是通过Java的默认机制来处理，除非你确定对此非常了解
 */
public class Ba {

    public static void main(String[] args) {
        Integer i=new Integer(100);
        Integer j=new Integer(110);
        compare(i,j);
    }

    public static void compare(Integer i,Integer j){
        /**
         * Java中“==”是用来判断两个操作数是否有相等关系的，
         * 如果是基本类型则判断值是否相等，
         * 如果是对象则判断是否是一个对象的两个引用，也就是地址是否相等，
         * 这里很明显是两个对象，两个地址，不可能相等
         */
        System.err.println(i==j);//false
        /**
         * Java中，“>”和“<”用来判断两个数字类型的大小关系，
         * 注意只能是数字型的判断，
         * 对于Integer包装类型，是根据其intValue()方法的返回值（也就是其相应的基本类型）进行比较的（其他包装类型是根据相应的value值来比较的，如doubleValue、floatValue等），
         * 显然，两者不可能有大小关系
         */
        System.err.println(i>j);//false
        System.err.println(i<j);//false

        //正确比较方式
        //i=j  0
        //i>j  1
        //i<j  -1
        System.err.println(i.compareTo(j));
    }
}
