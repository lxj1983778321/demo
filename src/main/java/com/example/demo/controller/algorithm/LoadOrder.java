package com.example.demo.controller.algorithm;/**
 * @author muyou
 * @date 2020/8/12 17:14
 */

/**
 *@Description TODO
 * 加载顺序
 *@Author lixingjian
 *@DATE 2020/8/12 17:14
 *@Version 1.0
 *
 **/
public class LoadOrder {
     private static int a;

     static {
         System.out.println("父类静态代码块");
     }

     LoadOrder(){
         System.out.println("父类构造函数");
     }

    public static void main(String[] args) {
        LoadOrderSon LoadOrder = new LoadOrderSon();
    }
}
class LoadOrderSon extends LoadOrder{
    static {
        System.out.println("子类静态代码块");
    }

    LoadOrderSon(){
        System.out.println("子类构造函数");
    }
}