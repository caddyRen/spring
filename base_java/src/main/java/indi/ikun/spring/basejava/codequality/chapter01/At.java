package indi.ikun.spring.basejava.codequality.chapter01;


/**
 * 20.不要只替换一个类
 * 例子不能在IDE工具（比如Eclipse）中运行
 * 那是因为在IDE中不能重现该问题，若修改了Constant类，IDE工具会自动编译所有的引用类，“智能”化屏蔽了该问题，但潜在的风险其实仍然存在
 *
 *
 * 对于final修饰的基本类型和String类型，编译器会认为它是稳定态（ImmutableStatus），
 * 所以在编译时就直接把值编译到字节码中了，
 * 避免了在运行期引用（Run-timeReference），以提高代码的执行效率。
 * 针对我们的例子来说，At类在编译时，字节码中就写上了“150”这个常量，而不是一个地址引用，
 * 因此无论你后续怎么修改常量类，只要不重新编译Client类，输出还是“150”
 *
 * 对于final修饰的类（即非基本类型），编译器认为它是不稳定态（Mutable Status），
 * 在编译时建立的则是引用关系（该类型也叫做Soft Final），
 * 如果Client类引入的常量是一个类或实例，即使不重新编译也会输出最新值
 *
 *
 * 风险
 *      直接采用替换class类文件的方式发布，会出现有的类（引用关系的类）使用了旧值，有的类（继承关系的类）使用的是新值
 *
 * 发布应用系统时禁止使用类文件替换方式，整体WAR包，jar包发布才是万全之策
 */
public class At {
    public static void main(String[] args) {
        System.err.println(AtConstant.MAX_AGE);
    }
}

class AtConstant{
    public final static int MAX_AGE=150;
}
//class AtConstant{
//    public final static int MAX_AGE=180;
//}
