package com.example.demo.ThreadTest.Fork_Join;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/23 10:23
 */
public class ForkJoinTest6 {

    /**
     * public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
     * invokeAll具有阻塞特性
     * @param args
     */
    public static void main(String[] args) {
        try {
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            List list = new ArrayList();
            list.add(new MyCallable6(5));
            list.add(new MyCallable6(4));
            list.add(new MyCallable6(3));
            list.add(new MyCallable6(2));
            list.add(new MyCallable6(1));
            List<Future<String>> futureList = forkJoinPool.invokeAll(list);
            for (int i = 0; i < futureList.size(); i++) {
                System.out.println(futureList.get(i).get() + " nowTime:" + System.currentTimeMillis());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class MyCallable6 implements Callable<String>{
    private int sleepValue;

    MyCallable6(int sleepValue){
        this.sleepValue = sleepValue;
    }

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName()+" sleep:" + sleepValue + " nowTime:"+System.currentTimeMillis());
        TimeUnit.SECONDS.sleep(sleepValue);
        return Thread.currentThread().getName()+" 的返回值";
    }
}
