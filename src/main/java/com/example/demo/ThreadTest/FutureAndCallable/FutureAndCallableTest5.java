package com.example.demo.ThreadTest.FutureAndCallable;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/20 9:27
 * cancel(boolean mayInterruptIfRunning)
 * 参数mayInterruptIfRunning与if(Thread.currentThread.isInterrupted)配合使用
 *
 */
public class FutureAndCallableTest5 implements Callable<String> {

    @Override
    public String call() throws Exception {
        int i = 0;
        while (i == 0){
            if(Thread.currentThread().isInterrupted()){
                throw new InterruptedException();
            }
            System.out.println("正在运行中");
        }
        return "结束运行，返回结果";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureAndCallableTest5 futureAndCallableTest5 = new FutureAndCallableTest5();
        ExecutorService executorService = new ThreadPoolExecutor(2,3,5,TimeUnit.SECONDS,new ArrayBlockingQueue<>(2));
        Future<String> future = executorService.submit(futureAndCallableTest5);
        TimeUnit.SECONDS.sleep(5);
        System.out.println(future.cancel(true)+"<----->"+future.isCancelled());
    }
}
