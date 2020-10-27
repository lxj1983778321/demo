package com.example.demo.ThreadTest.FutureAndCallable;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/20 9:27
 * 使用ExecutorService接口中的方法submit(Runnable,T result)
 * T result可以作为执行结果的返回值，不需要通过get()方法来进行获取
 */
public class FutureAndCallableTest3 implements Runnable {
    private String name;

    FutureAndCallableTest3(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+"进入run()方法");
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        FutureAndCallableTest3 futureAndCallableTest3 = new FutureAndCallableTest3("test");
        ExecutorService executorService = new ThreadPoolExecutor(2,3,5,TimeUnit.SECONDS,new ArrayBlockingQueue<>(2));
        for (int i = 0; i < 3; i++) {
           Future<FutureAndCallableTest3> future =  executorService.submit(futureAndCallableTest3,futureAndCallableTest3);
            FutureAndCallableTest3 futureAndCallableTest = future.get();
            System.out.println(futureAndCallableTest.getName());
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
