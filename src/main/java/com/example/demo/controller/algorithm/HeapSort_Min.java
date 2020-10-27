package com.example.demo.controller.algorithm;


import java.util.Arrays;

/**
 * @Description
 * 利用二叉堆实现从大到小排序--》最小堆
 * @Author lixingjian
 * @DATE 2020/7/10 9:29
 * @Version 1.0
 **/
public class HeapSort_Min {

    /**
     *
     * @param array
     * @param parentIndex 父节点下标
     * @param length 节点长度
     */
    public static void downAdjust(int[] array, int parentIndex, int length) {
        //构建最小堆通过出堆顶构成升序，最大堆通过出堆顶达到降序
        //获取父节点
        int temp = array[parentIndex];
        //获取孩子节点
        int childIndex = 2*parentIndex+1;

        while (childIndex < length){
            //寻找最小的叶子结点
            if(childIndex+1 < length && array[childIndex+1] < array[childIndex]){
                childIndex++;
            }
            if(temp <= array[childIndex]){
                break;
            }
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2*childIndex+1;
        }
        array[parentIndex] = temp;
    }



    /**
     * 堆排序（升序）
     *  @param array 待调整的堆
     */
    public static void heapSort(int[] array) {
        //  把无序数组构建成最小堆
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
            // “下沉”调整最小堆
            downAdjust(array, 0, i);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[] {1,3,2,6,5,7,8,9,10,0};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }
}
