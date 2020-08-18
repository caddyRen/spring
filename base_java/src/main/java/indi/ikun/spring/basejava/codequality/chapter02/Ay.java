package indi.ikun.spring.basejava.codequality.chapter02;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 25.不要让四舍五入亏了一方
 *
 * 银行的盈利渠道主要是利息差，
 * 从储户手里收拢资金，然后放贷出去，其间的利息差额便是所获得的利润。
 * 对一个银行来说，对付给储户的利息的计算非常频繁，
 * 人民银行规定每个季度末月的20日为银行结息日，一年有4次的结息日
 * 1.四舍。舍弃的数值：0.000、0.001、0.002、0.003、0.004，
 *      因为是舍弃的，对银行家来说，就不用付款给储户了，
 *      那每舍弃一个数字就会赚取相应的金额：0.000、0.001、0.002、0.003、0.004
 * 2.五入。进位的数值：0.005、0.006、0.007、0.008、0.009，
 *      因为是进位，对银行家来说，每进一位就会多付款给储户，也就是亏损了，
 *      那亏损部分就是其对应的10进制补数：0.005、0.004、0.003、0.002、0.001。
 *
 * 舍弃和进位的数字是在0到9之间均匀分布的，所以对于银行家来说，每10笔存款的利息因采用四舍五入而获得的盈利是：
 * 0.000+0.001+0.002+0.003+0.004-0.005-0.004-0.003-0.002-0.001=-0.005
 * 经计算得出，每10笔的利息计算中就损失0.005元，即每笔利息计算损失0.0005元，
 * 这对一家有5千万储户的银行来说（对国内的银行来说，5千万是个很小的数字），
 * 每年仅仅因为四舍五入的误差而损失的金额是：1000000.0
 * 每年因为一个算法误差就损失了10万元，事实上以上的假设条件都是非常保守的，实际情况可能损失得更多。
 * 那各位可能要说了，银行还要放贷呀，放出去这笔计算误差不就抵消掉了吗？
 * 不会抵销，银行的贷款数量是非常有限的，其数量级根本没有办法和存款相比。
 *
 * 这个算法误差是由美国银行家发现的（那可是私人银行，钱是自己的，白白损失了可不行），
 * 并且对此提出了一个修正算法，叫做银行家舍入（Banker's Round）的近似算法，其规则如下：
 * 1.舍去位的数值小于5时，直接舍去
 * 2.舍去位的数值大于等于6时，进位后舍去
 * 3.当舍去位的数值等于5时，分两种情况：
 *      5后面还有其他数字（非0），则进位后舍去；
 *      若5后面是0（即5是最后一个数字），则根据5前一位数的奇偶性来判断是否需要进位，奇数进位，偶数舍去
 * 四舍六入五考虑，五后非零就进一，五后为零看奇偶，五前为偶应舍去，五前为奇要进一
 * round(10.5551)=10.56
 * round(10.555)=10.56
 * round(10.545)=10.54
 *
 *
 * 普通的项目中舍入模式不会有太多影响，可以直接使用Math.round方法，
 * 但在大量与货币数字交互的项目中，一定要选择好近似的计算模式，尽量减少因算法不同而造成的损失
 *
 * 根据不同的场景，慎重选择不同的舍入模式，以提高项目的精准度，减少算法损失
 *
 */
public class Ay {
    public static void main(String[] args) {
        /**
         * 四舍五入的经典案例，绝对值相同的两个数字，
         * 近似值为什么就不同了呢？这是由Math.round采用的舍入规则所决定的(采用的是正无穷方向舍入规则)
         */
        System.err.println(Math.round(10.5));
        System.err.println(Math.round(-10.5));


        int accountNum=5000*10000;
        double cost=0.005*accountNum*4;
        System.err.println("每年损失："+cost);


        /**
         * java5以上版本使用银行家得舍入算法
         */
        //存款
        BigDecimal d=new BigDecimal(888888);
        //月利率，乘3计算季度利率
        BigDecimal r=new BigDecimal(0.001875*3);

        /**
         * 计算利息
         * setScale方法设置了精度
         * RoundingMode.HALF_EVEN参数表示使用银行家舍入法则进行近似计算
         *
         * ROUND_UP：远离零方向舍 向绝对值最大的方向舍入，只要舍弃位非0即进位
         * ROUND_DOWN：趋向零方向舍入 向绝对值最小的方向舍入，注意：所有的位都舍弃，不存在进位情况
         * ROUND_CEILING：向正无穷方向舍入 正数，舍入行为类似于ROUND_UP；负数，则舍入行为类似于ROUND_DOWN。注意：Math.round方法使用的即为此模式
         * ROUND_FLOOR：向负无穷方向舍入 正数，则舍入行为类似于 ROUND_DOWN；负数，则舍入行为类似于ROUND_UP
         * HALF_UP：最近数字舍入（5进） 四舍五入模式
         * HALF_DOWN：最近数字舍入（5舍） 在四舍五入中，5是进位的，而在HALF_DOWN中却是舍弃不进位
         * HALF_EVEN：银行家算法
         */
        BigDecimal i=d.multiply(r).setScale(2, RoundingMode.HALF_EVEN);
        System.err.println("季利息："+i);

        BigDecimal a=new BigDecimal(10.5);
        BigDecimal a2=new BigDecimal(-10.5);
        System.err.println(a.setScale(0,RoundingMode.UP));
        System.err.println(a2.setScale(0,RoundingMode.UP));
        BigDecimal a3=new BigDecimal(10.5);
        BigDecimal a4=new BigDecimal(-10.5);
        System.err.println(a3.setScale(0,RoundingMode.DOWN));
        System.err.println(a4.setScale(0,RoundingMode.DOWN));
        BigDecimal a5=new BigDecimal(10.5);
        BigDecimal a6=new BigDecimal(-10.5);
        System.err.println(a5.setScale(0,RoundingMode.CEILING));
        System.err.println(a6.setScale(0,RoundingMode.CEILING));
        BigDecimal a7=new BigDecimal(10.5);
        BigDecimal a8=new BigDecimal(-10.5);
        System.err.println(a7.setScale(0,RoundingMode.FLOOR));
        System.err.println(a8.setScale(0,RoundingMode.FLOOR));
        BigDecimal a9=new BigDecimal(10.5);
        BigDecimal a0=new BigDecimal(-10.5);
        System.err.println(a9.setScale(0,RoundingMode.HALF_UP));
        System.err.println(a0.setScale(0,RoundingMode.HALF_UP));
        BigDecimal a11=new BigDecimal(10.5);
        BigDecimal a22=new BigDecimal(-10.5);
        System.err.println(a11.setScale(0,RoundingMode.HALF_DOWN));
        System.err.println(a22.setScale(0,RoundingMode.HALF_DOWN));




    }
}