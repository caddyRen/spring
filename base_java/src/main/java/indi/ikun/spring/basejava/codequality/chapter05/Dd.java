package indi.ikun.spring.basejava.codequality.chapter05;

/**
 * 82.由点及面，一叶知秋—集合大家族
 *
 * 注意:commons-collections、google-collections是JDK之外的优秀数据集合工具包，使用拿来主义即可
 *
 *Java中的集合类丰富
 * 常用的ArrayList、HashMap
 * 不常用的Stack、Queue，
 * 线程安全的Vector、HashTable，
 * 线程不安全的LinkedList、TreeMap，
 * 阻塞式的ArrayBlockingQueue，
 * 非阻塞式的PriorityQueue等
 *
 * 1.List
 *     实现List接口的集合主要有：ArrayList、LinkedList、Vector、Stack，
 *         ArrayList是一个动态数组，
 *         LinkedList是一个双向链表，
 *         Vector是一个线程安全的动态数组，
 *         Stack是一个对象栈，遵循先进后出的原则
 * 2.Set
 *      Set是不包含重复元素的集合，其主要的实现类有：EnumSet、HashSet、TreeSet，
 *         EnumSet是枚举类型的专用Set，所有元素都是枚举类型；
 *         HashSet是以哈希码决定其元素位置的Set，其原理与HashMap相似，它提供快速的插入和查找方法；
 *         TreeSet是一个自动排序的Set，它实现了SortedSet接口
 * 3.Map
 *     Map是一个大家族，它可以分为排序Map和非排序Map，
 *      排序Map主要是TreeMap类，它根据Key值进行自动排序；
 *      非排序Map主要包括：HashMap、HashTable、Properties、EnumMap等，
 *          Properties是HashTable的子类，它的主要用途是从Property文件中加载数据，并提供方便的读写操作；
 *          EnumMap则是要求其Key必须是某一个枚举类型。
 *          Map中还有一个WeakHashMap类需要说明，它是一个采用弱键方式实现的Map类，
 *              它的特点是：WeakHashMap对象的存在并不会阻止垃圾回收器对键值对的回收，
 *              也就是说使用WeakHashMap装载数据不用担心内存溢出的问题，
 *              GC会自动删除不用的键值对，这是好事。但也存在一个严重问题：
 *              GC是静悄悄回收的（何时回收？God knows!），我们的程序无法知晓该动作，存在着重大的隐患。
 * 4.Queue
 *      队列，它分为两类，
 *      一类是阻塞式队列，队列满了以后再插入元素则会抛出异常，主要包括：
 *          ArrayBlockingQueue、PriorityBlockingQueue、LinkedBlockingQueue，
 *              其中ArrayBlockingQueue是一个以数组方式实现的有界阻塞队列，
 *              PriorityBlockingQueue是依照优先级组建的队列，
 *              LinkedBlockingQueue是通过链表实现的阻塞队列；
 *      另一类是非阻塞队列，无边界的，只要内存允许，都可以持续追加元素，
 *          我们最经常使用的是PriorityQueue类。
 *      还有一种队列，是双端队列，支持在头、尾两端插入和移除元素，它的主要实现类是：
 *          ArrayDeque、LinkedBlockingDeque、LinkedList
 * 5.数组
 *      数组与集合的最大区别就是数组能够容纳基本类型，而集合就不行，
 *      更重要的一点就是所有的集合底层存储的都是数组
 * 6.工具类
 *      数组的工具类是java.util.Arrays和java.lang.reflect.Array，
 *      集合的工具类是java.util.Collections
 * 7.扩展类
 *      集合类可以自行扩展，想写一个自己的List？没问题，但最好的办法还是“拿来主义”，
 *      可以使用Apache的commons-collections扩展包，
 *      也可以使用Google的google-collections扩展包，这些足以应对我们的开发需要
 */
public class Dd {

}
