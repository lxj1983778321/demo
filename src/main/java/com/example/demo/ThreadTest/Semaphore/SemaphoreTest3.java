package com.example.demo.ThreadTest.Semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author muyou
 * @date 2020/10/14 10:44
 * Semapore类中的acquire(int permits)参数作用以及动态添加permits许可数量
 */
public class SemaphoreTest3 {
    /**
     * 有参方法acquire(int permits)作用是每调用一次就使用permits个许可
     *如果多次调用release()\release(int permits)方法，还可以动态增加许可的个数
     */
    public static void main(String[] args) {
        try {
            Semaphore semaphore = new Semaphore(10);
            //semaphore.availablePermits()方法作用是显示当前可用的许可数量
            System.out.println("当前可用许可数量为：" + semaphore.availablePermits());
            semaphore.acquire(2);
            semaphore.acquire();
            semaphore.acquire(7);
            System.out.println("当前可用许可数量为："+semaphore.availablePermits());
            semaphore.release(2);
            semaphore.release();
            semaphore.release(7);
            System.out.println("当前可用许可数量为："+semaphore.availablePermits());
            semaphore.release(5);
            System.out.println("当前可用许可数量为："+semaphore.availablePermits());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
