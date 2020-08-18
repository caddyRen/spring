package indi.ikun.spring.basejava.codequality.chapter07;

import java.util.ArrayList;

/**
 * 101.注意Class类的特殊性
 * Java语言是先把Java源文件编译成后缀为class的字节码文件，
 * 然后再通过ClassLoader机制把这些类文件加载到内存中，
 * 最后生成实例执行的，这是Java处理的基本机制
 *
 * Java使用一个元类（MetaClass）来描述加载到内存中的类数据，这就是Class类，
 * 它是一个描述类的类对象，比如Dog.class文件加载到内存中后就会一个Class的实例对象描述之。
 * 因为Class类是“类中类”，也就有预示着它有很多特殊的地方：
 *  1）无构造函数。Java中的类一般都有构造函数，用于创建实例对象，
 *      但是Class类却没有构造函数，不能实例化，
 *      Class 对象是在加载类时由 Java 虚拟机通过调用类加载器中的defineClass方法自动构造的
 *  2）可以描述基本类型。
 *      虽然8个基本类型在JVM中并不是一个对象，它们一般存在于栈内存中，
 *      但是Class类仍然可以描述它们，例如可以使用int.class表示int类型的类对象
 *  3）其对象都是单例模式。
 *      一个Class的实例对象描述一个类，并且只描述一个类，
 *      反过来也成立，一个类只有一个Class实例对象
 *
 * Class类是Java的反射入口，
 * 只有在获得了一个类的描述对象后才能动态地加载、调用，
 * 一般获得一个Class对象有三种途径
 *      1)类属性方式，如String.class
 *      2)对象的getClass方法，如new String().getClass()
 *      3)forName方法加载，如Class.forName("java.lang.String")
 * 获得了Class对象后，
 * 就可以通过getAnnotations()获得注解，
 * 通过getMethods()获得方法，
 * 通过getConstructors()获得构造函数等，这为后续的反射代码铺平了道路
 */
public class Dw {
    public static void main(String[] args) {
        System.err.println(String.class.equals(new String().getClass()));
        System.err.println("ABC".getClass().equals(String.class));
        System.err.println(ArrayList.class.equals(new ArrayList<String>().getClass()));
    }

}

