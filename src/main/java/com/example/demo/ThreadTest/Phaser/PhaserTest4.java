package com.example.demo.ThreadTest.Phaser;

import java.util.concurrent.Phaser;

/**
 * @author muyou
 * @date 2020/10/16 14:51
 * 测试getRegisteredParties()方法和register()方法
 * getRegisteredParties()方法：获得注册的parties数量
 * register()方法：每调用一次就动态添加一个parties
 * bulkRegister()方法：批量增加parties数量
 * getArrivedParties()方法：获取已经被使用的parties的数量
 * getUnarrivedParties()方法：获取未被使用的parties的个数
 */
public class PhaserTest4{
    public static void main(String[] args) {
        Phaser phaser = new Phaser(5);
        System.out.println("当前注册时parties数量为："+ phaser.getRegisteredParties());
        phaser.register();
        System.out.println("调用register()方法后parties数量为："+ phaser.getRegisteredParties());
        phaser.bulkRegister(2);
        System.out.println("调用bulkRegister(int parties)方法后parties数量为："+ phaser.getRegisteredParties());
        System.out.println("调用getArrivedParties()方法查看已经使用的parties数量为："+ phaser.getArrivedParties());
        System.out.println("调用getUnarrivedParties()方法查看未被使用的parties数量为："+ phaser.getUnarrivedParties());
    }
}
