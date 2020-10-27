package com.example.demo.ThreadTest.FutureAndCallable;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/20 9:27
 * get()结合ExecutorService中的submit(Runnable)和isDone()的使用
 * get()方法用来获取返回值，具有阻塞特性，返回值为自定义泛型类型
 * isDone()判断当前方法是否完成，不管是正常执行完，或者抛出异常，只要执行完就是true，有风险
 */
public class FutureAndCallableTest2 implements Runnable {
    private String name;

    FutureAndCallableTest2(String name){
        this.name = name;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName()+"<---->"+this.name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        FutureAndCallableTest2 futureAndCallableTest2 = new FutureAndCallableTest2("First Test");
        ExecutorService executorService = new ThreadPoolExecutor(2,3,5,TimeUnit.SECONDS,new ArrayBlockingQueue<>(2));
        for (int i = 0; i < 3; i++) {
           Future future =  executorService.submit(futureAndCallableTest2);
            System.out.println(future.get()+"<---->"+future.isDone());
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
