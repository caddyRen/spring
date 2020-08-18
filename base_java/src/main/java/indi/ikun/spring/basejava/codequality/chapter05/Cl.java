package indi.ikun.spring.basejava.codequality.chapter05;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * 64.多种最值算法，适时选择
 *
 * 最值计算时使用集合最简单，使用数组性能最优
 */
public class Cl {
    /**
     * 1.快速查找最大值
     * 速度最快的算法。它不要求排序，只要遍历一遍数组即可找出最大值
     */
    public static int max(int[] data){
        int max=data[0];
        for (int i:data){
            max=max>i?max:i;
        }
        return max;
    }
    /**
     * 2.先排序，后取值
     * 从效率上来讲，当然是1.快速查找法更快一些，只用遍历一遍就可以计算出最大值。
     * 但在实际测试中我们发现，如果数组数量少于1万，两者基本上没有差别，直接使用数组先排序后取值的方式。
     * 如果数组元素超过1万，就需要依据实际情况来考虑：
     *      自己实现，可以提升性能；
     *      先排序后取值，简单，通俗易懂。排除性能上的差异，两者都可以选择，甚至后者更方便一些
     */
    public static int max2(int[] data){
        //排序
        //拷贝数组是为了不改变原有数组的元素顺序
        Arrays.sort(data.clone());
        //取最大值
        return data[data.length-1];
    }
    /**
     * 3.剔除重复数据，排序，取最大值，第二大值
     */
    public static int max3(Integer[] data){
        //转换为列表
        List<Integer> dataList=Arrays.asList(data);
        //转换为TreeSet，删除重复元素并升序排序
        TreeSet<Integer> ts=new TreeSet<>(dataList);
        //取得比最大值ts.last()小的最大值，即第二大值
        return ts.lower(ts.last());
    }
}
