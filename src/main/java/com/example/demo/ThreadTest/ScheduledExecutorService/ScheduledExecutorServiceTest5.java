package com.example.demo.ThreadTest.ScheduledExecutorService;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author muyou
 * @date 2020/10/21 17:00
 * setExecuteExistingDelayedTasksAfterShutdownPolicy(boolean value)
 * 设置关于是否执行现有延迟任务的策略，即使该执行者已经是 shutdown 。
 * 当对ScheduledThreadPoolExecutor执行了shutdown()方法时，任务是否继续执行，默认值为true，
 * 即当调用了shutdown()方法时任务还是继续运行，当使用setExecuteExistingDelayedTasksAfterShutdownPolicy(false)时任务不再运行
 */
public class ScheduledExecutorServiceTest5 implements Runnable{

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
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor =
                new ScheduledThreadPoolExecutor(1);
        System.out.println("main begin implement time = "+System.currentTimeMillis());
        scheduledThreadPoolExecutor.schedule(new ScheduledExecutorServiceTest5(),2,TimeUnit.SECONDS);
        //虽然使用了shutdown()方法，但任务还是继续运行了
        /*
        scheduledThreadPoolExecutor.shutdown();
        System.out.println("已经shutdown了");
        */

        //关闭线程池，不在接受任务
        /*
        scheduledThreadPoolExecutor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
        System.out.println("已经shutdown了");
        */
    }
}
