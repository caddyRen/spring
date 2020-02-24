package indi.ikun.spring.designpattern.pattern.creation.singleton.lazy.safe;

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
 * @Description: 单例模式 懒汉式 加同步锁 线程安全 效率低
 * @Author caddy
 * @date 2020-02-07 11:44:15
 * @version 1.0
 */
class Demo{

    private Demo(){}

    private static Demo instance;

    //同步方法，线程安全，但是 效率低
    public static synchronized Demo getInstance() {
        if(instance==null){
            instance=new Demo();
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