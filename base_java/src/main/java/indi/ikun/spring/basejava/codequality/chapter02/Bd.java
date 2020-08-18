package indi.ikun.spring.basejava.codequality.chapter02;

import java.util.Random;

/**
 * 30.不要随便设置随机种子
 *在Java中有两种方法可以获得不同的随机数：
 *  通过java.util.Random类获得随机数的原理
 *  Math.random，Math.random()方法也是通过生成一个Random类的实例，然后委托nextDouble()方法的，两者是殊途同归，没有差别
 *
 * 随机数在太多的地方使用了，比如加密、混淆数据等，
 * 我们使用随机数是期望获得一个唯一的、不可仿造的数字，以避免产生相同的业务数据造成混乱。
 * 在Java项目中通常是通过Math.random方法和Random类来获得随机数的
 *
 * Random类的默认种子（无参构造）是System.nanoTime()的返回值（JDK 1.5版本以前默认种子是System.currentTimeMillis()的返回值），
 * 注意这个值是距离某一个固定时间点的纳秒数，
 * 不同的操作系统和硬件有不同的固定时间点，
 * 也就是说不同的操作系统其纳秒值是不同的，
 * 而同一个操作系统纳秒值也会不同，随机数自然也就不同了。
 * （顺便说下，System.nanoTime不能用于计算日期，那是因为“固定”的时间点是不确定的，纳秒值甚至可能是负值，这点与System. currentTimeMillis不同。）
 *
 * new Random(1000)显式地设置了随机种子为1000，
 * 运行多次，虽然实例不同，但都会获得相同的三个随机数。所以，除非必要，否则不要设置随机种子
 */
public class Bd {

    public static void main(String[] args) {

        Random random = new Random();
        for(int i=1;i<4;i++){
            System.err.println(random.nextInt());
        }


        //-1244746321
        //1060493871
        //-1826063944
        //在同一台机器上，甭管运行多少次，所打印的随机数都是相同的,因为产生随机数的种子被固定了
        //种子不同，产生不同的随机数
        //种子相同，即使实例不同也产生相同的随机数
        Random random1=new Random(1000);
        for(int i=1;i<4;i++){
            System.err.println(random1.nextInt());
        }

    }


}
