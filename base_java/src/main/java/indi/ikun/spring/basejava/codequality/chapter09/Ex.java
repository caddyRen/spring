package indi.ikun.spring.basejava.codequality.chapter09;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 128.预防线程死锁
 * 线程死锁（DeadLock）是多线程编码中最头疼的问题，也是最难重现的问题，
 * 因为Java是单进程多线程语言，
 * 一旦线程死锁，则很难通过外科手术式的方法使其起死回生，
 * 很多时候只有借助外部进程重启应用才能解决问题
 *
 * 对于死锁的描述最经典的案例是哲学家进餐
 *      （五位哲学家围坐在一张圆形餐桌旁，人手一根筷子，
 *      做以下两件事情：吃饭和思考。
 *      要求吃东西的时候停止思考，思考的时候停止吃东西，
 *      而且必须使用两根筷子才能吃东西），
 * 解决此问题的方法很多，
 *      比如引入服务生（资源调度）、资源分级等方法都可以很好地解决此类死锁问题。
 *      在我们Java多线程并发编程中，死锁很难避免，也不容易预防，
 *      对付它的最好办法是测试：
 *          提高测试覆盖率，建立有效的边界测试，
 *          加强资源监控，这些方法能使死锁无处遁形，
 *          即使发生了死锁现象也能迅速查找到原因，提高系统的可靠性
 */
public class Ex {

    public static void main(String[] args) {
        Thread t=new Thread(new FooEx());
        t.start();

        /**
         * m1方法在执行时，线程t持有foo对象的锁，
         * 要想主线程获得m2方法的执行权限就必须等待m1方法执行完毕，
         * 也就是释放当前锁
         */
        final FooEx1 fooex1=new FooEx1();
        Thread t1=new Thread(()->{
            fooex1.m1();
        });
        t1.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fooex1.m2();
    }


    static class FooEx1{
        public synchronized void m1(){
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.err.println("m1执行完毕");
        }
        public synchronized void m2(){
            System.err.println("m2执行完毕");
        }
    }

}

/**
 * 在运行时当前线程（Thread-0）获得了FooEx对象的锁
 * （synchronized虽然是标注在方法上的，但实际作用的是整个对象），
 * 也就是该线程持有了FooEx对象的锁，所以它可以多次重入fun方法，也就是递归
 * 不会死锁
 * 可以这样来思考该问题，
 *      一个宝箱有N把钥匙，
 *      分别由N个海盗持有（也就是我们Java中的线程了），
 *      但是同一时间只能由一把钥匙打开宝箱，获取宝物，
 *      只有在上一个海盗关闭了宝箱（释放锁）后，
 *      其他海盗才能继续打开锁获取宝物，
 *      这里还有一个规则：
 *          一旦一个海盗打开了宝箱，
 *          则该宝箱内的所有宝物对他来说都是开放的，
 *          即使是“宝箱中的宝箱”（即内箱，递归）对他也是开放的
 */
class FooEx implements Runnable{
    @Override
    public void run() {
        fun(10);
    }

    public synchronized void fun(int i){
        if(--i>0){
            for (int j = 0; j < i; j++) {
                System.err.print("*");
            }
            System.err.println(i);
            fun(i);
        }
    }

}
/***************  死锁
 *线程死锁四个条件(只有满足了这些条件才可能产生线程死锁)
 *  1）互斥条件：一个资源每次只能被一个线程使用
 *  2）资源独占条件：一个线程因请求资源而阻塞时，对已获得的资源保持不放
 *  3）不剥夺条件：线程已获得的资源在未使用完之前，不能强行剥夺
 *  4）循环等待条件：若干线程之间形成一种头尾相接的循环等待资源关系（a=b,b=c,c=a）
 *解决线程死锁问题，就必须从这四个条件入手，一般情况下可以按照以下两种方式来解决：
 * 1)避免或减少资源共享
 *      一个资源被多个线程共享，若采用了同步机制，则产生的死锁可能性很大，
 *      特别是在项目比较庞大的情况下，很难杜绝死锁，
 *      对此最好的解决办法就是减少资源共享
 *      例如一个B/S结构的办公系统可以完全忽略资源共享，
 *      这是因为此类系统有三个特征：
 *          一是并发访问不会太高，
 *          二是读操作多于写操作，
 *          三是数据质量要求比较低，
 *       因此即使出现数据资源不同步的情况也不可能产生太大的影响，
 *       完全可以不使用同步技术。
 *       但是如果是一个支付清算系统就必须慎重考虑资源同步问题了，
 *       因为此类系统
 *          一是数据质量要求非常高（如果产生数据不同步的情况那可是重大生产事故），
 *          二是并发量大，
 *       不设置数据同步则会产生非常多的运算逻辑失效的情况，
 *       这会导致交易失败，产生大量的“脏”数据，
 *       系统可靠性将大大降低
 * 2)使用自旋锁
 *      例子，线程A在等待线程B释放资源，而线程B又在等待线程A释放资源，僵持不下，
 *      如果线程B设置了超时时间,在等待2秒后还是无法获得资源，则自行终结该任务.
 */
class Ex1{
    /**
     * 两个资源A和B，在两个线程A、B中使用了该资源，
     * 由于两个资源之间有交互操作，并且都是同步方法，
     * 因此在线程A休眠1秒钟后，它会试图访问资源B的b2方法，
     * 但是线程B持有该类的锁，并同时在等待A线程释放其锁资源，
     * 所以此时就出现了两个线程在互相等待释放资源的情况，也就是死锁
     * 此种情况下，线程A和线程B会一直互等下去，直到有外界干扰为止，
     *      比如终止一个线程，
     *      或者某一线程自行放弃资源的争抢，
     * 否则这两个线程就始终处于死锁状态了
     */
    public static void main(String[] args) {
        final ExA exa = new ExA();
        final ExB exb = new ExB();
        new Thread(()->{exa.a1(exb);},"线程ExA").start();
        new Thread(()->{exb.b1(exa);},"线程ExB").start();
    }
    static class ExA{
        public synchronized void a1(ExB b){
            String name=Thread.currentThread().getName();
            System.err.println(name+" 进入ExA.a1()");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println(name+"试图访问ExB.b2()");
            b.b2();
        }
        public synchronized void a2(){
            System.err.println("进入ExA.a2()");
        }
    }
    static class ExB{

        private Lock lock = new ReentrantLock();
        public synchronized void b1(ExA a){
            String name=Thread.currentThread().getName();
            System.err.println(name+" 进入ExB.b1()");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println(name+"试图访问ExA.a2()");
            a.a2();
        }

        /**
         * 使用tryLock实现了自旋锁（Spin Lock），它跟互斥锁一样，
         * 如果一个执行单元要想访问被自旋锁保护的共享资源，则必须先得到锁，
         * 在访问完共享资源后，也必须释放锁。
         * 如果在获取自旋锁时，没有任何执行单元保持该锁，那么将立即得到锁；
         * 如果在获取自旋锁时锁已经有保持者，那么获取锁操作将“自旋”在那里，直到该自旋锁的保持者释放了锁为止。
         * 在我们的例子中就是线程A等待线程B释放锁，在2秒内不断尝试是否能够获得锁，
         * 达到2秒后还未获得锁资源，线程A则结束运行，线程B将获得资源继续执行，
         * 死锁解除
         */
        public void b2(){
            try{
                //立即获得锁，或者2秒等待锁资源
                if(lock.tryLock(2,TimeUnit.SECONDS)){
                    System.err.println("进入ExB.b2()");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //释放锁
                lock.unlock();
            }
        }
    }
}