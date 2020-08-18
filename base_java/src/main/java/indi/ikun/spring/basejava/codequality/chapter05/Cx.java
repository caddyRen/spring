package indi.ikun.spring.basejava.codequality.chapter05;

import java.util.ArrayList;
import java.util.List;

/**
 * 76.集合运算时使用更优雅的方式
 *
 * 数学上定义：
 *      1.确定性
 *          给定一个集合，任给一个元素，该元素或者属于或者不属于该集合，二者必居其一，不允许有模棱两可的情况出现 [6]  。
 *      2.互异性
 *          一个集合中，任何两个元素都认为是  不相同  的，即每个元素只能出现一次。有时需要对同一元素出现多次的情形进行刻画，可以使用多重集，其中的元素允许出现多次 [6]  。
 *      3.无序性
 *          一个集合中，每个元素的地位都是相同的，元素之间是无序的。集合上可以定义序关系，定义了序关系后，元素之间就可以按照序关系排序。但就集合本身的特性而言，元素之间没有必然的序
 *
 *
 * 集合的这些操作在持久层中使用得非常频繁，
 * 从数据库中取出的就是多个数据集合，之后我们就可以使用集合的各种方法构建我们需要的数据了，
 * 需要两个集合的and结果，那是交集，需要两个集合的or结果，那是并集，需要两个集合的not结果，那是差集
 */
public class Cx {
    public static void main(String[] args) {
        List<String> list1=new ArrayList<>();
        list1.add("A");
        list1.add("B");//数学上规定集合内元素是不能重复的
        list1.add("B");//数学上规定集合内元素是不能重复的
        List<String> list2=new ArrayList<>();
        list2.add("C");
        list2.add("B");
        //并集
//        list1.addAll(list2);
        //交集
//        list1.retainAll(list2);
        //差集
//        list1.removeAll(list2);
        /**
         * 无重复并集
         * 先删除list2里list1中出现的元素
         * 再把剩下的list2并到list1
         */
        list2.removeAll(list1);
        list1.addAll(list2);



        displayList(list1);

    }

    public static void displayList(List<String> list){
        for(String string:list){
            System.err.println(string);
        }
    }
}

