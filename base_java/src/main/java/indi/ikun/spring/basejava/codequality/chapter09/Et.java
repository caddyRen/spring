package indi.ikun.spring.basejava.codequality.chapter09;

import java.util.concurrent.*;

/**
 * 124.异步运算考虑使用Callable接口
 * 多线程应用有两种实现方式，
 * 一种是实现Runnable接口，
 * 另一种是继承Thread类，
 * 这两个方式都有缺点：
 *      run方法没有返回值，不能抛出异常（这两个缺点归根到底是Runable接口的缺陷，Thread也是实现了Runnable接口），
 *      如果需要知道一个线程的运行结果就需要用户自行设计，线程类自身也不能提供返回值和异常。
 *      但是从Java 1.5开始引入了一个新的接口Callable，
 *      它类似于Runable接口，实现它就可以实现多线程任务
 */
public class Et {
    /**
     * Executors是一个静态工具类，提供了异步执行器的创建能力，
     *      如单线程执行器newSingleThreadExecutor、
     *      固定线程数量的执行器newFixedThreadPool等，
     * 一般它是异步计算的入口类。
     * Future关注的是线程执行后的结果，
     *      比如有没有运行完毕，执行结果是多少等
     *
     * 好处:
     *  1)尽可能多地占用系统资源，提供快速运算
     *  2)可以监控线程执行的情况，比如是否执行完毕、是否有返回值、是否有异常等
     *  3)可以为用户提供更好的支持，比如例子中的运算进度等
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //生成一个单线程异步执行器
        System.err.println("开始");
        ExecutorService es= Executors.newSingleThreadExecutor();
        Future<Integer> future=es.submit(new TaxCalculatorEt(100));
        while (!future.isDone()){
            Thread.sleep(200);
//            TimeUnit.MILLISECONDS.sleep(200);//此处sleep有问题，感觉像是唤醒了TaxCalculatorEt线程
            System.err.print("#");
        }
        System.err.println("\n计算完成，税金是："+future.get()+"元");
        es.shutdown();
    }
}

/**
 *实现Callable接口的类，只是表明它是一个可调用的任务，并不表示它具有多线程运算能力，还是需要执行器来执行
 *
 * 税款计算器，
 *  该运算可能要花费10秒钟的时间，
 *  此时不能让用户一直等着吧，需要给用户输出点什么，
 *  让用户知道系统还在运行，这也是系统友好性的体现：
 *      用户输入即有输出，
 *          若耗时较长，则显示运算进度。
 *  如果我们直接计算，就只有一个main线程，是不可能有友好提示的，
 *  如果税金不计算完毕，也不会执行后续动作，
 *  所以此时最好的办法就是重启一个线程来运算，让main线程做进度提示
 */
class TaxCalculatorEt implements Callable<Integer> {
    //本金
    private int seedMoney;
    //接收主线程提供参数
    public TaxCalculatorEt(int seedMoney) {
        this.seedMoney = seedMoney;
    }

    @Override
    public Integer call() throws Exception {
        //复杂计算，运行一次需要10秒
//        TimeUnit.MICROSECONDS.sleep(10000);//此处sleep有问题，并未休眠10秒

        Thread.sleep(10000);
        return seedMoney/10;
    }
}