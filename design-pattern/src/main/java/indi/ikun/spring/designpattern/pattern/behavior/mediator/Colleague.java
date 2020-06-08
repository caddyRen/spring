package indi.ikun.spring.designpattern.pattern.behavior.mediator;

/**
 * 同事类
 */
public abstract class Colleague {
     private Mediator mediator;
     public String name;

    public Colleague(Mediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public Mediator getMediator() {
        return mediator;
    }
    public abstract void SendMessage(int stateChange);
}


/**
 * 具体同事类
 */
class Alarm extends Colleague{

    public Alarm(Mediator mediator, String name) {
        super(mediator, name);
        //创建Alarm对象时,将自己放入到ConcreteMediator对象中[集合]
        mediator.Register(name,this);
    }

    public void SendAlarm(int stateChange){
        SendMessage(stateChange);
    }

    /**
     * 调用中介对象的getMessage
     * @param stateChange
     */
    @Override
    public void SendMessage(int stateChange) {
        this.getMediator().GetMessage(stateChange,name);

    }
}
/**
 * 具体同事类
 */
class TV extends Colleague{

    public TV(Mediator mediator, String name) {
        super(mediator, name);
        //创建Alarm对象时,将自己放入到ConcreteMediator对象中[集合]
        mediator.Register(name,this);
    }

    public void startTV(){
        System.err.println("start TV");
    }
    public void stopTV(){
        System.err.println("stop TV");
    }

    /**
     * 调用中介对象的getMessage
     * @param stateChange
     */
    @Override
    public void SendMessage(int stateChange) {
        getMediator().GetMessage(stateChange,name);

    }
}


/**
 * 具体同事类
 */
class Curtains extends Colleague{

    public Curtains(Mediator mediator, String name) {
        super(mediator, name);
        //创建Alarm对象时,将自己放入到ConcreteMediator对象中[集合]
        mediator.Register(name,this);
    }

    public void startCurtains(){
        System.err.println("start Curtains 开窗");
    }
    public void stopCurtains(){
        System.err.println("stop Curtains 关窗");
    }

    /**
     * 调用中介对象的getMessage
     * @param stateChange
     */
    @Override
    public void SendMessage(int stateChange) {
        getMediator().GetMessage(stateChange,name);

    }
}

/**
 * 具体同事类
 */
class CoffeeMachine extends Colleague{

    public CoffeeMachine(Mediator mediator, String name) {
        super(mediator, name);
        //创建Alarm对象时,将自己放入到ConcreteMediator对象中[集合]
        mediator.Register(name,this);
    }

    public void startCoffeeMachine(){
        System.err.println("start CoffeeMachine");
    }
    public void stopCoffeeMachine(){
        System.err.println("stop CoffeeMachine");
    }

    /**
     * 调用中介对象的getMessage
     * @param stateChange
     */
    @Override
    public void SendMessage(int stateChange) {
        getMediator().GetMessage(stateChange,name);

    }
}
