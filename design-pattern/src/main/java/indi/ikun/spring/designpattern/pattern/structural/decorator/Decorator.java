package indi.ikun.spring.designpattern.pattern.structural.decorator;


/**
 * @Description: 装饰着
 * @Author caddy
 * @date 2020-02-12 18:13:49
 * @version 1.0
 */
public class Decorator extends Drink {
    private Drink drink;

    public Decorator(Drink drink) {//组合
        this.drink = drink;
    }

    @Override
    public float cost() {
        //super.getCost()自己的价格
        //drink.cost()待包装类的价格
        return super.getCost()+drink.cost();
    }

    //递归调用
    @Override
    public String getDes() {
        return super.des+" "+super.getCost()+" "+drink.getDes();
    }
}

//具体的装饰类
class Milk extends Decorator{

    public Milk(Drink drink) {
        super(drink);
        setDes("Milk");
        setCost(2.0f);
    }
}

//具体的装饰类
class Chocolate extends Decorator{

    public Chocolate(Drink drink) {
        super(drink);
        setDes("Chocolate");
        setCost(5.0f);
    }
}
