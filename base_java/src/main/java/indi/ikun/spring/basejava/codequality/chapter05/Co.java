package indi.ikun.spring.basejava.codequality.chapter05;

import java.util.*;

/**
 * 67.不同的列表选择不同的遍历方法
 *
 * 实际测试效率区别不大，可能jdk1.8与作者的版本不一致
 *
 * 1.Java为ArrayList类加上了RandomAccess接口，就是在告诉我们ArrayList是随机存取的，采用下标方式遍历列表速度会更快
 * 2.LinkedList实现了双向链表，
 *      每个数据结点中都有三个数据项：前节点的引用（Previous Node）、本节点元素（NodeElement）、后继节点的引用（Next Node），
 *      也就是说在LinkedList中的两个元素本来就是有关联的，我知道你的存在，你也知道我的存在。
 *      元素之间已经有关联关系了，使用foreach也就是迭代器方式效率挺高，使用下标方式效率低
 *
 */
public class Co {

    public static void main(String[] args) {
        int stuNum=80*10000;
        List<Integer> scores=new ArrayList<>(stuNum);
        List<Integer> scores1=new LinkedList<>();

        for(int i=0;i<stuNum;i++){
            int temp=new Random().nextInt(150);
            scores.add(temp);
            scores1.add(temp);
        }
        long start=System.currentTimeMillis();
        System.err.println(average(scores));
        System.err.println(System.currentTimeMillis()-start);//8ms

        long start1=System.currentTimeMillis();
        System.err.println(average2(scores));
        System.err.println(System.currentTimeMillis()-start1);//7ms

        long start2=System.currentTimeMillis();
        System.err.println(average(scores1));
        System.err.println(System.currentTimeMillis()-start2);//11ms
    }

    /**
     * 优化
     */
    public static int average3(List<Integer> list){
        int sum=0;

        if(list instanceof RandomAccess){
            //随机存取的遍历下标
            for(int i=0;i<list.size();i++){
                sum+=list.get(i);
            }
        }else{
            //有序存取，使用foreach
            for(int i:list){
                sum+=i;
            }
        }

        return sum/list.size();
    }

    /**
     * foreach遍历时iterator迭代器的变形用法
     *
     * 迭代器：提供一种方法访问一个容器对象中的各个元素，同时又无须暴露该对象的内部细节
     *
     * 也就是说对于ArrayList，需要先创建一个迭代器容器，
     * 然后屏蔽内部遍历细节，对外提供hasNext、next等方法。
     * 问题是ArrayList实现了RandomAccess接口，已表明元素之间本来没有关系，
     * 可是，为了使用迭代器就需要强制建立一种互相“知晓”的关系，
     * 比如上一个元素可以判断是否有下一个元素，以及下一个元素是什么等关系
     * 这也就是通过foreach遍历耗时的原因
     *
     */
    public static int average(List<Integer> list){
        int sum=0;
        for(int i:list){
            sum+=i;
        }
        /**与上方foreach等价
        for(Iterator<Integer> i=list.iterator();i.hasNext();){
            sum+=i.next();
        }
        */
        return sum/list.size();
    }

    /**
     * for下标方式遍历性能高
     *
     * ArrayList数组实现了RandomAccess接口（随机存取接口），
     * 这也就标志着ArrayList是一个可以随机存取的列表。
     * 在Java中，RandomAccess和Cloneable、Serializable一样，都是标志性接口，不需要任何实现，只是用来表明其实现类具有某种特质的，
     * 实现了Cloneable表明可以被拷贝，
     * 实现了Serializable接口表明被序列化了，
     * 实现了RandomAccess则表明这个类可以随机存取，
     * 对我们的ArrayList来说也就标志着其数据元素之间没有关联，
     * 即两个位置相邻的元素之间没有相互依赖和索引关系，可以随机访问和存储
     */
    public static int average2(List<Integer> list){
        int sum=0;
        for(int i=0;i<list.size();i++){
            sum+=list.get(i);
        }
        return sum/list.size();
    }
}
