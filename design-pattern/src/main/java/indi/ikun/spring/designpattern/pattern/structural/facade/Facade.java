package indi.ikun.spring.designpattern.pattern.structural.facade;

/**
 * 外观模式 分层
 */
public interface Facade {

    //start时，Model1 on Model2 start
    void start();

    //end是，Model1 off Model2 stop
    void end();

}

class FacadeImpl implements Facade{

    private Model1 model1;
    private Model2 model2;
    private Model3 model3;

    public FacadeImpl() {
        this.model1 = Model1.getINSTANCE();
        this.model2 = Model2.getINSTANCE();
        this.model3 = Model3.getINSTANCE();
    }

    @Override
    public void start() {
        model1.on();

        model2.satrt();
    }

    @Override
    public void end() {
        model2.stop();
        model1.off();
    }
}



/**
 * Model 模拟模块功能
 */
class Model1{
    //单例模式，饿汉
    private static Model1 INSTANCE=new Model1();
    public static Model1 getINSTANCE(){
        return INSTANCE;
    }

    public void on(){
        System.err.println("Model1 is on");
        play();
    }
    public void off(){
        System.err.println("Model1 is off");
    }

    public void play(){
        System.err.println("Model1 is play");
    }

}

class Model2{

    public static Model2 INSTANCE=new Model2();
    public static Model2 getINSTANCE(){
        return INSTANCE;
    }

    public void satrt(){
        System.err.println("Model2 is start");
        play();
    }
    public void stop(){
        Model3.getINSTANCE().off();
        System.err.println("Model2 is stop");
    }

    public void play(){
        System.err.println("Model2 is play");
        Model3.getINSTANCE().on();
        Model3.getINSTANCE().play();
    }
}

class Model3{
    public static Model3 INSTANCE=new Model3();
    public static Model3 getINSTANCE(){
        return INSTANCE;
    }

    public void on(){
        System.err.println("Model3 is on");
    }
    public void off(){
        System.err.println("Model3 is off");
    }

    public void play(){
        System.err.println("Model3 is play");
    }
}
