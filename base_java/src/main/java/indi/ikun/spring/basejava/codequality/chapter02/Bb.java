package indi.ikun.spring.basejava.codequality.chapter02;


import java.util.Scanner;

/**
 * 28.优先使用整型池
 * Integer 常量池 -128-----127
 * 整型池的存在不仅仅提高了系统性能，同时也节约了内存空间，这也是我们使用整型池的原因
 *
 * 判断对象是否相等的时候，最好是用equals方法，避免用“==”产生非预期结果
 * 通过包装类的valueOf生成包装实例可以显著提高空间和时间性能
 *
 */
public class Bb {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNextInt()) {
            int ii = input.nextInt();
            System.err.println(ii + "相等判断");

            /**
             * new声明的就是要生成一个新的对象，没二话，这是两个对象，地址肯定不等，比较结果为false
             */
            Integer i = new Integer(ii);
            Integer j = new Integer(ii);
            System.err.println("new Integer(ii)对象>>>>>>" + (i == j));


            /**
             * 装箱生成的对象
             * 装箱动作是通过valueOf方法实现的，后两个算法（i=ii和 i = Integer.valueOf(ii)）是相同的
             * valueOf生成对象可以阅读一下Integer.valueOf的实现代码
             *
             * -128到127之间的int类型转换为Integer对象，则直接从cache数组中获得
             * cache是IntegerCache内部类的一个静态数组，
             * 容纳的是-128到127之间的Integer对象。
             * 通过valueOf产生包装对象时，如果int参数在-128和127之间，则直接从整型池中获得对象，
             * 不在该范围的int类型则通过new生成包装对象
             */
            i = ii;
            j = ii;
            System.err.println("基本类型转换的对象i=ii>>>>>>" + (i == j));


            i = Integer.valueOf(ii);
            j = Integer.valueOf(ii);
            System.err.println("valueOf产生的对象>>>>>>" + (i == j));
        }
    }


}
