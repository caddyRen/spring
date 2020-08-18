package indi.ikun.spring.basejava.codequality.chapter07;

/**
 * 104.使用forName动态加载类文件
 *
 * forName只是加载类，并不执行任何代码
 *
 * 动态加载（Dynamic Loading）是指在程序运行时加载需要的类库文件，
 * 对Java程序来说，一般情况下，一个类文件在启动时或首次初始化时会被加载到内存中，
 * 而反射则可以在运行时再决定是否要加载一个类，
 * 比如从Web上接收一个String参数作为类名，
 * 然后在JVM中加载并初始化，这就是动态加载，
 * 此动态加载通常是通过Class.forName(String)实现的
 *
 * 一个对象的生成必然会经过以下两个步骤
 *  1）加载到内存中生成Class的实例对象
 *  2）通过new关键字生成实例对象
 *
 *  使用的是import关键字产生的依赖包，
 *  JVM在启动时会自动加载所有依赖包下的类文件，
 *  要动态加载类文件，要使用forName方法，
 *  因为我们不知道生成的实例对象是什么类型（如果知道就不用动态加载），而且方法和属性都不可访问
 *  所以要使用forName方法动态加载一个类文件
 *
 *  加载一个类即表示要初始化该类的static变量，
 *  特别是static代码块，
 *  在这里可以做大量的工作，
 *      比如注册自己，
 *      初始化环境等，
 *  这才是要重点关注的逻辑
 *
 *  forName只是把一个类加载到内存中，
 *  并不保证由此产生一个实例对象，
 *  也不会执行任何方法，
 *  之所以会初始化static代码，
 *  是由类加载机制所决定的，
 *  而不是forName方法决定的。
 *  也就是说，如果没有static属性或static代码块，
 *  forName就只是加载类，没有任何的执行行为
 *
 *  动态加载，最经典的应用就是数据库驱动程序的加载
 *  Class.forName("com.mysql.jdbc.Driver");
 */
public class Dz {
    /**
     * 并没有对Utils做任何初始化，
     * 只是通过forName方法加载了Utils类，
     * 但是却产生了一个“Do Something”的输出，
     * 这就是因为Utils类被加载后，
     * JVM会自动初始化其static变量和static代码块，这是类加载机制所决定的
     */
    public static void main(String[] args) throws ClassNotFoundException {
        //动态加载
        Class.forName("indi.ikun.spring.basejava.codequality.chapter07.UtilsDz");
    }
}
class UtilsDz{
    //静态代码块
    static{
        System.err.println("do something");
    }
}

