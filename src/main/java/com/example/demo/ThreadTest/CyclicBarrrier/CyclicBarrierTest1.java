package com.example.demo.ThreadTest.CyclicBarrrier;
import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/15 14:31
 * 实现等所有线程都到达同步点时再继续运行的效果
 */
public class CyclicBarrierTest1 implements Runnable{
    private CyclicBarrier cyclicBarrier;

    CyclicBarrierTest1(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep((int)(Math.random()*10));
            System.out.println(Thread.currentThread().getName()+"到达等待点，到达时间为："+System.currentTimeMillis());
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName()+"开始执行屏障之后的任务，执行时间为："+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            //BrokenBarrierException异常：当某个线程等待处于断开状态的barrier时，或者barrier进入断开状态而线程处于等待状态时，抛出该异常
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        /**
         * new CyclicBarrier(5, new CyclicBarrierTest1_1());作用是设置为5个barrier同行者，只有五个线程全部执行了await()方法
         * 线程才会继续执行new CyclicBarrierTest1_1()中的run()方法
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"线程到达屏障点，增加自定义逻辑，执行时间为："+System.currentTimeMillis());
            }
        });
        CyclicBarrierTest1 cyclicBarrierTest1 = new CyclicBarrierTest1(cyclicBarrier);
        ExecutorService executorService = new ThreadPoolExecutor(5,5,10,TimeUnit.SECONDS,new ArrayBlockingQueue<>(5));
        for (int i = 0; i < 5; i++) {
            executorService.execute(cyclicBarrierTest1);
        }
        executorService.shutdown();
    }
}
