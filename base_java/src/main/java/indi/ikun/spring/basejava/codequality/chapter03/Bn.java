package indi.ikun.spring.basejava.codequality.chapter03;

/**
 * 40.匿名类的构造函数很特殊
 *
 */
public class Bn {
    private int i,j,result;
    public Bn(){}
    public Bn(int _i,int _j){
        i=_i;
        j=_j;
    }
    protected void setOperator(Ops _ops){
        result=_ops.equals(Ops.ADD)?i+j:i-j;
    }
    public int getResult(){
        return result;
    }

}
enum Ops{ADD,SUB}




class ClientBn{
    public static void main(String[] args) {
        Bn bn = new Bn(1, 2) {
            {
                setOperator(Ops.ADD);
            }
        };
        System.err.println(bn.getResult());
    }
}


/**
 * 模拟匿名类
 *  匿名类的构造函数特殊处理机制，
 *  一般类（也就是具有显式名字的类）的所有构造函数默认都是调用父类的无参构造的，
 *  而匿名类因为没有名字，只能由构造代码块代替，也就无所谓的有参和无参构造函数了，
 *  它在初始化时直接调用了父类的同参数构造，然后再调用了自己的构造代码块
 */
class AddBn extends Bn{
    {
        setOperator(Ops.ADD);
    }

    public AddBn(int _i, int _j) {
        super(_i,_j);
    }
}