package indi.ikun.spring.basejava.codequality.chapter05;


import java.util.Arrays;

/**
 * 62.警惕数组的浅拷贝
 * 案例：
 * 第一个箱子里有赤橙黄绿青蓝紫7色气球，
 * 现在希望在第二个箱子中也放入7个气球，其中最后一个气球改为蓝色，也就是赤橙黄绿青蓝蓝7个气球，
 * 那我们很容易就会想到第二个箱子中的气球可以通过拷贝第一个箱子中的气球来实现，毕竟有6个气球是一样的
 * <p>
 * 浅拷贝: 基本类型是直接拷贝值,其他都是拷贝引用地址
 * 数组的clone方法
 * Arrays.copyOf方法
 * 集合的clone方法
 */
public class Cj {
    public static void main(String[] args) {
        int ballonNum = 7;
        BalloonCj[] box1 = new BalloonCj[ballonNum];

        for (int i = 0; i < ballonNum; i++) {
            box1[i] = new BalloonCj(i, ColorCj.values()[i]);
        }

        /**
         * 解决方案
         */
        BalloonCj[] box3 = new BalloonCj[ballonNum];
        for (int i = 0; i < ballonNum; i++) {
            BalloonCj balloonCj = new BalloonCj(box1[i].getId(), box1[i].getColor());
            if (i == 6) {
                balloonCj.setColor(ColorCj.Blue);
            }
            box3[i] = balloonCj;
        }
        for (BalloonCj balloonCj : box1) {
            System.err.println(balloonCj);
        }
        System.err.println("----------------------");
        BalloonCj[] box2 = Arrays.copyOf(box1, box1.length);
        /**
         * box1[6]颜色也会被改变，因为数组拷贝copyOf()方法是浅拷贝，
         * 与序列化的浅拷贝完全相同：
         *      基本类型是直接拷贝值
         *      其他都是拷贝引用地址
         * 数组的clone方法也是与此相同的，同样是浅拷贝，
         * 而且集合的clone方法也都是浅拷贝
         */
        box2[6].setColor(ColorCj.Blue);


        for (BalloonCj balloonCj : box1) {
            System.err.println(balloonCj);
        }
    }
}

enum ColorCj {
    Red, Orange, Yellow, Green, Indigo, Blue, Violet;
}

class BalloonCj {
    private int id;
    private ColorCj color;

    public BalloonCj(int id, ColorCj color) {
        this.id = id;
        this.color = color;
    }

    @Override
    public String toString() {
        return "BalloonCj{" +
                "id=" + id +
                ", color=" + color +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ColorCj getColor() {
        return color;
    }

    public void setColor(ColorCj color) {
        this.color = color;
    }
}
