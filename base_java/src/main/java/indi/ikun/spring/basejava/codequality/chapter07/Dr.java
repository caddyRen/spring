package indi.ikun.spring.basejava.codequality.chapter07;

import java.util.List;

/**
 * 96.不同的场景使用不同的泛型通配符
 *
 * 如果一个泛型结构即用作“读”操作又用作“写”操作，不限定，使用确定的泛型类型即可，如List<E>
 *
 * Java泛型支持通配符（Wildcard），
 * 可以单独使用一个“？”表示任意类，
 * 也可以使用extends关键字表示某一个类（接口）的子类型，
 * 还可以使用super关键字表示某一个类（接口）的父类型
 *
 * 1)泛型结构只参与“读”操作则限定上界（extends关键字）
 * 2)泛型结构只参与“写”操作则限定下界（使用super关键字）
 *
 * 对于是要限定上界还是限定下界，
 * JDK的Collections.copy方法是一个非常好的例子，
 * 它实现了把源列表中的所有元素拷贝到目标列表中对应的索引位置上
 *
 * 源列表是用来提供数据的，所以src变量需要限定上界，带有extends关键字
 * 目标列表是用来写入数据的，所以dest变量需要界定上界，带有super关键字
 *  public static <T> void copy(List<? super T> dest, List<? extends T> src) {
 *         int srcSize = src.size();
 *         if (srcSize > dest.size())
 *             throw new IndexOutOfBoundsException("Source does not fit in dest");
 *
 *         if (srcSize < COPY_THRESHOLD ||
 *             (src instanceof RandomAccess && dest instanceof RandomAccess)) {
 *             for (int i=0; i<srcSize; i++)
 *                 dest.set(i, src.get(i));
 *         } else {
 *             ListIterator<? super T> di=dest.listIterator();
 *             ListIterator<? extends T> si=src.listIterator();
 *             for (int i=0; i<srcSize; i++) {
 *                 di.next();
 *                 di.set(si.next());
 *             }
 *         }
 *     }
 */
public class Dr {

    public static void main(String[] args) {

    }

    /**
     *不知道list到底存放的是什么元素，
     * 只能推断出是E类型的父类（当然，也可以是E类型，下同，不再赘述），
     * 但问题是E类型的父类是什么呢？无法再推断，只有运行时才知道，那么编码期就完全无法操作
     * 当然，你可以把它当作是Object类来处理，需要时再转换成E类型—这完全违背了泛型的初衷
     * 在这种情况下，“读”操作如果期望从List集合中读取数据就需要使用extends关键字了，
     * 也就是要界定泛型的上界
     */
    public static <E> void read(List<? super E> list){
        for (Object obj:list){
            //...
        }
    }
    /**
     * 推断出List集合中取出的是E类型的元素。
     * 具体是什么类型的元素就要等到运行时才能确定了，
     * 但它一定是一个确定的类型
     *
     * 比如read(Arrays.asList("A"))调用该方法时，
     * 可以推断出List中的元素类型是String，
     * 之后就可以对List中的元素进行操作了，
     * 如加入到另外的List<E>集合中，或者作为Map<E,V>的键等
     */
    public static <E> void read1(List<? extends E> list){
        for (E e:list){
            //...
        }
    }

    /**
     * 编译失败，失败的原因是list中的元素类型不确定，
     * 也就是编译器无法推断出泛型类型到底是什么，
     * 是Integer类型？
     * 是Double？
     * 还是Byte？
     * 这些都符合extends关键字的定义，
     * 由于无法确定实际的泛型类型，所以编译器拒绝了此类操作
     * 在此种情况下，只有一个元素是可以add进去的：
     * null值，这是因为null是一个万用类型，它可以是所有类的实例对象，所以可以加入到任何列表中
     */
    public static void write(List<? extends Number> list){
//        list.add(123);//编译失败
        //Object也不可以，因为Object不是Number的子类
//        list.add(new Object());//编译失败
        //null是一个万用类型，它可以是所有类的实例对象，所以可以加入到任何列表中
        list.add(null);
    }
    /**
     * 在这种“写”操作的情况下，使用super关键字限定泛型类型的下界才是正道
     * 甭管它是Integer类型的123，还是Float类型的3.14，都可以加入到list列表中，
     * 因为它们都是Number类型，这就保证了泛型类的可靠性
     */
    public static void write1(List<? super Number> list){
        list.add(123);
    }

}
