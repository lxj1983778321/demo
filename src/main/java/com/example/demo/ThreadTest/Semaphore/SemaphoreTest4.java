package com.example.demo.ThreadTest.Semaphore;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/14 10:53
 * 方法acquireUninterruptibly();的使用
 * acquireUninterruptibly();方法的作用是：使等待进入acquire()方法的线程，不允许被中断
 *
 */
public class SemaphoreTest4{
    private Semaphore semaphore = new Semaphore(1);

    /**
     *允许中断的例子
     */
    public void isInterruptMethod(){
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName()+"开始执行，执行时间为：" + System.currentTimeMillis());
            System.out.println(Thread.currentThread().getName()+"执行结束时间为：" + System.currentTimeMillis());
            semaphore.release();
        }catch (InterruptedException e){
            System.out.println(Thread.currentThread().getName()+"进入了catch模块，被中断了");
            e.printStackTrace();
        }
    }


    /**
     * 调用acquireUninterruptibly()方法不允许中断的例子
     */
    public void notInterruptMethod(){
            semaphore.acquireUninterruptibly();
            System.out.println(Thread.currentThread().getName()+"开始执行，执行时间为：" + System.currentTimeMillis());
            System.out.println(Thread.currentThread().getName()+"执行结束时间为：" + System.currentTimeMillis());
            semaphore.release();
    }



public static void main(String[] args) {
        try {
            SemaphoreTest4 semaphoreTest4 = new SemaphoreTest4();
            ThreadA threadA = new ThreadA(semaphoreTest4);
            threadA.setName("threadA");
            threadA.start();
            ThreadB threadB = new ThreadB(semaphoreTest4);
            threadB.setName("threadB");
            threadB.start();

            TimeUnit.SECONDS.sleep(1);
            threadB.interrupt();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
class ThreadA extends Thread{
    private SemaphoreTest4 semaphoreTest4;

    ThreadA(SemaphoreTest4 semaphoreTest4){
        this.semaphoreTest4 = semaphoreTest4;
    }

    @Override
    public void run() {
        //semaphoreTest4.isInterruptMethod();
        semaphoreTest4.notInterruptMethod();
    }
}

class ThreadB extends Thread{
    private SemaphoreTest4 semaphoreTest4;

    ThreadB(SemaphoreTest4 semaphoreTest4){
        this.semaphoreTest4 = semaphoreTest4;
    }

    @Override
    public void run() {
        //semaphoreTest4.isInterruptMethod();
        semaphoreTest4.notInterruptMethod();
    }
}