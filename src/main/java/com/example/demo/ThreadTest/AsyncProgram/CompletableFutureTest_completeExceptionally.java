package com.example.demo.ThreadTest.AsyncProgram;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author muyou
 * @date 2020/12/7 9:29
 * CompletableFuture提供了completeExceptionally方法来处理异常情况
 */
public class CompletableFutureTest_completeExceptionally {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<String>();

        new Thread(()->{
           try {
               if(true){
                   new RuntimeException("exception test");
               }
               //设置正确结果
               future.complete("ok");
           }catch (Exception e){
               future.completeExceptionally(e);
           }
           //设置计算结果到future
            System.out.println("-----"+Thread.currentThread().getName()+" set future result------");
        },"thread-1").start();
        System.out.println(future.get());
        //也可以修改为这样，当出现异常时返回默认值
        System.out.println(future.exceptionally(t->"default").get());
    }
}
