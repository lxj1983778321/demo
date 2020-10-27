package com.example.demo.ThreadTest.Executors;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/19 15:35
 * afterExecute()和beforeExecute()重写ThreadPoolExecutor类中的这俩个方法，可以对线程池中执行的线程对象进行监控
 */
public class ThreadPoolExecutorTest8 implements Runnable{
    private String userName;

    ThreadPoolExecutorTest8(String userName){
        this.userName = userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }


    @Override
    public void run() {
        try {
            System.out.println("开始，打印："+userName+"，时间为："+System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(2);
            System.out.println("打印结束，时间为："+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyThreadPoolExecutor myThreadPoolExecutor =
                new MyThreadPoolExecutor(2,3,5,TimeUnit.SECONDS,new ArrayBlockingQueue<>(2));
        myThreadPoolExecutor.execute(new ThreadPoolExecutorTest8("A"));
        myThreadPoolExecutor.execute(new ThreadPoolExecutorTest8("B"));
    }
}

class MyThreadPoolExecutor extends ThreadPoolExecutor{

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }


    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println(Thread.currentThread().getName()+"准备执行");
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        System.out.println(Thread.currentThread().getName()+"执行完毕");
    }
}