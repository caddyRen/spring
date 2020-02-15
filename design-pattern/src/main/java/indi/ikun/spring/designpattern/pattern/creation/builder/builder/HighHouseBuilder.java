package indi.ikun.spring.designpattern.pattern.creation.builder.builder;

/**
 * @Description: 高房子建造者
 * @Author caddy
 * @date 2020-02-11 11:43:06
 * @version 1.0
 */
public class HighHouseBuilder extends HouseBuilder {

    @Override
    public void buildBasic() {
        super.house.setName("高房子");
    }

    @Override
    public void buildWalls() {
        super.house.setHeight("10m");
    }

    @Override
    public void roofed() {
        System.err.println(super.house.getName()+" 封顶");
    }
}
