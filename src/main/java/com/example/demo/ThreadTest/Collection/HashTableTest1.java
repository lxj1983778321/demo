package com.example.demo.ThreadTest.Collection;

import java.util.Hashtable;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author muyou
 * @date 2020/10/23 16:06
 * hashTable虽然是线程安全的，但目前已不推荐使用了，
 * 且遍历时如果使用iterator迭代器与Hashtable的remove/put联用的话会报出ConcurrentModificationException异常
 */
public class HashTableTest1 implements Runnable{
    private static Hashtable hashtable = new Hashtable();
    private static AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void run() {
        while (atomicInteger.get() <= 50000){
            String name = Thread.currentThread().getName() + atomicInteger.get();
            hashtable.put(name,name);
            atomicInteger.incrementAndGet();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(5,5,5, TimeUnit.SECONDS,new ArrayBlockingQueue<>(5));
        HashTableTest1 hashMapTest1 = new HashTableTest1();
        for (int i = 0; i < 5; i++) {
            executorService.execute(hashMapTest1);
        }
        TimeUnit.SECONDS.sleep(10);
        Iterator iterator = hashtable.keySet().iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
            hashtable.remove(iterator.next());
        }
    }
}
