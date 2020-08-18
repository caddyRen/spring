package indi.ikun.spring.basejava.codequality.chapter01;


/**
 * 5.别让null值和空值威胁到变长方法
 */
public class Ae {

    public void methodA(String str, Integer... is) {

    }

    public void methodA(String str, String... is) {

    }

    public static void main(String[] args) {
        Ae ad = new Ae();
        ad.methodA("Hello", 0);
        ad.methodA("Hello", "world");
        //违反KISS原则（Keep it simple stupid 懒人原则），按照KISS原则设计的方法应该很容易调用，严禁出现这种情况
//        ad.methodA("Hello");
        //null没有类型，两个方法都符合调用请求，但是不知道调用哪一个，
        //调用者不应该隐藏实参类型（不应该用null）
        //这种情况，调用者需要猜测调用哪个方法，被调用者也可能产生内部逻辑混乱的情况
//        ad.methodA("Hello", null);
        //优化
        String[] str=null;
        ad.methodA("Hello", str);
    }
}