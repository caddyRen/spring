package indi.ikun.spring.basejava.first;

import java.util.Random;

/**
 * 常量变变量
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("常量会变"+Const.RAND_CONST);
    }
}


interface Const{
    public static final int RAND_CONST=new Random().nextInt();
}