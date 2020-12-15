package com.example.demo.ThreadTest.AsyncProgram;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author muyou
 * @date 2020/12/2 10:06
 * CompletableFuture进行组合运算
 * 基于thenCompose实现当一个CompletableFuture执行完毕后，使用第一个CompletableFuture的返回结果执行另一个CompletableFuture
 */
public class CompletableFutureTest_thenCompose {

    public static CompletableFuture<String> CompletableFuture_one(String id){
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return id;
            }
        });
        return completableFuture;
    }

    public static CompletableFuture<String> CompletableFutureTest_two(String id){
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return id+" ...";
            }
        });
        return completableFuture;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //执行结果：123 ...
        CompletableFuture resultFuture = CompletableFuture_one("123").thenCompose(id -> CompletableFutureTest_two(id));
        System.out.println(resultFuture.get());
        System.out.println("------------------------------------");
        //基于thenCombine实现当两个并发运行的CompletableFuture任务都完成后，使用两者结果作为参数再执行一个异步任务
        //执行结果是：456 789 ...
        CompletableFuture completableFuture =
                CompletableFuture_one("456")
                .thenCombine(CompletableFutureTest_two("789"),(one,two)->{
                    return one + " " + two;
                });
        System.out.println(completableFuture.get());
        System.out.println("----------------------------------");
    }
}
