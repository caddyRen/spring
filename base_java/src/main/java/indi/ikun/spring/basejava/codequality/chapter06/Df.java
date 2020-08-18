package indi.ikun.spring.basejava.codequality.chapter06;

/**
 * 84.使用构造函数协助描述枚举项
 *
 * 一般来说，我们经常使用的枚举项只有一个属性，
 *      即排序号，其默认值是从0、1、2……这一点我们非常熟悉。
 * 但是除了排序号外，枚举还有一个（或多个）属性：
 *      枚举描述，它的含义是通过枚举的构造函数，
 *      声明每个枚举项（也就是枚举的实例）必须具有的属性和行为
 *      这是对枚举项的描述或补充，目的是使枚举项表述的意义更加清晰准确
 *推荐大家在枚举定义中为每个枚举项定义描述，
 * 特别是在大规模的项目开发中，
 * 大量的常量项定义使用枚举项描述比在接口常量或类常量中增加注释的方式友好得多，简洁得多
 */
public class Df {
}

enum SeasonDf{
    Spring("春"),
    Summer("夏"),
    Autumn("秋"),
    Winter("冬");

    private String desc;

    SeasonDf(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}