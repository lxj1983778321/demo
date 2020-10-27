package com.example.demo.ThreadTest.Collection;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/27 10:27
 * CopyOnWriteArrayList
 * ArrayList非线程安全，如果需要在并发中实现线程安全，则可以使用CopyOnWriteArrayList
 */
public class CopyOnWriteArrayListTest1 implements Runnable{

    private CopyOnWriteArrayList<String> list;

    CopyOnWriteArrayListTest1(){
        this.list = new CopyOnWriteArrayList<String>();
    }

    public CopyOnWriteArrayList<String> getList() {
        return list;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 50; i++) {
            list.add(Thread.currentThread().getName() + " : " + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayListTest1 listTest1 = new CopyOnWriteArrayListTest1();
        ExecutorService executorService = new ThreadPoolExecutor(5,5,5, TimeUnit.SECONDS,new ArrayBlockingQueue<>(5));
        executorService.execute(listTest1);
        executorService.execute(listTest1);
        TimeUnit.SECONDS.sleep(5);
        System.out.println(listTest1.getList().size());
        while (!(listTest1.getList().isEmpty())){
            System.out.println(listTest1.getList().remove(0));
        }
    }
}
