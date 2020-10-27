package com.example.demo.ThreadTest.Semaphore;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/14 14:36
 * 多进路-多处理-多出路：
 * 目的是允许多个线程同时处理自己的任务
 */
public class SemaphoreTest6 implements Runnable{
    private Semaphore semaphore = new Semaphore(5);

    public void testMethod(){
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + "准备开始,开始时间为：" + System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(2);
            System.out.println("当前正在等待的线程数为：" + semaphore.getQueueLength());
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "执行自己的任务，打印" + (i+1));
            }
            semaphore.release();
            System.out.println(Thread.currentThread().getName() + "执行结束,结束时间为：" + System.currentTimeMillis());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        testMethod();
    }

    public static void main(String[] args) {
        SemaphoreTest6 semaphoreTest6 = new SemaphoreTest6();
        ExecutorService executorService = new ThreadPoolExecutor(4,4,10, TimeUnit.SECONDS,new ArrayBlockingQueue<>(4));
        for (int i = 0; i < 4; i++) {
            executorService.execute(semaphoreTest6);
        }
        executorService.shutdown();
    }
}
