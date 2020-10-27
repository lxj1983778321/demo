package com.example.demo.ThreadTest.CountDownLatch;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/15 10:54
 * 目的：等待所有线程到位后再执行以下的逻辑
 */
public class CountDownLatchTest3 implements Runnable{

    private CountDownLatch countDownLatch;

    CountDownLatchTest3(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    public void testMethod(){
        try {
            System.out.println(Thread.currentThread().getName()+"准备，准备时间为：" + System.currentTimeMillis());
            if(Thread.currentThread().getName().equals("pool-1-thread-2")){
                System.out.println(Thread.currentThread().getName()+"进入中断方法");
                Thread.currentThread().interrupt();
            }
            countDownLatch.await();
            System.out.println(Thread.currentThread().getName()+"结束，结束时间为：" + System.currentTimeMillis());
            downMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void downMethod(){
        System.out.println(Thread.currentThread().getName()+"调用countdown方法，时间为："+System.currentTimeMillis());
        countDownLatch.countDown();
        System.out.println(Thread.currentThread().getName()+"调用countdown方法结束,时间为："+System.currentTimeMillis());
    }

    @Override
    public void run() {
        testMethod();
    }

    public static void main(String[] args) throws InterruptedException{
        CountDownLatch countDownLatch = new CountDownLatch(10);
        CountDownLatchTest3 countDownLatchTest3 = new CountDownLatchTest3(countDownLatch);
        System.out.println("main方法---》当前有计数个数为：" + countDownLatch.getCount());
        ExecutorService executorService = new ThreadPoolExecutor(10,10,10, TimeUnit.SECONDS,new ArrayBlockingQueue<>(10));
        for (int i = 0; i < 10; i++) {
            executorService.execute(countDownLatchTest3);
            TimeUnit.SECONDS.sleep(2);
        }

    }
}
