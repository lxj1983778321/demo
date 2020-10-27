package com.example.demo.controller.algorithm;

import java.util.Stack;

/**
 * @Description
 * 删除k个数字后，获得删除后的最小值
 * 思路：
 * 把原有整数的所有数字从左到右进行比较，如果发现某一数字大于他右面的数字，
 * 必然会使该数字值降低，因为右面比他小的数字顶替了它的位置
 * @Author muyou
 * @DATE 2020/7/28 9:39
 * @Version 1.0
 **/
public class RemoveKDigits {


    /**
     *
     * @param num 原整数
     * @param k 删除K个整数
     */
    public static String removeKDigits(String num,int k){
        //如果为空，直接返回
        if(num.isEmpty()){
            return null;
        }
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < num.length(); i++) {
            if(i == 0 && stack.isEmpty()){
                stack.push(num.charAt(i));
                continue;
            }
            //拿到当前的值，和他的前一个值比较，是否大于他的前一个值
            char c = num.charAt(i);
            while (!stack.isEmpty() && stack.peek() > c && k > 0){
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        //将数据拼接输出
        //声明标识，用于确定第一个不为0的数字
        //用于拼接数据，返回最终结果
        String result = "";
        Boolean isZero = true;
        //首次默认为第一次出现的数字为0
        while (!stack.isEmpty()){
            char temp = stack.remove(0);
            if(temp == '0' && isZero == true){
                continue;
            }
            isZero = false;
            if(isZero == false){
                result += temp;
            }
        }
        return "".equals(result)?"0":result;
    }

    public static void main(String[] args) {
        System.out.println(removeKDigits("1593212",3));
        System.out.println(removeKDigits("30200",1));
        System.out.println(removeKDigits("10",2));
        System.out.println(removeKDigits("541270936",3));
    }
}
