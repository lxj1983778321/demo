package com.example.demo.ThreadTest.Collection;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author muyou
 * @date 2020/10/26 11:12
 * 支持并发操作的map对象
 */
public class ConcurrentHashMapTest1 implements Runnable{

    private static ConcurrentHashMap<Integer,String> concurrentHashMap = new ConcurrentHashMap<Integer, String>();
    private volatile AtomicInteger atomicInteger = new AtomicInteger(0);
    @Override
    public void run() {
        try {
            while (atomicInteger.get()<=100){
                TimeUnit.MILLISECONDS.sleep(2);
                if(atomicInteger.get() <= 100){
                    TimeUnit.MILLISECONDS.sleep(2);
                    concurrentHashMap.put(atomicInteger.get(),","+Thread.currentThread().getName()+"-->"+atomicInteger.get());
                    atomicInteger.incrementAndGet();
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(5,5,5, TimeUnit.SECONDS,new ArrayBlockingQueue<>(5));
        ConcurrentHashMapTest1 concurrentHashMapTest1 =
                new ConcurrentHashMapTest1();
        for (int i = 0; i < 2; i++) {
            executorService.execute(concurrentHashMapTest1);
            TimeUnit.MILLISECONDS.sleep(2);
        }
        TimeUnit.SECONDS.sleep(10);
        for (Map.Entry<Integer,String> entry:concurrentHashMap.entrySet()) {
            System.out.println(entry.getKey()+entry.getValue());
        }
    }
}