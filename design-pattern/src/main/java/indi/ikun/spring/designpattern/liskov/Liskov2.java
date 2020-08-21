package indi.ikun.spring.designpattern.liskov;

import java.util.HashMap;
import java.util.Map;

/**
 * 重载=子类自己的方法，跟父类没关系，只是方法名字相同，参数范围大于父类和返回值范围小于父类
 * 重写=改写父类方法 参数，返回值不能变
 */
public class Liskov2 {

    public static void main(String[] args) {
        HashMap m=new HashMap();
        Base2 base2=new Base2();
        base2.func(m);
        A3 a3=new A3();
        //由于子类重载了父类方法，如果子类没有重写父类，则调用的是父类的方法
        a3.func(m);
    }

}

class Base2{
    public Number func(HashMap map){
        System.err.println("父类方法被执行");
        return 1;
    }
}
class A3 extends Base2{

    public Integer func(Map map) {
        System.err.println("子类重载方法被执行");
        return 2;
    }

    //重写则执行此方法
//    @Override
//    public Number func(HashMap map) {
//        System.err.println("子类重写方法被执行");
//        return Integer.valueOf(2);
//    }

}

