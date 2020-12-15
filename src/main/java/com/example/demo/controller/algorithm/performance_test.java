package com.example.demo.controller.algorithm;
import java.util.Random;
import java.util.Scanner;

/**
 * @author muyou
 * @date 2020/12/11 14:49
 * （1）随机出10（自定义）道题，每题10（自定义分数）分，程序结束显示学生得分
 * （2）只允许0~50（自定义）以内的加减乘除，不允许超过范围，也不允许出现负数
 * （3）每道题学生有三次机会（答题机会自定义）输入答案，当学生输入错误答案，提醒学生重新输入，如果三次机会结束则输出正确答案
 * （4）每道题学生第一次答对10（分数自定义）分，第二次答对7（分数自定义）分，第三次答对5（分数自定义）分，否则不得分
 * （5）总成绩90分以上SMART，80-90显示GOOD，70-80显示OK，60-70显示PASS，60以下显示TRYAGAIN
 */
public class performance_test {
    //生成四则运算,1表示加，2表示减，3表示乘，4表示除
    public static String[] operations = new String[]{"1","2","3","4"};
    //随机发生器
    public static Random r = new Random();
    //用来计算总数
    private static int sum = 0;




    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入，要产生的题目数量");
        int title_number = sc.nextInt();
        System.out.println("请输入，题目结果不能超过的范围");
        int result_number = sc.nextInt();
        System.out.println("请输入，每道题的答题次数");
        int answers_performance = sc.nextInt();
        //用来存储，答题次数所对应的分数
        int[] result_performance = new int[answers_performance];

        System.out.println("请输入，每道题答对次数对应的分数，例如：第一次答对：10分，第二次答对：7分，第三次答对5分，第四次答对：0分");
        for (int i = 0; i < result_performance.length; i++) {
            System.out.println("第"+(i+1)+"次答对，所对应的分数为：");
            result_performance[i] = sc.nextInt();
        }

        //用来存储每道题答了几次
        int[] correct_number = new int[title_number];
        //用来生成四则运算的题目
        String[] results = new String[title_number];
        //用来存储每道题目的正确结果
        int[] result_sum = new int[title_number];

        //随机生成四则运算的题目
        for (int i = 0; i < title_number; i++) {
            //随机获取本次题目是哪个运算
            String operate = operations[r.nextInt(operations.length)];
            switch (operate){
                case "1":{
                    int number1 = r.nextInt(result_number);
                    int result = result_number - number1;
                    int number2 = r.nextInt(result);
                    results[i] = number1+ "+" + number2 + "=";
                    result_sum[i] = number1+number2;
                    break;
                }
                case "2":{
                    boolean flag = true;
                    int number1 = r.nextInt(result_number);
                    int number2 = r.nextInt(result_number);
                    if(number1<= number2){
                        int number = number1;
                        number1 = number2;
                        number2 = number;
                    }
                    results[i] = number1 + "-" + number2 + "=";
                    result_sum[i] = number1-number2;
                    break;
                }
                case "3":{
                    boolean flag = true;
                    while (flag){
                        int number1 = r.nextInt(result_number);
                        int number2 = r.nextInt(result_number);
                        int sum = number1*number2;
                        if(sum <= result_number){
                            results[i] = number1 + "*" + number2 + "=";
                            result_sum[i] = number1*number2;
                            flag = false;
                        }
                    }
                }
                case "4":{
                    boolean flag = true;
                    while (flag){
                        int number1 = r.nextInt(result_number)+1;
                        int number2 = r.nextInt(result_number)+1;
                        if(number1%number2 == 0){
                            if(sum<result_number){
                                results[i] = number1 + "/" + number2 + "=";
                                result_sum[i] = number1/number2;
                                flag = false;
                            }
                        }
                    }
                }
            }
        }

        //学生做答题目的，外层控制题目数量，内层控制答题数
        for (int i = 0; i < results.length; i++) {
            System.out.println("请回答第"+(i+1)+"道题");
            System.out.println(results[i]);
            System.out.println("请写入正确结果");
            for (int j =0; j < answers_performance; j++) {
                int title_result = sc.nextInt();
                if(title_result == result_sum[i]){
                    System.out.println("回答正确");
                    //这道题回答了几次
                    correct_number[i] = j;
                    break;
                }if((j+1)==answers_performance && title_result == result_sum[i]){
                    System.out.println("回答正确");
                    correct_number[i] = j;
                    break;
                }else if((j+1)==answers_performance && title_result != result_sum[i]){
                    System.out.println("回答错误，正确结果是：" + result_sum[i]);
                    correct_number[i] = j;
                }else {
                    System.out.println("回答错误，重新输入");
                }
            }
        }

        //计算总成绩
        for (int i = 0; i < correct_number.length; i++) {
             sum += result_performance[correct_number[i]];
        }
        System.out.println("题目做答完毕！！！");
        System.out.println("总得分是：" + sum);
        System.out.print("该成绩是：");
        int number_sum = result_performance[0]*title_number;
        int result_sum_90 = (int) (number_sum*0.9);
        int result_sum_80 = (int) (number_sum*0.8);
        int result_sum_70 = (int) (number_sum*0.7);
        int result_sum_60 = (int) (number_sum*0.6);
        if(sum > result_sum_90){
            System.out.println("SMART");
        }else if(result_sum_80 < sum && sum <= result_sum_90){
            System.out.println("GOOD");
        }else if(result_sum_70<sum && sum <= result_sum_80){
            System.out.println("OK");
        }else if(result_sum_60<sum && sum <= result_sum_70){
            System.out.println("PASS");
        }else {
            System.out.println("TRYAGAIN");
        }
    }
}
