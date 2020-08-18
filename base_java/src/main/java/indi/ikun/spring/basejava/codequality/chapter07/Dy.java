package indi.ikun.spring.basejava.codequality.chapter07;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 103.反射访问属性或方法时将Accessible设置为true
 * <p>
 * Java中通过反射执行一个方法的过程如下：
 * 获取一个方法对象，
 * 然后根据isAccessible返回值确定是否能够执行，
 * 如果返回值为false则需要调用setAccessible（true），
 * 最后再调用invoke执行方法
 *
 * 习惯用法：
 *      通过反射方式执行方法时，必须在invoke之前检查Accessible属性。
 *      但方法对象的Accessible属性并不是用来决定是否可访问的
 *
 * Accessible的属性并不是我们语法层级理解的访问权限，而是指是否更容易获得，是否进行安全检查
 * 动态修改一个类或方法或执行方法时都会受Java安全体系的制约，
 * 而安全的处理是非常消耗资源的（性能非常低），
 * 因此对于运行期要执行的方法或要修改的属性就提供了Accessible可选项：
 * 由开发者决定是否要逃避安全体系的检查。
 *
 * AccessibleObject是Field、Method、Constructor的父类，
 * 决定其是否可以快速访问而不进行访问控制检查，
 * 在AccessibleObject类中是以override变量保存该值的，
 * 但是具体是否快速执行是在Method类的invoke方法中决定的
 *
 * Accessible属性只是用来判断是否需要进行安全检查的，如果不需要则直接执行，
 * 这就可以大幅度地提升系统性能（当然了，由于取消了安全检查，也可以运行private方法、访问private私有属性了）。
 * 在大量的反射情况下，设置Accessible为true可以提升性能20倍以上
 *
 * AccessibleObject的其他两个子类Field和Constructor与Method的情形相似：
 * Accessible属性决定Field和Constructor是否受访问控制检查。
 * 在设置Field或执行Constructor时，
 * 务必要设置Accessible为true，
 * 这并不仅仅是因为操作习惯的问题，还是在为我们系统的性能考虑
 */
public class Dy {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "doStuff";
        Method m1 = FooDy.class.getDeclaredMethod(methodName);
        if(!m1.isAccessible()){
            m1.setAccessible(true);
        }
        m1.invoke(new FooDy());

        Method m2 = FooDy.class.getDeclaredMethod("doStuff1");
        if(!m2.isAccessible()){
            m2.setAccessible(true);
        }
        m2.invoke(new FooDy());
    }

    static class FooDy {
        void doStuff() {
            System.err.println(123);
        }
        public final void doStuff1(){
            System.err.println("final doStuff");
        }
    }
}

