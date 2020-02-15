package indi.ikun.spring.designpattern.pattern.creation.prototype;

/**
 * @Description: 实现Cloneable接口允许克隆
 * @Author caddy
 * @date 2020-02-11 14:07:57
 * @version 1.0
 */
public class Sheep implements Cloneable {
    String name;
    String color;
    int age;
    Sheep sheep;

    //浅拷贝，内部类不会拷贝
    public Sheep getSheep() {
        return sheep;
    }

    public void setSheep(Sheep sheep) {
        this.sheep = sheep;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }



    public Sheep(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", age=" + age +
//                ", sheep=" + sheep +
                //浅拷贝内部类只是引用了地址
                ", sheep hashcode= "+sheep.hashCode()+
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
