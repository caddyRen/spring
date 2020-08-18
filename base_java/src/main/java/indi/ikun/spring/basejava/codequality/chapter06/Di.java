package indi.ikun.spring.basejava.codequality.chapter06;

import java.util.Arrays;
import java.util.List;

/**
 * 87.使用valueOf前必须进行校验
 *
 * 每个枚举都是java.lang.Enum的子类
 * 都可以访问Enum类提供的方法，
 * 比如hashCode、name、valueOf等，
 * 其中valueOf方法会把一个String类型的名称转变为枚举项，
 * 也就是在枚举项中查找出字面值与该参数相等的枚举项。
 * 虽然这个方法很简单，但是JDK却做了一个对于开发人员来说并不简单的处理
 */
public class Di {
    public static void main(String[] args) {
        //复习66. Cn asList方法产生的List对象不可更改
        List<String> params= Arrays.asList("Spring","summer","Winter");
        //summer小写，枚举是首字母大写Summer
        /**
         * 抛出异常（不符合正常逻辑，不能转换就不转，一旦抛出这个异常，后续的代码就不会运行了）
         *
         * java.lang.IllegalArgumentException
         *
         * valueOf（String name）方法是不可见的，是JVM内置的方法
         * valueOf方法先通过反射从枚举类的常量声明中查找，
         * 若找到就直接返回，
         * 若找不到则抛出无效参数异常。
         * valueOf本意是保护编码中的枚举安全性，使其不产生空枚举对象，简化枚举操作，
         * 但是却又引入了一个我们无法避免的IllegalArgumentException异常
         *
         * 解决方案：
         * 1.使用try…catch捕捉异常
         * 2.扩展枚举类
         */
        for (String name:params){
            SeasonDi s=null;
            try{
                s=SeasonDi.valueOf(name);
            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }
            if(null!=s){
                System.err.println(s);
            }else
                System.err.println("无相关枚举项");
        }

        //扩展枚举类
        for (String name:params){
            if(SeasonDi.contains(name)){
                SeasonDi s=SeasonDi.valueOf(name);
                System.err.println(s);
            }
        }

        //有问题
        for (String name:params){
            SeasonDi s=SeasonDi.valueOf(name);
            if(null!=s){
                System.err.println(s);
            }else
                System.err.println("无相关枚举项");
        }
    }
}
enum SeasonDi {
    Spring, Summer, Autumn, Winter;
    //扩展枚举类
    public static boolean contains(String name){
        SeasonDi[] seasonDis=values();
        for (SeasonDi s:seasonDis){
            if(s.name().equals(name)){
                return true;
            }
        }
        return false;
    }
}