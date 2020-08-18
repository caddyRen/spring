package indi.ikun.spring.basejava.codequality.chapter09;

/**
 * 119.启动线程前stop方法是不可靠的
 */
public class Eo {
    /**
     * 此段代码中，设置了一个极端条件：
     * 所有的线程在启动前都执行stop方法，虽然它是一个已过时（Deprecated）的方法，但它的运行逻辑还是正常的，
     * 况且stop方法在此处的目的并不是停止一个线程，而是设置线程为不可启用状态。
     * 想来这应该是没有问题的，但是运行结果却出现了奇怪的现象：
     * 部分线程还是启动了，也就是在某些线程（没有规律）中的start方法正常执行了。
     * 在不符合判断规则的情况下，不可启用状态的线程还是启用了
     * 为什么？
     * 这是线程启动（start方法）的一个缺陷。
     * Thread类的stop方法会根据线程状态来判断是终结线程还是设置线程为不可运行状态，
     * 对于未启动的线程（线程状态为NEW）来说，会设置其标志位为不可启动，
     * 而其他的状态则是直接停止
     *
     * 根据stop源码，start源码分析得出
     * stop设置了一个不可启动布尔值，
     * start启动时，会先启动一个线程，然后判断上述布尔值，再stop0结束这个线程
     * 解决方案
     *不再使用stop方法进行状态的设置，
     * 直接通过判断条件来决定线程是否可启用。
     * 对于start方法的该缺陷，一般不会引起太大的问题，只是增加了线程启动和停止的精度而已
     */
    public static void main(String[] args) {
        //不分昼夜地制造垃圾邮件
        while (true){
            //多线程多个垃圾邮件制造机
            SpamMachine spamMachine = new SpamMachine();
            //条件判断，不符合条件就设置该线程不可执行
            if(!false){
                spamMachine.stop();
            }
            //如果线程是stop状态，则不会启动
            spamMachine.start();
        }
    }
}
class SpamMachine extends Thread{
    @Override
    public void run() {
        System.err.println("制造大量垃圾邮件");
    }
}

