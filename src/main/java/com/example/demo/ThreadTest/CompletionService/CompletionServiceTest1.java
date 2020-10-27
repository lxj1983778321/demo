package com.example.demo.ThreadTest.CompletionService;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/20 15:07
 * Future具有同步阻塞性的特点，会降低程序运行效率
 * 使用CompletionService可以解决这样的问题
 * 注：但是如果completionService中没有任务执行完的话，completionService.take().get()还是呈现阻塞特性
 */
public class CompletionServiceTest1 implements Callable<String> {
    private long sleepValue;

    CompletionServiceTest1(long sleepValue){
        this.sleepValue = sleepValue;
    }

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"进入执行，时间："+System.currentTimeMillis());
        TimeUnit.SECONDS.sleep(this.sleepValue);
        return "return "+Thread.currentThread().getName()+"<---->"+System.currentTimeMillis();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = new ThreadPoolExecutor(5,5,5,TimeUnit.SECONDS,new ArrayBlockingQueue<>(5));
        CompletionService completionService = new ExecutorCompletionService(executorService);

        CompletionServiceTest1 completionServiceTest1 = new CompletionServiceTest1(5);
        CompletionServiceTest1 completionServiceTest2 = new CompletionServiceTest1(4);
        CompletionServiceTest1 completionServiceTest3 = new CompletionServiceTest1(3);
        CompletionServiceTest1 completionServiceTest4 = new CompletionServiceTest1(2);
        CompletionServiceTest1 completionServiceTest5 = new CompletionServiceTest1(1);

        completionService.submit(completionServiceTest1);
        completionService.submit(completionServiceTest2);
        completionService.submit(completionServiceTest3);
        completionService.submit(completionServiceTest4);
        completionService.submit(completionServiceTest5);

        for (int i = 0; i < 5; i++) {
            System.out.println(completionService.take().get());
        }
    }
}
