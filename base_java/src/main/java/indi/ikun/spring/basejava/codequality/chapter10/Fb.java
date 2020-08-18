package indi.ikun.spring.basejava.codequality.chapter10;

/**
 * 132.提升Java性能的基本方法（以下为jdk1.5的方式）
 * 1)不要在循环条件中计算
 *      如果在循环（如for循环、while循环）条件中计算，则每循环一遍就要计算一次，这会降低系统效率
 * 2)尽可能把变量、方法声明为final static类型
 * 3)缩小变量的作用范围
 *      关于变量，
 *      能定义在方法内的就定义在方法内，
 *      能定义在一个循环体内的就定义在循环体内，
 *      能放置在一个try……catch块内的就放置在该块内，
 *      其目的是加快GC的回收
 * 4)频繁字符串操作使用StringBuilder或StringBuffer
 *      虽然String的联接操作（“+”号）已经做了很多优化，
 *      但在大量的追加操作上StringBuilder或StringBuffer还是比“+”号的性能好很多
 * 5)使用非线性检索
 *      如果在ArrayList中存储了大量的数据，
 *      使用indexOf查找元素会比java.utils.Collections.binarySearch的效率低很多，
 *      原因是binarySearch是二分搜索法，
 *      而indexOf使用的是逐个元素比对的方法。
 *      这里要注意：
 *          使用binarySearch搜索时，
 *          元素必须进行排序，
 *          否则准确性就不可靠了
 * 6)覆写Exception的fillInStackTrace方法
 *      115. Ek使用Throwable获得栈信息
 *      提到fillInStackTrace方法是用来记录异常时的栈信息的，
 *      这是非常耗时的动作，如果我们在开发时不需要关注栈信息，则可以覆盖之
 * 7)不建立冗余对象
 *      不需要建立的对象就不能建立，说起来很容易，
 *      要完全遵循此规则难度就很大了，我们经常就会无意地创建冗余对象
 *
 * 运行一段程序需要三种资源：CPU、内存、I/O，
 * 1）提升CPU的处理速度可以加快代码的执行速度，直接表现就是返回时间缩短了，效率提高了；
 * 2）内存是Java程序必须考虑的问题，在32位的机器上，一个JVM最多只能使用2GB的内存，
 *      而且程序占用的内存越大，寻址效率也就越低，这也是影响效率的一个因素。
 * 2）I/O是程序展示和存储数据的主要通道，如果它很缓慢就会影响正常的显示效果。
 * 所以在编码时需要从这三个方面入手（任何程序优化都是从这三方面入手的）
 *
 * Java的基本优化方法非常多，
 * 随着Java的不断升级，很多看似很正确的优化策略就逐渐过时了（或者说已经失效了）
 * 最基本的优化方法就是自我验证，找出最佳的优化途径，提高系统性能，不可盲目信任
 */
public class Fb {

//1）不要在循环条件中计算
    public void demo(int i,int count){
        //如果在循环（如for循环、while循环）条件中计算，则每循环一遍就要计算一次，这会降低系统效率
        while (i<count*2){
            //...
        }
        //推荐写法
        int total=count*2;
        while (i<total){
            //...
        }
    }
//2)尽可能把变量、方法声明为final static类型
    public String demo1(int num){
        // 每次调用该方法时都会重新生成一个cns数组，
        // 注意该数组不会改变，属于不变数组，
        // 在这种情况下，把它声明为类变量，并且加上final static修饰会更合适，
        // 在类加载后就生成了该数组，每次方法调用则不再重新生成数组对象了，这有助于提高系统性能
        String[] cns={"零","壹","贰"};
        return cns[num];
    }
    //推荐写法
    final static String[] cns1={"零","壹","贰"};
    public String demo11(int num){
        return cns1[num];
    }
//4)频繁字符串操作使用StringBuilder或StringBuffer
    public void string(int max){
        String str="xxxx";
        for (int i = 0; i < max; i++) {
            str+="log"+i;
        }
        //推荐写法
        StringBuilder sb=new StringBuilder(20000);
        sb.append("xxxx");
        for (int i = 0; i < max; i++) {
            sb.append("log"+i);
        }
        String log=sb.toString();
    }

    //7)不建立冗余对象
    public void doSomeThing() throws Exception {
        //变量msg只有在抛出异常时才用到
        //但是只要该方法被调用就会创建，不管会不会抛出异常
        //异常不是我们的主逻辑，
        // 不是我们代码必须或经常要到达的区域，
        // 所以不应为了这个不经常出现的场景每次都多定义一个字符串变量，
        // 会占用更多的内存
        //在catch块中定义msg才合理：需要的时候才创建对象
        String msg="我出现异常，快扶我一下";
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            throw new Exception(msg, e);
        }
    }
}

/**
 * 6)覆写Exception的fillInStackTrace方法
 */
class MyExceptionFb extends Exception{
    //如下覆盖fillInStackTrace的自定义异常会使性能提升10倍以上
   public Throwable fillInStackTrace(){
       return this;
   }

}