package com.example.demo.controller.algorithm;

import java.util.Arrays;

/**
 * @Description 借助最小二叉堆----》构建最小优先队列
 * @Author lixingjian
 * @DATE 2020/7/8 10:17
 * @Version 1.0
 **/
public class minPriorityQueue {
    private static int[] array;
    private int size;

    minPriorityQueue(){
        array = new int[32];
    }

    public void reSize(){
        int newSize = this.size*2;
        this.array = Arrays.copyOf(array,newSize);
    }

    //上浮调整---->最小堆
    public void upAndJust(){
        int childIndex = size-1;
        int parentIndex = (childIndex-1)/2;
        int temp = array[childIndex];
        while (childIndex > 0 && temp < array[parentIndex]){
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = parentIndex/2;
        }
        array[childIndex] = temp;
    }

    //下沉调整
    public void downAndJust(){
        //父节点
        int parentIndex = 0;
        //孩子节点
        int childIndex = 1;
        //父节点的值
        int temp = array[parentIndex];
        while (childIndex < size){
            if(childIndex+1 < size && array[childIndex+1]<array[childIndex]){
                childIndex++;
            }
            if(temp < array[childIndex]){
                return;
            }
            //交换
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = (childIndex+1)*2;
        }
        array[parentIndex] = temp;
    }

    //入队
    public void enQueue(int value){
        if(size >= array.length){
            reSize();
        }
        array[size++] = value;
        upAndJust();
    }

    public int deQueue()throws Exception{
        if(size <= 0){
            throw new Exception("this heap is Empty");
        }
        int heapValue = array[0];
        array[0] = array[--size];
        downAndJust();
        return heapValue;
    }

    public static void main(String[] args) throws Exception{
        minPriorityQueue minPriorityQueue = new minPriorityQueue();
        //向堆内插入数据
        minPriorityQueue.enQueue(3);
        minPriorityQueue.enQueue(5);
        minPriorityQueue.enQueue(10);
        minPriorityQueue.enQueue(2);
        minPriorityQueue.enQueue(7);
        System.out.println("--------------------------");
        System.out.println(Arrays.toString(array));
        //将最大元素从堆中取出
        System.out.println("--------------------------");
        System.out.println(minPriorityQueue.deQueue());
        System.out.println(minPriorityQueue.deQueue());
        System.out.println(minPriorityQueue.deQueue());
        System.out.println(minPriorityQueue.deQueue());
        System.out.println(minPriorityQueue.deQueue());
    }
}
