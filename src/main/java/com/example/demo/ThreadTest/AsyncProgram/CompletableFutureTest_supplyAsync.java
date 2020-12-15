package com.example.demo.ThreadTest.AsyncProgram;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @author muyou
 * @date 2020/12/1 10:03
 * 基于supplyAsync实现有返回值异步计算结果，
 * 例如：对原始数据加工，并需要获取到被加工后结果等
 * 默认情况下：supplyAsync和runAsync都是使用jvm内唯一的ForkJoinPool.commonPool()线程来执行内部任务
 */
public class CompletableFutureTest_supplyAsync {
    private static final int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    public static ThreadPoolExecutor poolExecutor =
            new ThreadPoolExecutor(
                    AVALIABLE_PROCESSORS,
                    AVALIABLE_PROCESSORS,
                    1,
                    TimeUnit.MINUTES,
                    new ArrayBlockingQueue<>(5));

    public static void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture future = CompletableFuture.supplyAsync(new Supplier<Object>() {
            @Override
            public Object get() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return "hello,already over";
            }
        },poolExecutor);
        System.out.println(future.get());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("begin：" + System.currentTimeMillis());
        supplyAsync();
        System.out.println("end：" + System.currentTimeMillis());
    }
}
