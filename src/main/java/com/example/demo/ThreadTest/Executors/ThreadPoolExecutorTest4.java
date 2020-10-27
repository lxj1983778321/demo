package com.example.demo.ThreadTest.Executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author muyou
 * @date 2020/10/19 15:35
 * 线程池拒绝策略---》AbortPolicy策略
 * AbortPolicy策略：当任务添加到线程池中被拒绝时，抛出RejectExecutionException异常
 */
public class ThreadPoolExecutorTest4 {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+"执行时间为："+System.currentTimeMillis());
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        ExecutorService executorService = new ThreadPoolExecutor(2,3,10,TimeUnit.SECONDS,new ArrayBlockingQueue<>(1));
        for (int i = 0; i < 5; i++) {
            executorService.execute(runnable);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
