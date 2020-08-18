package indi.ikun.spring.basejava.codequality.chapter05;

import java.util.*;

/**
 * 80.多线程使用Vector或HashTable
 *
 * Vector是ArrayList的多线程版本
 * HashTable是HashMap的多线程版本
 *
 * 线程安全
 * 同步修改异常
 *
 * 基本上所有的集合类都有一个叫做快速失败（Fail-Fast）的校验机制，
 * 当一个集合在被多个线程修改并访问时，就可能会出现ConcurrentModificationException异常，
 * 这是为了确保集合方法一致而设置的保护措施，
 * 它的实现原理就是我们经常提到的modCount修改计数器：
 * 如果在读列表时，modCount发生变化（也就是有其他线程修改）则会抛出ConcurrentModificationException异常。
 * 这与线程同步是两码事，线程同步是为了保护集合中的数据不被脏读、脏写而设置的
 */
public class Db {
    /**
     * Exception in thread "Thread-1" java.util.ConcurrentModificationException
     */
    public static void main(String[] args) {
        final List<String> tickets=new ArrayList<>();
        //初始化票据
        for(int i=0;i<100000;i++){
            tickets.add("火车票"+i);
        }
        //退票
        Thread returnThread=new Thread(()->{
                while(true){
                    tickets.add("车票"+ new Random().nextInt());
                }
            }
        );
        //售票
        Thread saleThread= new Thread(() -> {
            for(String tiket:tickets){
                tickets.remove(tiket);
            }
        });
        returnThread.start();
        saleThread.start();
    }
}

/**
 * 换成Vector还是出现同步修改异常
 */
class TestDb {
    /**
     * Exception in thread "Thread-1" java.util.ConcurrentModificationException
     */
    public static void main(String[] args) {
        final Vector<String> tickets=new Vector<>();
        for(int i=0;i<100000;i++){
            tickets.add("火车票"+i);
        }
        Thread returnThread=new Thread(()->{
            while(true){
                tickets.add("车票"+ new Random().nextInt());
            }
        }
        );

        Thread saleThread= new Thread(() -> {
            for(String tiket:tickets){
                tickets.remove(tiket);
            }
        });
        returnThread.start();
        saleThread.start();
    }
}

/**
 * 线程安全
 * 两个线程在卖同一张火车票，这才是线程不同步的问题，
 * Vector的每个方法前都加上了synchronized关键字，同时只会允许一个线程进入该方法，确保了程序的可靠性
 */
class TestDb1 {
    /**
     * Exception in thread "Thread-1" java.util.ConcurrentModificationException
     */
    public static void main(String[] args) {
        final Vector<String> tickets=new Vector<>();
        for(int i=0;i<2;i++){
            tickets.add("火车票"+i);
        }
        //十个售票窗口
        for(int i=0;i<3;i++){
            new Thread(() -> {
                while(true){
                    if(tickets.size()>0){
                        System.err.println(Thread.currentThread().getId()+" "+tickets.remove(0));
                    }else break;
                }
            }).start();
        }
    }
}
/**
 * 线程不安全
 * 两个线程在卖同一张火车票，这才是线程不同步的问题，
 * 此时把ArrayList修改为Vector即可解决问题，
 * 因为Vector的每个方法前都加上了synchronized关键字，
 * 同时只会允许一个线程进入该方法，确保了程序的可靠性
 */
class TestDb2 {
    /**
     * Exception in thread "Thread-1" java.util.ConcurrentModificationException
     */
    public static void main(String[] args) {
        final ArrayList<String> tickets=new ArrayList<>();
        for(int i=0;i<2;i++){
            tickets.add("火车票"+i);
        }
        //十个售票窗口
        for(int i=0;i<3;i++){
            new Thread(() -> {
                while(true){
                    if(tickets.size()>0){
                        System.err.println(Thread.currentThread().getId()+" "+tickets.remove(0));
                    }else break;
                }
            }).start();
        }
    }
}