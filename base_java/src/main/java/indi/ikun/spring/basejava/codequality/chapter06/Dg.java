package indi.ikun.spring.basejava.codequality.chapter06;

/**
 * 85.小心switch带来的空值异常
 *
 * assert默认时不启用的 设置一下jvm的参数，参数是-enableassertions或者-ea
 *
 */
public class Dg {

    public static void main(String[] args) {
        /**
         * java.lang.NullPointerException
         */
        doSports1(null);
        doSports(null);
    }

    /**
     * 目前Java中的switch语句只能判断
     * byte、short、char、int类型（JDK 7已经允许使用String类型），这是Java编译器的限制。
     * 枚举类型 编译时，编译器判断出switch语句后的参数是枚举类型，
     * 然后就会根据枚举的排序值继续匹配
     */
    public static void doSports(SeasonDg seasonDf){
        //复习断言，不应该使用断言来控制业务
        assert null!=seasonDf:"枚举参数不能为null";
        //推荐
        if(null==seasonDf)
            throw new IllegalStateException("枚举参数不能为null");
        switch (seasonDf){
            case Spring:
                System.err.println("春天放风筝");
                break;
            case Summer:
                System.err.println("夏天游泳");
                break;
            case Autumn:
                System.err.println("秋天打猎");
                break;
            case Winter:
                System.err.println("冬天溜冰");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + seasonDf);
        }
    }

    /**
     * doSports方法内的switch与下面代码相同
     *
     * switch语句是先计算season变量的排序值，
     * 然后与枚举常量的每个排序值进行对比的。
     * 在我们的例子中season变量是null值，
     * 无法执行ordinal方法，于是报空指针异常
     * @param seasonDf
     */
    public static void doSports1(SeasonDg seasonDf){
        //复习断言，不应该使用断言来控制业务,断言可以用于调试
        assert null!=seasonDf:"枚举参数不能为null";
        //推荐
        if(null==seasonDf)
            throw new IllegalStateException("枚举参数不能为null");

        //null.ordinal()空指针
        switch (seasonDf.ordinal()){
//            case SeasonDg.Spring.ordinal():
//                System.err.println("春天放风筝");
//                break;
//            case SeasonDg.Summer.ordinal():
//                System.err.println("夏天游泳");
//                break;
//            case SeasonDg.Autumn.ordinal():
//                System.err.println("秋天打猎");
//                break;
//            case SeasonDg.Winter.ordinal():
//                System.err.println("冬天溜冰");
//                break;
            default:
                throw new IllegalStateException("Unexpected value: " + seasonDf.ordinal());
        }
    }
}
enum SeasonDg {
    Spring, Summer, Autumn, Winter;
}
