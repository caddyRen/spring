package indi.ikun.spring.designpattern.pattern.creation.builder.builder;

import indi.ikun.spring.designpattern.pattern.creation.builder.product.House;

/**
 * @Description: 抽象建造者
 * @Author caddy
 * @date 2020-02-11 11:38:35
 * @version 1.0
 */
public abstract class HouseBuilder {

    House house=new House();

    public abstract void buildBasic();
    public abstract void buildWalls();
    public abstract void roofed();

    /**
     * @Description: 建房步骤
     * @Author caddy
     * @date 2020-02-11 11:41:33
     * @version 1.0
     */
    public House build(){
        return house;
    }

}
