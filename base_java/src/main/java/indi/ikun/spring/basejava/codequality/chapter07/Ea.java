package indi.ikun.spring.basejava.codequality.chapter07;

import java.lang.reflect.Array;

/**
 * 105.动态加载不适合数组
 * 注意：
 * 通过反射操作数组使用Array类，不要采用通用的反射处理API
 *
 * 如果forName要加载一个类，
 * 那它首先必须是一个类—8个基本类型排除在外，它们不是一个具体的类；
 * 其次，它必须具有可追索的类路径，否则就会报ClassNotFoundException
 *
 * 在Java中，数组是一个非常特殊的类，虽然它是一个类，但没有定义类路径
 * 数组虽然是一个类，在声明时可以定义为String[]，
 * 但编译器编译后会为不同的数组类型生成不同的类
 *  元素类型          编译后类型
 *  byte[]              [B
 *  char[]              [C
 *  double[]            [D
 *  float[]             [F
 *  int[]               [I
 *  long[]              [J
 *  short[]             [S
 *  boolean[]           [Z
 *  引用类型（如String[]） [L引用类型路径;（[Ljava.lang.String;）
 *
 * 数组比较特殊，要想动态创建和访问数组，基本的反射是无法实现的，
 * 于是Java就专门定义了一个Array数组反射工具类来实现动态探知数组的功能
 */
public class Ea {
    /**
     * 下面代码可以动态加载一个数组类，
     * 但是这没有任何意义，因为它不能生成一个数组对象，
     * 只是把一个String类型的数组类和long类型的数组类加载到了内存中（如果内存中没有该类的话），
     * 并不能通过newInstance方法生成一个实例对象，
     * 因为它没有定义数组的长度，
     * 在Java中数组是定长的，没有长度的数组是不允许存在的
     */
    public static void main(String[] args) throws ClassNotFoundException {
        String[] strs=new String[10];
//        Class.forName("java.lang.String[]");//异常
        Class.forName("[Ljava.lang.String;");
        Class.forName("[J");


        /**
         * 反射不能定义一个数组
         *使用Array数组反射类来动态加载
         */
        String[] strs1=(String[]) Array.newInstance(String.class,8);
        int[][] ints=(int[][]) Array.newInstance(int.class,2,3);
        System.err.println(strs1.length);
        System.err.println(ints.length);

        System.err.println(byte[].class);
        System.err.println(char[].class);
        System.err.println(int[].class);
        System.err.println(float[].class);
        System.err.println(double[].class);
        System.err.println(long[].class);
        System.err.println(short[].class);
        System.err.println(boolean[].class);


    }
}
