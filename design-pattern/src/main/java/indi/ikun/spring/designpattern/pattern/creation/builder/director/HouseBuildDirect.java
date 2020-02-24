package indi.ikun.spring.designpattern.pattern.creation.builder.director;

import indi.ikun.spring.designpattern.pattern.creation.builder.builder.HouseBuilder;
import indi.ikun.spring.designpattern.pattern.creation.builder.product.House;

/**
 * @Description: 建房指挥者
 * @Author caddy
 * @date 2020-02-11 11:47:41
 * @version 1.0
 */
public class HouseBuildDirect {

    public void setHouseBuilder(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    HouseBuilder houseBuilder;

    public HouseBuildDirect(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    public House build(){
        houseBuilder.buildBasic();
        houseBuilder.buildWalls();
        houseBuilder.roofed();
        return houseBuilder.build();
    }
}
