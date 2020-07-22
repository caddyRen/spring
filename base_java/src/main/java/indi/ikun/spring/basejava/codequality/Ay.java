package indi.ikun.spring.basejava.codequality;

/**
 * 25.不要让四舍五入亏了一方
 */
public class Ay {
    public static void main(String[] args) {
        /**
         * 四舍五入的经典案例，绝对值相同的两个数字，
         * 近似值为什么就不同了呢？这是由Math.round采用的舍入规则所决定的(采用的是正无穷方向舍入规则)
         */
        System.err.println(Math.round(10.5));
        System.err.println(Math.round(-10.5));
    }
}