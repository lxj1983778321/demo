package com.example.demo.ThreadTest.Collection;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/27 10:19
 * ConcurrentLinkedDeque支持队列头尾双端操作
 *
 */
public class ConcurrentLinkedDequeTest1 implements Runnable{

    private ConcurrentLinkedDeque deque;

    ConcurrentLinkedDequeTest1(){
        this.deque = new ConcurrentLinkedDeque();
    }

    public ConcurrentLinkedDeque getDeque() {
        return deque;
    }


    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
            for (int i = 0; i <= 50; i++) {
                deque.addLast(Thread.currentThread().getName() + " : " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedDequeTest1 dequeTest1 = new ConcurrentLinkedDequeTest1();
        ExecutorService executorService =
                new ThreadPoolExecutor(2,3,5,TimeUnit.SECONDS,new ArrayBlockingQueue<>(3));
        executorService.execute(dequeTest1);
        TimeUnit.SECONDS.sleep(1);
        executorService.execute(dequeTest1);
        TimeUnit.SECONDS.sleep(5);
        while (!(dequeTest1.getDeque().isEmpty())){
            System.out.println(dequeTest1.getDeque().removeFirst());
        }
    }
}
