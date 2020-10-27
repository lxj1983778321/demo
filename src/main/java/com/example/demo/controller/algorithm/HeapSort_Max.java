package com.example.demo.controller.algorithm;/**
 * @author muyou
 * @date 2020/10/13 10:42
 */

import java.util.Arrays;

/**
 *@Description TODO
 *@Author lixingjian
 *@DATE 2020/10/13 10:42
 *@Version 1.0
 *利用二叉堆实现从小到大排序---》最大堆
 **/
public class HeapSort_Max {

    /**
     * 构建最大堆
     * @param array
     * @param parentIndex
     * @param length
     */
    public static void downAdjust(int[] array,int parentIndex,int length){
        //父节点元素
        int temp = array[parentIndex];
        //左孩子节点
        int childIndex = 2 * parentIndex + 1;
        while (childIndex < length){
            //找出最大的孩子节点
            if(childIndex + 1 < length && array[childIndex+1] > array[childIndex]){
                childIndex++;
            }
            if(temp >= array[childIndex]){
                break;
            }
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = childIndex*2+1;
        }
        array[parentIndex] = temp;
    }
    /**
     * 堆排序（升序）
     *  @param array 待调整的堆
     */
    public static void heapSort(int[] array) {
        //  把无序数组构建成最大堆
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            downAdjust(array, i, array.length);
        }
        System.out.println(Arrays.toString(array));
        // 2. 循环删除堆顶元素，移到集合尾部，调整堆产生新的堆顶
        for (int i = array.length - 1; i > 0; i--) {
            // 最后1个元素和第1个元素进行交换
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            // “下沉”调整最大堆
            downAdjust(array, 0, i);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[] {1,3,2,6,5,7,8,9,10,0};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }
}
