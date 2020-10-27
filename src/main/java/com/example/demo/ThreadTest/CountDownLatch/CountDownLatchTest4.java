package com.example.demo.ThreadTest.CountDownLatch;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/15 14:16
 * 方法await(Long timeout,TimeUnit unit)作用：
 * 线程在指定的最大时间单位内进入waiting状态，如果超过这个时间则自动唤醒，程序继续向下运行
 */
public class CountDownLatchTest4 implements Runnable{
    private CountDownLatch countDownLatch;

    CountDownLatchTest4(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    public void testMethod(){
        try {
            System.out.println(Thread.currentThread().getName()+"准备开始执行，准备时间为："+System.currentTimeMillis());
            countDownLatch.await(3, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName()+"执行结束，执行结束时间为："+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        testMethod();
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        CountDownLatchTest4 countDownLatchTest4 = new CountDownLatchTest4(countDownLatch);
        ExecutorService executorService = new ThreadPoolExecutor(10,10,10, TimeUnit.SECONDS,new ArrayBlockingQueue<>(10));
        for (int i = 0; i < 10; i++) {
            executorService.execute(countDownLatchTest4);
        }
        executorService.shutdown();
    }
}
