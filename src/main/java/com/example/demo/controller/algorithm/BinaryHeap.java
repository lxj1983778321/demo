package com.example.demo.controller.algorithm;

import java.util.Arrays;

/**
 * @Description
 * 二叉堆---》最小二叉堆的构建
 * 逻辑物理结构：数组
 * 公式：
 * 假设父节点下标为：parent
 * 左孩子节点下表为：2*parent+1
 * 右孩子节点为：2*parent+2
 * @Author lixingjian
 * @DATE 2020/7/7 9:54
 * @Version 1.0
 **/
public class BinaryHeap {

    /**
     * 上浮调整
     */
    public static void upAndJust(int[] array){
        //最后一个非叶子结点的右孩子
        int childIndex = array.length-1;
        //最后一个非叶子结点(父节点)
        int parentIndex = (array.length-1)/2;
        //孩子节点的值
        int temp = array[childIndex];
        //孩子节点的值小于父节点---》则进行上浮调整
        while (childIndex>0 && temp < array[parentIndex]){
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex-1)/2;
        }
        array[childIndex]= temp;
    }

    /**
     *下沉调整
     * @param array 数组指针
     * @param parentIndex 父节点下标
     * @param length 数组长度
     */
    public static void downAndJust(int[] array,int parentIndex,int length){
        //父节点的值
        int temp = array[parentIndex];
        //左孩子节点下标
        int childIndex = 2*parentIndex+1;
        while (childIndex < length){
            //判断右孩子是否存在且右孩子的值小于左孩子的值，那么下标就定位到右孩子
            //找出左右孩子中最小的值
            if(childIndex+1<length && array[childIndex+1]<array[childIndex]){
                childIndex++;
            }
            //判断父节点的值是否小于孩子节点的值,如果小于则不进行下沉调整，结束操作
            if(temp < array[childIndex]){
                break;
            }
            //不需要进行交换操作，单向赋值就行
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2*parentIndex+1;
        }
        array[parentIndex] = temp;
    }

    /**
     * 创建二叉堆
     */
    public static void createBinaryHeap(int[] array){
        //初始从最后一个非叶子结点开始，进行下沉调整
        // i=(array.length-2)/2 即为最后一个非叶子结点的下标
        for (int i = (array.length-2)/2; i >=0 ; i--) {
            downAndJust(array,i,array.length);
        }
    }

    public static void main(String[] args) {
/*        int[] array = new int[] {1, 3, 2, 6, 5, 7, 8, 9, 10, 0 };
        upAndJust(array);
        System.out.println(Arrays.toString(array));
        System.out.println("-------------------------------------");*/
        int[] array = new int[] {7,1,3,10,5,2,8,9,6};
        createBinaryHeap(array);
        System.out.println(Arrays.toString(array));
    }
}
