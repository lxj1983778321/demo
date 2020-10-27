package com.example.demo.controller.algorithm;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *@Description TODO
 *@Author lixingjian
 *@DATE 2020/9/11 15:13
 *@Version 1.0
 * 实现一个函数：
 * 输入：整型数组arr，窗口大小为w
 * 输出：一个长度为n-w+1的数组res，res[i]表示每一种窗口状态下的最大值
 *要求：时间复杂度为o(N)
 * 例如：
 * 数组【4，3，5，4，3，3，6，7】，窗口大小为3时：
 * 【4 3 5】 4 3 3 6 7 窗口最大值为5
 * 4 【3 5 4】 3 3 6 7 窗口最大值为5
 * 4 3 【5 4 3】 3 6 7 窗口最大值为5
 * 4 3 5 【4 3 3】 6 7 窗口最大值为4
 * 4 3 5 4 【3 3 6】 7 窗口最大值为6
 * 4 3 5 4 3 【3 6 7】 窗口最大值为7
 **/
public class MaxWindow {

    public static int[] getMaxWindow(int[] arr,int w){
       if(arr == null || w < 1 || arr.length < w){
           return null;
       }
        //永远从队尾插入值，队头永远存储当前最大值
       LinkedList<Integer> qmax = new LinkedList<Integer>();
       int[] res = new int[arr.length - w + 1];//存储最终结果
       int index = 0;//res数组的下标
        for (int i = 0; i < arr.length; i++) {
            //如果队列不为空 且 当前队列的队尾值比当前arr[i]的值小，则弹出队尾值,直到符合由大到小的排序规则
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]){
                qmax.pollLast();
            }
            //如果队列为空，则直接加入
            qmax.addLast(i);
            //判断下标是否过期
            if (qmax.peekFirst() == i - w){
                qmax.pollFirst();
            }
            //如果到达最大窗口，则存储最大值,因为数组下标从0开始，所以为 w-1
            if (i >= w - 1){
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr= new int[]{4,3,5,4,3,3,6,7};
        System.out.println(Arrays.toString(getMaxWindow(arr,3)));
    }
}
