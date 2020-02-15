package indi.ikun.spring.designpattern.inversion;
/**
 * @Description: 依赖倒转原则
 * @Author caddy
 * @date 2020-02-04 16:03:43
 * @version 1.0
 */
public class DependenceInversion2 {
    public static void main(String[] args) {
        //客户端无需改变
        Person2 p2=new Person2();
        p2.receive(new Email2());
        p2.receive(new WX());
        p2.receive(new Message());
    }
}
/**
 * @Description: 定义抽象接口
 * @Author caddy
 * @date 2020-02-04 16:15:44
 * @version 1.0
 */
interface IReceiver{
    String getInfo();
}


/**
 * @Description: 具体细节
 * @Author caddy
 * @date 2020-02-04 16:15:58
 * @version 1.0
 */
class Email2 implements IReceiver{
    @Override
    public String getInfo(){
        return "Hello Email";
    }
}
class WX implements IReceiver{
    @Override
    public String getInfo(){
        return "Hello WX";
    }
}
class Message implements IReceiver{
    @Override
    public String getInfo(){
        return "Hello Message";
    }
}


/**
 * @Description: 完成Person接收消息的功能
 * @Author caddy
 * @date 2020-02-04 16:06:11
 * @version 1.0
 */
class Person2{
    /**
     * @Description: 对接口依赖 稳定性好
     * @Author caddy
     * @date 2020-02-04 16:16:38
     * @version 1.0
     */
    public void receive(IReceiver iReceiver){
        System.err.println(iReceiver.getInfo());

    }
}
