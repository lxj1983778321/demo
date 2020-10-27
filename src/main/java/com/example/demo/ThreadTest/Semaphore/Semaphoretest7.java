package com.example.demo.ThreadTest.Semaphore;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author muyou
 * @date 2020/10/14 14:52
 * 多进路-单处理-多出路
 * 目标是允许多个线程同时处理任务，但执行任务顺序是同步的
 */
public class Semaphoretest7 implements Runnable{

    private Semaphore semaphore = new Semaphore(5);
    private ReentrantLock lock = new ReentrantLock();

    public void testMethod(){
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + "准备开始,开始时间为：" + System.currentTimeMillis());
            lock.lock();
            TimeUnit.SECONDS.sleep(2);
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "执行自己的任务，打印" + (i+1));
            }
            lock.unlock();
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
        Semaphoretest7 semaphoreTest7 = new Semaphoretest7();
        ExecutorService executorService = new ThreadPoolExecutor(4,4,10, TimeUnit.SECONDS,new ArrayBlockingQueue<>(4));
        for (int i = 0; i < 4; i++) {
            executorService.execute(semaphoreTest7);
        }
        executorService.shutdown();
    }
}
