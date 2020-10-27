package com.example.demo.ThreadTest.Collection;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/27 9:52
 * ConcurrentLinkedQueue类提供了并发环境的队列操作
 * 方法poll()当没有获得数据时返回null，如果有数据时则移除表头，并将表头进行返回
 * element()：当没有数据时出现NoSuchElementException异常，如果有数据时则返回表头项
 * peek()：当没有数据时返回null，有数据时不移除表头，只将表头进行返回
 */
public class ConcurrentLinkedQueueTest1 implements Callable<ConcurrentLinkedQueue> {
    private ConcurrentLinkedQueue queue;

    ConcurrentLinkedQueueTest1(){
        queue = new ConcurrentLinkedQueue();
    }

    public ConcurrentLinkedQueue getQueue() {
        return queue;
    }


    @Override
    public ConcurrentLinkedQueue call() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        for (int i = 0; i <= 50 ; i++) {
            queue.add(Thread.currentThread().getName()+" : "+i);
        }
        return queue;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ConcurrentLinkedQueueTest1 queueTest1 = new ConcurrentLinkedQueueTest1();
        ExecutorService executorService =
                new ThreadPoolExecutor(2,3,5,TimeUnit.SECONDS,new ArrayBlockingQueue<>(3));
        Future<ConcurrentLinkedQueue> future = executorService.submit(queueTest1);
        TimeUnit.SECONDS.sleep(2);
        Future<ConcurrentLinkedQueue> future1 = executorService.submit(queueTest1);
        TimeUnit.SECONDS.sleep(10);

        ConcurrentLinkedQueue queue = future1.get();
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }
}

