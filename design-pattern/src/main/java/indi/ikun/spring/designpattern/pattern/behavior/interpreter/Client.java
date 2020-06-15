package indi.ikun.spring.designpattern.pattern.behavior.interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * 解释器模式 Interpreter Pattern
 * 给定一个表达式,定义它的文法的一种表示,并定义一个解释器,使用该解释器来解释语言中的表达式
 *
 * 在编译原理中,一个算术表达式通过词法分析器形成词法单元,
 * 而后这些词法单元,再通过语法分析器构建语法分析树,最终形成一颗抽象的语法分析树
 * 这里的词法分析器和语法分析器都可以看作是解释器
 *
 * 场景:
 * 1.将一个需要解释执行的语言中的句子表示为一个抽象语法树
 * 2.一些重复出现的问题可以用一种简单的语言来表达
 * 3.一个简单语法需要解释的场景
 * 例子,编译器,运算表达式计算,正则表达式,机器人等
 *
 */
public class Client {

    public static void main(String[] args) throws IOException {
        String expStr=getExpstr();

        HashMap<String,Integer> var=getValue(expStr);
        Calculator calculator=new Calculator(expStr);
        System.err.println(expStr+"="+calculator.run(var));
    }

    private static String getExpstr() throws IOException {
        System.err.print("输入表达式:");
        return (new BufferedReader(new InputStreamReader(System.in))).readLine();

    }

    private static HashMap<String,Integer> getValue(String expStr) throws IOException {

        HashMap<String,Integer> map=new HashMap<>();
        for(char ch:expStr.toCharArray()){
            if(ch!='+'&&ch!='-'){
                if(!map.containsKey(String.valueOf(ch))){
                    System.err.print("请输入"+String.valueOf(ch)+"的值:");
                    String in=(new BufferedReader(new InputStreamReader(System.in))).readLine();
                    map.put(String.valueOf(ch),Integer.valueOf(in));
                }
            }
        }
        return map;

    }
}
