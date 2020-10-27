package com.example.demo.ThreadTest.ScheduledExecutorService;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author muyou
 * @date 2020/10/21 17:00
 * cancel(boolean)与setRemoveOnCancelPolicy(boolean)
 * cancel(boolean)：设定是否取消任务，但该任务还是会存在于队列中
 * setRemoveOnCancelPolicy()：是否将取消的任务从队列中移除
 *
 * cancel()总结如下：
 * （1）在队列中的任务可以取消，任务不再运行
 * （2）正在运行中的任务可以结合if(Thread.currentThread().isInterrupted() == true)进行中断，使任务结束
 */
public class ScheduledExecutorServiceTest7 implements Runnable{

    private String name;

    ScheduledExecutorServiceTest7(String name){
        this.name = name;
    }

    @Override
    public void run() {
        try {
                //模拟程序正在运行中，否则程序运行太快，看不到效果
                while (true){
                    if(Thread.currentThread().isInterrupted() == true){
                        throw new InterruptedException();
                    }
                    System.out.println(Thread.currentThread().getName()+" is run name = "+this.name);
                    TimeUnit.SECONDS.sleep(2);
                }
        }catch (InterruptedException e){
            System.out.println(Thread.currentThread().getName()+"抛出中断异常");
            e.printStackTrace();
        }
    }

    /**
     * @param args
     * @throws InterruptedException
     *
     *运行结果如下：
     * true
     * 队列中的任务：java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask@4459eb14[Cancelled]
     * main end
     *结果表明：任务已取消，但取消的任务仍然存在于队列中
     */
  public static void main(String[] args) throws InterruptedException {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor =
                new ScheduledThreadPoolExecutor(2);
        //将任务放入队列中，1秒后再执行
        ScheduledFuture future = scheduledThreadPoolExecutor.schedule(new ScheduledExecutorServiceTest7("A"),1,TimeUnit.SECONDS);
        //当加上setRemoveOnCancelPolicy(true)时结合cancel(true)方法可以使队列将取消的任务删除
        //scheduledThreadPoolExecutor.setRemoveOnCancelPolicy(true);
        System.out.println(future.cancel(true));

        //获取队列中的任务
        BlockingQueue<Runnable> blockingQueue = scheduledThreadPoolExecutor.getQueue();
        Iterator<Runnable> iterator = blockingQueue.iterator();
        while (iterator.hasNext()){
            Runnable runnable = iterator.next();
            System.out.println("队列中的任务："+runnable);
        System.out.println("main end");
    }
  }

    /**
     * 验证，当程序已经处在运行中时，如何中断程序
     * 队列中无任务原因为任务马上被执行，并没有放入队列中
     * 实验说明正在运行任务执行cancel()方法后可以结合if(Thread.currentThread().isInterrupted() == true)进行打断
     * @param args
     */
/*
    public static void main(String[] args) throws InterruptedException {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor =
                new ScheduledThreadPoolExecutor(2);
        //任务不再放入队列中，而是直接开始执行
        ScheduledFuture future = scheduledThreadPoolExecutor.schedule(new ScheduledExecutorServiceTest7("A"),0,TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(2);


        BlockingQueue<Runnable> blockingQueue = scheduledThreadPoolExecutor.getQueue();
        System.out.println("取消任务执行之前的队列数量为：" + blockingQueue.size());
        System.out.println("取消结果为："+future.cancel(true));
        blockingQueue = scheduledThreadPoolExecutor.getQueue();
        System.out.println("取消任务执行之后的队列数量为："+blockingQueue.size());
        System.out.println("main end");
    }
*/

}
