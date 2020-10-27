package com.example.demo.controller.algorithm;

import java.util.Stack;

/**
 * @Description
 * 利用双栈实现队列
 * 原理：
 * 队列特点为：先进先出
 * 单栈为：先进后出
 * 使用一个栈用于入栈，另一个盏用于出栈 ----》即可实现队列的先进先出
 * @Author lixingjian
 * @DATE 2020/7/21 9:29
 * @Version 1.0
 **/
public class Stack_Implement_Queue {
    private Stack<Integer> inputStack = new Stack<Integer>();
    private Stack<Integer> outputStack = new Stack<Integer>();

    /**
     * 入队
     * @param element
     */
    public void enQueue(int element){
        inputStack.push(element);
    }

    /**
     * 出队操作
     * @return
     */
    public Integer deQueue(){
        if(outputStack.empty()){
            if(inputStack.empty()){
                return null;
            }
        }
        transfer();
        return outputStack.pop();
    }

    /**
     * 负责入栈的数据出栈到负责出栈的栈中
     */
    public void transfer(){
        while (!inputStack.empty()){
            outputStack.push(inputStack.pop());
        }
    }

    public static void main(String[] args) {
        Stack_Implement_Queue queue = new Stack_Implement_Queue();
        //队列先进先出
        queue.enQueue(5);
        queue.enQueue(3);
        queue.enQueue(2);
        queue.enQueue(1);
        //出队
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
    }
}
