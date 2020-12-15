package com.example.demo.ThreadTest.AsyncProgram;

import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author muyou
 * @date 2020/12/1 10:59
 * 基于thenApply实现异步任务A，激活异步任务B，这种方式异步任务B可以拿到异步任务A的执行结果，也可以获取异步任务B的结果
 */
public class CompletableFutureTest_thenApply {
    private static final int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    public static ThreadPoolExecutor poolExecutor =
            new ThreadPoolExecutor(
                    AVALIABLE_PROCESSORS,
                    AVALIABLE_PROCESSORS,
                    1,
                    TimeUnit.MINUTES,
                    new ArrayBlockingQueue<>(5));

    public static void thenApply() throws ExecutionException, InterruptedException {
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
        //Function泛型意思：传入的类型是String，返回的类型是String
        CompletableFuture<String> twoFuture = oneFuture.thenApplyAsync(new Function<String,String>() {
            @Override
            public String apply(String o) {
                return o+"twoFuture";
            }
        },poolExecutor);
        System.out.println(twoFuture.get());
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("begin："+System.currentTimeMillis());
        thenApply();
        System.out.println("end：" + System.currentTimeMillis());
    }
}
