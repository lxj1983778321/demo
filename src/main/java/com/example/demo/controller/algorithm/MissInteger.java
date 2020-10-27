package com.example.demo.controller.algorithm;

import java.util.Arrays;

/**
 * @Description TODO
 * @Author lixingjian
 * @DATE 2020/7/31 9:36
 * @Version 1.0
 **/
public class MissInteger {

    /**
     * 查找缺失的整数。
     * 问题描述：
     * 在一个无序数组里有99个不重复的正整数，范围1~100，唯独缺少一个整数，如何找出这个缺失的整数
     * 时间复杂度：O(n) 空间复杂度：O(1)
     */
    public static void getMissInteger(int[] array){
        int sum = 0;
        for (int i = 1; i <= 10; i++) {
            sum += i;
        }
        for (int i = 0; i < array.length; i++) {
            sum -= array[i];
        }
        System.out.println(sum);
    }

    /**
     * 问题描述：
     * 一个无序数组里有若干个正整数，范围是1~100，其中99个整数都出现了偶数次，
     * 只有一个正整数出现了奇数次，如何找出这个出现奇数次的数
     * 解题思路：
     * 数组里的元素依次进行异或运算，相同位为0，不同位为1
     * @param array
     */
    public static void getMissIntegerRepeat(int[] array){
        int sum = 0;
        for (int i = 1; i < array.length; i++) {
            sum = array[0] ^ array[i];
        }
        System.out.println(sum);
    }

    /**
     * 问题描述：
     * 一个无序数组里有若干个正整数，范围是1~100，其中98个整数都出现了偶数次，
     * 只有2个正整数出现了奇数次，如何找出这2个出现奇数次的整数
     * 解题思路：
     * 首先把数组元素依次进行异或运算，得到结果是2个出现奇数次的整数的异或运算结果，
     * 获取这个运算结果的不同位，依据不同位进行分组，把他转化为求一个奇数次的问题求解，
     * 分治法
     *
     */
    public static int[] getMissIntegerRepeatTwo(int[] array){
        //用来存储最终结果
        int[] result = new int[2];
        //首次进行异或运算，获取出现奇数次整数的异或结果
        int xorResult = 0;
        for (int i = 0; i < array.length; i++) {
            xorResult ^= array[i];
        }
        //结果为0，表示没有奇数次的整数出现，直接返回null
        if (xorResult == 0){
            return null;
        }
        //确定不同位，以便进行分组
        int seperator = 1;
        while (0 == (seperator & xorResult)){
            seperator <<= 1;
        }
        //遍历原数组，进行分组获取奇数次整数
        for (int i = 0; i < array.length; i++) {
            if(0 == (seperator & array[i])){
                result[0] ^= array[i];
            }else {
                result[1] ^= array[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {2,3,4,2,5,3,4,6,7,6};
        System.out.println(Arrays.toString(getMissIntegerRepeatTwo(array)));
    }
}
