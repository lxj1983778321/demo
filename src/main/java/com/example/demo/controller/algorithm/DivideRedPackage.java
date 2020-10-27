package com.example.demo.controller.algorithm;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *@Description TODO
 * 红包算法：
 * 二倍均值法
 *
 *@Author muyou
 *@DATE 2020/8/5 16:33
 *@Version 1.0
 *
 **/
public class DivideRedPackage {

    /**
     * 每次抢到的金额 = 随机区间【0.01，m/n * 2 - 0.01】
     * @param totalAmount 总金额
     * @param totalPeopleNum 总人数
     * @return
     */
    public static List<Integer> divideRedPackage(Integer totalAmount,Integer totalPeopleNum){
        List<Integer> amountList = new ArrayList<Integer>();
        Integer restAmount = totalAmount;
        Integer restPeopleNum = totalPeopleNum;
        Random random = new Random();
        for (int i = 0; i < totalPeopleNum - 1; i++) {
            //随即范围：【剩余人均金额的2倍 - 1】分
            int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
            restAmount -= amount;
            restPeopleNum --;
            amountList.add(amount);
        }
        amountList.add(restAmount);
        return amountList;
    }

    public static void main(String[] args) {
        List<Integer> amountList = divideRedPackage(1000,10);
        for (Integer amount : amountList) {
            System.out.println("抢到的金额为：" + amount);
        }
    }
}
