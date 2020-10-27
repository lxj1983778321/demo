package com.example.demo.ThreadTest.CompletionService;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/20 15:56
 * CompletionService对于异常的处理
 */
public class CompletionServiceTest5 implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"进入执行，时间为："+System.currentTimeMillis());
        TimeUnit.SECONDS.sleep(2);
        if(Thread.currentThread().getName().equals("pool-1-thread-1")){
            throw new Exception(Thread.currentThread().getName()+"抛出异常");
        }
        return "return "+Thread.currentThread().getName()+"<---->"+System.currentTimeMillis();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = new ThreadPoolExecutor(5,5,5,TimeUnit.SECONDS,new ArrayBlockingQueue<>(2));
        CompletionService completionService = new ExecutorCompletionService(executorService);

        CompletionServiceTest5 completionServiceTest5 = new CompletionServiceTest5();
        for (int i = 0; i < 5; i++) {
            completionService.submit(completionServiceTest5);
        }

        for (int i = 0; i < 5; i++) {
            /**
             * 当调用get()方法的时候：出现异常，就会进入catch块处理异常
             */
            //System.out.println(completionService.take().get());
            /**
             * 当调用take()方法时：不会抛出异常，会返回Future对象，但是对象信息也会有显示
             * 返回结果如下：
             * java.util.concurrent.FutureTask@6659c656[Completed normally]
             * java.util.concurrent.FutureTask@6d5380c2[Completed normally]
             * java.util.concurrent.FutureTask@45ff54e6[Completed normally]
             * java.util.concurrent.FutureTask@2328c243[Completed exceptionally: java.lang.Exception: pool-1-thread-1抛出异常]
             * java.util.concurrent.FutureTask@bebdb06[Completed normally]
             */
            System.out.println(completionService.take());
        }

    }
}
