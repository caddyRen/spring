package indi.ikun.spring.basejava.codequality.chapter03;


/**
 * 38.使用静态内部类提高封装性
 * Java中的嵌套类（Nested Class）分为两种：
 * 静态内部类（也叫静态嵌套类，Static Nested Class）：内部类，并且是静态（static修饰）的即为静态内部类
 * 只有在是静态内部类的情况下才能把static修复符放在类前，其他任何时候static都是不能修饰类
 * 强了类的封装性
 * 提高了代码的可读性
 * 内部类（Inner Class）。
 *
 * 1.提高封装性。
 *      从代码位置上来讲，静态内部类放置在外部类内，
 *      其代码层意义就是：静态内部类是外部类的子行为或子属性，两者直接保持着一定的关系，
 *      比如在我们的例子中，看到HomeBl类就知道它是Bl的HomeBl信息
 * 2.提高代码的可读性。
 *      相关联的代码放在一起，可读性当然提高了
 * 3.形似内部，神似外部。
 *      静态内部类虽然存在于外部类内，
 *      而且编译后的类文件名也包含外部类（格式是：外部类+$+内部类），
 *      但是它可以脱离外部类存在，也就是说我们仍然可以通过new HomeBl()声明一个HomeBl对象，只是需要导入“Bl.HomeBl”而已
 *外部类和静态内部类之间有强关联关系（不是组合关系Composition），这仅仅表现在“字面”上，而深层次的抽象意义则依赖于类的设计
 *
 *静态内部类与普通内部类有什么区别
 * 1.静态内部类不持有外部类的引用
 *      在普通内部类中，我们可以直接访问外部类的属性、方法，
 *      即使是private类型也可以访问，这是因为内部类持有一个外部类的引用，可以自由访问。
 *      而静态内部类，则只可以访问外部类的静态方法和静态属性（如果是private权限也能访问，这是由其代码位置所决定的），其他则不能访问
 * 2.静态内部类不依赖外部类
 *      普通内部类与外部类之间是相互依赖的关系，内部类实例不能脱离外部类实例，也就是说它们会同生同死，一起声明，一起被垃圾回收器回收。
 *      而静态内部类是可以独立存在的，即使外部类消亡了，静态内部类还是可以存在的
 * 3.普通内部类不能声明static的方法和变量
 *      普通内部类不能声明static的方法和变量，注意这里说的是变量，
 *      常量（也就是final static修饰的属性）还是可以的，而静态内部类形似外部类，没有任何限制
 *
 *
 */
public class Bl {
    //姓名
    private String name;
    //家庭
    private HomeBl home;

    public static class HomeBl {
        //地址
        private String address;
        //电话
        private String tel;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public HomeBl(String _address, String _tel) {
            address = _address;
            tel = _tel;

        }
    }

    public Bl(String _name) {
        name = _name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HomeBl getHome() {
        return home;
    }

    public void setHome(HomeBl home) {
        this.home = home;
    }

    public static void main(String[] args) {
        Bl bl = new Bl("李四");
        bl.setHome(new Bl.HomeBl("广州","021"));
    }
}

