package indi.ikun.spring.designpattern.javabase.string;

/**
 * String 字符串不经常变化的场景
 *
 * StringBuffer 线程安全
 * 频繁进行字符串的拼接,替换,删除等,并且允许在多线程环境中, XML解析,HTTP参数解析和封装
 *
 * StringBuilder 线程不安全
 * 频繁进行字符串的拼接,替换,删除等,并且运行在单线程的环境中,SQL拼接,JSON封装等
 */
public class TestStringBuffer {

    public static void main(String[] args) {

        String str="abc";
        String str1=str.substring(1);

        /**
         *  @Override
         *     public synchronized StringBuffer append(String str) {
         *         toStringCache = null;
         *         super.append(str);
         *         return this;
         *     }
         */
        StringBuffer sb=new StringBuffer("a");
        sb.append("b");


        /**
         * public StringBuilder append(String str) {
         *         super.append(str);
         *         return this;
         *     }
         */
        StringBuilder stringBuilder=new StringBuilder("a");
        stringBuilder.append("b");



    }
}
