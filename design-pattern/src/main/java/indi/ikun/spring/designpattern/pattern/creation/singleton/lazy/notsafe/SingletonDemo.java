package indi.ikun.spring.designpattern.pattern.creation.singleton.lazy.notsafe;

public class SingletonDemo {

    public static void main(String[] args) {
        for(int i=0;i<20;i++){
            TestThread t=new TestThread();
            new Thread(t).start();
        }
    }
}

/**
 * @Description: 单例模式 懒汉式 线程不安全
 * @Author caddy
 * @date 2020-02-07 11:44:15
 * @version 1.0
 */
class Demo{

    private Demo(){}

    private static Demo instance;

    public static Demo getInstance() {
        if(instance==null){
            instance=new Demo();
        }
        return instance;
    }
}

class TestThread implements Runnable{
    @Override
    public void run() {
        System.err.println(Demo.getInstance().hashCode());
    }
}