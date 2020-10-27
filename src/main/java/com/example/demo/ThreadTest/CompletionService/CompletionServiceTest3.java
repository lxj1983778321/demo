package com.example.demo.ThreadTest.CompletionService;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/20 15:07
 * 方法take()取得最先完成任务的Future对象,谁的执行时间短，谁最先返回
 * 验证take()方法的特性
 */
public class CompletionServiceTest3 implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"进入执行，时间："+System.currentTimeMillis());
        TimeUnit.SECONDS.sleep((int)(Math.random()*10));
        return "return "+Thread.currentThread().getName()+"<---->"+System.currentTimeMillis();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = new ThreadPoolExecutor(5,5,5,TimeUnit.SECONDS,new ArrayBlockingQueue<>(5));
        CompletionService completionService = new ExecutorCompletionService(executorService);
        CompletionServiceTest3 completionServiceTest3 = new CompletionServiceTest3();

        for (int i = 0; i < 5; i++) {
            completionService.submit(completionServiceTest3);
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(completionService.take().get());
        }
    }
}
