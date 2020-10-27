package com.example.demo.ThreadTest.Phaser;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/16 15:04
 * arrive()方法：getArrivedParties()方法（已被使用）的parties数量加1，达到new Phaser(3);要求的数量，直接向下执行,不会增加构造方法中的parties的数量
 */
public class PhaserTest5 implements Runnable{
    private Phaser phaser;

    PhaserTest5(Phaser phaser){
        this.phaser = phaser;
    }

    public void testMethod()throws InterruptedException{
        System.out.println(Thread.currentThread().getName()+"进入时间为："+System.currentTimeMillis());
        TimeUnit.SECONDS.sleep(2);
        if(Thread.currentThread().getName().equals("pool-1-thread-1")){
            phaser.arrive();
            System.out.println(Thread.currentThread().getName()+"当前已被使用的parties的数量为："+phaser.getArrivedParties());
        }
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName()+"越过屏障，时间为："+System.currentTimeMillis());
        System.out.println("当前已被使用的parties的数量为："+phaser.getArrivedParties());
        System.out.println("当前未被使用的parties的数量为："+phaser.getUnarrivedParties());
    }

    @Override
    public void run() {
        try {
            testMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //调用arrive()方法，任然达不到构造器要求的数量，被一直阻塞
        //Phaser phaser = new Phaser(5);
        //调用arrive()方法刚好达到要求，三个线程全部度过屏障
        //Phaser phaser = new Phaser(4);
        //调用arrive()方法后，有一个线程被阻塞在屏障之前，其余俩个线程通过屏障
        Phaser phaser = new Phaser(3);
        PhaserTest5 phaserTest5 = new PhaserTest5(phaser);

        ExecutorService executorService = new ThreadPoolExecutor(3,3,10, TimeUnit.SECONDS,new ArrayBlockingQueue<>(3));
        for (int i = 0; i < 3; i++) {
            executorService.execute(phaserTest5);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
