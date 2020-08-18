package indi.ikun.spring.basejava.codequality.chapter07;

/**
 * 98.建议采用的顺序是List<T>、List<?>、List<Object>
 *  List<T>、List<?>、List<Object>这三者都可以容纳所有的对象，
 *  但使用的顺序应该是首选List<T>，次之List<?>，最后选择List<Object>
 *      1)List<T>是确定的某一个类型
 *          List<T>表示的是List集合中的元素都为T类型，具体类型在运行期决定；
 *          List<?>表示的是任意类型，与List<T>类似，
 *          而List<Object>则表示List集合中的所有元素为Object类型，因为Object是所有类的父类，
 *          所以List<Object>也可以容纳所有的类类型，
 *          从这一字面意义上分析，List<T>更符合习惯：
 *              编码者知道它是某一个类型，只是在运行期才确定而已
 *     2）List<T>可以进行读写操作
 *          List<T>可以进行诸如add、remove等操作，
 *              因为它的类型是固定的T类型，在编码期不需要进行任何的转型操作
 *          List<?>是只读类型的，不能进行增加、修改操作，
 *              因为编译器不知道List中容纳的是什么类型的元素，也就无法校验类型是否安全了，
 *              而且List<?>读取出的元素都是Object类型的，
 *              需要主动转型，
 *              所以它经常用于泛型方法的返回值。
 *              注意，List<?>虽然无法增加、修改元素，
 *              但是却可以删除元素，比如执行remove、clear等方法，
 *              那是因为它的删除动作与泛型类型无关。
*           List<Object>也可以读写操作，
 *              但是它执行写入操作时需要向上转型（Upcast），
 *              在读取数据后需要向下转型（Downcast），
 *              而此时已经失去了泛型存在的意义了
 *  推而广之
 *  Dao<T>应该比Dao<?>、Dao<Object>更先采用，
 *  Desc<Person>则比Desc<?>、Desc<Object>更优先采用
 */
public class Dt {

    public static void main(String[] args) {
    }

}

