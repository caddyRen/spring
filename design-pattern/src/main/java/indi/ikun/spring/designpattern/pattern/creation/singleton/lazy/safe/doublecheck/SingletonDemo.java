package indi.ikun.spring.designpattern.pattern.creation.singleton.lazy.safe.doublecheck;

import java.time.Instant;

public class SingletonDemo {

    public static void main(String[] args) {
        Long millisecond = Instant.now().toEpochMilli();
        for(int i=0;i<2000;i++){
            TestThread t=new TestThread();
            new Thread(t).start();
        }
        Long millisecond2 = Instant.now().toEpochMilli();
        System.err.println(millisecond2-millisecond);
    }
}

/**
 * @Description: 单例模式 懒汉式 双重检查 线程安全
 * @Author caddy
 * @date 2020-02-07 11:44:15
 * @version 1.0
 */
class Demo{

    private Demo(){}

    private static Demo instance;

    public static Demo getInstance() {
        if(instance==null){
            //同步代码块
            synchronized (Demo.class){
                if(instance==null){
                    instance=new Demo();
                }
            }
        }
        return instance;
    }
}

class TestThread implements Runnable{
    @Override
    public void run() {
        Demo.getInstance().hashCode();
//        System.err.println(Demo.getInstance().hashCode());
    }
}