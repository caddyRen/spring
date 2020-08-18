package indi.ikun.spring.basejava.codequality.chapter05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 76.使用shuffle打乱列表
 *
 *  1.可以用在程序的“伪装”上
 *      游戏中的打怪、修行、群殴时宝物的分配策略
 *  2.可以用在抽奖程序中
 *      年会的抽奖程序，先使用shuffle把员工排序打乱，每个员工的中奖几率就是相等的了，然后就可以抽取第一名、第二名
 *  3.可以用在安全传输方面
 *      发送端发送一组数据，先随机打乱顺序，然后加密发送，接收端解密，然后自行排序，即可实现即使是相同的数据源，也会产生不同密文的效果，加强了数据的安全性
 *
 * 在网站上我们经常会看到关键字云（Word Cloud）和标签云（Tag Cloud），
 * 用于表明这个关键字或标签是经常被查阅的，而且还可以看到这些标签的动态运动，
 * 每次刷新都会有不一样的关键字或标签，让浏览者觉得这个网站的访问量非常大，短短的几分钟就有这么多的搜索量
 */
public class Cy {
    public static void main(String[] args) {
        int tagCloudNum = 4;
        List<String> tagClouds = new ArrayList<>(tagCloudNum);
        tagClouds.add("123");
        tagClouds.add("456");
        tagClouds.add("789");
        tagClouds.add("000");
        //使用随机数打乱,每次都产生不同的顺序
        Random random = new Random();
        for (int i = 0; i < tagCloudNum; i++) {
            int randomPosition = random.nextInt(tagCloudNum);
            String temp = tagClouds.get(i);
            tagClouds.set(i, tagClouds.get(randomPosition));
            tagClouds.set(randomPosition, temp);
        }
        for (String string : tagClouds) {
            System.err.println(string);
        }

        //优化
        for (int i = 0; i < tagCloudNum; i++) {
            int randomPosition = random.nextInt(tagCloudNum);
            Collections.swap(tagClouds, i, randomPosition);
        }
        for (String string : tagClouds) {
            System.err.println(string);
        }

        //优化 一句话，即可打乱一个列表的顺序
        Collections.shuffle(tagClouds);
        for (String string : tagClouds) {
            System.err.println(string);
        }
    }


}

