package indi.ikun.spring.basejava.codequality.chapter03;

/**
 * 47.在equals中使用getClass进行类型判断
 * 使用instanceof关键字检查子类e1是否是父类PersonBu的实例，
 * 由于两者存在继承关系，那结果当然是true了
 * 但是反过来就不成立了，e1或e2可不等于p1
 * 这也是违反对称性原则的一个典型案例
 *
 * p1与e1、e2相等，但e1竟然与e2不相等，似乎一个简单的等号传递都不能实现。
 * e1.equals(e2)调用的是子类Employee的equals方法，不仅仅要判断姓名相同，还要判断工号是否相同，
 * 两者工号是不同的，不相等也是自然的了。
 * 等式不传递是因为违反了equals的传递性原则，
 * 传递性原则是指对于实例对象x、y、z来说，如果x.equals(y)返回true，y.equals(z)返回true，那么x.equals(z)也应该返回true
 * 这种情况发生的关键是父类使用了instanceof关键字，
 * 它是用来判断是否是一个类的实例对象的，这很容易让子类“钻空子”。
 * 想要解决也很简单，使用getClass来代替instanceof进行类型判断
 * 考虑到Employee也有可能被继承，也需要把它的instanceof修改为getClass。
 * 总之，在覆写equals时建议使用getClass进行类型判断，而不要使用instanceof
 */
public class Bu {
    public static void main(String[] args) {
        EmployeeBu e1 = new EmployeeBu("张三",100);
        EmployeeBu e2 = new EmployeeBu("张三",1001);
        PersonBu p3=new PersonBu("张三");
        //调用父类equals
        System.err.println(p3.equals(e1));
        //调用子类equals
        System.err.println(e1.equals(p3));

        System.err.println(p3.equals(e2));
        System.err.println(e2.equals(p3));

        System.err.println(e1.equals(e2));
        System.err.println(e2.equals(e1));
    }
}

class EmployeeBu extends PersonBu{
    private int id;
    public EmployeeBu(String _name,int _id){
        super(_name);
        id=_id;
    }
    @Override
    public boolean equals(Object obj){
        if(obj!=null&&obj.getClass()==this.getClass()){
            EmployeeBu e=(EmployeeBu)obj;
            return super.equals(obj)&&e.getId()==id;

        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
class PersonBu{
    private String name;

    public PersonBu(String _name){
        name=_name;
    }
    @Override
    public boolean equals(Object obj){
        if(obj!=null&&obj.getClass()==this.getClass()){
            PersonBu p=(PersonBu)obj;
            if(p.getName()==null||name==null){
                return false;
            }else
                return name.equalsIgnoreCase(p.getName());

        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}