package com.example.demo.controller.algorithm;

import java.util.Arrays;

/**
 *@Description TODO
 *@Author lixingjian
 *@DATE 2020/8/11 17:40
 *@Version 1.0
 *
 **/
public class Max_Binaryheap {

    //构建大根堆：将array看成完全二叉树的顺序存储结构
      private static int[] buildMaxHeap(int[] array){
                //从最后一个节点array.length-1的父节点（array.length-1-1）/2开始，直到根节点0，反复调整堆
                 for(int i=(array.length-2)/2;i>=0;i--){
                         adjustDownToUp(array, i,array.length);
                     }
                 return array;
      }

    //将元素array[k]自下往上逐步调整树形结构
     private static void adjustDownToUp(int[] array,int k,int length){
         int temp = array[k];
         for(int i=2*k+1; i<length-1; i=2*i+1){    //i为初始化为节点k的左孩子，沿节点较大的子节点向下调整
                 if(i<length && array[i]<array[i+1]){  //取节点较大的子节点的下标
                         i++;   //如果节点的右孩子>左孩子，则取右孩子节点的下标
                     }
                 if(temp>=array[i]){  //根节点 >=左右子女中关键字较大者，调整结束
                         break;
                     }else{   //根节点 <左右子女中关键字较大者
                         array[k] = array[i];  //将左右子结点中较大值array[i]调整到双亲节点上
                         k = i; //【关键】修改k值，以便继续向下调整
                     }
             }
         array[k] = temp;  //被调整的结点的值放人最终位置
      }

    //堆排序
      public static int[] heapSort(int[] array){
         array = buildMaxHeap(array); //初始建堆，array[0]为第一趟值最大的元素
         for(int i=array.length-1;i>1;i--){
                 int temp = array[0];  //将堆顶元素和堆低元素交换，即得到当前最大元素正确的排序位置
                 array[0] = array[i];
                 array[i] = temp;
                 adjustDownToUp(array, 0,i);  //整理，将剩余的元素整理成堆
             }
         return array;
      }

    public static void main(String[] args) {
        int[] array = new int[] {1,3,2,6,5,7,8,9,10,0};
        int[] array1 = {87,45,78,32,17,65,53,9,122};
        System.out.println(Arrays.toString(heapSort(array1)));
    }
}
