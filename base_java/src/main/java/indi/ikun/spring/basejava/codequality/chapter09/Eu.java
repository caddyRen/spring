package indi.ikun.spring.basejava.codequality.chapter09;

import java.util.concurrent.*;

/**
 * 125.优先选择线程池
 *
 * 在Java 1.5之前，实现多线程编程比较麻烦，需要自己启动线程，并关注同步资源，防止出现线程死锁等问题，
 * 在1.5版之后引入了并行计算框架，大大简化了多线程开发。
 * 线程有五个状态：
 *      新建状态（New）、
 *      可运行状态（Runnable，也叫做运行状态）、
 *      阻塞状态（Blocked）、
 *      等待状态（Waiting）、
 *      结束状态（Terminated），
 * 线程的状态只能由新建转变为了运行态后才可能被阻塞或等待，最后终结，不可能产生本末倒置的情况，
 * 比如想把一个结束状态的线程转变为新建状态，则会出现异常
 *
 * 一个线程的运行时间分为三部分：
 *      T1为线程启动时间，
 *      T2为线程体的运行时间，
 *      T3为线程销毁时间，
 * 如果一个线程不能被重复使用，
 * 每次创建一个线程都需要经过启动、运行、销毁这三个过程，那么这势必会增大系统的响应时间
 * T2是无法避免的，只有通过优化代码来实现降低运行时间。
 * T1和T2都可以通过线程池（Thread Pool）来缩减时间，
 *      比如在容器（或系统）启动时，创建足够多的线程，
 *      当容器（或系统）需要时直接从线程池中获得线程，
 *      运算出结果，再把线程返回到线程池中---ExecutorService就是实现了线程池的执行器，
 *
 * 使用线程池减少的是线程的创建和销毁时间，
 * 这对于多线程应用来说非常有帮助，
 *      比如我们最常用的Servlet容器，每次请求处理的都是一个线程，
 *      如果不采用线程池技术，每次请求都会重新创建一个线程，
 *      这会导致系统的性能负荷加大，响应效率下降，降低了系统的友好性
 */
public class Eu {
    public static void main(String[] args) throws InterruptedException {
        //新建状态
        Thread t=new Thread(()-> System.err.println("线程在运行。。。"));
        //运行
        t.start();
        //是否运行状态，若不是等待10ms
        while (!t.getState().equals(Thread.State.TERMINATED)){
            TimeUnit.MILLISECONDS.sleep(10);
        }
        //直接由结束状态转变为运行状态
        //报IllegalThreadStateException异常，原因就是不能从结束状态直接转变为可运行状态
        t.start();
    }
}

/**
 * 实现线程池的三个名词
 *  1）工作线程（Worker）
 *      线程池中的线程，只有两个状态：可运行状态和等待状态，在没有任务时它们处于等待状态，运行时可以循环地执行任务
 *  2）任务接口（Task）
 *      每个任务必须实现的接口，以供工作线程调度器调度，
 *      它主要规定了
 *          任务的入口、
 *          任务执行完的场景处理、
 *          任务的执行状态等。
 *      这里有两种类型的任务：
 *          有返回值（或异常）的Callable接口任务
 *          无返回值并兼容旧版本的Runnable接口任务
 *  3）任务队列（Wok Queue）
 *      也叫做工作队列，用于存放等待处理的任务，
 *      一般是BlockingQueue的实现类，用来实现任务的排队处理
 */
class Eu1 {
    //先创建了一个包含两个线程的线程池，然后在线程池中多次运行线程体
    public static void main(String[] args) {
        //2个线程的线程池
        ExecutorService es=Executors.newFixedThreadPool(2);
        //多次执行线程体
        for (int i = 0; i < 4; i++) {
            es.submit(()-> System.err.println(Thread.currentThread().getName()));
        }
        //关闭执行器
        es.shutdown();
    }
}
/**
 * 结合下面的源码分析得出
 * 线程池的创建过程：
 *      创建一个阻塞队列以容纳任务，
 *      在第一次执行任务时创建足够多的线程（不超过许可线程数），并处理任务，
 *      之后每个工作线程自行从任务队列中获得任务，
 *      直到任务队列中的任务数量为0为止，
 *      此时，线程将处于等待状态，
 *      一旦有任务再加入到队列中，
 *      即唤醒工作线程进行处理，实现线程的可复用性
 *

使用了LinkedBlockingQueue作为任务队列管理器，所有等待处理的任务都会放在该队列中，
    需要注意的是，此队列是一个阻塞式的单端队列
public class Executors {
    public static ExecutorService newFixedThreadPool(int nThreads) {
        //生成一个最大为nThreads的线程池执行器
        return new ThreadPoolExecutor(
            nThreads,
            nThreads,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());
    }
}

线程池中的线程是在submit第一次提交任务时建立的
代码关键是execute方法,实现了三个职责
    1)创建足够多的工作线程数，数量不超过最大线程数量，并保持线程处于运行或等待状态
    2)把等待处理的任务放到任务队列中
    3)从任务队列中取出任务来执行
    关键是工作线程的创建，
    它也是通过new Thread方式创建的一个线程，
    只是它创建的并不是我们的任务线程（虽然我们的任务实现了Runnable接口，但它只是起一个标志性的作用），
    而是经过包装的Worker线程
public abstract class AbstractExecutorService implements ExecutorService {
 public Future<?> submit(Runnable task) {
        //检查任务是否为null
        if (task == null) throw new NullPointerException();
        //把Runnable任务包装成具有返回值的任务对象，不过此时并没有执行，只是包装
        RunnableFuture<Void> ftask = newTaskFor(task, null);
        //执行此任务
        execute(ftask);
        //返回任务预期执行结果
        return ftask;
    }
}

 Worker线程 简化示意代码
 execute方法是通过Worker类启动的一个工作线程，执行的是我们的第一个任务，
 然后该线程通过getTask方法从任务队列中获取任务，之后再继续执行，
 但问题是任务队列是一个BlockingQueue，是阻塞式的，
 也就是说如果该队列元素为0，则保持等待状态，直到有任务进入为止
 public class ThreadPoolExecutor extends AbstractExecutorService {
    private final class Worker extends AbstractQueuedSynchronizer implements Runnable
    {
        //运行一次任务
        private void runTask(Runnable task){
            //这里task才是我们自定义实现Runnable接口的任务
            task.run();
        }
        //工作线程也是线程必须实现run方法
        private void run(){
           while(task!=null ||(task=getTask())!=null{
            runTask(task);
            task=null;
           }
        }
        //从任务队列中获得任务
        Runnable getTask(){
           for(;;){
                return workQueue.take();
           }
        }
 }
 }

 查看LinkedBlockingQueue的take方法
 public class LinkedBlockingQueue<E> extends AbstractQueue<E>
 implements BlockingQueue<E>, java.io.Serializable {
     public E take() throws InterruptedException {
         E x;
         int c = -1;
         final AtomicInteger count = this.count;
         final ReentrantLock takeLock = this.takeLock;
         takeLock.lockInterruptibly();
         try {
 //如果队列中元素数量为0，则等待
             while (count.get() == 0) {
             notEmpty.await();
         }
 //等待状态结束，弹出头元素
         x = dequeue();
         c = count.getAndDecrement();
 //如果队列数量还对于1个，唤醒其他线程
         if (c > 1)
            notEmpty.signal();
         } finally {
            takeLock.unlock();
         }
         if (c == capacity)
         signalNotFull();
 //返回头元素
         return x;
     }
 }
*/

