package com.example.demo.ThreadTest.Executors;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/19 15:35
 * 线程池拒绝策略---》CallerRunsPolicy策略
 * CallerRunsPolicy策略：当任务添加到线程池中被拒绝时，会调用线程池的Thread线程对象处理被拒绝的任务
 */
public class ThreadPoolExecutorTest5 {
    public static void main(String[] args) {
        MyThreadA myThreadA = new MyThreadA();
        myThreadA.setName("A");
        myThreadA.start();
        System.out.println("main方法执行完毕");
    }
}
class MyThreadA extends Thread{
    @Override
    public void run() {
        MythreadB mythreadB = new MythreadB();
        ExecutorService executorService =
                new ThreadPoolExecutor(2,3,5,TimeUnit.SECONDS,new LinkedBlockingDeque<>(2),new ThreadPoolExecutor.CallerRunsPolicy());
        System.out.println("a开始执行，时间："+System.currentTimeMillis());
        executorService.execute(mythreadB);
        executorService.execute(mythreadB);
        executorService.execute(mythreadB);
        executorService.execute(mythreadB);
        executorService.execute(mythreadB);
        executorService.execute(mythreadB);
        System.out.println("a执行完毕，时间："+System.currentTimeMillis());
    }
}

class MythreadB extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"执行B的run方法");
    }
}