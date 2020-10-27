package com.example.demo.controller.algorithm;

/**
 * @Description TODO
 * 最优金矿收益：典型的动态规划
 * 解决思路：
 * 每一个金矿都存在挖与不挖，这俩种情况，
 * 如果不挖就是w个工人去挖前几个金矿
 * 如果挖了，就是总工人数减去挖这个金矿的工人数，剩下的工人在前几个金矿的收益加上这个金矿的收益，
 * 比较俩种情况的值，哪个值更大，从而确定最大收益
 * @Author muyou
 * @DATE 2020/7/30 9:36
 * @Version 1.0
 **/
public class BestGoldMining {

    /**
     * 获取最大收益
     * @param w 工人数量
     * @param p 挖金矿的所需工人数
     * @param g 金矿的储量
     * @return
     */
    public static int getBestGoldMining(int w,int[] p,int[] g){
        int[] result = new int[w+1];
        for (int i = 1; i < g.length; i++) {
            for (int j = w; j >= 1; j--) {
                if(j >= p[i-1]){
                    result[j] = Math.max(result[j],result[j - p[i-1]]+g[i-1]);
                }
            }
        }
        return result[w];
    }

    public static void main(String[] args) {
        int w = 10;
        int[] p = {5,5,3,4,3};
        int[] g = {400,500,200,300,350};
        System.out.println(getBestGoldMining(w,p,g));
    }
}
