package indi.ikun.spring.basejava.codequality.chapter07;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * 100.数组的真实类型必须是泛型类型的子类型
 *
 * 当一个泛型类（特别是泛型集合）转变为泛型数组时，
 * 泛型数组的真实类型不能是泛型类型的父类型（比如顶层类Object），
 * 只能是泛型类型的子类型（当然包括自身类型），否则就会出现类型转换异常
 *
 */
public class Dv {

    /**
     * 抛异常：
     *    java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to [Ljava.lang.String
     * 1）为什么Object数组不能向下转型为String数组
     *    数组是一个容器，只有确保容器内的所有元素类型与期望的类型有父子关系时才能转换，
     *    Object数组只能保证数组内的元素是Object类型，
     *    却不能确保它们都是String的父类型或子类，所以类型转换失败
     * 2）为什么是main方法抛出异常，而不是toArray方法
     *    其实，是在 toArray方法中进行的类型向下转换，而不是main方法中。
     *    那为什么异常会在main方法中抛出，应该在toArray方法的“T[] t = (T[])newObject[list.size()]”这段代码才对呀？
     *    那是因为泛型是类型擦除的，toArray方法经过编译后与如下代码相同
     *      public static Object[] toArray(List list){
     *         Object[] t=(Object[])new Object[list.size()];
     *         for (int i=0;i<list.size();i++){
     *             t[i] =list.get(i);
     *         }
     *         return t;
     *     }
     *     public static void main(String[] args) {
     *         List<String> list= Arrays.asList("A","B");
     *         for (String str:(String[])toArray(list))
     *             System.err.println(str);
     *     }
     *    toArray方法返回后会进行一次类型转换，
     *    Object数组转换成了String数组，
     *    于是就报ClassCastException异常了
     */
    public static void main(String[] args) {
        List<String> list= Arrays.asList("A","B");
//        for (String str:toArray(list))//异常
//            System.err.println(str);//异常
        for (String str:toArray1(list,String.class))
            System.err.println(str);


        //要想把一个Obejct数组转换为String数组，
        //只要Object数组的实际类型（Actual Type）也是String就可以了
        //objArray的实际类型和表面类型都是String数组
        Object[] objArray={"A","B"};
//        String[] strArray=(String[])objArray;//抛异常ClassCastException

        String [] ss={"A","B"};
        //objs的真实类型是String数组，显示类型为Object数组
        Object[] objs=ss;
        //顺利转换为String数组
        String[] strs=(String[])objs;

    }

    /**
     *List接口的toArray方法可以把一个集合转化为数组，但是使用不方便，
     * toArray()方法返回的是一个Object数组，所以需要自行转变；
     * toArray(T[] a)虽然返回的是T类型的数组，但是还需要传入一个T类型的数组，这也挺麻烦的，
     * 我们期望输入的是一个泛型化的List，这样就能转化为泛型数组

     */
    public static <T> T[] toArray(List<T> list){
        T[] t=(T[])new Object[list.size()];
        for (int i=0;i<list.size();i++){
            t[i] =list.get(i);
        }
        return t;
    }
    //优化
    public static <T> T[] toArray1(List<T> list,Class<T> tClass){
        T[] t=(T[]) Array.newInstance(tClass,list.size());
        for (int i=0;i<list.size();i++){
            t[i] =list.get(i);
        }
        return t;
    }

}

