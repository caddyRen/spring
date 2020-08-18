package indi.ikun.spring.basejava.codequality.chapter09;

/**
 * 122.使用线程异常处理器提升系统可靠性
 * Java 1.5版本以后在Thread类中增加了setUncaughtExceptionHandler方法，实现了线程异常的捕捉和处理
 * 在实际环境中应用，则需要注意以下三个方面：
 * 1)共享资源锁定
 *      如果线程异常产生的原因是资源被锁定，自动重启应用只会增加系统的负担，无法提供不间断服务。
 *      例如一个即时通信服务器（XMPP Server）出现信息不能写入的情况时，
 *      即使再怎么重启服务，也是无法解决问题的。
 *      在此情况下最好的办法是停止所有的线程，释放资源
 * 2)脏数据引起系统逻辑混乱
 *      异常的产生中断了正在执行的业务逻辑，
 *      特别是如果正在执行一个原子操作
 *         （像即时通信服务器的用户验证和签到这两个事件应该在一个操作中处理，
 *         不允许出现验证成功但签到不成功的情况），
 *      但如果此时抛出了运行期异常就有可能会破坏正常的业务逻辑，
 *      例如出现用户认证通过了，但签到不成功的情况，
 *      在这种情景下重启应用服务器，虽然可以提供服务，但对部分用户则产生了逻辑异常
 * 3)内存溢出
 *      线程异常了，但由该线程创建的对象并不会马上回收，
 *      如果再重新启动新线程，再创建一批新对象，特别是加入了场景接管，
 *      就非常危险了，
 *      例如即时通信服务，
 *      重新启动一个新线程必须保证原在线用户的透明性，即用户不会察觉服务重启，
 *      在此种情况下，就需要在线程初始化时加载大量对象以保证用户的状态信息，
 *      但是如果线程反复重启，很可能会引起OutOfMemory内存泄露问题
 */
public class Er {
    public static void main(String[] args) {
        TcpServer tcpServer = new TcpServer();
    }

}

class TcpServer implements Runnable{
    //创建后即运行
    public TcpServer() {
        Thread t=new Thread(this);
        t.setUncaughtExceptionHandler(new TcpServerExceptionHandler());
        t.start();
    }

    @Override
    public void run() {
        //正常业务运行3秒
        for(int i=0;i<3;i++){
            try{
                Thread.sleep(1000);
                System.err.println("系统正常运行"+i);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        //抛出异常
        throw new RuntimeException();
    }
    //异常处理器
    private static class TcpServerExceptionHandler implements Thread.UncaughtExceptionHandler{
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            //记录线程异常信息
            System.err.println("线程 "+t.getName()+"出现异常，自动重启，请分析原因");
            e.printStackTrace();
            //重启线程，保证业务不中断
            new TcpServer();
        }
    }
}
