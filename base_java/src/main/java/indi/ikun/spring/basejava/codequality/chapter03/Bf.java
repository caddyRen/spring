package indi.ikun.spring.basejava.codequality.chapter03;

/**
 * 32.静态变量一定要先声明后赋值
 *
 * 静态变量是类加载时被分配到数据区（Data Area）的，它在内存中只有一个拷贝，
 * 不会被分配多次，其后的所有赋值操作都是值改变，地址则保持不变。
 * JVM初始化变量是先声明空间，然后再赋值的
 * int i=100;等价于
 * int i;
 * i=100;
 * 静态变量是在类初始化时首先被加载的，
 * JVM会去查找类中所有的静态声明，然后分配空间，
 * 注意这时候只是完成了地址空间的分配，还没有赋值，
 * 之后JVM会根据类中静态赋值（包括静态类赋值和静态块赋值）的先后顺序来执行。
 * 对于程序来说，就是先声明了int类型的地址空间，并把地址传递给了i，
 * 然后按照类中的先后顺序执行赋值动作，
 * (BfA类)首先执行静态块中i=100，接着执行i=1，那最后的结果就是i=1
 * (Bf类)首先执行静态类赋值中i=1，接着执行静态块中i=100，那最后的结果就是i=100
 * 位置最靠后的有最终的决定权
 */
public class Bf {

    public static int i=1;

    static {
        i=100;
    }

    public static void main(String[] args) {
        //100
        System.err.println(i);
    }

}

class BfA {

    static {
        i=100;
    }

    public static int i=1;

    public static void main(String[] args) {
        //1
        System.err.println(i);
    }

}
