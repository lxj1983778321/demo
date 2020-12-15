package com.example.demo.ThreadTest.AsyncProgram;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/11/5 15:35
 * 缺点，确实可以在main线程获取到异步任务执行结果，但是必须以阻塞的代价来获取结果，在异步任务执行完毕之前，main线程无法继续其他任务
 */
public class AsyncThreadPoolExample {

    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final static ThreadPoolExecutor EXECUTOR =
            new ThreadPoolExecutor(AVALIABLE_PROCESSORS,
                    AVALIABLE_PROCESSORS*2,
                    1,TimeUnit.MINUTES,
                    new LinkedBlockingQueue<>(5));

    public static String method_A(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("<------->method_A<-------->");
        return "A Method Done";
    }

    public static String method_B(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("<------->method_B<-------->");
        return "B Method Done";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(System.currentTimeMillis()+"----start");
        Future<String> future = EXECUTOR.submit(()->method_A());
        Future<String> future1 = EXECUTOR.submit(()->method_B());
        System.out.println(future.get()+future1.get());
        System.out.println("main end");

    }
}
