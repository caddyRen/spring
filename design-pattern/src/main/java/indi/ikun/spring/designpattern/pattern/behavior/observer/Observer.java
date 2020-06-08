package indi.ikun.spring.designpattern.pattern.behavior.observer;

public interface Observer {

    void update(String data);
}

class Test1 implements Observer{

    private String data;

    @Override
    public void update(String data) {
        this.data=data;
        display();
    }

    public void display(){
        System.err.println(data);
    }
}


class Test2 implements Observer{

    private String data;

    @Override
    public void update(String data) {
        this.data=data;
        display();
    }

    public void display(){
        System.err.println("====="+data);
    }
}