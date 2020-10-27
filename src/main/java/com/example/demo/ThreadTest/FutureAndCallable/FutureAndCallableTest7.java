package com.example.demo.ThreadTest.FutureAndCallable;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/20 9:27
 * 对于异常的处理
 */
public class FutureAndCallableTest7 implements Callable<String> {


    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"进入执行，时间："+System.currentTimeMillis());
        if(Thread.currentThread().getName().equals("pool-1-thread-3")){
            throw new Exception(Thread.currentThread().getName()+"抛出异常");
        }
        System.out.println(Thread.currentThread().getName()+"结束执行,时间："+System.currentTimeMillis());
        return "return "+Thread.currentThread().getName()+"<---->"+System.currentTimeMillis();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            FutureAndCallableTest7 futureAndCallableTest6 = new FutureAndCallableTest7();
            ExecutorService executorService = new ThreadPoolExecutor(5,5,5,TimeUnit.SECONDS,new ArrayBlockingQueue<>(2));
            for (int i = 0; i < 5; i++) {
                Future<String> future = executorService.submit(futureAndCallableTest6);
                System.out.println(future.get());
            }
        }catch (Exception e){
            System.out.println("进入catch块,异常信息2为："+e.getMessage());
            e.printStackTrace();

        }

    }

}
