package indi.ikun.spring.basejava.codequality.chapter08;

import java.util.ArrayList;
import java.util.List;

/**
 * 110.提倡异常封装
 * Java语言的异常处理机制可以确保程序的健壮性，
 * 提高系统的可用率，
 * 但是Java API提供的异常都是比较低级的（这里的低级是指“低级别”的异常），
 * 只有开发人员才能看得懂，才明白发生了什么问题。
 * 而对于终端用户来说，这些异常基本上就是天书，与业务无关，是纯计算机语言的描述
 * 异常封装有三方面的优点
 *  1）提高系统的友好性
 *  2）提高系统的可维护性
 *  3）解决Java异常机制自身的缺陷
 *      Java中的异常一次只能抛出一个，
 *      使用自行封装的异常，能一次抛出两个（或多个）异常
 */
public class Ef {
    public static void doStuff() throws MyException{
        List<Throwable> list=new ArrayList<>();
        try{
            //...
        }catch (Exception e){
            list.add(e);
        }
        try{
            //...
        }catch (Exception e){
            list.add(e);
        }
        if(list.size()>0){
            throw new MyException(list);
        }
    }
}
class MyException extends Exception{
    private List<Throwable> causes=new ArrayList<>();

    public List<Throwable> getExceptions() {
        return causes;
    }

    public MyException(List<? extends Throwable> causes) {
        this.causes.addAll(causes);

    }
}