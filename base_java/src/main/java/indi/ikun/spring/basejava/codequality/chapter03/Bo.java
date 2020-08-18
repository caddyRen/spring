package indi.ikun.spring.basejava.codequality.chapter03;


/**
 * 40.让多重继承成为现实
 * Java中提供的内部类可以曲折地实现多重继承
 *
 * 多重继承指的是一个类可以同时从多于一个的父类那里继承行为与特征，
 * 按照这个定义来看，我们的儿子类、女儿类都实现了从父亲类、母亲类那里所继承的功能，
 * 应该属于多重继承。这要完全归功于内部类
 */
public class Bo {

}
interface FatherBo{
    int strong();
}
interface MotherBo{
    int kind();
}
class FatherBoImpl implements FatherBo{
    @Override
    public int strong() {
        return 8;
    }
}
class MotherBoImpl implements MotherBo{
    @Override
    public int kind() {
        return 8;
    }
}
class SonBo extends FatherBoImpl implements MotherBo{
    @Override
    public int strong() {
        return super.strong()+1;
    }

    @Override
    public int kind() {
        return new MotherSpecial().kind();
    }
    //内部类继承母亲
    private class MotherSpecial extends MotherBoImpl{
        public int kind(){
            return super.kind()-1;
        }
    }
}

class Daughter extends MotherBoImpl implements FatherBo{
    @Override
    public int strong() {
        return new FatherSpecial().strong();
    }

    @Override
    public int kind() {
        return super.kind()+1;
    }
    //内部类继承父亲
    private class FatherSpecial extends FatherBoImpl{
        public int strong(){
            return super.strong()-2;
        }
    }
}
