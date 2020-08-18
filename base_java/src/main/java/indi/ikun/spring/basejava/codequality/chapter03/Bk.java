package indi.ikun.spring.basejava.codequality.chapter03;

/**
 * 37.构造代码块会想你所想
 * 案例：统计一个类的实例数量 根据36结果应为4，实际是3（正确）
 *
 * 如果遇到this关键字（也就是构造函数调用自身其他的构造函数时）则不插入构造代码块
 *
 * 编译器在编译时发现String形参的构造函数调用了无参构造，于是放弃插入构造代码块，所以只执行了一次构造代码块
 *
 * 构造代码块是为了提取构造函数的共同量，减少各个构造函数的代码而产生的，
 * 因此，Java就很聪明地认为把代码块插入到没有this方法的构造函数中即可，
 * 而调用其他构造函数的则不插入，确保每个构造函数只执行一次构造代码块
 *
 *
 * 注意
 * 不要以为this是特殊情况，那super也会类似处理了。
 * 其实不会，在构造代码块的处理上，super方法没有任何特殊的地方，
 * 编译器只是把构造代码块插入到super方法   之后  执行而已，仅此不同
 */
public class Bk {
    public static void main(String[] args) {
        new BaseBk();
        new BaseBk("");
        new BaseBk(1);
        System.err.println(BaseBk.getNumOfObjects());
    }

}
class BaseBk{
    //对象计数器
    private static int numOfObjects=0;
    {
        //构造代码块，计算产生对象数量
        numOfObjects++;
    }

    //
    public BaseBk() {

    }
    //有参构造调用无参构造
    public BaseBk(String _str) {
        //编译器在编译时发现String形参的构造函数调用了无参构造，于是放弃插入构造代码块，所以只执行了一次构造代码块
        this();
    }

    //有参构造不调用其他构造
    public BaseBk(int _i) {

    }

    public static int getNumOfObjects(){
        return numOfObjects;
    }


}
