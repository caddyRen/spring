package indi.ikun.spring.designpattern.pattern.behavior.strategy;

import java.util.Arrays;
import java.util.Comparator;

public class Strategy {
    public static void main(String[] args) {
        Integer[] data = {11, 2, 43, 33, 24, 3};

        Comparator<Integer> comparable = new Comparator<Integer>() {

            //升序
            @Override
            public int compare(Integer var1, Integer var2) {
                if (var1.compareTo(var2) > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };
        Arrays.sort(data, comparable);

        System.err.println(Arrays.toString(data));


        Arrays.sort(data, (var1, var2) -> {
            //将序
            if (var1.compareTo(var2) > 0) {
                return -1;
            } else {
                return 1;
            }
        });

        System.err.println(Arrays.toString(data));

    }
}
