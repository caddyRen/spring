package indi.ikun.spring.designpattern.pattern.behavior.strategy;

/**
 * 算法使用者
 */
public class Duck {

    public String name;
    public Fly fly;
    public Quack quack;

    public void disPlay() {
        System.err.println(name+" : ");
        this.fly();
        this.quack();
    }

    public void fly(){
        fly.fly();
    }

    public void quack(){
        quack.quack();
    }

    public void setFly(Fly fly) {
        this.fly = fly;
    }

    public void setQuack(Quack quack) {
        this.quack = quack;
    }
}


class ToyDuck extends Duck {

    public ToyDuck() {
        name="玩具鸭";
        fly=new NoFly();
        quack=new JiJi();
    }
}


class PekingDuck extends Duck {

    public PekingDuck() {
        name="北京鸭";
        fly=new BadFly();
        quack=new GaGa();
    }
}

class WildDuck extends Duck {

    public WildDuck() {
        name="野鸭";
        fly=new GoodFly();
        quack=new NoQuack();
    }
}