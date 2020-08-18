package indi.ikun.spring.basejava.codequality.chapter08;


/**
 * 115.使用Throwable获得栈信息
 * AOP编程可以很轻松地控制一个方法调用哪些类，也能够控制哪些方法允许被调用，
 * 一般来说切面编程（比如AspectJ）只能控制到方法级别，
 * 不能实现代码级别的植入（Weave），
 * 比如一个方法被类A的m1方法调用时返回1，
 * 在类B的m2方法调用时返回0（同参数情况下），
 * 这就要求被调用者具有识别调用者的能力。
 * 在这种情况下，可以使用Throwable获得栈信息，然后鉴别调用者并分别输出
 */
public class Ek {
    public static void main(String[] args) {
        InvokerEk.m1();
        InvokerEk.m2();
    }
}

/**
 * 在出现异常时（或主动声明一个Throwable对象时），
 * JVM会通过fillInStackTrace方法记录下栈帧信息，
 * 然后生成一个Throwable对象，这样就可以知道类间的调用顺序、方法名称及当前行号等
 *
 * 获得栈信息可以对调用者进行判断，然后决定不同的输出，
 * 比如我们的m1和m2方法，同样是输入参数，同样的调用方法，但是输出却不同，
 * 方法m1调用m方法是正常显示，
 * 而方法m2调用却会返回“错误”数据。
 * 因此我们虽然可以依据调用者不同产生不同的逻辑，但这仅局限在对此方法的广泛认知上。
 * 更多的时候我们使用m方法的变形体
 */
class FooEk{
    public static boolean m(){
        StackTraceElement[] sts=new Throwable().getStackTrace();
        for(StackTraceElement st:sts){
            if(st.getMethodName().equals("m1")){
                return true;
            }
        }
        return false;
    }

    /**
     * m方法的变形体
     * 只是把“return false”替换成一个运行期异常，
     * 除了m1方法外，其他方法调用都会产生异常，
     * 该方法常用作离线注册码校验，
     * 当破解者试图暴力破解时，
     * 由于主执行者不是期望的值，
     * 因此会返回一个经过包装和混淆的异常信息，大大增加了破解的难度。
     */
    public static boolean m1(){
        //获取当前栈信息
        StackTraceElement[] sts=new Throwable().getStackTrace();
        //检查是否是m1方法调用
        for(StackTraceElement st:sts){
            if(st.getMethodName().equals("m1")){
                return true;
            }
        }
        throw new RuntimeException("除m1方法外，该方法不允许其他方法调用");
    }
}

/**
 * 两个方法m1和m2都调用了Foo的m方法，都是无参调用，返回值却不同，
 * 这是Throwable类发挥效能了。
 * JVM在创建一个Throwable类及其子类时会把当前线程的栈信息记录下来，以便在输出异常时准确定位异常原因，
 * Throwable源码：
 * public class Throwable implements Serializable {
 *        //出现异常的栈记录
 *        private StackTraceElement[] stackTrace = UNASSIGNED_STACK;
 *        public Throwable() {
 *         fillInStackTrace();
 *        }
 *        //本地方法，抓取执行时栈信息
 *        public synchronized Throwable fillInStackTrace() {
 *              if (stackTrace != null ||
 *                   backtrace != null ) {
 *                  fillInStackTrace(0);
 *                  stackTrace = UNASSIGNED_STACK;
 *               }
 *              return this;
 *        }
 * }
 */
class InvokerEk{
    public static void m1(){
        System.err.println(FooEk.m());//true
    }
    public static void m2(){
        System.err.println(FooEk.m());//false
    }

}
