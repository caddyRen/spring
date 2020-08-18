package indi.ikun.spring.basejava.codequality.chapter04;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 57.推荐在复杂字符串操作中使用正则表达式
 * 字符串的操作，诸如追加、合并、替换、倒序、分割等，都是在编码过程中经常用到的，
 * 而且Java也提供了append、replace、reverse、split等方法来完成这些操作，它们使用起来也确实方便，
 * 但是更多的时候，需要使用正则表达式来完成复杂的处理
 *
 *
 * 在Java的正则表达式中“\b”表示的是一个单词的边界，
 * 它是一个位置界定符，一边为字符或数字，另外一边则非字符或数字，
 * 例如“A”这样一个输入就有两个边界，即单词“A”的左右位置，
 * 这也就说明了为什么要加上“\w”（它表示的是字符或数字）
 *
 * 正则表达式在字符串的查找、替换、剪切、复制、删除等方面有着非凡的作用，
 * 特别是面对大量的文本字符需要处理（如需要读取大量的LOG日志）时，
 * 使用正则表达式可以大幅地提高开发效率和系统性能，
 * 但是正则表达式是一个恶魔（Regular Expressions is evil），它会使程序难以读懂
 *
 * 注意: 正则表达式是恶魔，威力巨大，但难以控制
 */
public class Ce {
    public static void main(String[] args) {
        Scanner input =new Scanner(System.in);
        while(input.hasNext()){
            String str=input.nextLine();
            //正则表达式对象
            Pattern pattern=Pattern.compile("\\b\\w+\\b");
            //生产匹配器
            Matcher matcher=pattern.matcher(str);
            int wordsCount=0;
            while (matcher.find()){
                wordsCount++;
            }
            System.err.println(str+"单词数："+wordsCount);
        }
    }
}