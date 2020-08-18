package indi.ikun.spring.basejava.codequality.chapter11;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 140.推荐使用Guava扩展工具包
 * 2008年Google发布了Google-collections扩展工具包，
 * 主要是对JDK的Collection包进行了扩展，
 * 2010年Google发布了Guava项目，
 * 其中包含了
 *      collections
 *      caching
 *      primitivessupport
 *      concurrency libraries
 *      common annotations
 *      I/O等，
 * 这些都是项目编码中的基本工具包，它的主要功能
 * 1）Collections com.google.common.collect包中主要包括四部分：
 *      1）不可变集合（Immutable Collections）包括
 *          ImmutableList
 *          ImmutableMap
 *          ImmutableSet
 *          ImmutableSortedMap
 *          ImmutableSortedSet等，
 *          它比不可修改集合（Unmodifiable Collections）更容易使用，效率更高，而且占用的内存更少
 *      2）多值Map
 *          在JDK中，Map中的一个键对应一个值，
 *          在put一个键值对时，如果键重复了，则会覆盖原有的值，在大多数情况下这比较符合实际应用，
 *          但有的时候确实会存在一个键对应多个值的情况，
 *          比如我们的通讯录，一个人可能会对应两个或三个号码，此时使用JDK的Map就有点麻烦了。
 *          在这种情况下，使用Guava的Multimap可以很好地解决问题
 *      3）Table表
 *          在GIS（Geographic Information System，地理信息系统）中，
 *          经常会把一个地点标注在一个坐标上，
 *          比如把上海人民广场标注在北纬31.23、东经121.48的位置上，
 *          也就是说只要给出了准确的经度和纬度就可以进行精确的定位
 *
 *          两个键决定一个值
 *
 *          这在Guava中是使用Table来表示的
 *          其实Guava的Table类与我们经常接触的DBRMS表非常类似，可以认为它是一个没有Schema限定的数据表
 *      4）集合工具类
 *          Guava的集合工具类分得比较细，
 *          比如Lists、Maps、Sets分别对应的是List、Map、Set的工具类，
 *          它们的使用方法比较简单
 * 2)字符串操作
 *      Guava提供了两个非常好用的字符串操作工具：
 *          Joiner连接器
 *          Splitter拆分器
 *      当然，字符串的连接和拆分使用JDK的方法也可以实现，但是使用Guava更简单一些
 * 3)基本类型工具
 *      基本类型工具在primitives包中，是以基本类型名+s的方式命名的，
 *      比如Ints是int的工具类，
 *      Doubles是double的工具类，
 *      注意这些都是针对基本类型的，而不是针对包装类型的
 *
 * Guava还提供了其他操作（如I/O操作），相对来说功能不是非常强大
 */
public class Fk {

    public static void main(String[] args) {
        //1）Collections
        collections();
        //2)字符串操作工具
        joinerAndSplitter();
        //3)基本类型工具
        baseClass();
    }

    /**
     * 1）Collections
     */
    public static void collections(){
        //不可变集合，of方法有多个重载，其目的就是为了便于在初始化的时候直接生成一个不可变集合
        //不可变列表
        ImmutableList<String> list=ImmutableList.of("A","B","C");
        //不可变Map
        ImmutableBiMap<Integer,String> map=ImmutableBiMap.of(1,"壹",2,"贰",3,"叁");
//多值map: 一个键对应多个值
        Multimap<String,String> phoneBook= ArrayListMultimap.create();
        phoneBook.put("张三","110");
        phoneBook.put("张三","119");
        //输出的结果是一个包含两个元素的Collection
        System.err.println(phoneBook.get("张三"));//[110, 119]
//Table表: 两个键决定一个值
        Table<Double,Double,String> g=HashBasedTable.create();
        g.put(31.23,121.48,"人民广场");
        System.err.println(g.get(31.23,121.48));
        //类似于数据库表
        Table<Integer,Integer,String> user=HashBasedTable.create();
        //第一行，第一列，值是张三
        user.put(1,1,"张三");
        //第一行，第二列，值是李四
        user.put(1,2,"李四");
        System.err.println(user.get(1,1));
//集合工具类
        Map<String,Integer> users=new HashMap<>();
        users.put("张三",20);
        users.put("李四",22);
        users.put("王五",25);
        //lambda
        Map<String,Integer> filtedMap=Maps.filterValues(users, input -> input>20);
        //常规写法
       /* Map<String,Integer> filtedMap=Maps.filterValues(users, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer input) {
                return input>20;
            }
        });*/

        System.err.println(filtedMap);
    }

    /**
     * 字符串操作工具
     */
    public static void joinerAndSplitter(){
        //定义连接符号
        Joiner joiner =Joiner.on(", ");
        String str=joiner.skipNulls().join("嘿","Guava很不错的。");
        System.err.println(str);
        Map<String,String> map=new HashMap<>();
        map.put("张三","普通员工");
        map.put("李四","领导");
        //Joiner不仅能够连接字符串，还能够把Map中的键值对串联起来，比直接输出Map优雅了许多
        System.err.println(Joiner.on("\r\n").withKeyValueSeparator(" 是 ").join(map));

        //Splitter是做字符拆分的
        String str2="你好，Guava";
        for (String s:
             Splitter.on("，").split(str)) {
            System.err.println(s);
        }
        //按照固定长度分隔
        //fixedLength方法，它是按照给定长度进行拆分的，
        // 比如在进行格式化打印的时候，一行最大可以打印120个字符，此时使用该方法就非常简单了
        for (String s :
                Splitter.fixedLength(2).split(str)) {
            System.err.println(s);
        }


    }

    /**
     * 基本类型工具
     */
    public static void baseClass(){
        int[] ints={10,9,20,40,80};
        //从数组中取最大值
        System.err.println(Ints.max(ints));
        List<Integer> integers=new ArrayList<>();
        //把包装类型集合转换为基本类型数组
        ints=Ints.toArray(integers);
    }

}