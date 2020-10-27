package com.example.demo.ThreadTest.CountDownLatch;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author muyou
 * @date 2020/10/15 10:09
 * 目的：多个线程与同步点间的阻塞特性
 */
public class CountDownLatchTest2 implements Runnable{

    private CountDownLatch countDownLatch;
    private Lock lock = new ReentrantLock();

    CountDownLatchTest2(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+"执行countDown()方法,执行时间为："+System.currentTimeMillis());
            countDownLatch.countDown();
            System.out.println("当前有计数个数为：" + countDownLatch.getCount());
            TimeUnit.SECONDS.sleep(2);
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            //创建10个计数
            CountDownLatch countDownLatch = new CountDownLatch(10);
            CountDownLatchTest2 countDownLatchTest2 = new CountDownLatchTest2(countDownLatch);
            System.out.println("main方法---》当前有计数个数为：" + countDownLatch.getCount());
            ExecutorService executorService = new ThreadPoolExecutor(10,10,10,TimeUnit.SECONDS,new ArrayBlockingQueue<>(10));
            for (int i = 0; i < 10; i++) {
                executorService.execute(countDownLatchTest2);
            }
            System.out.println(Thread.currentThread().getName()+"调用await()方法，进入等待状态");
            countDownLatch.await();
            System.out.println(Thread.currentThread().getName()+"解除等待状态");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
