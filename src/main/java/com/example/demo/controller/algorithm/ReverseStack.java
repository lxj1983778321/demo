package com.example.demo.controller.algorithm;/**
 * @author muyou
 * @date 2020/9/9 17:15
 */

import java.util.Arrays;
import java.util.Stack;

/**
 *@Description TODO
 *@Author lixingjian
 *@DATE 2020/9/9 17:15
 *@Version 1.0
 *利用递归方法和栈的特性逆序输出栈
 **/
public class ReverseStack {

    //利用递归方法，将栈的最低层元素移除并获取
    public static int getAndRemoveLastElement(Stack<Integer> stack){
        int result = stack.pop();
        if(stack.isEmpty()){
           return result;
        }else {
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }

    //
    public static Stack<Integer> reverse(Stack<Integer> stack){
        if(stack.isEmpty()){
            return null;
        }
        //获取最后一个元素，并移除
        int i = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(i);
        return stack;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack.toString());
        System.out.println(reverse(stack).toString());

    }

}
