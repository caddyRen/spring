package indi.ikun.spring.basejava.codequality.chapter08;

/**
 * 111.采用异常链传递异常
 *
 * 设计模式中有一个模式叫做责任链模式（Chain of Responsibility），
 * 它的目的是将多个对象连成一条链，
 * 并沿着这条链传递该请求，
 * 直到有对象处理它为止，
 * 异常的传递处理也应该采用责任链模式。
 *
 * 一个系统友好性的标志是用户对该系统的“粘性”，
 * 粘性越高，系统越友好，粘性越低系统友好性越差，
 * 友好的界面和功能是一个方面，
 * 另外一方面就是系统出现非预期情况时的处理方式
 *
 * 先封装，然后传递，过程如下:
 *      1)把FileNotFoundException封装为MyException
 *      2)抛出到逻辑层，逻辑层根据异常代码（或者自定义的异常类型）确定后续处理逻辑，
 *          然后抛出到展现层
 *      3)展现层自行决定要展现什么，
 *          如果是管理员则可以展现低层级的异常，
 *          如果是普通用户则展示封装后的异常
 *使用异常链进行异常的传递
 *
 * 异常需要封装和传递，
 * 我们在进行系统开发时不要“吞噬”异常，
 * 也不要“赤裸裸”地抛出异常，
 * 封装后再抛出，
 * 或者通过异常链传递，可以达到系统更健壮、友好的目的
 */
public class Eg {

    /**
     * 捕捉到Exception异常，
     * 然后把它转化为IOException异常并抛出（此种方式也叫作异常转译），
     * 调用者获得该异常后再调用getCause方法即可获得Exception的异常信息，
     * 如此即可方便地查找到产生异常的根本信息，便于解决问题
     *
     */
    public void doStuff() throws IOExceptionEg {
        try{
            //...
        }catch (Exception e){
            throw new IOExceptionEg(e);
        }
    }
}
class IOExceptionEg extends Exception{
    //定义异常原因
    public IOExceptionEg(String message) {
        super(message);
    }
    //定义异常原因，并携带原始异常
    public IOExceptionEg(String message,Throwable cause){
        super(message,cause);
    }
    //保留原始异常信息
    public IOExceptionEg(Throwable cause) {
        super(cause);
    }
}
