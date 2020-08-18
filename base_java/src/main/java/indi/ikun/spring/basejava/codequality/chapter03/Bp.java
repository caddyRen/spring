package indi.ikun.spring.basejava.codequality.chapter03;

/**
 * 42.让工具类不可实例化
 * 工具类的方法和属性都是静态的，不需要生成实例即可访问，
 * 而且JDK也做了很好的处理，由于不希望被初始化，于是就设置构造函数为private访问权限，
 * 表示除了类本身外，谁都不能产生一个实例
 *
 * 工具类最好不要做继承的打算，
 * 因为如果子类可以实例化的话，那就要调用父类的构造函数，
 * 可是父类没有可以被访问的构造函数，于是问题就会出现
 */
public class Bp {
    /**
     * Don’t let anyone instantiate this class
     *
     * java反射可以修改构造函数的访问权限，
     * 所以只加private访问权限并不能有效限制
     * 可以增加一个抛出异常，错误
     */
    private Bp() {
        throw new Error("不能实例化");
    }
}
