package indi.ikun.spring.basejava.codequality.chapter05;

/**
 * 68.频繁插入和删除时使用LinkedList
 *
 * 1.插入元素
 *  LinkedList是一个双向链表，它的插入只是修改相邻元素的next和previous引用
 *  把自己插入到链表，然后再把前节点的next和后节点的previous指向自己
 *
 *  ArrayList插入元素涉及到拷贝其他无关的元素，效率会影响
 *  经过作者实际测试得，LinkedList的插入效率比ArrayList快50倍以上
 *
 * 2.删除元素
 *  ArrayList提供了删除指定位置上的元素、删除指定值元素、删除一个下标范围内的元素集等删除动作，三者的实现原理基本相似，都是找到索引位置，然后删除
 *  删除的index位置后的元素都向前移动了一位，最后一个位置空出来了，这又是一次数组拷贝，和插入一样，如果数据量大，删除动作必然会暴露出性能和效率方面的问题。
 *  ArrayList其他的两个删除方法与此相似
 *
 *  LinkedList提供了非常多的删除操作，比如删除指定位置元素、删除头元素等，与之相关的poll方法也会执行删除动作
 *  双向链表的标准删除算法，没有任何耗时的操作，全部是引用指针的变更，效率自然高了。
 *  在实际测试中得知，处理大批量的删除动作，LinkedList比ArrayList快40倍以上
 *
 * 3.修改元素
 *  修改元素值，在这一点上LinkedList输给了ArrayList，
 *  这是因为LinkedList是顺序存取的，因此定位元素必然是一个遍历过程，效率大打折
 *  LinkedList这种顺序存取列表的元素定位方式会折半遍历，这是一个极耗时的操作。
 *  而ArrayList的修改动作则是数组元素的直接替换，简单高效。
 *  在修改动作上，LinkedList比ArrayList慢很多，特别是要进行大量的修改时，两者完全不在一个数量级
 *
 *一个实时交易的系统，即使写作操再少，使用LinkedList也比ArrayList合适，
 * 因为此类系统是争分夺秒的，多N个毫秒可能就会造成交易数据不准确；
 *
 * 而对于一个批量系统来说，几十毫秒、几百毫秒，甚至是几千毫秒的差别意义都不大，
 * 这时是使用LinkedList还是ArrayList就看个人爱好了，
 * 当然，如果系统已经处于性能临界点了那就必须使用LinkedList
 *
 * 4.写操作 add
 * 两者在增加元素时性能上基本没有什么差别。
 * 区别只是在增加时LinkedList生成了一个Entry元素，其previous指向倒数第二个Entry，next置空；
 * 而ArrayList则是把元素追加到了数组中而已，
 * 两者的性能差别非常微小
 *
 */
public class Cp {

}
