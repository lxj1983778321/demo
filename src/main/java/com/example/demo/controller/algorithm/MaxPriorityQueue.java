package com.example.demo.controller.algorithm;

import java.util.Arrays;

/**
 * @Description
 * 借助最大二叉堆---》构建最大优先队列
 * @Author lixingjian
 * @DATE 2020/7/7 11:05
 * @Version 1.0
 **/
public class MaxPriorityQueue {
    //数组大小
   private int[] array;
   //初始化大小，以后用于扩容
    private int size;

    MaxPriorityQueue(){
        array = new int[32];
    }

    //扩容方法
    public void reSize(){
        int newSize = this.size*2;
        this.array = Arrays.copyOf(this.array,newSize);
    }

    //上浮调整-->构建最大堆
    public void upAndJust(){
        //获取孩子节点
        int childIndex = size-1;
        //获取父节点
        int parentIndex = (childIndex-1)/2;
        //获取孩子节点的值
        int temp = array[childIndex];
        //确定孩子节点最终的位置
        while (childIndex > 0 && temp > array[parentIndex]){
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = parentIndex/2;
        }
        array[childIndex] = temp;
    }

    //下沉调整--->构建最大堆
    public void downAndJust(){
        int parentIndex = 0;
        int childIndex = 1;
        //获取父节点的值
        int temp = array[parentIndex];
        while (childIndex<size){
            if(childIndex+1<size && array[childIndex+1]>array[childIndex]){
                childIndex++;
            }
            if(temp>=array[childIndex]){
                break;
            }
            //下沉，交换位置
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

    //出队
    public int deQueue()throws Exception{
        if(size <= 0){
            throw new Exception("this queue is empty");
        }
        //获取堆顶值
        int heapValue = array[0];
        //将堆最后一个元素移动到堆顶
        array[0] = array[--size];
        downAndJust();
        return heapValue;
    }

    public static void main(String[] args) throws Exception{
        MaxPriorityQueue priorityQueue = new MaxPriorityQueue();
        //向堆内插入数据
        priorityQueue.enQueue(3);
        priorityQueue.enQueue(5);
        priorityQueue.enQueue(10);
        priorityQueue.enQueue(2);
        priorityQueue.enQueue(7);
        //将最大元素从堆中取出
        System.out.println(priorityQueue.deQueue());
        System.out.println(priorityQueue.deQueue());
    }
}
