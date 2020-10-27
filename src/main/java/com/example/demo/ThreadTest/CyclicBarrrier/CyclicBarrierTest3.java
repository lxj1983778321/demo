package com.example.demo.ThreadTest.CyclicBarrrier;
/**
 * @author muyou
 * @date 2020/10/15 15:51
 */

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 *@Description TODO
 *@Author lixingjian
 *@DATE 2020/10/15 15:51
 *@Version 1.0
 *验证屏障重置性以及getNumberWaiting()方法
 * getNumberWaiting()方法作用是获得有几个线程到达屏障点
 **/
public class CyclicBarrierTest3 implements Runnable{
    private CyclicBarrier cyclicBarrier;

    CyclicBarrierTest3(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {

            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName()+"达到条件，向下执行，时间为："+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        CyclicBarrierTest3 cyclicBarrierTest3 = new CyclicBarrierTest3(cyclicBarrier);
        Thread thread1 = new Thread(cyclicBarrierTest3);
        thread1.start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("当前等待线程数为："+cyclicBarrier.getNumberWaiting());
        Thread thread2 = new Thread(cyclicBarrierTest3);
        thread2.start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("当前等待线程数为："+cyclicBarrier.getNumberWaiting());


        Thread thread3 = new Thread(cyclicBarrierTest3);
        thread3.start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("当前等待线程数为："+cyclicBarrier.getNumberWaiting());
    }
}
