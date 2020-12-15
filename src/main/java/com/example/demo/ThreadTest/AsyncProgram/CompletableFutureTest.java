package com.example.demo.ThreadTest.AsyncProgram;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/11/11 17:03
 */
public class CompletableFutureTest {

    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final static ThreadPoolExecutor POOL_EXECUTOR =
            new ThreadPoolExecutor(AVALIABLE_PROCESSORS,AVALIABLE_PROCESSORS*2,
                    1, TimeUnit.MINUTES,new LinkedBlockingQueue<>(5));

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建一个CompletableFuture对象
        CompletableFuture<String> future = new CompletableFuture<String>();
        //开启线程计算任务结果
        POOL_EXECUTOR.execute(()->{
            try {
                //模拟计算任务
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //调用complete方法会直接返回结果，下面的get方法会获取到结果,main线程会直接继续向下执行

            future.complete("hello,XXX");
        });
        System.out.println(future.get());
        System.out.println("main end");
    }

}
