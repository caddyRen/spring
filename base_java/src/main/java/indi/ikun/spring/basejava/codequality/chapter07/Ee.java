package indi.ikun.spring.basejava.codequality.chapter07;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 109.不需要太多关注反射效率
 * 反射的效率相对于正常的代码执行确实低很多（经过测试，相差15倍左右），
 * 但是它是一个非常有效的运行期工具类，
 * 只要代码结构清晰、可读性好那就先开发起来，
 * 等到进行性能测试时证明此处性能确实有问题时再修改也不迟
 * （一般情况下反射并不是性能的终极杀手，而代码结构混乱、可读性差则很可能会埋下性能隐患）
 */
public class Ee {
    public static void main(String[] args) {
        BaseDao u=new UserDao();
        u.get(12);
    }
}

/**
 * Java的泛型类型只存在于编译期，
 * 因为该工具只支持继承的泛型类，
 * 如果是在Java编译时已经确定了泛型类的类型参数，那当然可以通过泛型获得
 *
 * 对于反射效率问题，不要做任何的提前优化和预期，这基本上是杞人忧天，
 * 很少有项目是因为反射问题引起系统效率故障的（除非是拷贝工的垃圾代码，这不在我们的讨论范围之内），
 * 而且根据二八原则，80%的性能消耗在20%的代码上，
 * 这20%的代码才是我们关注的重点，不要单单把反射作为重点关注对象
 */
class UtilsEe{
    //获得一个泛型类的实际泛型类型
    public static<T> Class<T> getGenricClassType(Class clz){
        Type type=clz.getGenericSuperclass();
        if(type instanceof ParameterizedType){
            ParameterizedType pt=(ParameterizedType)type;
            Type[] types=pt.getActualTypeArguments();
            if(types.length>0&&types[0] instanceof Class){
                //若有多个泛型参数，依据位置索引返回
                return (Class)types[0];
            }
        }
        return (Class)Object.class;
    }
}

/**
 * BaseDao和UserDao是ORM中的常客，
 * BaseDao实现对数据库的基本操作，比如增删改查，
 * 而UserDao则是一个比较具体的数据库操作，其作用是对User表进行操作，
 * 如果BaseDao能够提供足够多的基本方法，
 * 比如单表的增删改查，那些与UserDao类似的BaseDao子类就可以省去大量的开发工作。
 * 但问题是持久层的session对象（这里模拟的是Hibernate Session）需要明确一个具体的类型才能操作，
 * 比如get查询，需要获得两个参数：实体类类型（用于确定映射的数据表）和主键
 * 获取实体类类型
 *  最好的办法就是父类泛型化，
 *  子类明确泛型参数，
 *  然后通过反射读取相应的类型即可，
 *  于是就有了我们代码中的clz变量：
 *      通过反射获得泛型类型。
 *      如此实现后，UserDao可以不用定义任何方法，
 *      继承过来的父类操作方法已经能满足基本需求了，
 *      这样代码结构清晰，可读性又好
 *
 * 如果考虑反射效率问题，
 * 没有clz变量，不使用反射，
 * 每个BaseDao的子类都要实现一个查询操作，
 * 代码将会大量重复，
 * 违反了“Don’t RepeatYourself”这条最基本的编码规则，
 * 这会致使项目重构、优化难度加大，代码的复杂度也会提高很多
 */
abstract class BaseDao<T>{
    private Class<T> clz=UtilsEe.getGenricClassType(getClass());
    public void get(long i){
        System.err.println("get..."+i);
    }
}

/**
 * 对于UserDao类，
 * 编译器编译时已经明确了其参数类型是String，
 * 因此可以通过反射的方式来获取其类型，
 * 这也是getGenricClassType方法使用的场景
 */
class UserDao extends BaseDao<String>{
}