package com.example.demo.ThreadTest.ScheduledExecutorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/21 15:14
 * ScheduledThreadPoolExecutor 使用Callable延迟运行
 * 阻塞是因为get()方法
 * <V> ScheduledFuture<V> schedule(Callable<V> var1, long var2, TimeUnit var4);
 * 第二个参数从现在开始延迟执行的时间
 * 这里的延迟执行指的是在long delay后创建并启用ScheduledFuture获取返回值
 */
public class ScheduledExecutorServiceTest1 implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " begin implement，Time = " + System.currentTimeMillis());
        System.out.println(Thread.currentThread().getName() + " end implement,Time = " + System.currentTimeMillis());
        return Thread.currentThread().getName() + "返回值";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorServiceTest1 scheduledExecutorServiceTest1 = new ScheduledExecutorServiceTest1();

        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(2);

        List<ScheduledFuture<String>> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
           ScheduledFuture<String> scheduledFuture =  scheduledExecutorService.schedule(scheduledExecutorServiceTest1,4, TimeUnit.SECONDS);
           list.add(scheduledFuture);
        }
        System.out.println("main 开始打印返回值时间：" + System.currentTimeMillis());
        for (int i = 0; i < 2; i++) {
            System.out.println("返回值为：" + list.get(i).get());
        }
        System.out.println("main 结束打印返回值时间：" + System.currentTimeMillis());
    }
}
