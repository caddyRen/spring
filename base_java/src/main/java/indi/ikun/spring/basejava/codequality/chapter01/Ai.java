package indi.ikun.spring.basejava.codequality.chapter01;

//静态导入
import static java.lang.Math.PI;

/**
 * 9.少用静态导入
 * java5开始引入静态导入语法 import static
 * 目的是减少字符输入量，提高代码的可阅读性
 *
 * 规则
 *  1.不使用*通配符，除非是导入静态常量类（只包含常量的类或接口）
 *  2.方法名是具有明确、清晰表象意义的工具类
 *
 * 滥用静态导入会使程序更难阅读更难维护
 * 静态导入后，代码中就不用再写类名了，
 * 若使用*号通配符，会把一个类的所有静态元素导入进来，大可不必，
 * 用什么拿什么带着类名访问，更直观
 *
 */
public class Ai {
    //不使用静态导入
    public static double calCircleArea(double r){
        return Math.PI*r*r;
    }

    //使用静态导入
    public static double calCircleArea2(double r){
        return PI*r*r;
    }

}