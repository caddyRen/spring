package indi.ikun.spring.designpattern.pattern.behavior.state;

/**
 * 抽奖活动
 */
public class Activity {
    State state=null;

    int count=0;
    State noRaffleState=new NoRaffleState(this);
    State canRaffleState=new CanRaffleState(this);
    State dispenseState=new DispenseState(this);
    State dispenseOutState=new DispenseOutState(this);

    public Activity(int count) {
        this.state=getNoRaffleState();
        this.count = count;
    }

    public void debuctMoney(){
        state.deduceMoney();
    }
    public void raffle(){
        if (state.raffle()){
            state.dispensePrize();
        }
    }


    public State getState(){
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public State getNoRaffleState() {
        return noRaffleState;
    }

    public void setNoRaffleState(State noRaffleState) {
        this.noRaffleState = noRaffleState;
    }

    public State getCanRaffleState() {
        return canRaffleState;
    }

    public void setCanRaffleState(State canRaffleState) {
        this.canRaffleState = canRaffleState;
    }

    public State getDispenseState() {
        return dispenseState;
    }

    public void setDispenseState(State dispenseState) {
        this.dispenseState = dispenseState;
    }

    public State getDispenseOutState() {
        return dispenseOutState;
    }

    public void setDispenseOutState(State dispenseOutState) {
        this.dispenseOutState = dispenseOutState;
    }
}
