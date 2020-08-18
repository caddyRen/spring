package indi.ikun.spring.basejava.codequality.chapter09;


/**
 * 120.不使用stop方法停止线程
 *
 * 线程启动完毕后，在运行时可能需要终止，
 * Java提供的终止方法只有一个stop，
 * 但是不建议使用这个方法，因为它有以下三个问题
 * 1）stop方法是过时的
 *      从Java编码规则来说，已经过时的方法不建议采用
 * 2）stop方法会导致代码逻辑不完整
 *      stop方法是一种“恶意”的中断，一旦执行stop方法，
 *      即终止当前正在运行的线程，不管线程逻辑是否完整，这是非常危险的
 * 3）stop方法会破坏原子逻辑
 *
 * 如果期望终止一个正在运行的线程，
 *      则不能使用已经过时的stop方法，需要自行编码实现，
 *      如此即可保证原子逻辑不被破坏，代码逻辑不会出现异常。
 * 如果我们使用的是线程池（比如ThreadPoolExecutor类），
 *      那么可以通过shutdown方法逐步关闭池中的线程，它采用的是比较温和、安全的关闭线程方法，完全不会产生类似stop方法的弊端
 */
public class Ep {
    /**
     * 2）stop方法会导致代码逻辑不完整
     *
     * 子线程是一个lambda匿名内部类，
     * 它的run方法在执行时会休眠1秒钟，
     * 然后再执行后续的逻辑，
     * 而主线程则是休眠0.1秒后终止子线程的运行，
     * 也就是说，JVM在执行thread.stop()时，子线程还在执行sleep(1000)，
     * 此时stop方法会清除栈内信息，结束该线程，这也就导致了run方法的逻辑不完整，
     * 输出语句println代表的是一段逻辑，可能非常重要，比如子线程的主逻辑、资源回收、情景初始化等，
     * 但是因为stop线程了，这些就都不再执行，
     * 于是就产生了业务逻辑不完整的情况。这是极度危险的，因为我们不知道子线程会在什么时候被终止，
     * stop连基本的逻辑完整性都无法保证。
     * 而且此种操作也是非常隐蔽的，
     * 子线程执行到何处会被关闭很难定位，这为以后的维护带来了很多麻烦
     */
    public static void main(String[] args) {
        Thread thread= new Thread(() -> {
            try{
                //子线程休眠1秒
                Thread.sleep(1000);
            }catch (Exception e){

            }
            //此处如果是子线程的主逻辑、资源回收、情景初始化等，不会被执行
            System.err.println("此处不会执行");
        });
        thread.start();
        try {
            //主线程休眠0.1秒
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //子线程停止
        thread.stop();
    }

}

/**
 * stop方法会破坏原子逻辑
 *
 * 1）线程t1启动，并执行run方法，由于没有其他线程持同步代码块的锁，所以t1线程执行自加后执行到sleep方法即开始休眠，
 *      此时a=1。
 * 2）JVM又启动了5个线程，也同时运行run方法，由于synchronized关键字的阻塞作用，这5个线程不能执行自增和自减操作，
 *      等待t1线程锁释放。
 * 3）主线程执行了t1.stop方法，终止了t1线程，
 *      注意，由于a变量是所有线程共享的，所以其他5个线程获得的a变量也是1。
 * 4）其他5个线程依次获得CPU执行机会，打印出a值
 *
 * 原本期望synchronized同步代码块中的逻辑都是原子逻辑，
 * 不受外界线程的干扰，
 * 但是结果却出现原子逻辑被破坏的情况，
 * 这也是stop方法被废弃的一个重要原因：破坏了原子逻辑
 */
class MutilThreadEp implements Runnable{
    int a=0;
    @Override
    public void run() {
        synchronized (""){
            a++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a--;
            String tn=Thread.currentThread().getName();
            System.err.println(tn+":a="+a);
        }
    }
}
class Ep1{
    public static void main(String[] args) {
        MutilThreadEp t=new MutilThreadEp();
        Thread t1=new Thread(t);
        t1.start();
        for (int i=0;i<5;i++){
            new Thread(t).start();
        }
        t1.stop();
    }
}

/**
 *
 */
class SafeStopThread extends Thread {
    int a=0;
    @Override
    public void run() {
        synchronized (""){
            while(!isInterrupted()){
                String tn=Thread.currentThread().getName();
                System.err.println(tn+":a="+a);
                if(isInterrupted())
                    break;
            }
        }

    }

    /**
     * interrupt方法不能终止一个线程状态，
     * 它只会改变中断标志位
     *      （如果在t1.interrupt()前后输出t1.isInterrupted()则会发现分别输出了false和true），
     * 如果需要终止该线程，还需要自行进行判断
     */
    public static void main(String[] args) {
        SafeStopThread t1=new SafeStopThread();
        t1.start();
        System.err.println(t1.isInterrupted());
        t1.interrupt();
        System.err.println(t1.isInterrupted());
    }

}

