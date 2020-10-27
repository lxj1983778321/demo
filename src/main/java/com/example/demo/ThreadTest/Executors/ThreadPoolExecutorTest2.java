package com.example.demo.ThreadTest.Executors;

import java.util.concurrent.*;

/**
 * 使用ThreadFactory+execute()+UncaughtExceptionHandler处理异常
 * @author muyou
 * @date 2020/10/19 10:50
 */
public class ThreadPoolExecutorTest2 implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"进入执行，时间为："+System.currentTimeMillis());
        String abc = null;
        abc.indexOf(0);
        System.out.println(Thread.currentThread().getName()+":"+System.currentTimeMillis());
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutorTest2 threadPoolExecutorTest2 = new ThreadPoolExecutorTest2();
        ExecutorService executorService =
                new ThreadPoolExecutor(4,5,10, TimeUnit.SECONDS,new ArrayBlockingQueue<>(5),new MyThreadFactoryTest2());
        //除了使用构造方法添加外，也可以使用setThreadFactory()方法添加自定义线程工厂
        //ThreadPoolExecutor的setThreadFactory()方法添加线程工厂

        for (int i = 0; i < 3; i++) {
            executorService.execute(threadPoolExecutorTest2);
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
class MyThreadFactoryTest2 implements ThreadFactory {
    private volatile int i = 0;
    @Override
    public Thread newThread(Runnable runnable) {
        i = i+1;
        Thread thread = new Thread(runnable);
        thread.setName("ThreadPoolExecutorTest2:"+i);

        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                System.out.println(Thread.currentThread().getName()+"出现异常，时间为："+System.currentTimeMillis());
                System.out.println("出现的异常信息为："+throwable.getMessage());
                throwable.printStackTrace();
            }
        });
        return thread;
    }
}