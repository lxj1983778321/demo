package com.example.demo.ThreadTest.FutureAndCallable;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/20 9:27
 * 验证Future的缺点:get()方法具有阻塞性
 */
public class FutureAndCallableTest6 implements Callable<String> {

   private ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();


    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"进入执行，时间："+System.currentTimeMillis());
        int time = threadLocalRandom.nextInt(0,10);
        System.out.println("睡眠时间为"+time);
        TimeUnit.SECONDS.sleep(time);
        System.out.println(Thread.currentThread().getName()+"结束执行,时间："+System.currentTimeMillis());
        return "return "+Thread.currentThread().getName()+"<---->"+System.currentTimeMillis();
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureAndCallableTest6 futureAndCallableTest6 = new FutureAndCallableTest6();
        ExecutorService executorService = new ThreadPoolExecutor(2,3,5,TimeUnit.SECONDS,new ArrayBlockingQueue<>(2));
        for (int i = 0; i < 5; i++) {
            Future<String> future = executorService.submit(futureAndCallableTest6);
            System.out.println(future.get());
        }
    }

}
