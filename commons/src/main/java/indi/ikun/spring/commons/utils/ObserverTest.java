package indi.ikun.spring.commons.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 观察者模式，作为消息中间件
 * -XX:+PrintGCDetails -Xloggc:/home/caddy/gc.log
 */
public class ObserverTest {

    public static void main(String[] args) {
        int t=13;
        t1(t);//sync锁
//        t2(t);//单线程，串行
    }
    public static void t2(int t){
        MsgSendObserver msgSendObserver = new MsgSendObserver();
        MSG msg = new MSG();
        msg.registerObserver(msgSendObserver);
        for (int i = 0; i < t; i++) {
            msg.setData("thread-"+ i);
        }
    }
    public static void t1(int t){
        MsgSendObserver msgSendObserver = new MsgSendObserver();
        MSG msg = new MSG();
        msg.registerObserver(msgSendObserver);
        //同步锁机制，jvisualvm显示线程大部分时间是在等待获取锁，效率不高
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int i = 0; i < t; i++) {
            int finalI = i;
            //模拟并发访问，竞争锁，并发耗时
            executorService.submit(() -> msg.setData("thread-"+ finalI));
        }
//        会等待线程任务执行完才关闭线程池
        executorService.shutdown();
    }


}
//观察者
interface Observer {
    void update(String data);
}
//管理观察者和具体消费者
interface Subject {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObserver();
}
class MsgSendObserver implements Observer{

    @Override
    public void update(String data) {
        System.out.println("send msg "+data);
    }
}
/**
 * 消息
 */
class MSG implements Subject{

    private String data;

    List<Observer> observers;

    public MSG() {
        observers = new ArrayList<>();
    }

    public synchronized void setData(String data) {
        this.data = data+" "+ this.hashCode()+" "+System.currentTimeMillis();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.notifyObserver();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
        }
    }

    @Override
    public void  notifyObserver() {
        for (Observer observer : observers
        ) {
            observer.update(data);
        }
    }
}
