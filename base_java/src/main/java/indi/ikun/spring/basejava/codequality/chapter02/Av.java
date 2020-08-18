package indi.ikun.spring.basejava.codequality.chapter02;


import java.math.BigDecimal;

/**
 * 22.用整数类型处理货币
 *
 *在计算机中浮点数有可能（注意是可能）是不准确的，
 * 它只能无限接近准确值，而不能完全精确。
 * 为什么会如此呢？这是由浮点数的存储规则所决定的
 * 我们先来看0.4这个十进制小数如何转换成二进制小数，
 * 使用“乘2取整，顺序排列”法
 * 我们发现0.4不能使用二进制准确的表示，
 * 在二进制数世界里它是一个无限循环的小数，也就是说，“展示”都不能“展示”，
 * 更别说是在内存中存储了（浮点数的存储包括三部分：符号位、指数位、尾数，具体不再介绍），
 * 可以这样理解，在十进制的世界里没有办法准确表示1/3，
 * 那在二进制世界里当然也无法准确表示1/5（如果二进制也有分数的话倒是可以表示），
 * 在二进制的世界里1/5是一个无限循环小数
 *
 * 解决方案：
 *  1.使用BigDecimal
 *      BigDecimal是专门为弥补浮点数无法精确计算的缺憾而设计的类，
 *      并且它本身也提供了加减乘除的常用数学算法。
 *      特别是与数据库Decimal类型的字段映射时，BigDecimal是最优的解决方案
 *  2.使用整型
 *      把参与运算的值扩大100倍，并转变为整型，然后在展现时再缩小100倍，
 *      这样处理的好处是计算简单、准确，
 *      一般在非金融行业（如零售行业）应用较多。
 *      此方法还会用于某些零售POS机，它们的输入和输出全部是整数，那运算就更简单
 */
public class Av {
    public static void main(String[] args) {
        System.err.println(10.00-9.60);

        double d1=10.00;
        double d2=9.60;
        BigDecimal b1=new BigDecimal(Double.toString(d1));
        BigDecimal b2=new BigDecimal(Double.toString(d2));
        System.err.println(b1.subtract(b2));

        System.err.println((10.00*100-9.60*100)/100);

    }
}