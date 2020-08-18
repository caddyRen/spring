package indi.ikun.spring.basejava.codequality.chapter01;


/**
 * 1.不要在常量和变量中出现易混淆的字母
 * 包名全小写、类名首字母全大写、常量全部大写并用下划线分隔、变量采用驼峰命名法（Camel Case）;
 * 不要将易混字母数字混合使用iIlL10Oo(小写字母i、大写字母I、小写字母l、大写字母L、数字1、数字0、大写字母O、小写字母o)
 * 字母和数字混合使用，字母“l”务必大写，字母“O”则增加注释
 * 可以在idea中设置appearance使用Microsoft YaHei UI字体
 */
public class Aa {
    public static void main(String[] args) {
        long i=1l;
        //1-常量会变306753251
        System.out.println(i+i);
    }
}