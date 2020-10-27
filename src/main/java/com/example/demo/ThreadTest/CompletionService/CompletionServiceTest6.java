package com.example.demo.ThreadTest.CompletionService;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/20 15:56
 * Future<V> submit(Runnable task,V result)
 * CompletionService类也可以运行Runnable方法
 */
public class CompletionServiceTest6 implements Runnable {

    private String name;

    CompletionServiceTest6(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"进入run()方法");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = new ThreadPoolExecutor(2,3,5,TimeUnit.SECONDS,new ArrayBlockingQueue<>(2));
        CompletionService completionService = new ExecutorCompletionService(executorService);

        CompletionServiceTest6 completionServiceTest6 = new CompletionServiceTest6("测试方法6");
        for (int i = 0; i < 2; i++) {
           Future<CompletionServiceTest6> future =  completionService.submit(completionServiceTest6,completionServiceTest6);
            System.out.println(future.get()+"<---->"+future.get().getName());
        }
    }


}
