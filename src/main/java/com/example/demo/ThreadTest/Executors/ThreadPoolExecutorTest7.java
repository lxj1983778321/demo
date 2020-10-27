package com.example.demo.ThreadTest.Executors;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author muyou
 * @date 2020/10/19 15:35
 * 线程池拒绝策略---》DiscardPolicy策略
 * DiscardPolicy策略：当任务添加到线程池中被拒绝时，线程池将丢弃被拒绝的任务
 */
public class ThreadPoolExecutorTest7{
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName()+"执行结束，时间为："+System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        ArrayBlockingQueue queue = new ArrayBlockingQueue(2);
        ExecutorService executorService =
                new ThreadPoolExecutor(2,3,5,TimeUnit.SECONDS,queue,new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 8; i++) {
            executorService.execute(runnable);
        }
    }
}