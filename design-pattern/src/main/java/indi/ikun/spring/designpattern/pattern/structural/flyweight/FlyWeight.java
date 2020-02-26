package indi.ikun.spring.designpattern.pattern.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式 抽象类
 */
public abstract class FlyWeight {

    abstract void use(UnSharedConcreteFlyWeight unSharedConcreteFlyWeight);

}


/**
 * 外部状态 共享
 */
class ConcreteFlyWeight extends FlyWeight{

    private String name;

    public ConcreteFlyWeight(String name) {
        this.name = name;
    }

    @Override
    void use(UnSharedConcreteFlyWeight unSharedConcreteFlyWeight) {
        System.err.println("当前共享外部状态为： "+name+"\n 不共享的内部状态为："+unSharedConcreteFlyWeight.getName());
    }
}

/**
 * 内部状态 不共享
 */
class UnSharedConcreteFlyWeight{
    private String name;

    public String getName() {
        return name;
    }

    public UnSharedConcreteFlyWeight(String name) {
        this.name = name;
    }
}

/**
 * 从这个共厂获取元数据，即 ConcreteFlyWeight
 */
class FlyWeightFactory{
    //存放共享内容的集合
    Map<String,FlyWeight> map=new HashMap<>();


    public FlyWeight get(String name){

        if(!map.containsKey(name)){
            map.put(name,new ConcreteFlyWeight(name));
        }
        return map.get(name);

    }

    public int count(){
        return map.size();
    }

}