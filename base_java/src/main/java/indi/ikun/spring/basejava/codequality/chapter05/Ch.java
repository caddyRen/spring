package indi.ikun.spring.basejava.codequality.chapter05;


import java.util.List;

/**
 * 60.性能考虑，数组是首选
 *
 * 数组在实际的系统开发中用得越来越少了，
 * 我们通常只有在阅读一些开源项目时才会看到它们的身影，
 * 在Java中它确实没有List、Set、Map这些集合类用起来方便，
 * 但是在基本类型处理方面，数组还是占优势的，而且集合类的底层也都是通过数组实现的
 *
 *
 * 注意：
 * 性能要求较高的场景中使用数组替代集合
 */
public class Ch {
    public static int sum(List<Integer> datas){
        int sum=0;
        for(int i=0;i<datas.size();i++){
            /**
             * 拆箱
             * Integer对象通过intValue方法自动转换成了一个int基本类型，
             * 对于性能濒于临界的系统来说该方案是比较危险的，特别是大数量的时候
             *
             * 首先，在初始化List数组时要进行装箱动作，把一个int类型包装成一个Integer对象，
             * 虽然有整型池在，但不在整型池范围内的都会产生一个新的Integer对象，
             * 而且众所周知，基本类型是在栈内存中操作的，而对象则是在堆内存中操作的，
             * 栈内存的特点是速度快，容量小，
             * 堆内存的特点是速度慢，容量大（从性能上来讲，基本类型的处理占优势）。
             * 其次，在进行求和计算（或者其他遍历计算）时要做拆箱动作，
             * 因此无谓的性能消耗也就产生
             *
             */
            sum+=datas.get(i);
        }
        return sum;
    }
}
