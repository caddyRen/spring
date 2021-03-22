package indi.ikun.spring.commons.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 生产者消费者模式
 * 阻塞队列
 */
public class ConsumerAndProducerTest {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        threadPool.submit(new Producer());
        threadPool.submit(new Producer());
        threadPool.submit(new Producer());
//        threadPool.submit(new Producer());
//        threadPool.submit(new Producer());
//        threadPool.submit(new Producer());
//        threadPool.submit(new Consumer());
//        threadPool.submit(new Consumer());
        threadPool.shutdown();
    }

}

class MyBlockingQueue{

    //当队列满的时候会造成死锁,并不会显示要求抛出异常，可查看源码
    /**
     *
     * @throws IllegalStateException if this deque is full
     * @throws NullPointerException  {@inheritDoc}
    public void addLast(E e) {
    if (!offerLast(e))
    throw new IllegalStateException("Deque full");
    }
    // BlockingQueue methods
     * Inserts the specified element at the end of this deque unless it would
     * violate capacity restrictions.  When using a capacity-restricted deque,
     * it is generally preferable to use method {@link #offer(Object) offer}.
     *
     * <p>This method is equivalent to {@link #addLast}.
     *
     * @throws IllegalStateException if this deque is full
     * @throws NullPointerException if the specified element is null

    public boolean add(E e) {
        addLast(e);
        return true;
    }
     */
//    public static final BlockingQueue<String> QUEUE=new LinkedBlockingDeque<>(0);
    public static final BlockingQueue<String> QUEUE=new LinkedBlockingDeque<>(10);
//    public static final BlockingQueue<String> QUEUE=new LinkedBlockingDeque<>(100);
}


class Producer implements Runnable{

    public static void add(String str){
        try {
            Thread.sleep(100);
            System.out.println("add "+str);

            MyBlockingQueue.QUEUE.add(str);
        } catch (IllegalStateException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (int i=0;i<10;i++){
            add("hi"+i);
        }
    }
}

class Consumer implements Runnable {

    public static String take(){
        String take ="null";
        try{
            Thread.sleep(100);
            //阻塞队列
            take=MyBlockingQueue.QUEUE.take();
            System.err.println(take);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        return take;
    }


    @Override
    public void run() {
        while (true){take();}
    }
}
