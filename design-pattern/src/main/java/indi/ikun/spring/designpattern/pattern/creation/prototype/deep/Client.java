package indi.ikun.spring.designpattern.pattern.creation.prototype.deep;

/**
 * @Description: 深拷贝
 * @Author caddy
 * @date 2020-02-11 15:09:31
 * @version 1.0
 */
public class Client {
    public static void main(String[] args) {

        Sheep s1=new Sheep("tom","yellow",1);
        s1.setDog(new Dog("jack"));

        try {
            //重写clone
            Sheep s2=(Sheep)s1.clone();
            System.err.println("1---"+s1.toString());
            System.err.println("1---"+s1.hashCode());
            System.err.println(s2.toString());
            System.err.println(s2.hashCode());


            //序列化反序列化
            Sheep s3=(Sheep)s1.deepClone();
            System.err.println("1---"+s1.toString());
            System.err.println("1---"+s1.hashCode());
            System.err.println(s3.toString());
            System.err.println(s3.hashCode());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}
