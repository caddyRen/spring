package indi.ikun.spring.basejava.codequality.chapter07;

/**
 * 99.严格限定泛型类型采用多重界限
 * 案例
 * 在公交车费优惠系统中，对部分人员（如工资低于2500元的上班族并且是站立着的乘客）车费打8折、
 * 注意这里的类型参数有两个限制条件：一为上班族；二为是乘客。
 * 具体到我们的程序中就应该是一个泛型参数具有两个上界（Upper Bound）
 *
 * 在Java的泛型中，可以使用“&”符号关联多个上界并实现多个边界限定，
 * 而且只有上界才有此限定，
 * 下界没有多重限定的情况
 * 多个下界，编码者可自行推断出具体的类型，
 * 比如“? super Integer”和“? extends Double”，
 * 可以更细化为Number类型了，
 * 或者Object类型了，
 * 无须编译器推断
 */
public class Du {

    public static void main(String[] args) {
        discount(new MeDu());
    }

    /**
     *使用“&”符号设定多重边界（Multi Bounds），
     * 指定泛型类型T必须是Staff和Passenger的共有子类型，
     * 此时变量t就具有了所有限定的方法和属性，要再进行判断就易如反掌了
     *
     *
     */
    public static <T extends StaffDu & PassengerDu> void discount(T t){
        if(t.getSalary()<2500&&t.isStanding()){
            System.err.println("八折");
        }
    }
}
interface StaffDu{
    int getSalary();
}
interface PassengerDu{
    boolean isStanding();
}

/**
 * “MeDu”这种类型的人物有很多，
 * 比如做系统分析师也是一个职员，也坐公交车，但他的工资实现就和我不同，
 * 再比如Boss级的人物，偶尔也坐公交车，对大老板来说他也只是一个职员，他的实现类也不同，
 * 也就是说如果我们使用“T extends MeDu”是限定不了需求对象的,可以考虑使用多重限定
 */
class MeDu implements StaffDu,PassengerDu{
    @Override
    public int getSalary() {
        return 2000;
    }

    @Override
    public boolean isStanding() {
        return true;
    }
}
