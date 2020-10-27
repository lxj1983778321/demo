package com.example.demo.controller.algorithm;

import java.util.Arrays;

/**
 * @Description
 * 快速排序---》递归+单边循环法
 * @Author lixingjian
 * @DATE 2020/7/9 10:33
 * @Version 1.0
 **/
public class QuickSort {

    public static void quickSort(int[] array,int startIndex,int endIndex){
        if(startIndex >= endIndex){
            return;
        }
        //获取基准元素
        int pivotIndex = parttion(array,startIndex,endIndex);
        quickSort(array,startIndex,pivotIndex-1);
        quickSort(array,pivotIndex+1,endIndex);
    }
    //获取基准元素，并对数组进行排序
    private static int parttion(int[] array,int startIndex,int endIndex){
        //取第一个位置元素作为基准元素
        int pivot = array[startIndex];
        //基准元素下标
        int mark = startIndex;
        for (int i = startIndex+1; i <= endIndex; i++) {
            if(array[i] < pivot){
                //指针移动到下一个位置
                mark++;
                //交换
                int temp = array[i];
                array[i] = array[mark];
                array[mark] = temp;
            }
        }
        array[startIndex] = array[mark];
        array[mark] = pivot;
        return mark;
    }

    public static void main(String[] args) {
        int[] array = new int[]{3,1,4,7,2,9,8,0};
        quickSort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));

    }
}
