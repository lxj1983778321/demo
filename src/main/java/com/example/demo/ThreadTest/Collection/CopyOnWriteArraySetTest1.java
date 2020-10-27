package com.example.demo.ThreadTest.Collection;

import java.util.Iterator;
import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/27 10:37
 * CopyOnWriteArraySet可以解决多线程下HashSet线程不安全问题
 */
public class CopyOnWriteArraySetTest1 implements Runnable{

    private CopyOnWriteArraySet<String> set;

    CopyOnWriteArraySetTest1(){
        //构造器底层用的是new CopyOnWriteArrayList();
        this.set = new CopyOnWriteArraySet<String>();
    }

    public CopyOnWriteArraySet<String> getSet() {
        return set;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 50; i++) {
            set.add(Thread.currentThread().getName()+" : " + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArraySetTest1 setTest1 = new CopyOnWriteArraySetTest1();
        ExecutorService executorService = new ThreadPoolExecutor(5,5,5, TimeUnit.SECONDS,new ArrayBlockingQueue<>(5));
        executorService.execute(setTest1);
        executorService.execute(setTest1);
        TimeUnit.SECONDS.sleep(5);

        System.out.println(setTest1.getSet().size());
        if(!(setTest1.getSet().isEmpty())){
            Iterator iterator = setTest1.getSet().iterator();
            while (iterator.hasNext()){
                System.out.println(iterator.next());
            }
        }
    }
}
