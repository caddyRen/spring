package indi.ikun.spring.basejava.codequality.chapter05;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 78.减少HashMap中元素的数量
 * 注意：
 * 尽量让HashMap中的元素少量并简单
 * <p>
 * 在系统开发中，我们经常会使用HashMap作为数据集容器，或者是用缓冲池来处理，一般很稳定，
 * 但偶尔也会出现内存溢出的问题（如OutOfMemory错误），
 * 而且这经常是与HashMap有关的，
 * 比如我们使用缓冲池操作数据时，大批量的增删查改操作就可能会让内存溢出，下面建立一段模拟程序，重现该问题
 * <p>
 * HashMap底层的数组变量名叫table，(static class Node<K,V> implements Map.Entry<K,V> {)
 * 它是Node<K,V>类型的数组，保存的是一个一个的键值对（在我们的例子中Node<K,V>是由两个String类型组成的）。
 * 对我们的例子来说，HashMap比ArrayList多了一次封装，把String类型的键值对转换成Entry对象后再放入数组，
 * 这就多了40万个对象，这应该是问题产生的第一个原因
 * <p>
 * HashMap的长度也是可以动态增加的，它的扩容机制与ArrayList稍有不同
 * 在插入键值对时，会做长度校验，
 * 如果大于或等于阀值（threshold变量），则数组长度增大一倍。
 * 默认是当前长度与加载因子的乘积
 * final V putVal(int hash, K key, V value, boolean onlyIfAbsent,boolean evict)
 * if (++size > threshold)
 * resize();
 * <p>
 * HashMap默认的加载因子（loadFactor变量）是0.75，
 * 也就是说只要HashMap的size大于数组长度的0.75倍时，就开始扩容，
 * 经过计算得知（怎么计算的？查找2的N次幂大于40万的最小值即为数组的最大长度，再乘以0.75就是最后一次扩容点，计算的结果是N=19）
 * 在Map的size为393216时，符合了扩容条件，
 * 于是393216个元素准备开始大搬家，
 * 那首先要申请一个长度为1048576（当前长度的两倍嘛，2的19次方再乘以2，即2的20次方）的数组，
 * 但问题是此时剩余的内存只有7MB了，不足以支撑此运算，于是就报内存溢出了！这是第二个原因，也是最根本的原因
 * <p>
 * ArrayList的扩容策略，它是在小于数组长度的时候才会扩容1.5倍，
 * 经过计算得知，ArrayList的size在超过80万后（一次加两个元素，40万的两倍），最近的一次扩容会是在size为1005308时，
 * 也就是说，如果程序设置了增加元素的上限为502655，同样会报内存溢出，
 * 因为它也要申请一个1507963长度的数组，如果没这么大的地方，就会报错
 * <p>
 * HashMap比ArrayList多了一个层Entry的底层对象封装，多占用了内存，
 * 并且它的扩容策略是2倍长度的递增，
 * 同时还会依据阀值判断规则进行判断，
 * 因此相对于ArrayList来说，它就会先出现内存溢出
 * <p>
 * 可以在声明时指定HashMap的默认长度和加载因子来减少此问题的发生。
 * 可以不再频繁地进行数组扩容，但仍然避免不了内存溢出问题，因为键值对的封装对象Entry还是少不了的，
 * 内存依然增长较快
 */
public class Cz {
    /**
     * 模拟内存溢出
     * <p>
     * 启动参数设置-Xms63M -Xmx63M
     * 异常信息
     * Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
     * at java.util.HashMap.newNode(HashMap.java:1747)
     * at java.util.HashMap.putVal(HashMap.java:631)
     * at java.util.HashMap.put(HashMap.java:612)
     * at indi.ikun.spring.basejava.codequality.chapter05.Cz.main(Cz.java:34)
     * <p>
     * GC overhead limit exceeded异常  jdk1.6新增的错误类型 参考（https://zhuanlan.zhihu.com/p/88956975）
     * JVM花费了98%的时间进行垃圾回收，而只得到2%可用的内存，
     * 频繁的进行内存回收(最起码已经进行了5次连续的垃圾回收)，
     * JVM就会曝出java.lang.OutOfMemoryError: GC overhead limit exceeded错误
     * 如果没有这个异常，经过垃圾回收释放的2%可用内存空间会快速的被填满，迫使GC再次执行，
     * 出现频繁的执行GC操作， 服务器会因为频繁的执行GC垃圾回收操作而达到100%的时使用率，服务器运行变慢，
     * 应用系统会出现卡死现象，平常只需几毫秒就可以执行的操作，现在需要更长时间，甚至是好几分钟才可以完成
     * <p>
     * 解决方法：
     * 1、增加heap堆内存。
     * 2、增加堆内存后错误依旧，获取heap内存快照，使用Eclipse MAT工具，找出内存泄露发生的原因并进行修复。
     * 3、优化代码以使用更少的内存或重用对象，而不是创建新的对象，从而减少垃圾收集器运行的次数。如果代码中创建了许多临时对象(例如在循环中)，应该尝试重用它们。
     * 4、升级JDK到1.8，最起码也是1.7，并使用G1GC垃圾回收算法。
     * 5、除了使用命令-xms1g -xmx2g设置堆内存之外，尝试在启动脚本中加入配置:
     * <p>
     * -XX:+UseG1GC （增加这个即可实现作者的异常）
     * -XX:G1HeapRegionSize=n
     * -XX:MaxGCPauseMillis=m
     * -XX:ParallelGCThreads=n
     * -XX:ConcGCThreads=n
     */
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        final Runtime rt = Runtime.getRuntime();
        rt.addShutdownHook(
                new Thread(() -> {
                    StringBuffer sb = new StringBuffer();
                    long heapMaxSize = rt.maxMemory() >> 20;
                    sb.append("最大可用内存：" + heapMaxSize + "M\n");
                    long total = rt.totalMemory() >> 20;
                    sb.append("对内存大小：" + total + "M\n");
                    long free = rt.freeMemory() >> 20;
                    sb.append("空闲内存：" + free + "M\n");
                    System.err.println(sb);
                })
        );
        //放入近40万键值对
        for (int i = 0; i < 393217; i++) {
            //仅是增加
            map.put("key" + i, "value" + i);
        }
    }

}

/**
 * 与ArrayList做一个对比，把相同数据插入到ArrayList中并不会内存泄漏
 * <p>
 * 最大可用内存：64M
 * 对内存大小：64M
 * 空闲内存：10M
 */
class TestArrayList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        final Runtime rt = Runtime.getRuntime();
        rt.addShutdownHook(
                new Thread(() -> {
                    StringBuffer sb = new StringBuffer();
                    long heapMaxSize = rt.maxMemory() >> 20;
                    sb.append("最大可用内存：" + heapMaxSize + "M\n");
                    long total = rt.totalMemory() >> 20;
                    sb.append("对内存大小：" + total + "M\n");
                    long free = rt.freeMemory() >> 20;
                    sb.append("空闲内存：" + free + "M\n");
                    System.err.println(sb);
                })
        );
        //放入近40万键值对
        for (int i = 0; i < 393217; i++) {
            //仅是增加
            list.add("key" + i);
            list.add("value" + i);
        }
    }
}
