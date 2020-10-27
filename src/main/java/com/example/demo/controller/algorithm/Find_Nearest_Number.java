package com.example.demo.controller.algorithm;

import java.util.Arrays;

/**
 * @Description
 * 字典序算法：寻找数字全排列的下一个数
 * 思路：
 * 从后向前查看逆序区域，找到逆序区域的前一位，也就是数字置换边界
 * 让逆序区域的前一位和逆序区域中大于它的最小的数字交换边界
 * 把原来的逆序区域变为顺序状态
 * @Author lixingjian
 * @DATE 2020/7/24 9:27
 * @Version 1.0
 **/
public class Find_Nearest_Number {
    /**
     * 主方法
     * findNearestNumber
     * @param numbers
     * @return
     */
    public static int[] findNearsNumber(int[] numbers){
        //1.确定逆序区域，获取数字置换边界
        int index = findTransNumber(numbers);
        //index为0,表示数字全部相等，无法得到的全排列的下一个数
        if(index == 0){
            return null;
        }
        //避免直接操作入参
        int[] numbersCopy = Arrays.copyOf(numbers,numbers.length);
        //让逆序区域的前一位和逆序区域中大于它的最小的数字交换边界
        exchangeHead(numbersCopy,index);
        //把原来的逆序区域变为顺序状态
        reverse(numbersCopy,index);
        return numbersCopy;
    }

    /**
     * 获取逆序区域，获取数字置换边界
     * @return
     */
    public static int findTransNumber(int[] numbers){
        for (int i = numbers.length-1; i > 0; i--) {
            if(numbers[i] > numbers[i-1]){
                return i;
            }
        }
        return 0;
    }

    /**
     * 让逆序区域的前一位和逆序区域中大于它的最小的数字交换边界
     * @param numbers
     * @param index
     */
    public static void exchangeHead(int[] numbers,int index){
        int head = numbers[index-1];
        for (int i = numbers.length-1; i > 0; i--) {
            if(head < numbers[i]){
                numbers[index-1] = numbers[i];
                numbers[i] = head;
                break;
            }
        }
    }

    /**
     * 把原来的逆序区域变为顺序状态
     * @param numbers
     * @param index
     */
    public static void reverse(int[] numbers,int index){
        for (int i = index,j = numbers.length-1; i < j; i++,j--) {
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }
    }

    public static void main(String[] args) {
        int[] number = new int[]{1,2,3,4,5};
        System.out.println(Arrays.toString(findNearsNumber(number)));
    }
}
