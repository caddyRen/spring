package indi.ikun.spring.designpattern.pattern.creation.singleton.lazy.notsafe;

public class SingletonDemo1 {

    public static void main(String[] args) {
        for(int i=0;i<20;i++){
            TestThread1 t=new TestThread1();
            new Thread(t).start();
        }
    }
}

/**
 * @Description: 单例模式 懒汉式  同步代码块 线程不安全
 * @Author caddy
 * @date 2020-02-07 11:44:15
 * @version 1.0
 */
class Demo1{

    private Demo1(){}

    private static Demo1 instance;

    public static Demo1 getInstance() {
        if(instance==null){
            //同步代码块 线程不安全
            synchronized (Demo1.class){
                instance=new Demo1();
            }
        }
        return instance;
    }
}

class TestThread1 implements Runnable{
    @Override
    public void run() {
        System.err.println(Demo1.getInstance().hashCode());
    }
}