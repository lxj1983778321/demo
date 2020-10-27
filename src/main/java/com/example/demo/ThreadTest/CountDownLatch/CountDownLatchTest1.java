package com.example.demo.ThreadTest.CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author muyou
 * @date 2020/10/15 9:41
 *初步使用：查看CountDownLatch计数不为0时的线程阻塞效果
 */
public class CountDownLatchTest1 implements Runnable{
    //创建一个计数的CountDownLatch类对象
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public void testMetchod(){
        try {
            System.out.println(Thread.currentThread().getName()+"开始执行，执行时间为：" + System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName()+"进入等待状态,进入时间为：" + System.currentTimeMillis());
            countDownLatch.await();
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName()+"执行结束，结束时间为："+System.currentTimeMillis());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void downMethod(){
        System.out.println(Thread.currentThread().getName()+"调用countDownLatch的countDown()方法,调用时间为："+System.currentTimeMillis());
        countDownLatch.countDown();
    }
    @Override
    public void run() {
        testMetchod();
    }


    public static void main(String[] args) {
        try {
            CountDownLatchTest1 countDownLatchTest1 = new CountDownLatchTest1();
            Thread thread = new Thread(countDownLatchTest1);
            thread.start();
            Thread.sleep(5000);
            countDownLatchTest1.downMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
