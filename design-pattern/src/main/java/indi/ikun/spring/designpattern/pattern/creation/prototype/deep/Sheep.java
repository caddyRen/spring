package indi.ikun.spring.designpattern.pattern.creation.prototype.deep;

import java.io.*;

/**
 * @Description: 深拷贝
 * @Author caddy
 * @date 2020-02-11 14:07:57
 * @version 1.0
 */
public class Sheep implements Cloneable , Serializable {
    private static final long serailVersionUID=1L;
    String name;
    String color;
    int age;
    Dog dog;
    //深拷贝2 通过对象序列化 推荐使用
    public Object deepClone(){
        Sheep object=null;

        //创建流对象
        ByteArrayOutputStream bos=null;
        ObjectOutputStream oos=null;
        ByteArrayInputStream bis=null;
        ObjectInputStream ois=null;
        try{
            //序列化
            bos=new ByteArrayOutputStream();
            oos=new ObjectOutputStream(bos);
            //当前这个对象以对象流的方式输出
            oos.writeObject(this);

            //反序列化
            bis=new ByteArrayInputStream(bos.toByteArray());
            ois=new ObjectInputStream(bis);
            object =(Sheep) ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                bos.close();
                oos.close();
                bis.close();
                ois.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return object;
    }



    //深拷贝 重写clone方法
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object object=null;
        //浅拷贝
        object=super.clone();
        Sheep sheep=(Sheep)object;
        sheep.dog= (Dog) dog.clone();
        return object;
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
                ", sheep hashcode= "+dog.hashCode()+
                '}';
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
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
