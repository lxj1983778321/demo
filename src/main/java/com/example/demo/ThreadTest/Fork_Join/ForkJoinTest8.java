package com.example.demo.ThreadTest.Fork_Join;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

/**
 * @author muyou
 * @date 2020/10/23 14:15
 */
public class ForkJoinTest8 {

    /**
     * isTerminating()和isTerminated()
     * isTerminating():判断线程是否正在终止，正在终止返回true
     * （如果正在执行的程序，处于shutdown()\shutDownNow()方法之后处于正在终止但尚未完全终止时）
     * isTerminated()：判断已经关闭，返回true
     * @param args
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask forkJoinTask = forkJoinPool.submit(runnable);
        System.out.println("A =" + forkJoinPool.isTerminating());
        forkJoinPool.shutdown();
        System.out.println("B =" + forkJoinPool.isTerminating());
        System.out.println(forkJoinTask.get());
        TimeUnit.SECONDS.sleep(2);
        System.out.println("C =" + forkJoinPool.isTerminated());

    }
}
