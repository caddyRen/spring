package indi.ikun.spring.designpattern.pattern.creation.singleton.staticinnerclass;

public class SingletonDemo {

    public static void main(String[] args) {

        for(int i=0;i<10;i++) {
            TestThread t = new TestThread();
            new Thread(t).start();
        }
    }
}

/**
 * @Description: 单例模式 静态内部类  线程安全，由jvm实现
 * @Author caddy
 * @date 2020-02-07 11:44:15
 * @version 1.0
 */
class Demo{

    private Demo(){}

    private static class DemoInstance{
        private static final Demo INSTANCE=new Demo();
    }
    public static Demo getInstance(){
        return DemoInstance.INSTANCE;
    }
}

class TestThread implements Runnable{
    @Override
    public void run() {
        System.err.println(Demo.getInstance().hashCode());
    }
}