package com.example.demo.ThreadTest.AsyncProgram;

import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author muyou
 * @date 2020/12/1 10:38
 * thenAccept:基于thenAccept实现异步任务A执行完毕后，激活异步任务B，注：异步任务B，可以拿到异步任务A返回结果
 */
public class CompletableTest_thenAccept {
    private static final int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    public static ThreadPoolExecutor poolExecutor =
            new ThreadPoolExecutor(
                    AVALIABLE_PROCESSORS,
                    AVALIABLE_PROCESSORS,
                    1,
                    TimeUnit.MINUTES,
                    new ArrayBlockingQueue<>(5));

    public static void thenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture oneFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "oneFuture";
            }
        },poolExecutor);
        CompletableFuture twoFuture = oneFuture.thenAcceptAsync(new Consumer() {
            @Override
            public void accept(Object o) {
                try {
                    //对oneFuture返回结果进行处理
                    System.out.println("拿到的oneFuture值为：" + oneFuture.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        },poolExecutor);
        //twoFuture返回的结果固定为null
        System.out.println("twoFuture：" + twoFuture.get());
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("begin："+System.currentTimeMillis());
        thenAccept();
        System.out.println("end：" + System.currentTimeMillis());
    }
}
