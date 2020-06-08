package indi.ikun.spring.designpattern.pattern.behavior.mediator;

import java.util.HashMap;
import java.util.Map;

public abstract class Mediator {

    //将中介的对象放到集合里
    public abstract void Register(String colleagueName, Colleague colleague);

    //接收由具体的同事对象发出的消息
    public abstract void GetMessage(int stateChange, String colleagueName);

    //自己也可以发消息
    public abstract void SendMesage();

}

//中介
class ConcreteMediator extends Mediator {

    private Map<String, Colleague> colleagueMap;
    private Map<String, String> interMap;

    public ConcreteMediator() {
        colleagueMap = new HashMap<>();
        interMap = new HashMap<>();
    }

    @Override
    public void Register(String colleagueName, Colleague colleague) {
        colleagueMap.put(colleagueName, colleague);
        if(colleague instanceof Alarm){
            interMap.put("Alarm",colleagueName);
        }else if(colleague instanceof TV){
            interMap.put("TV",colleagueName);
        }else if(colleague instanceof CoffeeMachine){
            interMap.put("CoffeeMachine",colleagueName);
        }else if(colleague instanceof Curtains){
            interMap.put("Curtains",colleagueName);
        }

    }

    /**
     * 中介核心方法
     * 根据得到的消息,完成任务
     * 协调各个同事对象,完成任务
     * @param stateChange
     * @param colleagueName
     */
    @Override
    public void GetMessage(int stateChange, String colleagueName) {
        if (colleagueMap.get(colleagueName) instanceof TV) {
            if(stateChange==0){
                ((Curtains)colleagueMap.get(interMap.get("Curtains"))).stopCurtains();
                ((CoffeeMachine)colleagueMap.get(interMap.get("CoffeeMachine"))).startCoffeeMachine();
                ((TV)colleagueMap.get(interMap.get("TV"))).startTV();
            }else if(stateChange==1){
                ((TV)colleagueMap.get(interMap.get("TV"))).stopTV();
                ((CoffeeMachine)colleagueMap.get(interMap.get("CoffeeMachine"))).stopCoffeeMachine();
                ((Curtains)colleagueMap.get(interMap.get("Curtains"))).startCurtains();
            }
        }else if (colleagueMap.get(colleagueName) instanceof Alarm) {
            if(stateChange==0){
                ((Curtains)colleagueMap.get(interMap.get("Curtains"))).stopCurtains();
                ((CoffeeMachine)colleagueMap.get(interMap.get("CoffeeMachine"))).stopCoffeeMachine();
            }else if(stateChange==1){
                ((Curtains)colleagueMap.get(interMap.get("Curtains"))).startCurtains();
                ((CoffeeMachine)colleagueMap.get(interMap.get("CoffeeMachine"))).startCoffeeMachine();
            }
        }else if (colleagueMap.get(colleagueName) instanceof CoffeeMachine) {
            if(stateChange==0){
                ((CoffeeMachine)colleagueMap.get(interMap.get("CoffeeMachine"))).stopCoffeeMachine();
            }else if(stateChange==1){
                ((CoffeeMachine)colleagueMap.get(interMap.get("CoffeeMachine"))).startCoffeeMachine();
            }
        }

    }

    /**
     * 自己也可以发消息
     */
    @Override
    public void SendMesage() {

    }
}