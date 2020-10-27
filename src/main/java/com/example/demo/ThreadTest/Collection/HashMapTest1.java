package com.example.demo.ThreadTest.Collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author muyou
 * @date 2020/10/23 15:39
 * 验证HashMap为非线程安全
 * 运行结果数据丢失，全部为null，
 */
public class HashMapTest1 implements Runnable{

    private  static HashMap<String,String> map = new HashMap();
    private static AtomicInteger atomicInteger = new AtomicInteger();
    @Override
    public void run() {
        while (atomicInteger.get() < 50000){
            String name = Thread.currentThread().getName()+atomicInteger.get();
            map.put(name,name);
            atomicInteger.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(5,5,5, TimeUnit.SECONDS,new ArrayBlockingQueue<>(5));
        HashMapTest1 hashMapTest1 = new HashMapTest1();
        for (int i = 0; i < 5; i++) {
            executorService.execute(hashMapTest1);
        }
        TimeUnit.SECONDS.sleep(10);
        for (int i = 0; i < map.size(); i++) {
            System.out.println(map.get(i));
        }
    }
}