package indi.ikun.spring.designpattern.pattern.behavior.command;

/**
 * 命令模式
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
