package indi.ikun.spring.basejava.codequality.chapter09;

import java.util.concurrent.*;

/**
 * 125.适时选择不同的线程池来实现
 * Java的线程池实现从最根本上来说只有两个：
 *      ThreadPoolExecutor类
 *      Scheduled-ThreadPoolExecutor类
 * 这两个类还是父子关系，
 * 但是Java为了简化并行计算，
 *      还提供了一个Executors的静态类，
 * 它可以直接生成多种不同的线程池执行器，
 *      比如单线程执行器、
 *      带缓冲功能的执行器等，
 * 归根结底还是使ThreadPoolExecutor类或ScheduledThreadPoolExecutor类的封装类
 *
 * 线程池的管理过程：
 *      首先创建线程池，
 *      然后根据任务的数量逐步将线程增大到corePoolSize数量，
 *      如果此时仍有任务增加，则放置到workQueue中，直到workQueue爆满为止，
 *      然后继续增加池中的线程数量（增强处理能力），最终达到maximumPoolSize，
 *      那如果此时还有任务要增加进来,这就需要handler来处理，或者丢弃新任务，或者拒绝新任务，或者挤占已有任务等
 * 在任务队列和线程池都饱和的情况下，
 * 一旦有线程处于等待（任务处理完毕，没有新任务增加）状态的时间超过keepAliveTime，则该线程终止，
 * 也就是说池中的线程数量会逐渐降低，直至为corePoolSize数量为止
 *
 * 场景：
 *  在一条生产线上，车间规定是可以有corePoolSize数量的工人，
 *  但是生产线刚建立时，工作不多，不需要那么多的人。
 *  随着工作数量的增加，工人数量也逐渐增加，直至增加到corePoolSize数量为止，
 *  此时任务还在增加，那怎么办
 * 解决方案：
 *  任务排队，corePoolSize数量的工人不停歇地处理任务，
 *  新增加的任务按照一定的规则存放在仓库中（也就是我们的workQueue中），
 *  一旦任务增加的速度超过了工人处理的能力，也就是说仓库爆满时，
 *  车间就会继续招聘工人（也就是扩大线程数），直至工人数量达到maximumPoolSize为止，
 *  那如果所有的maximumPoolSize工人都在处理任务，而且仓库也是饱和状态，新增任务的该怎么处理呢？
 *  这就会扔给一个叫handler的专门机构去处理了，它要么丢弃这些新增的任务，要么无视，要么替换掉别的任务
 *  过了一段时间后，任务的数量逐渐减少了，导致有一部分工人处以待工状态，
 *  为了减少开支（Java是为了减少系统资源消耗），于是开始辞退工人，直至保持为corePoolSize数量的工人为止，
 *  此时即使没有工作，也不再辞退工人（池中线程数量不再减少），
 *  这也是为了保证以后再有任务时能够快速的处理
 *
 *  Executors提供的几个创建线程池的便捷方法
 *      1）newSingleThreadExecutor：单线程池
 *              一个池中只有一个线程在运行，该线程永不超时。
 *              而且由于是一个线程，当有多个任务需要处理时，
 *              会将它们放置到一个无界阻塞队列中逐个处理
 *              看源码
 *      2）newCachedThreadPool：缓冲功能的线程池
 *              建立了一个线程池，而且线程数量是没有限制的（当然，不能超过Integer的最大值），
 *              新增一个任务即有一个线程处理，或者复用之前空闲的线程，或者新启动一个线程，
 *              但是一旦一个线程在60秒内一直是出于等待状态时（也就是1分钟没工作可做），则会被终止
 *              看源码：
 *                  任务队列使用了同步阻塞队列，这意味着向队列中加入一个元素，即可唤醒一个线程（新创建的线程或复用池中空闲线程）来处理，这种队列已经没有队列深度的概念了
 *      3）newFixedThreadPool：固定线程数量的线程池
 *              在初始化时已经决定了线程的最大数量，
 *              若任务添加的能力超出了线程处理能力，则建立阻塞队列容纳多余的任务
 *              看源码：
 *                  它的corePoolSize和maximumPoolSize是相等的，
 *                  也就是说，如果任务增长速度非常快，超过了LinkedBlockingQueue的最大容量（Integer的最大值），
 *                  那此时会按照ThreadPoolExecutor默认的拒绝策略（默认是DiscardPolicy，直接丢弃）来处理
 * 三种线程池执行器都是ThreadPoolExecutor的简化版，
 * 目的是帮助开发人员屏蔽过多的线程细节，简化多线程开发。
 * 当需要运行异步任务时，可以直接通过Executors获得一个线程池，然后运行任务，
 * 不需要关注ThreadPoolExecutor的一系列参数是什么含义。
 * 如果这三个线程池不能满足要求，此时则可以直接操作ThreadPoolExecutor来实现复杂的多线程运算。
 * 可以这样来比喻：
 *      newSingleThreadExecutor、newCachedThreadPool、newFixedThreadPool是线程池的简化版，
 *      而ThreadPoolExecutor则是旗舰版
 *      简化版更容易操作，需要了解的知识相对少些，方便实用，
 *      而旗舰版功能齐全，适用面广，但难于驾驭
 */
