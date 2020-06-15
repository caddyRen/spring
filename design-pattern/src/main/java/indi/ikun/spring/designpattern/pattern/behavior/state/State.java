package indi.ikun.spring.designpattern.pattern.behavior.state;

import java.util.Random;

/**
 * 抽象状态
 */
public interface State {
    //扣除50积分
    void deduceMoney();

    //是否抽中奖品
    boolean raffle();

    //发放奖品
    void dispensePrize();
}

/**
 * 不能抽奖状态,可以扣积分
 */
class NoRaffleState implements State {

    Activity activity;

    public NoRaffleState(Activity activity) {
        this.activity = activity;
    }

    /**
     * 扣除积分后将状态设置为可以抽奖状态
     */
    @Override
    public void deduceMoney() {
        System.err.println("成功扣50积分,可以抽奖");
        activity.setState(activity.getCanRaffleState());
    }

    /**
     * 未扣积分,不能抽奖
     */
    @Override
    public boolean raffle() {
        System.err.println("扣了积分才可以抽奖");
        return false;

    }

    /**
     * 未抽奖不能发奖品
     */
    @Override
    public void dispensePrize() {
        System.err.println("未抽奖,不能发放奖品");

    }
}

/**
 * 中奖状态
 */
class CanRaffleState implements State {
    Activity activity;

    public CanRaffleState(Activity activity) {
        this.activity = activity;
    }

    /**
     * 扣过积分,可以进行抽奖
     */
    @Override
    public void deduceMoney() {
        System.err.println("已经扣取过积分");
    }

    /**
     * 可以抽奖
     *
     * @return
     */
    @Override
    public boolean raffle() {
        System.err.println("正在抽奖...");
        Random r = new Random();
        int num = r.nextInt(10);
        if (num == 0) {
            //抽中设置为可以发放奖品状态
            activity.setState(activity.getDispenseState());
            return true;
        } else {
            //未抽中设置为 未扣积分状态
            System.err.println("未中奖");
            activity.setState(activity.getNoRaffleState());
            return false;
        }
    }

    /**
     * 抽奖状态,还不能领奖品
     */
    @Override
    public void dispensePrize() {
        System.err.println("正在抽奖,还不能领奖品");

    }
}

/**
 * 发放奖品状态
 */
class DispenseState implements State {
    Activity activity;

    public DispenseState(Activity activity) {
        this.activity = activity;
    }

    /**
     *
     */
    @Override
    public void deduceMoney() {
        System.err.println("正在发奖品,不能扣积分");
    }

    @Override
    public boolean raffle() {
        System.err.println("正在发奖品,不能抽奖");
        return false;

    }

    @Override
    public void dispensePrize() {
        if (activity.getCount() > 0) {
            activity.setCount(activity.getCount()-1);
            System.err.println("恭喜中奖");
            if (activity.getCount() <=0) {
                activity.setState(activity.getDispenseOutState());
            } else {
                activity.setState(activity.getNoRaffleState());
            }
        } else {
            System.err.println("奖品派完了");
            activity.setState(activity.getDispenseOutState());
        }
    }
}

/**
 * 礼物派完了
 */
class DispenseOutState implements State {
    Activity activity;

    public DispenseOutState(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void deduceMoney() {
        System.err.println("奖品已经够发完了");
    }

    @Override
    public boolean raffle() {
        System.err.println("奖品已经够发完了");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.err.println("奖品派完了");
    }
}
