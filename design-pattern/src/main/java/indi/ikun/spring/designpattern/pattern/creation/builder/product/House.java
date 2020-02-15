package indi.ikun.spring.designpattern.pattern.creation.builder.product;

/**
 * @Description: 产品
 * @Author caddy
 * @date 2020-02-11 11:35:44
 * @version 1.0
 */
public class House {
    String height;
    String name;

    @Override
    public String toString() {
        return "House{" +
                "height='" + height + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
