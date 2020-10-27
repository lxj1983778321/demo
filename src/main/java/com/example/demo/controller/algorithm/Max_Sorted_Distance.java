package com.example.demo.controller.algorithm;

/**
 * @Description
 * 字典序算法：
 * 原数组排序后相邻的最大差值
 * 步骤：
 *
 * @Author lixingjian
 * @DATE 2020/7/21 10:02
 * @Version 1.0
 **/
public class Max_Sorted_Distance {

    public static int getMaxSortedDistance(int[] array){
        //1. 确定最大值和最小值
        int max = array[0];
        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            max = max>array[i]?max:array[i];
            min = min<array[i]?min:array[i];
        }
        //初始化桶
        int interval = max - min;
        if(interval == 0){
            return 0;
        }
        int bucketNum = array.length;
        Bucket[] buckets = new Bucket[bucketNum];
        for (int i = 0; i < bucketNum; i++) {
            buckets[i] = new Bucket();
        }
        //遍历原始数组确定桶的最大值和最小值
        for (int i = 0; i < array.length; i++) {
            int index = ((array[i]-min) * (bucketNum-1)/interval);
            if(buckets[index].min == null || buckets[index].min > array[i]){
                buckets[index].min = array[i];
            }
            if(buckets[index].max == null || buckets[index].max < array[i]){
                buckets[index].max = array[i];
            }
        }
        //遍历桶，找到最大差值
        int leftMax = buckets[0].max;
        int maxDistance = 0;
        for (int i = 0; i < buckets.length; i++) {
            if(buckets[i].min == null){
                continue;
            }
            if(buckets[i].min - leftMax > maxDistance){
                maxDistance = buckets[i].min - maxDistance;
            }
            leftMax = buckets[i].max;
        }
        return maxDistance;
    }
}
class Bucket{
    Integer max;
    Integer min;
}
