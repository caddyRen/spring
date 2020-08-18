package indi.ikun.spring.basejava.codequality.chapter01;

import java.util.Random;

/**
 * 2.莫让常量蜕变成变量
 * 每次启动结果都会变
 * 务必让常量的值在运行期保持不变
 * 常量就是常量，在编译期就必须确定其值，不应该在运行期更改，否则程序的可读性会非常差
 */
public class Ab {
    public static void main(String[] args) {
        //1-常量会变306753251
        System.out.println("1-常量会变"+Const.RAND_CONST);
    }
}

interface Const{
    int RAND_CONST=new Random().nextInt();
}