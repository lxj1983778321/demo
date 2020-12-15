package com.example.demo.ThreadTest.AsyncProgram;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * @author muyou
 * @date 2020/12/2 9:35
 * 基于whenComplete设置回调函数，当异步任务执行完毕后进行回调，不会阻塞调用线程
 */
public class CompletableFutureTest_whenComplete {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "it over";
            }
        });
        //上面异步任务执行完毕后，会回调下面的回调函数
        future.whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String s, Throwable throwable) {
                //如果没有异常那个，则打印执行结果
                if(throwable == null){
                    System.out.println(s);
                }else {
                    //打印异常信息
                    System.out.println(throwable.getMessage());
                }
            }
        });
        //挂起当前main线程，等待异步任务执行完毕
        /**
         * 这里挂起main函数所在线程，是因为具体执行异步任务的是Forkjoin的commonPool线程池，其中都是Deamon线程，
         * 当唯一用户线程main线程退出后整个jvm就退出了，会导致异步任务得不到执行
         */
        Thread.currentThread().join();
    }
}