public class Ev {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //newSingleThreadExecutor：单线程池
        ExecutorService es1=Executors.newSingleThreadExecutor();
        //复习lambda写法
        //Future<String> future=es.submit(() -> "newSingleThreadExecutor：单线程池");
        Future<String> future=es1.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return Thread.currentThread().getName()+"newSingleThreadExecutor：单线程池";
            }
        });
        System.err.println(future.get());
        es1.shutdown();
        //newCachedThreadPool：缓冲功能的线程池
        ExecutorService es2=Executors.newCachedThreadPool();
        Future<String> future2=es2.submit(() -> Thread.currentThread().getName()+"newCachedThreadPool：缓冲功能的线程池");
        Future<String> future22=es2.submit(() -> Thread.currentThread().getName()+"newCachedThreadPool：缓冲功能的线程池");
        System.err.println(future22.get());
        System.err.println(future2.get());
        es2.shutdown();

        //newFixedThreadPool：固定线程数量的线程池
        ExecutorService es3=Executors.newFixedThreadPool(1);
        Future<String> future3=es3.submit(() -> Thread.currentThread().getName()+"newFixedThreadPool：固定线程数量的线程池");
        Future<String> future33=es3.submit(() -> Thread.currentThread().getName()+"newFixedThreadPool：固定线程数量的线程池");
        System.err.println(future3.get());
        System.err.println(future33.get());
        es3.shutdown();

    }
}
//ThreadPoolExecutor类源码
/*
public class ThreadPoolExecutor extends AbstractExecutorService {
    //ThreadPoolExecutor最完整的构造函数，其他的构造函数都是引用该构造函数实现的
    public ThreadPoolExecutor(
        int corePoolSize,                   //最小线程数:线程池启动后，在池中保持线程的最小数量。需要说明的是线程数量是逐步到达corePoolSize值的，例如corePoolSize被设置为10，而任务数量只有5，则线程池中最多会启动5个线程，而不是一次性地启动10个线程
        int maximumPoolSize,                //最大线程数量:这是池中能够容纳的最大线程数量，如果超出，则使用RejectedExecutionHandler拒绝策略处理
        long keepAliveTime,                 //线程最大生命期:生命期有两个约束条件：一是该参数针对的是超过corePoolSize数量的线程；二是处于非运行状态的线程(如果corePoolSize为10，maximumPoolSize为20，此时线程池中有15个线程在运行，一段时间后，其中有3个线程处于等待状态的时间超过了keepAliveTime指定的时间，则结束这3个线程，此时线程池中则还有12个线程正在运行)
        TimeUnit unit,                      //时间单位:keepAliveTime的时间单位，可以是纳秒、毫秒、秒、分钟等选项
        BlockingQueue<Runnable> workQueue,  //任务队列:当线程池中的线程都处于运行状态，而此时任务数量继续增加，则需要有一个容器来容纳这些任务，这就是任务队列
        ThreadFactory threadFactory,        //线程工厂:定义如何启动一个线程，可以设置线程名称，并且可以确认是否是后台线程等
        RejectedExecutionHandler handler    //拒绝任务处理器:由于超出线程数量和队列容量而对继续增加的任务进行处理的程序
    ) {
        //检验输入条件
        if (corePoolSize < 0 ||
                maximumPoolSize <= 0 ||
                maximumPoolSize < corePoolSize ||
                keepAliveTime < 0)
            throw new IllegalArgumentException();
        //检验运行环境
        if (workQueue == null || threadFactory == null || handler == null)
            throw new NullPointerException();
        this.acc = System.getSecurityManager() == null ?
                null :
                AccessController.getContext();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }
}
*/
