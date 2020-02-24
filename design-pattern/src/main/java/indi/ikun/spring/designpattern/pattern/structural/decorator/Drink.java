package indi.ikun.spring.designpattern.pattern.structural.decorator;

/**
 * @Description: 饮料基类
 * @Author caddy
 * @date 2020-02-12 16:01:25
 * @version 1.0
 */
public abstract class Drink {

    public String des;
    private float cost;

    public void setDes(String des) {
        this.des = des;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getDes() {
        return des;
    }

    //子类实现递归价格
   public abstract float cost();
}

/**
 * @Description: Caffee基类
 * @Author caddy
 * @date 2020-02-12 16:07:05
 * @version 1.0
 */
class Caffee extends Drink{

    @Override
    public float cost() {
        return getCost();
    }
}

/**
 * @Description: 具体咖啡类，扩展很方便
 * @Author caddy
 * @date 2020-02-12 16:08:59
 * @version 1.0
 */
class Decaf extends Caffee{

    public Decaf() {
        setCost(4.0f);
        setDes("Decaf");
    }
}

/**
 * @Description: 具体咖啡类，扩展很方便
 * @Author caddy
 * @date 2020-02-12 16:08:59
 * @version 1.0
 */
class LongBlack extends Caffee{

    public LongBlack() {
        setCost(10.0f);
        setDes("LongBlack");
    }
}


