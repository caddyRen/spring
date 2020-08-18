package indi.ikun.spring.basejava.codequality.chapter06;

import java.util.EnumSet;

/**
 * 89.枚举项的数量限制在64个以内
 * 注意：
 *  枚举项数量不要超过64，否则建议拆分
 *
 *  为了更好地使用枚举，Java提供了两个枚举集合：EnumSet和EnumMap，这两个集合的使用方法都比较简单，
 *  EnumSet表示其元素必须是某一枚举的枚举项，
 *  EnumMap表示Key值必须是某一枚举的枚举项，
 *  由于枚举类型的实例数量固定并且有限，相对来说EnumSet和EnumMap的效率会比其他Set和Map要高
 *
 *  在项目中一般会把枚举用作常量定义，可能会定义非常多的枚举项，
 *  然后通过EnumSet访问、遍历，但它对不同的枚举数量有不同的处理方式
 *
 * 枚举项的排序值ordinal是从0、1、2……依次递增的，没有重号，没有跳号，
 * RegularEnumSet就是利用这一点把每个枚举项的ordinal映射到一个long类型的每个位上的，
 * 注意看RegularEnumSet类的addAll方法的elments元素，
 * 它使用了无符号右移操作，
 * 并且操作数是负值，
 * 位移也是负值，这表示是负数（符号位是1）的“无符号左移”：
 * 符号位为0，并补充低位，
 * 简单地说，Java把一个不多于64个枚举项的枚举映射到了一个 long类型 变量上。
 * 这才是EnumSet处理的重点，其他的size方法、constains方法等都是根据elements计算出来的。
 * 想想看，一个long类型的数字包含了所有的枚举项，其效率和性能肯定是非常优秀的
 *
 * long类型是64位的，所以RegularEnumSet类型也就只能负责枚举项数量不大于64的枚举
 * 大于64则由JumboEnumSet处理
 * JumboEnumSet类把枚举项按照64个元素一组拆分成了多组，
 * 每组都映射到一个long类型的数字上，
 * 然后该数组再放置到elements数组中。
 * 简单来说JumboEnumSet类的原理与RegularEnumSet相似，
 * 只是JumboEnumSet使用了 long数组 容纳更多的枚举项
 *
 */
public class Dk {
    public static void main(String[] args) {
        /**
         * 追踪一下allOf--->noneOf方法
         * 发现当枚举项数量小于等于64时，创建一个RegularEnumSet实例对象，
         * 大于64时则创建一个JumboEnumSet实例对象
         */
        EnumSet<ConstDk> cs=EnumSet.allOf(ConstDk.class);
        EnumSet<LargeConstDk> lcs=EnumSet.allOf(LargeConstDk.class);
        System.err.println(cs.size());//64
        System.err.println(lcs.size());//65
        System.err.println(cs.getClass());//class java.util.RegularEnumSet
        System.err.println(lcs.getClass());//class java.util.JumboEnumSet
    }
}
/**
 * 64项
 */
enum ConstDk{
    A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,
    AA,AB,AC,AD,AE,AF,AG,AH,AI,AJ,AK,AL,AM,AN,AO,AP,AQ,AR,AS,AT,AU,AV,AW,AX,AY,AZ,
    BA,BB,BC,BD,BE,BF,BG,BH,BI,BJ,BK,BL;
}

/**
 * 65项
 */
enum LargeConstDk{
    A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,
    AA,AB,AC,AD,AE,AF,AG,AH,AI,AJ,AK,AL,AM,AN,AO,AP,AQ,AR,AS,AT,AU,AV,AW,AX,AY,AZ,
    BA,BB,BC,BD,BE,BF,BG,BH,BI,BJ,BK,BL,BM;
}
