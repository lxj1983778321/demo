package com.example.demo.ThreadTest.Semaphore.productAndConsumer;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author muyou
 * @date 2020/10/14 15:04
 * 使用semaphore实现多生产者/多消费者，控制并发数量为5
 */
public class Product implements Runnable{
    private LinkedList<Integer> list;
    private int capacity;//容量
    private Lock lock;
    private Condition productCondition;
    private Condition consumerCondition;
    private Semaphore semaphore = new Semaphore(4);
    private volatile int i = 0;

    public Product(LinkedList<Integer> list, int capacity, Lock lock, Condition productCondition, Condition consumerCondition){
        this.list = list;
        this.capacity = capacity;
        this.lock = lock;
        this.productCondition = productCondition;
        this.consumerCondition = consumerCondition;
    }

    /**
     * 生产数据逻辑
     */
    public void productMessage(){
        try {
            semaphore.acquire();
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "得到许可，开始执行，执行时间为：，" + System.currentTimeMillis());
            while (list.size() == capacity){
                System.out.println("队列已满" + Thread.currentThread().getName() + "进入等待");
                productCondition.await();
            }
            TimeUnit.SECONDS.sleep(4);
            i = i+1;
            System.out.println("当前可用许可数为："+semaphore.availablePermits());
            System.out.println("当前等待线程数为："+semaphore.getQueueLength());
            System.out.println(Thread.currentThread().getName() + "执行结果为，生产数据：" + i);
            list.addLast(i);
            consumerCondition.signalAll();
            lock.unlock();
            semaphore.release();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){
            productMessage();
        }
    }
}
