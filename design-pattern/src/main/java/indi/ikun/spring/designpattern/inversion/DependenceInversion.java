package indi.ikun.spring.designpattern.inversion;
/**
 * @Description: 依赖倒转原则
 * @Author caddy
 * @date 2020-02-04 16:03:43
 * @version 1.0
 */
public class DependenceInversion {
    public static void main(String[] args) {
        Person p=new Person();
        p.receive(new Email());
    }
}

class Email{
    public String getInfo(){
        return "Hello world";
    }
}

/**
 * @Description: 完成Person接收消息的功能
 * @Author caddy
 * @date 2020-02-04 16:06:11
 * @version 1.0
 * 1.简单，
 * 2.如果我们获取的对象是微信，短信等，则应新增类，同时Person也要增加相应的接收方法
 * 3.解决思路，引入抽象接口，IReceiver表示接收者，Person类与IReceiver接口发生依赖
 *      因为Email，WeiXin属于接收的范围，他们各自实现的IReceiver接口，这样就可以符合依赖倒转原则
 */
class Person{
    public void receive(Email email){
        System.err.println(email.getInfo());

    }
}
