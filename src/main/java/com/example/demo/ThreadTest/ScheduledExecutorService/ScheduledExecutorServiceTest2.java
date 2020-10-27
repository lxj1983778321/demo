package com.example.demo.ThreadTest.ScheduledExecutorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/21 16:42
 * ScheduledThreadPoolExecutor 使用Runnable延迟运行
 * 这里的延迟执行指的是在long delay后创建并启用ScheduledFuture获取返回值
 */
public class ScheduledExecutorServiceTest2 implements Runnable{

    @Override
    public void run() {
            System.out.println(Thread.currentThread().getName() + " begin implement，Time = " + System.currentTimeMillis());
            System.out.println(Thread.currentThread().getName() + " end implement,Time = " + System.currentTimeMillis());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorServiceTest2 scheduledExecutorServiceTest2 = new ScheduledExecutorServiceTest2();

        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(2);

        List<ScheduledFuture<String>> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
          ScheduledFuture scheduledFuture =  scheduledExecutorService.schedule(new ScheduledExecutorServiceTest2(),4,TimeUnit.SECONDS);
          list.add(scheduledFuture);
        }
        System.out.println("main 开始打印返回值时间：" + System.currentTimeMillis());
        for (int i = 0; i < 2; i++) {
            System.out.println("返回值为：" + list.get(i).get());
        }
        System.out.println("main 结束打印返回值时间：" + System.currentTimeMillis());
    }
}
