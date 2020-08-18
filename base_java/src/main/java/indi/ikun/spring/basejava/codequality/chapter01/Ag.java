package indi.ikun.spring.basejava.codequality.chapter01;


/**
 * 7.警惕自增的陷阱
 * i++是一个表达式，
 * 是有返回值的，返回值就是count自加 前 的值
 * java对自加的处理方式
 * 1.先把i的值（注意是值，不是引用）拷贝到一个临时变量区，
 * 2.然后对i变量+1
 * 3.最后返回 临时变量区 的值
 * <p>
 * 扩展
 * c++中count=count++与count++等效
 * PHP中与java处理方式相同
 */
public class Ag {

    public static void main(String[] args) {
        int count = 0;
        System.err.println(count(count) + "");
    }

    private static int count(int count) {
        for (int i = 0; i < 10; i++) {
            /**
             * 1.JVM 把count值0拷贝到临时变量区
             * 2.count值+1，count=1
             * 3.返回临时变量区的值0
             * 4.返回值赋值给count，count重置为0
             */
            count = count++;
        }
        return count;
    }

    //count=count++使用java代码解释
    public static int mockAdd(int count) {
        int temp = count;
        count = count + 1;
        return temp;
    }

}