package indi.ikun.spring.designpattern.pattern.behavior.command;

/**
 * 命令模式
 * 1.将发起请求的对象与执行请求的对象解耦.
 * 发起请求的对象是调用者,调用者只要调用命令对象的execute()方法就可以让接收者工作,而不必知道具体的接收者对象是谁
 * 命令对象会负责让接收者执行请求的动作,请求发起者和请求执行者之间的解耦是通过命令对象实现的,命令对象起到了纽带桥梁的作用
 * 2.容易设计一个命令队列,只要把命令对象放到队列,就可以多线程的执行命令
 * 3.容易实现对请求的撤销和重做
 * 4.可能导致某些系统有过多的具体命令类,增加系统的复杂度
 * 5.空命令也是一种设计模式,可以省去判空的操作
 * 6.经典应用场景,界面的一个按钮就是一条命令,模拟CMD(DOS命令)订单的撤销/恢复,触发-反馈机制
 */
public class Client {

    public static void main(String[] args) {
        LightReceiver lightReceiver = new LightReceiver();
        LightOffCommand lightOffCommand = new LightOffCommand(lightReceiver);
        LightOnCommand lightOnCommand = new LightOnCommand(lightReceiver);

        RemoteController remoteController = new RemoteController();

        remoteController.setCommand(0, lightOnCommand, lightOffCommand);

        // remoteController.onButtonWasPushed(0);
        remoteController.offButtonWasPushed(0);
        remoteController.undoButtonWasPushed();
        remoteController.undoButtonWasPushed();
        remoteController.undoButtonWasPushed();
        remoteController.undoButtonWasPushed();
        remoteController.undoButtonWasPushed();
        remoteController.undoButtonWasPushed();
        remoteController.undoButtonWasPushed();
        remoteController.undoButtonWasPushed();


    }
}
