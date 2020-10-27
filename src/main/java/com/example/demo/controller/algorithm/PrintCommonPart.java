package com.example.demo.controller.algorithm;

/**
 *@Description TODO
 *@Author lixingjian
 *@DATE 2020/9/21 16:20
 *@Version 1.0
 *输出俩个有序链表的公共部分：
 *前提条件：有序链表
 * 思路：
 * （1）如果node1的值小于node2，node1向下移动
 * （2）如果node2的值小于node1，node2向下移动
 * （3）相等，则输出，然后同时向下移动
 **/
public class PrintCommonPart {
    public void printCommonPart(Node node1,Node node2){
        while (node1 != null && node2 != null){
            if(node1.data < node2.data){
                node1 = node1.next;
            }else if(node2.data < node1.data){
                node2 = node2.next;
            }else {
                System.out.println(node1.data);
                node1 = node1.next;
                node2 = node2.next;
            }
        }
    }
}
