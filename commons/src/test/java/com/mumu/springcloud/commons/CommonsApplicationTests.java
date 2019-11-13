package com.mumu.springcloud.commons;

import lombok.extern.slf4j.Slf4j;
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

    /**
     *@Author caddyR
     *@Description //冒泡排序，值与下标同时排序
     *@Date 2019-09-26 19:44
     *@Param []
     *@return void
    **/
    @Test
    public void bubbleSort(){
        int[] param={11,2,4,2,9,11,8,32,11,33,1};
        for(int i=1;i<=param.length;i++){
            for(int j=0;j<param.length-i;j++){
                if(param[j]>param[j+1]){
                    swap(param,j,j+1);
                }
                serr(param);
            }
            System.err.println();
        }
    }
    /**
     *@Author caddyR
     *@Description //快速排序
     *@Date 2019-09-26 19:44
     *@Param []
     *@return void
    **/
    @Test
    public void quickSortTest(){
        int[] param={7,2,4,2,9,12,6,32,7,33,8};
        quickSort(param,0,param.length-1);

    }

    /**
     *@Author caddyR
     *@Description //比较的是值，排序的是数组下标
     *@Date 2019-09-27 11:31
     *@Param [arr, left, right]
     *@return void
    **/
    public void quickSort(int[] arr,int left,int right){
        int pivot=arr[left];
        int i,j;
        i=left;
        j=right;
        if(i>j){
            return;
        }else{
            while(i<j){
                while(i<j&&pivot<=arr[j]){
                    j--;
                }
                while(i<j&&pivot>=arr[i]){
                    i++;
                }
                if(i<j){
                    swap(arr,i,j);
                    serr(arr);
                    i++;
                    j--;
                }
            }
            arr[left]=arr[i];
            arr[i]=pivot;
            quickSort(arr,left,i-1);
            quickSort(arr,i+1,right);
        }

    }





    public void serr(int[] param){
        for(int m=0;m<param.length;m++){
            System.err.print(param[m]);
            if(m<param.length-1){
                System.err.print(",");
            }
        }
        System.err.println();

    }
    public void swap(int[] param,int i,int j){
        int temp=param[j];
        param[j]=param[i];
        param[i]=temp;
    }


}
