package indi.ikun.spring.basejava.codequality.chapter04;


/**
 * 56.自由选择字符串拼接方法
 * 对一个字符串进行拼接有三种方法：
 *      加号（最常用）,最慢
 *          编译器对字符串的加号做了优化，它会使用StringBuilder的append方法进行追加，按道理来说，其执行时间也应该是0毫秒，
 *          不过它最终是通过toString方法转换成String字符串的
 *          str1=new StringBuilder(str1).append("c").toString();
 *          每次循环都会创建一个StringBuilder对象，
 *          二是每次执行完毕都要调用toString方法将其转换为字符串-----执行时间就是耗费在这里
 *      concat方法，稍快
 *      StringBuilder（或StringBuffer）的append方法，最快
 *
 * 三者的实现方法不同，性能也就不同，但并不表示我们一定要使用StringBuilder，
 * 这是因为“+”非常符合我们的编码习惯，适合人类阅读，两个字符串拼接，就用加号连一下，这很正常，也很友好，在大多数情况下我们都可以使用加号操作，
 * 只有在系统性能临界（如在性能“增之一分则太长”的情况下）的时候才可以考虑使用concat或append方法。
 * 而且，很多时候系统80%的性能是消耗在20%的代码上的，我们的精力应该更多的投入到算法和结构上
 *
 */
public class Cd {
    public static void main(String[] args) {
        String str1 = "a";
        long start1=System.currentTimeMillis();
        for(int i=0;i<50000;i++){
            //str1=new StringBuilder(str1).append("c").toString();
            //每次循环都会创建一个StringBuilder对象，二是每次执行完毕都要调用toString方法将其转换为字符串—它的执行时间就是耗费在这里
            str1+="c";
        }
        long end1=System.currentTimeMillis();
        System.err.println(end1-start1);

        for(int i=0;i<50000;i++){
            /**
             * 源码:
             *  public String concat(String str) {
             *         int otherLen = str.length();
             *         if (otherLen == 0) {
             *             return this;
             *         }
             *         int len = value.length;
             *         char buf[] = Arrays.copyOf(value, len + otherLen);
             *         str.getChars(buf, len);
             *         return new String(buf, true);
             *     }
             * 整体看上去就是一个数组拷贝，
             * 虽然在内存中的处理都是原子性操作，速度非常快，
             * 不过，最后的return语句，每次的concat操作都会新创建一个String对象，
             * 这就是concat速度慢下来的真正原因，它创建了5万个String对象
             */
            str1=str1.concat("c");
        }
        long end2=System.currentTimeMillis();
        System.err.println(end2-end1);

        StringBuilder sb=new StringBuilder("a");
        for(int i=0;i<50000;i++){
            /**
             * 源码：
             * public AbstractStringBuilder append(String str) {
             *         if (str == null)
             *             return appendNull();
             *         int len = str.length();
             *         ensureCapacityInternal(count + len);
             *         str.getChars(0, len, value, count);
             *         count += len;
             *         return this;
             *     }
             *  整个append方法都在做字符数组处理，加长，然后数组拷贝，
             *  这些都是基本的数据处理，没有新建任何对象，所以速度也就最快
             */
            sb.append("c");
        }
        long end3=System.currentTimeMillis();
        System.err.println(end3-end2);
    }
}
