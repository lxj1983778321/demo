package com.example.demo.ThreadTest.Executors;

import java.util.Iterator;
import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/19 15:35
 * 线程池拒绝策略---》DiscardOldestPolicy策略
 * DiscardOldestPolicy策略：当任务添加到线程池中被拒绝时，线程池会放弃等待队列中最旧的未处理任务，然后将拒绝的任务添加到等待队列中
 */
public class ThreadPoolExecutorTest6 implements Runnable{

    private String userName;

    ThreadPoolExecutorTest6(String userName){
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public void run() {
        try {
            System.out.println(userName+"：run");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(2);
        ExecutorService executorService = new ThreadPoolExecutor(2,3,10,TimeUnit.SECONDS,queue,new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int i = 0; i < 5; i++) {
            executorService.execute(new ThreadPoolExecutorTest6("Runnable"+ (i+1)));
        }

        Thread.sleep(50);

        Iterator iterator = queue.iterator();
        while (iterator.hasNext()){
            Object obj = iterator.next();
            System.out.println(((ThreadPoolExecutorTest6)obj).getUserName());
        }

        executorService.execute(new ThreadPoolExecutorTest6("Runnable6"));
        executorService.execute(new ThreadPoolExecutorTest6("Runnable7"));

        iterator = queue.iterator();
        while (iterator.hasNext()){
            Object obj = iterator.next();
            System.out.println(((ThreadPoolExecutorTest6)obj).getUserName());
        }
    }
}