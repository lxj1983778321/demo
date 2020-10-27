package com.example.demo.controller.algorithm;

/**
 * @Description 判断一个整数是否为2的整数次幂
 * @Author lixingjian
 * @DATE 2020/7/14 10:44
 * @Version 1.0
 **/
public class IspowerOf2 {

    public static Boolean isPowerOf2(int value){
        int num = value & (value-1);
        return num == 0 ? true:false;
    }

    public static void main(String[] args) {
        System.out.println(isPowerOf2(8));
        System.out.println(isPowerOf2(9));
    }
}
