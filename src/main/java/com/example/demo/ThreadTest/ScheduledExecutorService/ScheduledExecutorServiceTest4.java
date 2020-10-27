package com.example.demo.ThreadTest.ScheduledExecutorService;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author muyou
 * @date 2020/10/21 17:00
 * 使用scheduleWithFixedDelay()方法实现周期性执行
 *scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)
 * 第二个参数的时间单位为毫秒
 * 创建并执行在给定的初始延迟之后首先启用的定期动作，随后在一个执行的终止和下一个执行的开始之间给定的延迟。
 */
public class ScheduledExecutorServiceTest4 implements Runnable{

    /**
     * 验证执行任务时间>period预定的周期时间，即产生超时效果
     */
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+" begin implement, time = "+System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(4);
            System.out.println(Thread.currentThread().getName()+" end implement, time = "+System.currentTimeMillis()+"\n");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService =
                new ScheduledThreadPoolExecutor(1);
        System.out.println("main begin implement time = "+System.currentTimeMillis());
        scheduledExecutorService.scheduleWithFixedDelay(new ScheduledExecutorServiceTest4(),2,2,TimeUnit.SECONDS);
        System.out.println("main end implement time = "+System.currentTimeMillis()+"\n");
    }
}
