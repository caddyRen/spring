package indi.ikun.spring.basejava.codequality.chapter11;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.mutable.MutableInt;
import org.springframework.util.StringUtils;

/**
 * 141.Apache扩展包
 * Apache Commons通用扩展包基本上是每个项目都会使用的，只是使用的多少不同而已，
 * 一般情况下
 * 1）lang3包用作JDK的基础语言扩展
 *      lang3是Apache Commons 团队发布的工具包，要求jdk版本在1.5以上，
 *      相对于lang来说完全支持java5的特性，废除了一些旧的API。
 *      该版本无法兼容旧有版本，于是为了避免冲突改名为lang3
 *      Apache的Lang功能实在是太实用了，它的很多工具类都是我们在开发过程中经常会用到的，
 *      虽然采用JDK的原始类也可以实现，但会花费更多的精力，
 *      而且Lang的更新频度很高，用它时不用担心会有太多的Bug
 *      1）字符串操作工具类
 *          JDK提供了String类，也提供了一些基本的操作方法，
 *          但是要知道String类在项目中是应用最多的类，
 *          这也预示着JDK提供的String工具不足以满足开发需求，
 *          Lang包弥补了这个缺陷，
 *          它提供了诸如
 *              StringUtils（基本的String操作类）、
 *              StringEscapeUtils（String的转义工具）、
 *              RandomStringUtils（随机字符串工具）等非常实用的工具
 *      2）Object工具类
 *          每个类都有equals、hashCode、toString方法，
 *          如果我们自己编写的类需要覆写这些方法，就需要考虑很多的因素了，特别是equals方法
 *      3）可变的基本类型
 *          基本类型都有相应的包装类型，
 *          但是包装类型不能参与加、减、乘、除运算，要运算还得转化为基本类型
 *      4）其他Utils工具
 * 2）BeanUtils JavaBean的操作工具包，
 *      不仅可以实现属性的拷贝、转换等，还可以建立动态的Bean，甚至建立一些自由度非常高的Bean
 *      1）属性拷贝
 *          在分层开发时经常会遇到PO（Persistence Object）和VO（Value Object）之间的转换问题，
 *          有多种方法可以解决之，比如自己写代码PO.setXXX(VO.getXXX())，但是在属性较多的时候容易出错，
 *          最好的办法就是使用BeanUtils来操作
 *      2）动态Bean和自由Bean
 *          定义一个Bean必然会需要一个类，比如User、Person等，而且还必须在编译期定义完毕，生成.class文件，
 *          虽然Bean是一个有固定格式的数据载体，严格要求确实没错，但在某些时候这限制了Bean的灵活性，
 *          比如要在运行期生成一个动态Bean，或者在需要生成无固定格式的Bean时可以使用BeanUtils包解决该问题
 *      3）转换器
 *          期望把一个Bean的所有String类型属性在输出之前都加上一个前缀
 *          一个一个进行属性过滤
 *          或者使用反射来检查属性类型是否是String，然后加上前缀
 *          这样是可以解决，但不优雅
 *          可以使用BeanUtils包解决
 * 3）Collections用作集合扩展，
 *      1）Bag
 *          Bag是Collections中的一种，它可以容纳重复元素，
 *          与List的最大不同点是它提供了重复元素的统计功能，
 *          比如一个盒子中有100个球，现在要计算出蓝色球的数量，使用Bag就很容易实现
 *      2）lazy系列
 *          在集合中的元素被访问时它才会生成，这也就涉及一个元素的生成问题了，可通过Factory的实现类来完成
 *      3）双向Map
 *          JDK中的Map要求键必须唯一，
 *          而双向Map（Bidirectory Map）则要求键、值都必须唯一，
 *          也就是键值是一一对应的，
 *          此类Map的好处就是既可以根据键进行操作，也可以反向根据值进行操作
 * 4）DBCP用作数据库连接池等
 */
public class Fl {

    public static void main(String[] args) {
        lang3();
        mutable();
    }
    /**
     * 字符串操作工具类
     */
    public static void lang3(){
        String str="abcdawdaw";
        //判空
        System.err.println(StringUtils.isEmpty(str));
        //a出现次数
        System.err.println(StringUtils.countOccurrencesOf(str,"a"));
        //随机生成，长度为10的仅字母的字符串
        System.err.println(RandomStringUtils.randomAlphabetic(10));
        //随机生成，长度为10的ASCII字符串
        System.err.println(RandomStringUtils.randomAscii(10));
    }

    /**
     * 可变的基本类型
     */
    public static void mutable(){
        //声明一个可变的int类型
        MutableInt mi=new MutableInt(10);
        //mi加10
        mi.add(10);
        System.err.println(mi);
        //自加1
        mi.increment();
        System.err.println(mi);
    }

}

/**
 * Object工具类
 */
class PersonFl{
    private String name;
    private int age;

    @Override
    public String toString() {
        //自定义输出格式
        return new ToStringBuilder(this)
                .append("姓名",name)
                .append("年龄",age)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonFl personFl = (PersonFl) o;
        //只有姓名相同，就认为两个对象相等
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(name, personFl.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        //自定义hashCode
        return HashCodeBuilder.reflectionHashCode(this);
    }

}
