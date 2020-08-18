package indi.ikun.spring.basejava.codequality.chapter04;


import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

/**
 * 59.对字符串排序持一种宽容的心态
 *
 * 如果是排序对象是经常使用的汉字，使用Collator类排序完全可以满足我们的要求，毕竟GB2312已经包含了大部分的汉字，
 * 如果需要严格排序，则要使用一些开源项目来自己实现了，
 * 比如pinyin4j可以把汉字转换为拼音，然后我们自己来实现排序算法，
 * 不过此时你也会发现要考虑诸如算法、同音字、多音字等众多问题。
 *
 * 注意：
 * 如果排序不是一个关键算法，使用Collator类即可
 */
public class Cg {
    public static void main(String[] args) {
        String[] strs={"张三（Z）","李四（L）","王五（W）"};
        /**
         * Arrays工具类的默认排序是通过数组元素的compareTo方法来进行比较的
         * String compareTo源码:
         *  public int compareTo(String anotherString) {
         *         int len1 = value.length;
         *         int len2 = anotherString.value.length;
         *         int lim = Math.min(len1, len2);
         *         char v1[] = value;
         *         char v2[] = anotherString.value;
         *
         *         int k = 0;
         *         while (k < lim) {
         *             char c1 = v1[k];
         *             char c2 = v2[k];
         *             if (c1 != c2) {
         *                 return c1 - c2;
         *             }
         *             k++;
         *         }
         *         return len1 - len2;
         *     }
         * 这里是字符比较（减号操作符），也就是UNICODE码值的比较，
         * 查一下UNICODE代码表，“张”的码值是5F20，而“李”是674E，
         * 这样一看，“张”排在“李”的前面也就很正确了
         * 非英文的String排序可能会出现不准确的情况
         * 对非英文String排序Java推荐使用Collator类进行排序
         */
        Arrays.sort(strs);
        for (String str:strs){
            System.err.println(str);
        }
    }
}

/**
 * Java使用的是UNICODE编码，而中文UNICODE字符集是来源于GB18030的，
 * GB18030又是从GB2312发展起来，
 * GB2312是一个包含了7000多个字符的字符集，它是按照拼音排序，并且是连续的，
 * 之后的GBK、GB18030都是在其基础上扩充出来的，所以要让它们完整排序也就难上加难
 */
class ClientCg {
    public static void main(String[] args) {
        String[] strs={"张三（Z）","李四（L）","王五（W）"};
        //定义一个中文排序器
        Comparator<Object> c= Collator.getInstance(Locale.CHINA);
        //升序排列
        Arrays.sort(strs,c);
        for (String str:strs){
            System.err.println(str);
        }
    }
}
class Client2Cg {
    public static void main(String[] args) {
        String[] strs={"犇（Ben）","鑫（X）","张三（Z）","李四（L）","王五（W）"};
        Comparator<Object> c= Collator.getInstance(Locale.CHINA);
        Arrays.sort(strs,c);
        for (String str:strs){
            System.err.println(str);
        }
    }
}
