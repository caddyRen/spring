package indi.ikun.spring.designpattern.pattern.behavior.command;

/**
 * 命令,需要执行的命令都在这里,可以是接口或者抽象类
 */
public interface Command {
    //执行
    public void execute();

    //撤销
    public void undo();
}

/**
 * 将Receiver和一个动作绑定,调用相应的操作,实现execute
 */
class ConcreateCommand implements Command {
    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}

/**
 * 命令接收者,知道如何实施和执行一个请求相关的操作
 */
class Receiver {


}

/**
 * 调用者角色
 */
class Invoker {


}

/**
 * 电灯接受命令
 */
class LightReceiver {

    public void on() {
        System.err.println("电灯打开");
    }

    public void off() {
        System.err.println("电灯关闭");
    }


}

/**
 * 电灯开
 */
class LightOnCommand implements Command {

    private LightReceiver lightReceiver;

    public LightOnCommand(LightReceiver lightReceiver) {
        this.lightReceiver = lightReceiver;
    }

    //聚合LightReceiver
    @Override
    public void execute() {
        lightReceiver.on();
    }

    @Override
    public void undo() {
        lightReceiver.off();
    }
}

/**
 * 电灯关
 */
class LightOffCommand implements Command {

    private LightReceiver lightReceiver;

    public LightOffCommand(LightReceiver lightReceiver) {
        this.lightReceiver = lightReceiver;
    }


    @Override
    public void execute() {
        lightReceiver.off();
    }

    @Override
    public void undo() {
        lightReceiver.on();
    }
}


/**
 * 空命令,可省去对空的判断
 * 没有任何命令,空执行,可以初始化每个按钮
 * 当调用空命令时,对象什么都不做
 */
class NoCommand implements Command {

    @Override
    public void execute() {
        System.err.println("空命令");
    }

    @Override
    public void undo() {
        System.err.println("空命令");
    }
}

/**
 * 遥控器
 * 聚合很多Command
 */
class RemoteController {

    //开
    Command[] onCommands;
    //关
    Command[] offCommands;

    //撤销
    Command undoCommand;

    public RemoteController() {
        onCommands = new Command[5];
        offCommands = new Command[5];

        for (int i = 0; i < 5; i++) {
            onCommands[i] = new NoCommand();
            offCommands[i] = new NoCommand();
        }
    }

    public void setCommand(int no, Command onCommand, Command offCommand) {
        onCommands[no] = onCommand;
        offCommands[no] = offCommand;
    }

    public void onButtonWasPushed(int no) {
        onCommands[no].execute();
        undoCommand=onCommands[no];
    }

    public void offButtonWasPushed(int no) {
        offCommands[no].execute();
        undoCommand=offCommands[no];
    }

    public void undoButtonWasPushed() {
        undoCommand.undo();
    }

}

