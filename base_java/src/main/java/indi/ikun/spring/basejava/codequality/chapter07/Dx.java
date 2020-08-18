package indi.ikun.spring.basejava.codequality.chapter07;

import java.lang.reflect.Method;

/**
 * 102.适时选择getDeclared×××和get×××
 * <p>
 * Java的Class类提供了很多的getDeclared×××方法和get×××方法，
 * 例如
 * getDeclared-Method和getMethod成对出现，
 * getDeclaredConstructors和getConstructors也是成对出现
 * getMethod方法获得的是所有public访问级别的方法，包括从父类继承的方法，
 * getDeclaredMethod获得是自身类的所有方法，包括公用（public）方法、私有（private）方法等，而且不受限于访问权限
 * <p>
 * Java之所以如此处理，
 * 是因为反射本意只是正常代码逻辑的一种补充，
 * 而不是让正常代码逻辑产生翻天覆地的变动，
 * 所以public的属性和方法最容易获取，
 * 私有属性和方法也可以获取，但要限定本类。
 * <p>
 * 列出所有继承自父类的方法，
 * 先获得父类，
 * 然后使用getDeclaredMethods，
 * 之后持续递归即可
 */
public class Dx {
    public static void main(String[] args) throws NoSuchMethodException {
        String methodName = "doStuff";
        Method m1 = FooDx.class.getDeclaredMethod(methodName);
//        Method m2 = FooDx.class.getMethod(methodName);//java.lang.NoSuchMethodException
    }

    static class FooDx {
        void doStuff() {
        }
    }
}

