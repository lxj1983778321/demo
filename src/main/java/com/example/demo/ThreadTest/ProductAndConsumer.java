package com.example.demo.ThreadTest;

import java.util.LinkedList;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author muyou
 * @date 2020/9/22 17:03
 * 生产者和消费者模式
  */

public class ProductAndConsumer {
   private static LinkedList<Integer> list;
   private static int capacity;
   private static Lock lock;
   private static Condition productCondition;
   private static Condition consumerCondition;

   ProductAndConsumer(int capacity){
       this.list = new LinkedList<Integer>();
       this.capacity = capacity;
       this.lock = new ReentrantLock();
       this.productCondition = this.lock.newCondition();
       this.consumerCondition = this.lock.newCondition();
   }

    public static void main(String[] args) {
        ProductAndConsumer productAndConsumer = new ProductAndConsumer(10);
        Product product = new Product(list,capacity,lock,productCondition,consumerCondition);
        Consumer consumer = new Consumer(list,capacity,lock,productCondition,consumerCondition);

        ExecutorService executorService = new ThreadPoolExecutor(10,10,10,TimeUnit.SECONDS,new ArrayBlockingQueue<>(10));
        for (int i = 0; i < 5; i++) {
            executorService.execute(product);
            executorService.execute(consumer);
        }
        //停止接收新任务，原任务继续执行
       executorService.shutdown();
   }

}
class Product implements Runnable{
    private LinkedList<Integer> list;
    private int capacity;
    private Lock lock;
    private Condition product;
    private Condition consumer;
    private volatile int i = 1;

    Product(LinkedList<Integer> list,int capacity,Lock lock,Condition product,Condition consumer){
        this.list = list;
        this.capacity = capacity;
        this.lock = lock;
        this.product = product;
        this.consumer = consumer;
    }

    public void product(){
        try {
            lock.lock();
            while (list.size() == capacity){
                System.out.println("消息队列已满，生产者进入等待状态");
                product.await();
            }
            TimeUnit.SECONDS.sleep(1);
            list.addLast(i);
            System.out.println(Thread.currentThread().getName() + "生产者生产消息" + i);
            i = i + 1;
            consumer.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


    @Override
    public void run() {
        while (true){
            product();
        }
    }
}
class Consumer implements Runnable{

    private LinkedList<Integer> list;
    private int capacity;
    private Lock lock;
    private Condition product;
    private Condition consumer;

    Consumer(LinkedList<Integer> list,int capacity,Lock lock,Condition product,Condition consumer){
        this.list = list;
        this.capacity = capacity;
        this.lock = lock;
        this.product = product;
        this.consumer = consumer;
    }

    public void consumer(){
        try {
            lock.lock();
            while (list.size() == 0){
                System.out.println("消息队列已空，消费者进入等待状态");
                consumer.await();
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + "消费者消费消息" +   list.removeFirst());
            product.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        while (true){
            consumer();
        }
    }
}