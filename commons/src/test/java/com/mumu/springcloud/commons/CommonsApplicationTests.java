package com.mumu.springcloud.commons;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName CommonsApplicationTests
 * @Description TODO
 * @Author caddyR
 * @Date 2019-07-16 11:13
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonsApplicationTests {


    @Test
    public void test() {
        //冒泡排序
        int[] arr = {11, 7, 4, 3, 6, 29, 22, 3, 8, 2};
        int temp;
        for (int m = 1; m <arr.length; m++) {
            System.err.print(m+":");
            for (int i = 0; i < arr.length-m; i++) {
                if (arr[i] > arr[i + 1]) {
                    temp = arr[i + 1];
                    arr[i + 1] = arr[i];
                    arr[i] = temp;
                }
                System.err.print(i+",");
            }

            System.err.println();
            for (int j = 0; j < arr.length; j++) {
                System.err.print(arr[j]);
                if (j == (arr.length - 1)) {
                    System.err.println(";");
                } else {
                    System.err.print(",");
                }
            }

        }
    }
}
