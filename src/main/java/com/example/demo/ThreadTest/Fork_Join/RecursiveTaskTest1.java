package com.example.demo.ThreadTest.Fork_Join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * @author muyou
 * @date 2020/10/22 15:44
 * 使用RecursiveTask执行多个任务并打印返回值
 * 任务运行之间是异步的，但是join是同步的（一直等待获取返回值）
 */
public class RecursiveTaskTest1 {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTaskA = pool.submit(new MyRecursiveTaskA());
        ForkJoinTask<Integer> forkJoinTaskB = pool.submit(new MyRecursiveTaskB());

        System.out.println(forkJoinTaskA.join() + " A :" + System.currentTimeMillis());
        System.out.println(forkJoinTaskB.join() + " B :" + System.currentTimeMillis());
    }
}
class MyRecursiveTaskA extends RecursiveTask<Integer>{

    @Override
    protected Integer compute() {
        try {
            System.out.println(Thread.currentThread().getName()+" begin A implement " + System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName()+" end A implement " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 100;
    }
}

class MyRecursiveTaskB extends RecursiveTask<Integer>{

    @Override
    protected Integer compute() {
        try {
            System.out.println(Thread.currentThread().getName()+" begin B implement " + System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(4);
            System.out.println(Thread.currentThread().getName()+" end B implement " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 100;
    }
}