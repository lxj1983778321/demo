package com.example.demo.ThreadTest.ScheduledExecutorService;

import com.example.demo.ThreadTest.Executors.ThreadPoolExecutorTest6;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author muyou
 * @date 2020/10/21 17:00
 * 测试setContinueExistingDelayedTasksAfterShutdownPolicy()与scheduleAtFixedRate()和scheduleWithFixedDelay()方法联用
 */
public class ScheduledExecutorServiceTest6 implements Runnable{

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

    /**
     * 方法setExecuteExistingDelayedTasksAfterShutdownPolicy()可以与schedule()和shutdown()方法联合使用
     * 但setExecuteExistingDelayedTasksAfterShutdownPolicy()方法不能与scheduleAtFixedRate()和scheduleWithFixedDelay()方法联用
     * 如果想实现shutdown()关闭线程池后，池中的任务继续重复运行，
     * 则要将scheduleAtFixedRate()和scheduleWithFixedDelay()方法与setContinueExistingDelayedTasksAfterShutdownPolicy()联合使用
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor =
                new ScheduledThreadPoolExecutor(1);
        System.out.println("main begin implement time = "+System.currentTimeMillis());
        scheduledThreadPoolExecutor.scheduleAtFixedRate(new ScheduledExecutorServiceTest6(),1,2,TimeUnit.SECONDS);
        //当为true，虽然shutdown但该任务还会执行
        scheduledThreadPoolExecutor.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
        //当为false，shutdown后该任务不会执行
        //scheduledThreadPoolExecutor.setContinueExistingPeriodicTasksAfterShutdownPolicy(false);
        //和scheduleWithFixedDelay()方法也是同理，不再实验
        //关闭线程池，不再接受任务
        scheduledThreadPoolExecutor.shutdown();
        System.out.println("已经shutdown了");

    }
}
