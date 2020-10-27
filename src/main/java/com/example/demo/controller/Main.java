package com.example.demo.controller;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            //获取行和列
            int m = sc.nextInt();
            int n = sc.nextInt();
            //生成二维矩阵
            String[][] a = new String[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    //填充矩阵数据
                    a[i][j] = sc.next();
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.println(a[i][j]);
                }
            }
        }
    }
}