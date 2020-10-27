package com.example.demo.ThreadTest.FutureAndCallable;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/20 9:27
 * get()结合ExecutorService中的submit(Callable<T>)的使用
 * get()方法用来获取返回值
 */
public class FutureAndCallableTest1 implements Callable<String> {
    private String name;

    FutureAndCallableTest1(String name){
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        return Thread.currentThread().getName()+"<---->"+this.name;
    }

    public static void main(String[] args) throws Exception {
        FutureAndCallableTest1 futureAndCallableTest1 = new FutureAndCallableTest1("First Test");
        ExecutorService executorService = new ThreadPoolExecutor(2,3,5,TimeUnit.SECONDS,new ArrayBlockingQueue<>(2));
        for (int i = 0; i < 3; i++) {
           Future<String> future =  executorService.submit(futureAndCallableTest1);
            System.out.println(future.get());
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
