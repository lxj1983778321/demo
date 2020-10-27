package com.example.demo.controller.algorithm;

import java.util.Arrays;

/**
 * @Description TODO
 * @Author lixingjian
 * @DATE 2020/7/13 16:59
 * @Version 1.0
 **/
public class MaoPaoSort {

    public static void sort_plus(int[] arr) {
        //无序边界，默认全部无序
        int sortBorded = arr.length-1;
        //最后一次下标交换位置，默认为0
        int lastExchangeIndex = 0;
        for (int i = 0; i < arr.length-1; i++) {
            //是否有序，默认为有序
            boolean isSort = true;
            for (int j = 0; j < sortBorded; j++) {
                if(arr[j] > arr[j+1]){
                    //产生了交换，标识为无序
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    lastExchangeIndex = j;
                    isSort = false;
                }
            }
            sortBorded = lastExchangeIndex;
            if(isSort){
                break;
            }
        }
    }

    public static void main(String[] args){
        int[] array = new int[]{3,4,2,1,5,6,7,8};
        sort_plus(array);
        System.out.println(Arrays.toString(array));
    }
}
