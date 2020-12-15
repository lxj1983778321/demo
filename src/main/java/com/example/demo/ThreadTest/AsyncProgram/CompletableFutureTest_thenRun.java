package com.example.demo.ThreadTest.AsyncProgram;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @author muyou
 * @date 2020/12/1 10:19
 * 基于thenRun实现异步任务A，执行完毕后，激活异步任务B，注意：这种方式激活的任务B拿不到任务A的执行结果（是任务A不会把执行结果直接传给B，需要自己通过get获取）
 */
public class CompletableFutureTest_thenRun {
    private static final int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    public static ThreadPoolExecutor poolExecutor =
            new ThreadPoolExecutor(
                    AVALIABLE_PROCESSORS,
                    AVALIABLE_PROCESSORS,
                    1,
                    TimeUnit.MINUTES,
                    new ArrayBlockingQueue<>(5));
    public static void thenRun() throws ExecutionException, InterruptedException {
        CompletableFuture oneFuture= CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "hello";
            }
        },poolExecutor);
        CompletableFuture twoFuture = oneFuture.thenRun(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("oneFuture:" + oneFuture.get());
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("twoFuture：" + twoFuture.get());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("begin："+System.currentTimeMillis());
        thenRun();
        System.out.println("end：" + System.currentTimeMillis());
    }

}
