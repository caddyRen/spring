package indi.ikun.spring.basejava.codequality.chapter05;

import java.util.*;

/**
 * 79.集合中的哈希码不要重复
 *
 * 注意：HashMap中的hashCode应避免冲突
 *
 * 在一个列表中查找某值是非常耗费资源的，
 * 随机存取的列表是遍历查找，
 * 顺序存储列表是链表查找，
 * Collections的二分法查找，
 * 但这都不够快，毕竟都是遍历，
 * 最快的还要数以Hash开头的集合（如HashMap、HashSet等类）查找
 *
 * HashMap的存储主线还是数组，遇到哈希冲突的时候则使用链表解决。
 * 了解了HashMap是如何存储的，：使用hashCode定位元素，若有哈希冲突，则遍历对比，
 * 在没有哈希冲突的情况下，HashMap的查找则是依赖hashCode定位的，因为是直接定位，那效率当然就高
 *
 * 如果哈希码相同，它的查找效率就与ArrayList没什么两样了，遍历对比，性能会大打折扣。
 * 特别是在那些进度紧张的项目中，虽重写了hashCode方法但返回值却是固定的，此时如果把这些对象插入到HashMap中，查找就相当耗时
 */
public class Da {
    public static void main(String[] args) {
        int size=10000;
        List<String> list=new ArrayList<>(size);
        for(int i=0;i<size;i++){
            list.add("value"+i);
        }
        long start=System.nanoTime();
        /**
         *ArrayList的contains就是一个遍历对比，
         * for循环逐个进行遍历，判断equals的返回值是否为true，为true即找到结果，不再遍历
         * 源码:
         * public int indexOf(Object o) {
         *         if (o == null) {
         *             for (int i = 0; i < size; i++)
         *                 if (elementData[i]==null)
         *                     return i;
         *         } else {
         *             for (int i = 0; i < size; i++)
         *                 if (o.equals(elementData[i]))
         *                     return i;
         *         }
         *         return -1;
         *     }
         */
        list.contains("value"+(size-1));
        long end=System.nanoTime();
        System.err.println(end-start+"ns");//纳秒ns 405100ns

        Map<String,String> map=new HashMap<>(size);
        for (int i=0;i<size;i++){
            map.put("key" + i, "value" + i);
        }
        long start1=System.nanoTime();
        /**
         * HashMap实现的一个关键点，能根据hashCode定位它在数组中的位置
         * HashMap的table数组是如何存储元素
         *  1.table数组的长度永远是2的N次幂
         *  2.table数组中的元素是Node类型
         *  3.table数组中的元素位置是不连续的
         *
         * HashMap插入元素计算hash码，定位元素
         * 哈希码相同，并key相等，则覆盖
         * HashMap每次增加元素时都会先计算其哈希码，
         * 然后使用hash方法再次对hashCode进行抽取和统计，
         * 同时兼顾哈希码的高位和低位信息产生一个唯一值，也就是说hashCode不同，hash方法返回的值也不同。
         * 之后再通过indexFor方法与数组长度做一次与运算，即可计算出其在数组中的位置，
         * 简单地说，hash方法和indexFor方法就是把哈希码转变成数组的下标。
         *
         * null值也是可以作为key值的，它的位置永远是在Node数组中的第一位
         *
         * 哈希运算存在着哈希冲突问题，
         * 即对于一个固定的哈希算法f(k)，允许出现f(k1)=f(k2)，
         * 但k1≠k2的情况，也就是说两个不同的Node，可能产生相同的哈希码，
         * HashMap是通过链表处理这个问题的，
         * 每个键值对都是一个Node，
         * 其中每个Node都有一个next变量，
         * 也就是说它会指向下一个键值对---很明显，这应该是一个单向链表，该链表是由addEntry方法完成的
         *
         * 源码:
         * public boolean containsKey(Object key) {
         *         return getNode(hash(key), key) != null;
         * }
         * final Node<K,V> getNode(int hash, Object key) {
         *         Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
         *         if ((tab = table) != null && (n = tab.length) > 0 &&
         *             (first = tab[(n - 1) & hash]) != null) {
         *             // always check first node
         *             if (first.hash == hash &&
         *                 ((k = first.key) == key || (key != null && key.equals(k))))
         *                 return first;
         *             if ((e = first.next) != null) {
         *                 if (first instanceof TreeNode)
         *                     return ((TreeNode<K,V>)first).getTreeNode(hash, key);
         *                 do {
         *                     //哈希码相同，并且键也相等才符合条件
         *                     if (e.hash == hash &&
         *                         ((k = e.key) == key || (key != null && key.equals(k))))
         *                         return e;
         *                 } while ((e = e.next) != null);
         *             }
         *         }
         *         return null;
         *     }
         *
         */
        map.containsKey("key"+(size-1));
        long end1=System.nanoTime();
        System.err.println(end1-start1+"ns");//纳秒ns 11100ns

    }

}
