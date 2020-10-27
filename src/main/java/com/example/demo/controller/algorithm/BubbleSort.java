package com.example.demo.controller.algorithm;

import java.util.Arrays;

/**
 * @Description 进化版冒泡排序
 * @Author muyou
 * @DATE 2020/7/9 9:36
 * @Version 1.0
 **/
public class BubbleSort {

    public static void sort(int[] array){
        //最后一次交换下标
        int lastExchangeIndex = 0;
        //有序边界-->默认为全部有序
        int sortBorder  = array.length-1;
        for (int i = 0; i < array.length-1; i++) {
            Boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                if(array[j] < array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    //交换证明数组无序
                    isSorted = false;
                    //最后一个元素交换的下标
                    lastExchangeIndex = j;
                }
            }
            //重新确定有序边界
            sortBorder = lastExchangeIndex;
            //是否有序
            if(isSorted){
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{5,3,4,2,7,8,9,1};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
