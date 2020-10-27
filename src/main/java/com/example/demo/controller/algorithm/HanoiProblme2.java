package com.example.demo.controller.algorithm;
/**
 * @author muyou
 * @date 2020/9/14 9:31
 * 汉诺塔问题：限制条件为，必须经过中间，不能直接从左移动到右，或从右移动到左
 * 使用递归实现汉诺塔问题的求解
 */
public class HanoiProblme2 {

    /**
     *
     * @param num
     * @param left
     * @param mid
     * @param right
     * @return
     */
    public int hanoiProbme(int num,String left,String mid,String right){
        if(num < 1){
            return 0;
        }
        return process(num,left,mid,right,left,right);
    }

    /**
     * 过程
     * @param num
     * @param left
     * @param mid
     * @param right
     * @param from 初始位置
     * @param to 目标位置
     * @return
     */
    public int process(int num,String left,String mid,String right,String from ,String to){
        if(num == 1){
            if(from.equals(mid) || to.equals(mid)){
                System.out.println("move 第 1 个 from " + from +" to " + to);
                return 1;
            }else {
                System.out.println("move 第 1 个 from " + from +" to " + mid);
                System.out.println("move 第 1 个 from " + mid +" to " + to);
                return 2;
            }
        }

        //将塔移动到中的情况
        if(from.equals(mid) || to.equals(mid)){
            String another = ((from.equals(left) || to.equals(left)) ? right : left);
            //将1~num-1的塔，全部移动到左或右
            int part1 = process(num -1,left,mid,right,from,another);
            //将最后一个塔移动到中，需要一步
            int part2 = 1;
            System.out.println("move 第 " + num + " 个 from " + from + " to " + to);
            //将1~num-1的塔再移动到中
            int part3 = process(num-1,left,mid,right,another,to);
            return part1+part2+part3;
        }
        //将塔从左移到右或从右移到左
        else {
            //将1~num-1层塔从左移动到右
            int part1 = process(num-1,left,mid,right,from,to);
            //将第num层塔从左移到中
            int part2 = 1;
            System.out.println("move 第 " + num + " 个 from " + from + " to " + mid);
            //将1~num-1层塔从右移到左
            int part3 = process(num-1,left,mid,right,to,from);
            //将第num层塔从中移到右
            int part4 = 1;
            System.out.println("move 第 " + num + " 个 from " + mid + " to " + to);
            int part5 = process(num-1,left,mid,right,from,to);
            return part1+part2+part3+part4+part5;
        }

    }

    public static void main(String[] args) {
        HanoiProblme2 hanoiProblme = new HanoiProblme2();
        System.out.println(hanoiProblme.hanoiProbme(2, "left", "mid", "right"));
    }
}
