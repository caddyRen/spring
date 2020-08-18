package indi.ikun.spring.basejava.codequality.chapter09;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 131.CyclicBarrier让多线程齐步走
 *
 * 案例：
 *      两个工人从两端挖掘隧道，各自独立奋战，中间不沟通，
 *      如果两人在汇合点处碰头了，则表明隧道已经挖通。
 *      这描绘的也是在多线程编程中，
 *      两个线程独立运行，在没有线程间通信的情况下，
 *      如何解决两个线程汇集在同一原点的问题。
 *      Java提供了CyclicBarrier（关卡，也有翻译为栅栏）工具类来实现
 * CyclicBarrier关卡可以让所有线程全部处于等待状态（阻塞），
 * 然后在满足条件的情况下继续执行，
 * 这就好比是一条起跑线，
 * 不管是如何到达起跑线的，
 * 只要到达这条起跑线就必须等待其他人员，
 * 待人员到齐后再各奔东西，
 *
 * CyclicBarrier关注的是汇合点的信息，
 *      而不在乎之前或之后做何处理。
 * CyclicBarrier可以用在系统的性能测试中，
 *      例如我们编写了一个核心算法，
 *      但不能确定其可靠性和效率如何，
 *      我们就可以让N个线程汇集到测试原点上，
 *      然后“一声令下”，所有的线程都引用该算法，
 *      即可观察出算法是否有缺陷
 */
public class Fa {
    static class WorkerFa implements Runnable{
        //关卡
        private CyclicBarrier cb;
        public WorkerFa(CyclicBarrier cb) {
            this.cb = cb;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(new Random().nextInt(1000));
                System.err.println(Thread.currentThread().getName()+"-到达汇合点");
                //到达汇合点
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 定义了一个需要等待2个线程汇集的CyclicBarrier关卡，
     * 并且定义了完成汇集后的任务（输出“隧道已经打通！”），
     * 然后启动了2个线程（也就是2个工人）开始执行任务。
     * 代码逻辑如下：
     *      1）2个线程同时开始运行，实现不同的任务，执行时间不同。
     *      2）“工人1”线程首先到达汇合点（也就是cb.await语句），转变为等待状态。
     *      3）“工人2”线程到达汇合点，满足预先的关卡条件（2个线程到达关卡），继续执行。
     *          此时还会额外的执行两个动作：
     *              1>执行关卡任务（也就是run方法）
     *              2>唤醒“工人1”线程。
     *      4）“工人1”线程继续执行
     */
    public static void main(String[] args) {
        //设置汇集数量，以及汇集完成后的任务,有两个汇集即ok
        CyclicBarrier cb=new CyclicBarrier(2,()->{
            System.err.println("隧道已打通");
        });
        new Thread(new WorkerFa(cb),"工人1").start();
        new Thread(new WorkerFa(cb),"工人2").start();
//        new Thread(new WorkerFa(cb),"工人3").start();
    }

}