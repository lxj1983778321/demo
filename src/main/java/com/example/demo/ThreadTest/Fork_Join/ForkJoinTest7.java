package com.example.demo.ThreadTest.Fork_Join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @author muyou
 * @date 2020/10/23 10:35
 *
 */
public class ForkJoinTest7 {


    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //是异步的
        forkJoinPool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+" begin implement <-----> "+System.currentTimeMillis());
                    TimeUnit.SECONDS.sleep(4);
                    System.out.println(Thread.currentThread().getName()+" end implement <-----> "+System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        /*
            //运行结果为：main end 线程立即销毁，
            System.out.println("main end");
        */

        /*
            //运行结果表明：调用shutdown后，任务依然运行，直到结束
            TimeUnit.SECONDS.sleep(1);
            forkJoinPool.shutdown();
            System.out.println("main end");
            TimeUnit.SECONDS.sleep(5);
        */

/*        //代码运行后进程被马上销毁，说明ForkJoin对调用shutdown()方法后再执行的任务时出现异常，进程也就马上销毁了，正在运行的任务也被销毁了
        TimeUnit.SECONDS.sleep(1);
        forkJoinPool.shutdown();
        forkJoinPool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+" begin implement <-----> "+System.currentTimeMillis());
                    TimeUnit.SECONDS.sleep(4);
                    System.out.println(Thread.currentThread().getName()+" end implement <-----> "+System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("main end");
        TimeUnit.SECONDS.sleep(5);*/

        //为了防止在关闭pool时再运行任务，可以加入判断解决进程意外销毁的问题
        TimeUnit.SECONDS.sleep(1);
        forkJoinPool.shutdown();
        if(forkJoinPool.isShutdown() == false){
            forkJoinPool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName()+" begin implement <-----> "+System.currentTimeMillis());
                        TimeUnit.SECONDS.sleep(4);
                        System.out.println(Thread.currentThread().getName()+" end implement <-----> "+System.currentTimeMillis());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        System.out.println("main end");
        TimeUnit.SECONDS.sleep(5);
    }
}
