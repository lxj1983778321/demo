package com.example.demo.ThreadTest.FutureAndCallable;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/20 9:27
 * cancel(boolean mayInterruptIfRunning)
 * 参数mayInterruptIfRunning的作用是：如果线程正在运行则是否中断正在运行的线程，true表示中断，返回值代表发送取消任务的命令是否完成
 * 在代码中需要使用if(Thread.currentThread.isInterrupted)进行配合
 *
 */
public class FutureAndCallableTest4 implements Callable<String> {

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        return Thread.currentThread().getName()+"进入执行";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureAndCallableTest4 futureAndCallableTest4 = new FutureAndCallableTest4();
        ExecutorService executorService = new ThreadPoolExecutor(2,3,5,TimeUnit.SECONDS,new ArrayBlockingQueue<>(2));
        for (int i = 0; i < 3; i++) {
            Future<String> future = executorService.submit(futureAndCallableTest4);
            //get()方法具有阻塞特性，知道线程return返回值，才会向下执行，把下面语句注释掉，会看到cancel方法执行成功
            //System.out.println(future.get());
            System.out.println(future.cancel(true)+"<---->"+future.isCancelled());
        }
    }
}
