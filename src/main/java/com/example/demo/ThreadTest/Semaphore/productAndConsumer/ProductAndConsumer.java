package com.example.demo.ThreadTest.Semaphore.productAndConsumer;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * @author muyou
 * @date 2020/10/14 15:05
 * 使用semaphore实现多生产者/多消费者，控制并发数量为5
 */
public class ProductAndConsumer {
    private static  LinkedList<Integer> list;
    private static int capacity;//容量
    private static Lock lock;
    private static Condition productCondition;
    private static Condition consumerCondition;

    public ProductAndConsumer(int capacity){
        this.list = new LinkedList<>();
        this.capacity = capacity;
        this.lock = new ReentrantLock();
        this.productCondition = lock.newCondition();
        this.consumerCondition = lock.newCondition();
    }

    public static void main(String[] args) {
        ProductAndConsumer productAndConsumer = new ProductAndConsumer(5);
        Product product = new Product(list,capacity,lock,productCondition,consumerCondition);
        Consumer consumer = new Consumer(list,capacity,lock,productCondition,consumerCondition);
        ExecutorService executorService1 =
                new ThreadPoolExecutor(12,12,10, TimeUnit.SECONDS,new ArrayBlockingQueue<>(12));

        ExecutorService executorService2 =
                new ThreadPoolExecutor(12,12,10, TimeUnit.SECONDS,new ArrayBlockingQueue<>(12));
        for (int i = 0; i < 6 ; i++) {
            executorService1.execute(product);
            executorService2.execute(consumer);
        }
        executorService1.shutdown();
        executorService2.shutdown();
    }
}
