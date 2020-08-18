package indi.ikun.spring.basejava.codequality.chapter03;

/**
 * 33.构造函数尽量简化
 *
 * 通过new关键字生成对象时必然会调用构造函数，
 * 构造函数的简繁情况会直接影响实例对象的创建是否繁琐。
 * 在项目开发中，我们一般都会制订构造函数尽量简单，尽可能不抛异常，尽量不做复杂算法等规范
 *
 * 构造函数简化，再简化，应该达到“一眼洞穿”的境界
 */
public class Bh {

    public static void main(String[] args) {
        /**
         * 代码意图
         * 1.通过SimpleServerBh的构造函数接收端口参数
         * 2.子类的构造函数默认调用父类的构造函数。
         *
         * 3.父类构造函数调用子类的getPort方法获得端口号
         *
         * 4.父类构造函数建立端口监听机制
         * 5.对象创建完毕，服务监听启动，正常运行
         *
         *  分析子类实例化过程：
         *  子类实例化时，会首先初始化父类（注意这里是初始化，可不是生成父类对象），
         *  也就是初始化父类的变量，调用父类的构造函数，
         *  然后才会初始化子类的变量，调用子类自己的构造函数，最后生成一个实例对象
         *
         *  代码执行过程：
         *  子类SimpleServerBh的构造函数接收int类型的参数：1000
         *  父类初始化常变量，也就是DEFAULT_PORT初始化，并设置为40000
         *  执行父类无参构造函数，也就是子类的有参构造中默认包含了super()方法
         *  父类无参构造函数执行到“int port=getPort()”方法，调用子类的getPort方法实现
         *  子类的getPort方法返回port值（注意，此时port变量还没有赋值，是0）或DEFAULT_PORT（此时已经是40000）了
         *  父类初始化完毕，开始初始化子类的实例变量，port赋值100
         *  执行子类构造函数，port被重新赋值为1000
         *  子类SimpleServerBh实例化结束，对象创建完毕
         */
        ServerBh serverBh = new SimpleServerBh(1000);
        ServerBhNew serverBhNew = new SimpleServerBhNew(1000);
        serverBhNew.start();
    }
}

abstract class ServerBh {
    public final static int DEFAULT_PORT = 40000;

    public ServerBh() {
        int port = getPort();
        System.err.println("端口号：" + port);//40000或者0永远不会出现100或者1000
    }

    protected abstract int getPort();
}



class SimpleServerBh extends ServerBh {
    private int port = 100;

    public SimpleServerBh(int _port) {
        port = _port;
    }
    @Override
    protected int getPort() {
        return Math.random() > 0.5 ? port : DEFAULT_PORT;
    }
}
abstract class ServerBhNew {
    public final static int DEFAULT_PORT = 40000;
    protected abstract int getPort();
    protected void start(){
        int port = getPort();
        System.err.println("端口号：" + port);//40000或者0永远不会出现100或者1000
    }
}
class SimpleServerBhNew extends ServerBhNew {
    private int port = 100;

    public SimpleServerBhNew(int _port) {
        port = _port;
    }
    @Override
    protected int getPort() {
        return Math.random() > 0.5 ? port : DEFAULT_PORT;
    }
}
