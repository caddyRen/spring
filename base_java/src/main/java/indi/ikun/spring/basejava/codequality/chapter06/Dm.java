package indi.ikun.spring.basejava.codequality.chapter06;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 91.枚举和注解结合使用威力更大
 *
 * 注解的写法和接口很类似，都采用了关键字interface，
 * 而且都不能有实现代码，
 * 常量定义默认都是public static final类型的等，
 * 它们的主要不同点是：
 * 注解要在interface前加上@字符，而且不能继承，不能实现，这经常会给我们的开发带来一些障碍
 *
 * ACL（Access Control List，访问控制列表）设计案例
 * ACL三要素
 *  1）资源，有哪些信息是要被控制起来的
 *  2）权限级别，不同的访问者规划在不同的级别中
 *  3）控制器（也叫鉴权人），控制不同的级别访问不同的资源（鉴权人是整个ACL的设计核心）
 */
public class Dm {
    public static void main(String[] args) {
        Foo foo = new Foo();
        Access access=foo.getClass().getAnnotation(Access.class);
        System.err.println(access.level());
        if(access==null||!access.level().identify()){
            System.err.println(access.level().REFUSE_WORD);
        }
    }
}

/**
 * 鉴权人
 */
interface Identifier{
    //无权访问时的礼貌语
    String REFUSE_WORD="无权访问";
    //鉴权
    boolean identify();
}

/**
 * 使用枚举定义通用鉴权者
 */
enum CommonIdentifier implements Identifier{
    //权限等级
    Reader,Author,Admin;
    //实现鉴权
    @Override
    public boolean identify() {
        switch (this){
            case Admin:
            case Author:
                return true;
            case Reader:
                return false;
            default:
                throw new AssertionError("类型异常");
        }
    }
}
/**
 * 定义权限级别注解
 * 把资源和权限级别关联起来
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Access{
    CommonIdentifier level() default CommonIdentifier.Admin;
}

/**
 * 资源
 */
@Access(level=CommonIdentifier.Author)
class Foo{}


