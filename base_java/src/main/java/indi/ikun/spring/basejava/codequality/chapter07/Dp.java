package indi.ikun.spring.basejava.codequality.chapter07;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 94.不能初始化泛型参数和数组
 * 泛型类型在编译期被擦除，我们在类初始化时将无法获得泛型的具体参数
 *
 * 类的成员变量是在类初始化前初始化的，
 * 所以要求在初始化前它必须具有明确的类型，
 * 否则就只能声明，不能初始化
 *
 */
public class Dp {
}

/**
 * t、tArray、list都是类变量，都是通过new声明了一个类型，看起来非常相似啊！
 * 但这段代码是编译通不过的，
 * 因为编译器在编译时需要获得T类型，
 * 但泛型在编译期类型已经被擦除了，
 * 所以new T()和new T[5]都会报错
 *      （可能有读者疑惑了：泛型类型可以擦除为顶级类Object，
 *      那T类型擦除成Object不就可以编译了吗？
 *      这样也不行，泛型只是Java语言的一部分，
 *      Java语言毕竟是一个强类型、编译型的安全语言，
 *      要确保运行期的稳定性和安全性就必须要求在编译器上严格检查）。
 * new ArrayList<T>()却不会报错
 * 这是因为ArrayList表面是泛型，其实已经在编译期转型为Object了
 * 观察ArrayList源码
 * 容纳ArrayList元素的定义：其类型是Object
 *      transient Object[] elementData;
 * Object是所有类的父类，数组又允许协变（Covariant），
 * 因此elementData数组可以容纳所有的实例对象。
 * 元素加入时向上转型为Object类型（E类型转为Object），
 * 取出时向下转型为E类型（Object转为E类型），如此处理而已
 */
class FooDp<T>{
//    private T t=new T();//编译报错
//    private T[] tArrays=new T[5];//编译报错
    private List<T> list=new ArrayList<>();
}

/**
 * 泛型数组解决方案
 * 在运行期获得T的类型，也就是tType参数，
 * 一般情况下泛型类型是无法获取的，
 * 不过，在客户端调用时多传输一个T类型的class就会解决问题
 */
class FooDp1<T>{
    private T t;
    private T[] tArrays;
    private List<T> list=new ArrayList<>();

    public FooDp1() {
        try{
            Class<?> tType=Class.forName("");
            t=(T)tType.newInstance();
            tArrays=(T[]) Array.newInstance(tType,5);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
