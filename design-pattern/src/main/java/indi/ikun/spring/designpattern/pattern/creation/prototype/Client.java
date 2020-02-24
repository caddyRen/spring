package indi.ikun.spring.designpattern.pattern.creation.prototype;


public class Client {
    public static void main(String[] args) {

        Sheep s1=new Sheep("tom","yellow",1);
        s1.setSheep(new Sheep("jack","black",2));

        try {
            Sheep s2=(Sheep)s1.clone();

            System.err.println("1---"+s1.toString());
            System.err.println("1---"+s1.hashCode());
            System.err.println(s2.toString());
            System.err.println(s2.hashCode());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


        //spring源码 原型模式分析
//        ApplicationContext a=new ClassPathXmlApplicationContext("");
//        a.getBean("test");

        //1.ctrl+alt+左键查看getBean()  选择AbstractApplicationContext
        //2.ctrl+alt+左键查看getBean(requiredType) 选择AbstractRefreshableApplicationContext
        //3.ctrl+左键查看doGetBean(name, null, null, false);
        /**
         *
         *               doGetBean方法内
         *               // Create bean instance.
         *              //此处判断是创建单例对象还是原型对象
         * 				if (mbd.isSingleton()) {
         * 					...
         *                }
         *
         * 				else if (mbd.isPrototype()) {
         * 			        ...
         *                }
         */





    }
}
