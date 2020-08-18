package indi.ikun.spring.basejava.codequality.chapter09;

/**
 * 123.volatile不能保证数据同步
 *
 * 注意：volatile不能保证数据是同步的，只能保证线程能够获得最新值
 *
 * volatile关键字比较少用，原因无外乎两点，
 * 一是在Java 1.5之前该关键字在不同的操作系统上有不同的表现，所带来的问题就是移植性较差；
 * 二是比较难设计，而且误用较多，这也导致它的“名誉”受损
 *
 * 每个线程都运行在栈内存中，
 * 每个线程都有自己的工作内存（Working Memory，比如寄存器Register、高速缓冲存储器Cache等），
 * 线程的计算一般是通过工作内存进行交互的，
 * 线程读取变量示意图：[image](src/main/resources/img/123_1.png)
 * 线程在初始化时从主内存中加载所需的变量值到工作内存中，
 * 然后在线程运行时，
 *      如果是读取，则直接从工作内存中读取，
 *      若是写入则先写到工作内存中，之后再刷新到主存中，
 * 这是JVM的一个简单的内存模型，但是这样的结构在多线程的情况下有可能会出现问题，
 * 比如：
 *      A线程修改变量的值，也刷新到了主存中，
 *      但B、C线程在此时间内读取的还是本线程的工作内存，
 *      也就是说它们读取的不是最“新鲜”的值，
 *      此时就出现了不同线程持有的公共资源不同步的情况
 * 使用synchronized同步代码块，
 * 或者使用Lock锁来解决该问题，
 * 不过，Java可以使用volatile更简单地解决此类问题，
 * volatile变量操作示意图[image](src/main/resources/img/123_2.png)
 * 比如在一个变量前加上volatile关键字，
 * 可以确保每个线程对本地变量的访问和修改都是直接与主内存交互的，
 * 而不是与本线程的工作内存交互的，保证每个线程都能获得最“新鲜”的变量值
 */
public class Es {
    /**
     * 1）启动100个线程，修改共享资源count的值
     * 2）暂停15毫秒，观察活动线程数是否为1（即只剩下主线程在运行），若不为1，则再等待15毫秒
     * 3）判断共享资源是否是不安全的，即实际值与理想值是否相同，若不相同，则发现目标，此时count的值为脏数据
     * 4）如果没有找到，继续循环，直到达到最大循环次数为止
     */
    public static void main(String[] args) throws Exception{
        //理想值，并作为最大循环次数
        int value=1000;
        //循环次数，防止出现无线循环造成死机情况
        int loops=0;
        //主线程组，用于估计活动线程数
        ThreadGroup tg=Thread.currentThread().getThreadGroup();
        while (loops++<value){
            //共享资源清零
            UnSafeThread ut=new UnSafeThread();
            for (int i = 0; i <value ; i++) {
                new Thread(ut).start();
            }
            //先等15ms，等待活动线程数量为1
            do{
                Thread.sleep(15);
            }while (tg.activeCount()!=1);
            //检查实际值于理论值是否一致
            if(ut.getCount()!=value){
                System.err.println("循环到第 "+loops+" 遍，出现线程不安全情况");
                System.err.println("此时，count="+ut.getCount());
                System.exit(0);
            }
        }
    }

}
class UnSafeThread implements Runnable{
    private volatile int count=0;

    /**
     * run方法的主要逻辑是共享资源count的自加运算，
     * 为count变量加上了volatile关键字，确保是从主内存中读取和写入的
     */
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            /**
             * UnsafeThread类的消耗CPU计算是必须的，
             * 其目的是加重线程的负荷，以便出现单个线程抢占整个CPU资源的情景，
             * 否则很难模拟出volatile线程不安全的情况
             */
            Math.hypot(Math.pow(92456789,i),Math.cos(i));
        }
        /**
         * count++ 表示的是先取出count的值然后再加1，也就是count=count+1，
         * 在某两个紧邻的时间片段内会发生如下神奇的事情：
         * （1）第一个时间片段
         *      A线程获得执行机会，因为有关键字volatile修饰，所以它从主内存中获得count的最新值998，
         *      接下来的事情又分为两种类型：
         *          1） 如果是单CPU，此时调度器暂停A线程执行，出让执行机会给B线程，
         *              于是B线程也获得了count的最新值998。
         *          1） 如果是多CPU，此时线程A继续执行，
         *              而线程B也同时获得count的最新值998。
         * （2）第二个时间片段
         *      1）如果是单CPU，B线程执行完加1动作（这是一个原子处理），count的值为999，
         *          由于是volatile类型的变量，所以直接写入主内存，
         *          然后A线程继续执行，计算的结果也是999，重新写入主内存中。
         *      2）如果是多CPU，A线程执行完加1动作后修改主内存的变量count为999，
         *          线程B执行完毕后也修改主内存中的变量为999
         * 这两个时间片段执行完毕后，原本期望的结果为1000，但运行后的值却为999，
         * 这表示出现了线程不安全的情况。
         * 这也是我们要说明的：
         *      volatile关键字并不能保证线程安全，
         *      它只能保证当线程需要该变量的值时能够获得最新的值，
         *      而不能保证多个线程修改的安全性
         */
        count++;
    }
    public int getCount(){
        return count;
    }
}

