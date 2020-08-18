package indi.ikun.spring.basejava.codequality.chapter01;


import java.util.List;

/**
 * 19.断言绝对不是鸡肋
 * 在防御式编程中经常会用断言（Assertion）对参数和环境做出判断，避免程序因不当的输入或错误的环境而产生逻辑异常
 * java中的断言使用的是assert关键字，基本用法如下
 * assert <布尔表达式>
 * assert <布尔表达式>:<错误信息>
 *
 * 在布尔表达式为false时，抛出AssertionError错误，并附带错误信息
 * assert的语法特性
 * 1. assert默认时不启用的 设置一下jvm的参数，参数是-enableassertions或者-ea
 *      断言时为了调式程序服务的，目的时为了能够快速、方便地检查到程序异常，
 *      但Java在默认条件下时不启用的，要启用就需要在编译、运行时加上相关的关键字，
 * 2.assert抛出的异常AssertionError时继承自Error的
 *      断言失败JVM会抛出一个AssertionError错误，是不可恢复的，也就表示这是一个严重问题，开发者必须予以关注并解决。
 * assert虽然是做断言的，但不能将其等价于if...else
 *
 *
 *  不可使用的情况
 *  1.在对外公开的方法中 encode方法
 *      防御式编程最核心的一点就是：所有的外部因素（输入参数、环境变量、上下文）都是"有潜在威胁"的，都存在着摧毁程序的风险，
 *      为了防止主程序的正确性，需要处处校验，不满足条件就不再执行后续程序，
 *  2.在执行逻辑代码的情况下
 *      assert的支持是可选的，在开发时可以让它运行，但在生产系统中则不需要其运行（提高性能）因此，在assert的布尔表达式中不能执行逻辑代码
 *      否则会影响环境不同而产生不同的逻辑。
 *  可用的情况：
 *  1.在私有方法中放置assert作为输入参数的校验
 *      在私有方法中可以放置assert校验输入参数，
 *      因为私有方法的使用者是作者自己，
 *      私有方法的调用者和被调用者之间是一种弱契约关系，或者说没有契约关系，
 *      其间的约束是依靠作者自己控制的，因此加上assert可以更好地预防自己犯错，或者无意的程序犯错
 *  2.流程控制中不可能达到的区域
 *      类似于JUnit的fail方法，其标志性的意义就是：程序执行到这里就是错误的
 *  3.建立程序探针
 *      我们可能会在一段程序中定义两个变量，分别代表两个不同的业务含义，
 *      但是两者有固定的关系，例如var1=var2*2，
 *      那我们就可以在程序中到处设“桩”，断言这两者的关系，
 *      如果不满足即表明程序已经出现了异常，业务也就没有必要运行下去了
 */
public class As {
    public static void main(String[] args) {
        AsStringUtils.encode(null);
    }
}

class AsStringUtils{

    /**
     * 1.在对外公开的方法中 不能使用
     *
     * encode方法对输入参数做了不为空的假设，为空则抛出AssertionError错误，
     * 问题：
     *    encode是一个public方法，这标志着它对外公开的，任何一个类只要能够传递一个String参数（遵守契约）就可以调用，
     *    但是调用方按照规范和契约（用null调用），缺获得了一个AssertionError错误，所以encode方法破坏了契约协定
     * @param str
     * @return
     */
    public static String encode(String str){
        assert null != str :"加密的字符串为null";
        //业务代码...
        return "123";
    }


    /**
     * 2.在执行逻辑代码的情况下 不能使用
     *
     * 问题：
     *      在启用assert环境下，该方法没有任何问题，但是一旦投入到生产环境，就不会启用断言，则个方法中list的remove动作永远不会执行，
     *      所以也就永远不会报错或异常，因为根本就没有执行remove
     * @param list
     * @param element
     */
    public void doSomething(List list,Object element){
        assert list.remove(element):"删除元素"+element+"失败";
        //业务代码...
    }

    //可以使用的情况
    public void sayOk(){
        int i=7;
        while(i>7){
            /*业务代码*/
        }
        //程序业务由上面代码完成，运行到此处说明由错误
        assert false:"运行到这里表示错误";
    }
}