package indi.ikun.spring.basejava.codequality.chapter05;


import java.util.ArrayList;
import java.util.List;

/**
 * 63.在明确的场景下，为集合指定初始容量
 *
 * 注意：非常有必要在集合初始化时声明容量
 *
 * Vector与ArrayList不同的地方是它提供了递增步长（capacityIncrement变量），
 * 其值代表的是每次数组拓长时要增加的长度，
 * 不设置此值则是容量翻倍（默认是不设置递增步长的，可以通过构造函数来设置递增步长）。
 * 其他集合类的扩容方式与此相似，如HashMap是按照倍数增加的，
 * Stack继承自Vector，所采用的也是与其相同的扩容原则
 *
 * ArrayList、Vector、HashMap等集合，一般都是直接用new跟上类名声明出一个集合来，然后使用add、remove等方法进行操作,而且因为它是自动管理长度的
 * jdk源码：
 *      ArrayList add方法->ensureCapacityInternal 方法 ->ensureExplicitCapacity 方法
 *      grow方法计算新的集合长度，使用移位运算，扩大约1.5倍
 *      int newCapacity = oldCapacity + (oldCapacity >> 1);
 *
 * 移位运算
 * <<左移
 * 右边空出的位用0填补高位左移溢出则舍弃该高位。计算机中常用补码表示数据，注，用补码计算
 * 00001000  8
 * 00100000  左移后32
 * 负数第一位表示符号
 * 10001000  -8
 * 11110111  -8反码
 * 11111000  -8补码（+1后得补码）
 * 11100000  整体左移，溢出的舍弃
 * 11011111  补码进行逆运算，先-1得反码
 * 10100000  再根据反码得正码
 * >>右移
 * 左边空出的位用0或者1填补。正数用0填补，负数用1填补。注：不同的环境填补方式可能不同；低位右移溢出则舍弃该位
 * 00001000  8
 * 00000100  右移后4
 * 负数第一位表示符号
 * 10001000  -8
 * 11110111  -8反码
 * 11111000  -8补码（+1后得补码）
 * 11111100  整体右移，右溢出舍去
 * 11111011  补码进行逆运算，先-1得反码
 * 10000100  再根据反码得正码 -4
 * >>>无符号右移
 * 无符号右移：正数与右移规则一样，负数的无符号右移，就是相应的补码移位所得，在高位补0即可
 */
public class Ck {
    public static void main(String[] args) {
        System.err.println(8 << 2);//32
        System.err.println(-8 << 1);//-16
        System.err.println(8 >> 1);//4
        System.err.println(-8 >> 1);//-4
        System.err.println(32 >>> 2);//8
        System.err.println(-8 >>> 2);//-8
        List<String> a = new ArrayList<>();
        //进入add，ensureCapacityInternal()
        //int newCapacity = oldCapacity + (oldCapacity >> 1);
        a.add("1");
    }
}
