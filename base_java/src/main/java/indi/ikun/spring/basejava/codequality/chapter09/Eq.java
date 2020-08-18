package indi.ikun.spring.basejava.codequality.chapter09;

/**
 * 121.线程优先级只使用三个等级
 *
 * Thread类中设置了三个优先级，
 * 此意就是告诉开发者，
 * 建议使用优先级常量，
 *      public final static int MIN_PRIORITY = 1;
 *      public final static int NORM_PRIORITY = 5;
 *      public final static int MAX_PRIORITY = 10;
 * 而不是1到10随机的数字
 *
 * 线程的优先级（Priority）决定了线程获得CPU运行的机会，
 * 优先级越高获得的运行机会越大，
 * 优先级越低获得的机会越小。
 * Java的线程有10个级别（准确地说是11个级别，级别为0的线程是JVM的，应用程序不能设置该级别）
 * 1)并不是严格遵照线程优先级别来执行的
 *      优先级为1的线程可能比优先级为2的线程先执行，
 *      但很少会出现优先级为2的线程比优先级为10的线程先执行
 *      （这里用了一个词“很少”，是说确实有可能出现，只是几率非常低，
 *      因为优先级只是表示线程获得CPU运行的机会，并不代表强制的排序号）
 * 2）优先级差别越大，运行机会差别越明显
 *      优先级为10的线程通常会比优先级为2的线程先执行，
 *      但是优先级为6的线程和优先级为5的线程差别就不太明显
 * 获得CPU资源是依照操作系统设定的线程优先级来分配的，
 * 每个线程要运行，需要操作系统分配优先级和CPU资源，
 * 对于Java来说，JVM调用操作系统的接口设置优先级，
 * 比如Windows操作系统是通过调用SetThreadPriority函数来设置的
 *
 * 不同的操作系统线程优先级是不相同的，
 * Windows有7个优先级，
 * Linux有140个优先级，
 * Freebsd则有255个（此处指的是优先级总数，不同操作系统有不同的分类，如中断级线程、操作系统级等，各个操作系统具体用户可用的线程数量也不相同）。
 * Java是跨平台的系统，需要把这个10个优先级映射成不同操作系统的优先级，
 * 于是界定了Java的优先级只是代表抢占CPU的机会大小，
 * 优先级越高，抢占CPU的机会越大，被优先执行的可能性越高，
 * 优先级相差不大，则抢占CPU的机会差别也不大，这就是导致了优先级为9的线程可能比优先级为10的线程先运行
 *
 * 在编码时直接使用这些优先级常量，
 * 可以说在大部分情况下MAX_PRIORITY的线程会比NORM_PRIORITY的线程先运行，
 * 但是不能认为必然会先运行，不能把这个优先级做为核心业务的必然条件，
 * Java无法保证优先级高肯定会先执行，
 * 只能保证高优先级有更多的执行机会。
 * 因此，建议在开发时只使用此三类优先级，没有必要使用其他7个数字，
 * 这样也可以保证在不同的操作系统上优先级的表现基本相同
 *
 * 如果优先级相同，也是由操作系统决定的，
 * 基本上是按照FIFO原则（先入先出，First Input FirstOutput）
 * 但也是不能完全保证
 */
public class Eq {
    /**
     * 创建了20个线程，每个线程在运行时都耗尽了CPU资源，
     * 因为优先级不同，
     * 线程调度应该最先处理优先级最高的，
     * 然后处理优先级最低的，
     * 也就是先执行2个优先级为10的线程，
     * 然后执行2个优先为9的线程，
     * 2个优先级为8的线程……但是结果却并不是这样的
     */
    public static void main(String[] args) {
        for(int i=0;i<20;i++){
            System.err.println(i+" % 10 = "+i%10);
            new TestThread().start(i%10+1);
        }
    }

}
class TestThread implements Runnable{

    public void start(int _priority){
        Thread t=new Thread(this);
        t.setPriority(_priority);
        t.start();
    }
    @Override
    public void run() {
        for (int i=0;i<100000;i++){
            Math.hypot(Math.pow(924526789,i),Math.cos(i));
        }
        System.err.println(Thread.currentThread().getPriority());
    }
}

