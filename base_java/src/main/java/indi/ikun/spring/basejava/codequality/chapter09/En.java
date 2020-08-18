package indi.ikun.spring.basejava.codequality.chapter09;

/**
 * 118.不推荐覆写start方法
 *
 * 继承自Thread类的多线程类不必覆写start方法
 *
 * 多线程比较简单的实现方式是继承Thread类，
 * 然后覆写run方法，
 * 在客户端程序中通过调用对象的start方法即可启动一个线程，这是多线程程序的标准写法
 *
 * 覆写start的场景，都可以用其他的方式来实现，例如类变量、事件机制、监听等方式
 *
 */
public class En {
    public static void main(String[] args) {
        MultiThreadEn multiThreadEn = new MultiThreadEn();
        multiThreadEn.start();
    }
}

class MultiThreadEn extends Thread{

    @Override
    public void run() {
        System.err.println("run...");
    }

    /**
     * 非要覆写start方法，
     * 要在start方法中加上super.start
     */
    @Override
    public void start() {
        /*线程启动前的业务处理*/
        System.err.println("线程启动前的业务处理");
        super.start();
        System.err.println("线程启动后的业务处理");
        /*线程启动后的业务处理*/
    }
}
