package com.example.demo.controller.algorithm;/**
 * @author muyou
 * @date 2020/9/10 16:58
 */

import java.util.Stack;

/**
 *@Description TODO
 *@Author lixingjian
 *@DATE 2020/9/10 16:58
 *@Version 1.0
 *利用辅助栈和辅助变量 将指定栈从顶到底，按照指定的顺序排序。。。
 **/
public class SortStackByStack {

    /**
     * 按照从大到小排序
     * 实现原理：
     * 逐一取出stack的栈顶元素，如果发现cur的值小于辅助栈的栈顶元素，那就将辅助栈的元素逐一弹出，直到辅助栈的栈顶元素小于cur为止
     * 然后再继续循环执行，将cur压入到help中
     *
     * 栈的特性：在本题中，越大的越在栈顶
     * @param stack
     * @return
     */
    public static Stack<Integer> sortStackByStackToMax(Stack<Integer> stack){
        Stack<Integer> help = new Stack<Integer>();
        while (!stack.isEmpty()){
            int cur = stack.pop();
            while (!help.isEmpty() && help.peek() > cur){
                stack.push(help.pop());
            }
            help.push(cur);
        }
        while (!help.isEmpty()){
            stack.push(help.pop());
        }
        return stack;
    }

    /**
     * 按照从小到大排序
     * 实现原理：
     * 逐一取出stack的栈顶元素，如果发现cur的值大于辅助栈的栈顶元素，那就将辅助栈的元素逐一弹出，直到辅助栈的栈顶元素大于cur为止
     * 然后再继续循环执行，将cur压入到help中
     *
     * 栈的特性：在本题中，越小的越在栈顶
     * @param stack
     * @return
     */
    public static Stack<Integer> sortStackByStackToMin(Stack<Integer> stack){
        Stack<Integer> help = new Stack<Integer>();
        while (!stack.isEmpty()){
            int cur = stack.pop();
            while (!help.isEmpty() && help.peek() < cur){
                stack.push(help.pop());
            }
            help.push(cur);
        }
        while (!help.isEmpty()){
            stack.push(help.pop());
        }
        return stack;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(5);
        stack.push(4);
        stack.push(2);
        stack.push(3);
        System.out.println(("main" + stack.toString()));
        System.out.println("Max To Min" + sortStackByStackToMax(stack).toString());
        System.out.println("Min To Max" + sortStackByStackToMin(stack).toString());
    }
}
