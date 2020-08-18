package indi.ikun.spring.basejava.codequality.chapter05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 71.推荐使用subList处理局部列表
 * <p>
 * 一个简单的需求：
 * 一个列表有100个元素，现在要删除索引位置为20～30的元素.
 * <p>
 * “one-lining”一行代码就解决上述问题
 * <p>
 * ArrayList的removeRange方法有protected关键字修饰着，不能直接使用
 */
public class Cs {
    public static void main(String[] args) {
        //初始化固定长度不可变列表
        List<Integer> initData = Collections.nCopies(100, 0);
        //转换为可变列表
        ArrayList<Integer> list = new ArrayList<Integer>(initData);
        System.err.println(list.size());
        //删除指定范围元素
        // 用subList先取出一个子列表，然后清空。
        // 因为subList返回的List是原始列表的一个视图，
        // 删除这个视图中的所有元素，最终就会反映到原始字符串上，那么一行代码即解决问题
        list.subList(20, 30).clear();
        System.err.println(list.size());
    }

}
