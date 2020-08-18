package indi.ikun.spring.basejava.codequality.chapter10;

/**
 * 133.若非必要，不要克隆对象
 *
 * 注意：克隆对象并不比直接生成对象效率高
 *
 * 通过clone方法生成一个对象时，就会不再执行构造函数了，
 * 只是在内存中进行数据块的拷贝，
 * 此方法看上去似乎应该比new方法的性能好很多，
 * 但是Java的缔造者们也认识到“二八原则”，
 * 80%（甚至更多）的对象是通过new关键字创建出来的，
 * 所以对new在生成对象（分配内存、初始化）时做了充分的性能优化，
 * 事实上，一般情况下new生成的对象比clone生成的性能方面要好很多
 *
 */
public class Fc {
    /**
     * 用new生成对象比clone方法快很多！
     * 原因是Apple的构造函数非常简单，
     * 而且JVM对new做了大量的性能优化，
     * 而clone方式只是一个冷僻的生成对象方式，并不是主流，
     * 它主要用于构造函数比较复杂，对象属性比较多，
     * 通过new关键字创建一个对象比较耗时间的时候
     */
    public static void main(String[] args) {
        final int maxLoops=10*10000;
        int loops=0;
        long start=System.currentTimeMillis();
        AppleFc appleFc=new AppleFc();
        while (++loops<maxLoops){
            appleFc.clone();
        }
        long mid=System.currentTimeMillis();
        System.err.println(mid);
        System.err.println(start);
        System.err.println((mid-start)+"ns");//5ns左右
        while (--loops>0){
            new AppleFc();
        }
        long end=System.currentTimeMillis();
        System.err.println(end);
        System.err.println(mid);
        System.err.println((end-mid)+"ns");//1ns左右
    }

    public static class AppleFc implements Cloneable {
        @Override
        protected Object clone(){
            try {
                return super.clone();
            } catch (CloneNotSupportedException e) {
                throw new Error();
            }
        }
    }


}

