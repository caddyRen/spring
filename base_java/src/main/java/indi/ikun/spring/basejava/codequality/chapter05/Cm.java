package indi.ikun.spring.basejava.codequality.chapter05;

import java.util.Arrays;
import java.util.List;

/**
 * 65.避开基本类型数组转换列表陷阱
 *
 * 注意：原始类型数组不能作为asList的输入参数，否则会引起程序逻辑混乱
 *
 * 在Java中，数组是一个对象，它是可以泛型化的，
 * 也就是说我们的例子是把一个int类型的数组作为了T的类型，所以转换后在List中就只有一个类型为int数组的元素了
 * 不仅仅是int类型的数组有这个问题，其他7个基本类型的数组也存在相似的问题，
 * 在把基本类型数组转换成列表时，要特别小心asList方法的陷阱，避免出现程序逻辑混乱的情况
 */
public class Cm {
    /**
     * 把一个int类型的数组作为了T的类型
     * 所以转换后在List中就只有一个类型为int数组的元素
     */
    public static void main(String[] args) {
        int[] data={1,2,3,4,5};
        List list= Arrays.asList(data);
        System.err.println(list.size());
        System.err.println(data.equals(list.get(0)));
        System.err.println(data==list.get(0));
    }

}
class Cm1 {
    /**
     * 使用包装类即可
     */
    public static void main(String[] args) {
        Integer[] data={1,2,3,4,5};
        List list= Arrays.asList(data);
        System.err.println(list.size());
        System.err.println(data.equals(list.get(0)));
        System.err.println(data==list.get(0));
    }

}
