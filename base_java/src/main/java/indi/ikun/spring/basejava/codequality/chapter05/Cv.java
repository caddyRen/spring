package indi.ikun.spring.basejava.codequality.chapter05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 72.不推荐使用binarySearch对列表进行检索
 * <p>
 * 使用binarySearch首先要考虑排序问题
 * 使用binarySearch的二分法查找比indexOf的遍历算法性能上高很多，
 * 特别是在大数据集而且目标值又接近尾部时，binarySearch方法与indexOf相比，性能上会提升几十倍，
 * 因此在从性能的角度考虑时可以选择binarySearch
 * <p>
 * 对一个列表进行检索时，我们使用得最多的是indexOf方法，
 * 它简单、好用，而且也不会出错，虽然它只能检索到第一个符合条件的值，
 * 但是我们可以生成子列表后再检索，这样也就可以查找出所有符合条件的值了
 * <p>
 * Collections工具类也提供一个检索方法：binarySearch，
 * 该方法也是对一个列表进行检索的，可查找出指定值的索引值
 * JDK上对binarySearch的描述：
 * 使用二分搜索法搜索指定列表，以获得指定对象。
 * 其实现的功能与indexOf是相同的，只是使用的是二分法搜索列表
 */
public class Cv {
    public static void main(String[] args) {
        List<String> cities = new ArrayList<>();
        cities.add("广州");
        cities.add("上海");
        cities.add("北京");
        cities.add("广州");
        cities.add("天津");
        cities.add("天津");
        cities.add("广州");
        cities.add("广州");
        cities.add("天津");
        cities.add("上海");
        cities.add("北京");
        cities.add("北京");
        List<Integer> result = new ArrayList<>();
        getIndex(cities, "广州", result);
        for (int i = 0; i < result.size(); i++) {
            if (i > 0) {
                result.set(i, result.get(i) + 1 + result.get(i - 1));
            }
            System.err.println(result.get(i));
        }

        /**
         * 二分法查询的一个首要前提是：数据集已经实现升序排列，否则二分法查找的值是不准确的
         * 拷贝一个数组，然后再排序，再使用binarySeach查找指定值
         */
        Collections.sort(cities);
        for (String str :cities) {
            System.err.println(str);
        }
        int index2 = Collections.binarySearch(cities, "广州");
        System.err.println(index2);
    }

    /**
     * 获取所有目标的位置信息，
     *
     * @param list
     * @param target
     * @param result 子列表结果集
     */
    public static void getIndex(List<String> list, String target, List<Integer> result) {
        if (list.contains(target)) {
            int index = list.indexOf(target);
            //记录位置，此时记录的是子列表中的位置，最后要计算得出源列表位置
            result.add(index);
            //判断是否还有剩余元素（是否还有子列表），有则递归
            if (index < list.size() - 1) {
                List<String> sublist = list.subList(index + 1, list.size());
                if (sublist.contains(target)) {
                    getIndex(sublist, target, result);
                }
            }
        }
    }
}
