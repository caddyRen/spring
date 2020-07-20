package indi.ikun.spring.basejava.codequality;


/**
 * 3.三元操作符的类型务必一致
 *  保证三元操作符中的两个操作数类型一致，即可减少可能错误的发生
 *  三元操作符类型的转换规则
 *  1.若两个操作数不可转换，则不做转换，返回值为Object类型
 *  2.若两个操作数是明确类型的表达式（比如变量），则按照正常的二进制数字来转换，int转long，long转float
 *  3.若两个操作数中有一个是数字s，另一个是表达式，且其类型表示为T，那么若数字S在T的范围内，则转换为T类型；若S超出了T类型的范围，则T转换为S类型
 *  4.若两个操作数都是直接量数字，则返回值类型为范围较大者
 */
public class Ac {
    public static void main(String[] args) {
        int i=80;
        String s=String.valueOf(i<100?90:100);
        String s1=String.valueOf(i<100?90:100.0);
        System.err.println("两者变得不相等了"+s.equals(s1));
    }
}