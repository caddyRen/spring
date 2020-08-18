package indi.ikun.spring.basejava.codequality.chapter02;

import java.util.Scanner;

/**
 * 24.边界，边界，还是边界
 * int -2147483648到2147483647
 *
 * 输入800 正常
 * 输入2147483647应该异常，确正常
 *
 * 2147483647是int类型的最大值，
 * 输入了一个最大值，使校验条件失效了，
 * order的值是2147483647，那再加上1000就超出int的范围了，其结果是-2147482649，那当然是小于正数2000了！
 * 一句话可归结其原因：数字越界使检验条件失效
 *
 * 在单元测试中，有一项测试叫做边界测试（也有叫做临界测试），
 * 如果一个方法接收的是int类型的参数，
 * 那以下三个值是必测的：0、正最大、负最小，其中正最大和负最小是边界值，
 * 如果这三个值都没有问题，方法才是比较安全可靠的。
 * 例子就是因为缺少边界测试，致使生产系统产生了严重的偏差
 */
public class Ax {

    public final static int LIMIT = 2000;

    public static void main(String[] args) {
        int cur = 1000;
        Scanner input = new Scanner(System.in);
        while (input.hasNextInt()) {
            int order = input.nextInt();
            if (order > 0 && order + cur <= LIMIT) {
                System.err.println("ok:" + order);
            } else {
                System.err.println("error");
            }
        }
    }

}