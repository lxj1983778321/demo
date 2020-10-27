package com.example.demo.controller.algorithm;

import java.util.Stack;

/**
 * @Description
 * 最小栈实现 要求：进栈、出栈，返回最小值时间复杂度都为O(1)----》入栈，出栈，取最小元素
 * @Author lixingjian
 * @DATE 2020/7/14 9:45
 * @Version 1.0
 **/
public class MinStack {
    private Stack<Integer> mainStack = new Stack<Integer>();
    private Stack<Integer> minStack = new Stack<Integer>();

    public void push(int value){
        mainStack.push(value);
        //如果辅助栈为空，或者元素小于辅助栈当前栈顶元素，则入栈
        if(minStack.isEmpty() || value <= minStack.peek()){
            minStack.push(value);
        }
    }

    public int pop(){
        //如果主栈和辅助栈栈顶元素相等，则辅助栈出栈
        if(mainStack.peek().equals(minStack.peek())){
            return minStack.pop();
        }
        return mainStack.pop();
    }

    public Integer geiMin(){
        if(mainStack.empty()){
            return null;
        }

        return minStack.pop();
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(4);
        stack.push(9);
        stack.push(7);
        stack.push(3);
        stack.push(8);
        stack.push(5);
        System.out.println(stack.geiMin());
        System.out.println("---------------");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println("-----------------");
        System.out.println(stack.geiMin());
    }
}
