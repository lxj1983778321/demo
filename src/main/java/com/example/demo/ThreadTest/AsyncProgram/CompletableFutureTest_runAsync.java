package com.example.demo.ThreadTest.AsyncProgram;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.*;
/**
 * @author muyou
 * @date 2020/12/1 9:33
 * 基于runAsync系列方法实现无返回值异步计算，比如异步打印日志，异步做消息通知等
 */
public class CompletableFutureTest_runAsync {
    private static final int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    public static ThreadPoolExecutor poolExecutor =
            new ThreadPoolExecutor(
                    AVALIABLE_PROCESSORS,
                    AVALIABLE_PROCESSORS,
                    1,
                    TimeUnit.MINUTES,
                    new ArrayBlockingQueue<>(5));

    public static void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture future = CompletableFuture.runAsync(()->{
           try {
               TimeUnit.SECONDS.sleep(2);
               System.out.println("it over");
           }catch (InterruptedException e) {
               e.printStackTrace();
           }
        },poolExecutor);
        //runAsync无返回值，所以get为mull
        System.out.println("同步等待返回结果" + future.get());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("begin：" + System.currentTimeMillis());
        runAsync();
        System.out.println("end：" + System.currentTimeMillis());
    }
}
