package com.example.demo.ThreadTest.Semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author muyou
 * @date 2020/10/14 11:38
 * 方法availablePermits()是返回当前可用的许可数量
 * 方法drainPermits()是返回立即可用的所有许可数量，并将可用许可数置为0
 * 方法getQueueLength()作用是取得等待许可的线程个数
 * 方法hasQueuedThreads()作用是判断有没有线程正在等待这个许可
 * 方法tryAcquire()作用是尝试获取1个许可，如果获取不到就返回false，常与if等条件判断语句连用
 * 方法tryAcquire(int primits)作用是尝试获取primits个许可，如果获取不到就返回false
 * 方法tryAcquire(int primits,long timeout,TimeUnit time)作用是在指定时间内获取primits个许可，如果获取失败返回false
 */
public class SemaphoreTest5 {

    public static void main(String[] args) {
        try {
            Semaphore semaphore = new Semaphore(10);

            System.out.println("可用许可数量：" + semaphore.availablePermits());
            semaphore.acquire(10);
            System.out.println("可用许可数量：" + semaphore.availablePermits());
            semaphore.release(10);
            System.out.println("可用许可数量：" + semaphore.availablePermits());
            System.out.println("可用许可数量：" + semaphore.drainPermits());
            System.out.println("可用许可数量：" + semaphore.availablePermits());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
