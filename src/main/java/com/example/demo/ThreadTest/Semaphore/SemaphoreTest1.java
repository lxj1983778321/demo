package com.example.demo.ThreadTest.Semaphore;

import java.util.concurrent.*;

/**
 *@Description TODO
 *@Author muyou
 *@DATE 2020/10/14 9:33
 *@Version 1.0
 *类Semphore的同步性
 **/
public class SemaphoreTest1 implements Runnable{

    /**
     * permits表示同一时间最多允许多少个线程同时执行acquire()和release()之间的代码，
     * 构造函数后边还可以跟上，Boolean值，true表示使用公平信号量，false使用非公平信号量
     * 公平信号量作用是：线程启动顺序与调用semaphore.acquire();的顺序有关，先启动的线程优先获得许可
     *、new Semaphore(1);最多允许1个线程执行acquire()和release()之间的代码
     */
    private Semaphore semaphore = new Semaphore(1);
    private volatile int i = 1;
    /**
     * 多线程中的同步概念：排着队去执行一个任务，执行任务是一个一个执行，并不能并发执行
     */
    public void testMethod(){
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName()+"进入执行，时间为"+System.currentTimeMillis());
            i = i+1;
            System.out.println(Thread.currentThread().getName()+"修改共享变量的值为：" + i);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+"执行完毕时间为："+System.currentTimeMillis());
            semaphore.release();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        testMethod();
    }

    public static void main(String[] args) {
        SemaphoreTest1 semaphoreTest1 = new SemaphoreTest1();
        ExecutorService executorService = new ThreadPoolExecutor(4,4,10,TimeUnit.SECONDS,new ArrayBlockingQueue<>(4));
        for (int i = 0; i < 3; i++) {
            executorService.execute(semaphoreTest1);
        }
        executorService.shutdown();
    }
}
