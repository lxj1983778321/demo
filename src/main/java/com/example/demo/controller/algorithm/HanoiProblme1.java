package com.example.demo.controller.algorithm;

/**
 *@Description TODO
 *@Author lixingjian
 *@DATE 2020/9/14 10:56
 *@Version 1.0
 * 汉诺塔问题：无限制版
 **/
public class HanoiProblme1 {

    public int  hanoiProblme(int num,String left,String mid,String right,String from,String to){
        if(num < 1){
            return 0;
        }
        return process(num,left,mid,right,from,to);
    }

    public int  process(int num,String left,String mid,String right,String from,String to){
        /**
         * 如果是移动多个，不管是从中间往边移动，还是从边往中间移动，所需要的步骤都是俩步：
         * 例如：从左向右移动
         * 将1~num-1的塔先移动到中间，然后将第num层的塔移动到右边，
         * 然后将中间的1~num-1层的塔，移动到右边
         * 总体步数为：num-1+1 = num
         */
            int part1 =  num -1;
            int part2 = 1;
            int part3 = num-1;
            return part1+part2+part3;
    }

    public static void main(String[] args) {
        HanoiProblme1 hanoiProblme1 = new HanoiProblme1();
        System.out.println(hanoiProblme1.hanoiProblme(3, "left", "mid", "right", "left", "right"));
    }
}
