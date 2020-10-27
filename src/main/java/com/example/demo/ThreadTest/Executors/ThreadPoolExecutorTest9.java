package com.example.demo.ThreadTest.Executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author muyou
 * @date 2020/10/23 11:05
 * 验证shutdown后再提交任务
 * 运行结果表明：
 * 运行shutdown后再提交任务会抛出RejectedExecutionException异常，所以不会打印信息 main end 但线程池中已经运行的任务会正常运行结束
 * shutdownNow()：会立即停止正在运行的任务，并关闭线程池，抛出异常
 */
public class ThreadPoolExecutorTest9 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(5,5,10,TimeUnit.SECONDS,new ArrayBlockingQueue<>(5));
        executorService.execute(new MyRunnable9());
        executorService.execute(new MyRunnable9());
        TimeUnit.SECONDS.sleep(2);
        //executorService.shutdownNow();
        executorService.shutdown();
        executorService.execute(new MyRunnable9());
        System.out.println("main end");
    }
}
class MyRunnable9 implements Runnable{
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+" begin <----->" + System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName()+" end <----->" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}