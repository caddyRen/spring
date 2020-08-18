package indi.ikun.spring.basejava.codequality.chapter09;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 127.Lock与synchronized是不一样的
 * 显式锁（Lock类）和内部锁（synchronized关键字）不同
 * 1）Lock支持更细粒度的锁控制
 *      假设读写锁分离，写操作时不允许有读写操作存在，而读操作时读写可以并发执行，这一点内部锁就很难实现
 * 2）Lock是无阻塞锁，synchronized是阻塞锁
 *      当线程A持有锁时，线程B也期望获得锁，
 *      此时，如果程序中使用的是显式锁，
 *          则B线程为等待状态（在通常的描述中，也认为此线程被阻塞了），
 *      若使用的是内部锁则为
 *          阻塞状态
 * 3）Lock可实现公平锁，synchronized只能是非公平锁
 *      非公平锁：
 *          当一个线程A持有锁，而线程B、C处于阻塞（或等待）状态时，
 *          若线程A释放锁，JVM将从线程B、C中随机选择一个线程持有锁并使其获得执行权，
 *          这叫做非公平锁（因为它抛弃了先来后到的顺序）；
 *      公平锁：
 *          若JVM选择了等待时间最长的一个线程持有锁，则为公平锁（保证每个线程的等待时间均衡）。
 *      注意，
 *          即使是公平锁，JVM也无法准确做到“公平”，在程序中不能以此作为精确计算。
 *          显式锁默认是非公平锁，但可以在构造函数中加入参数true来声明出公平锁，
 *          而synchronized实现的是非公平锁，它不能实现公平锁
 * 4）Lock是代码级的，synchronized是JVM级的
 *      Lock是通过编码实现的，
 *      synchronized是在运行期由JVM解释的，
 *      相对来说synchronized的优化可能性更高，毕竟是在最核心部分支持的，
 *      Lock的优化则需要用户自行考虑
 * 显式锁和内部锁的功能各不相同，在性能上也稍有差别，
 * 但随着JDK的不断推进，相对来说，显式锁使用起来更加便利和强大，
 * 在实际开发中选择哪种类型的锁就需要根据实际情况考虑了：
 *      灵活、强大则选择Lock，
 *      快捷、安全则选择synchronized
 */
public class Ew {
    /**
     * 开始TaskWithLock
     * 线程名称pool-1-thread-2执行时间：20s
     * 线程名称pool-1-thread-1执行时间：20s
     * 线程名称pool-1-thread-3执行时间：20s
     * TaskWithLock结束
     * 开始TaskWithSync
     * 线程名称pool-2-thread-1执行时间：30s
     * 线程名称pool-2-thread-3执行时间：32s
     * 线程名称pool-2-thread-2执行时间：34s
     * TaskWithSync结束
     * <p>
     * Lock锁为什么不出现互斥情况
     * 因为对于同步资源来说（示例中是代码块），
     * 显式锁是对象级别的锁，Lock锁是跟随对象的，
     * 把Lock定义为多线程类的私有属性是起不到资源互斥作用的，
     * 除非是把Lock定义为所有线程的共享变量
     * 而内部锁是类级别的锁，synchronized锁是跟随类的
     */

    public static void main(String[] args) throws Exception {
        //看运行的时间戳，显式锁是同时运行的，
        // 很显然在pool-1-thread-1线程执行到sleep时，
        // 其他两个线程也会运行到这里，一起等待，然后一起输出
        runTasks(TaskWithLock.class);
        //内部锁的输出则是我们的预期结果：
        // pool-2-thread-1线程在运行时其他线程处于等待状态，
        // pool-2-thread-1执行完毕后，
        // JVM从等待线程池中随机获得一个线程pool-2-thread-3执行，
        // 最后再执行pool-2-thread-2
        runTasks(TaskWithSync.class);
    }

    public static void runTasks(Class<? extends Runnable> clz) throws Exception {
        ExecutorService es = Executors.newCachedThreadPool();
        System.err.println("开始" + clz.getSimpleName());
        for (int i = 0; i < 3; i++) {
            es.submit(clz.newInstance());
        }
        TimeUnit.SECONDS.sleep(10);
        System.err.println(clz.getSimpleName() + "结束");
        es.shutdown();
    }
}

class Ew1 {
    /**
     * 线程名称Thread-0、Thread-1、Thread-2会逐个输出（顺序不一定,可能是print的锅），
     * 也就是一个线程在执行时，其他线程就处于等待状态。
     * 注意，这里三个线程运行的实例对象是同一个类（都是Client$1类的实例）
     */
    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    lock.lock();
                    Thread.sleep(2000);
                    System.err.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }).start();
        }
    }
}

//任务要具备多线程能力时必须实现Runnable接口
class TaskEw {
    /**
     * 模拟了一个执行时间比较长的计算
     */
    public void doSomething() {
        try {
            //模拟方式，使用sleep方法将线程的状态从运行状态转变为等待状态
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            //异常处理
        }
        StringBuffer sb = new StringBuffer();
        sb.append("线程名称" + Thread.currentThread().getName());
        sb.append("执行时间：" + Calendar.getInstance().get(13) + "s");
        System.err.println(sb);
    }
}

//显式锁任务
class TaskWithLock extends TaskEw implements Runnable {
    //声明显式锁
    private final Lock lock = new ReentrantLock();

    //显式锁的锁定和释放必须在一个try……finally块中，
    //确保即使出现运行期异常也能正常释放锁，保证其他线程能够顺利执行
    @Override
    public void run() {
        try {
            //开始锁定
            lock.lock();
            doSomething();
        } finally {
            //释放锁
            lock.unlock();
        }
    }
}

//内部锁任务
class TaskWithSync extends TaskEw implements Runnable {
    @Override
    public void run() {
        //内部锁
        synchronized ("A") {
            doSomething();
        }
    }
}

/**
 * 读写锁分离
 */
class FooEw{
    //可重入的读写锁
    private final ReentrantReadWriteLock rwl=new ReentrantReadWriteLock();
    //读锁
    private final Lock r=rwl.readLock();
    //写锁
    private final Lock w=rwl.writeLock();
    private int i=1;
    //读操作，可并发执行
    public void read(){
        try{
            r.lock();
            Thread.sleep(1000);
            System.err.println(Thread.currentThread().getName()+"read...= "+i);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            r.unlock();
        }
    }
    //写操作，同时只允许一个写操作
    public void write(Object _obj){
        try{
            w.lock();
            Thread.sleep(1000);
            i++;
            System.err.println(Thread.currentThread().getName()+" writing...= "+i);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            w.unlock();
        }
    }
}

class FooEwRun{

    public static void main(String[] args) {
        FooEw fooEw=new FooEw();
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 6; i++) {
            es.submit(()->{
                fooEw.read();
            });
            es.submit(()->{
                fooEw.write(1);
            });
        }
        es.shutdown();
    }
}