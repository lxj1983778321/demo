package com.example.demo.ThreadTest.Executors;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/19 14:36
 * 方法set/getRejectedExecutionHandler()作用是可以处理任务被拒绝时的行为,
 * 实现接口为：RejectedExecutionHandler
 */
public class ThreadPoolExecutorTest3 implements Runnable{

    private String userName;

    ThreadPoolExecutorTest3(String userName){
        this.userName = userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+"开始执行时间为："+System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(4);
            System.out.println(Thread.currentThread().getName()+"执行完毕时间为："+System.currentTimeMillis());
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //定义拒绝时执行的行为
        MyRejectedExecutionHandler myRejectedExecutionHandler = new MyRejectedExecutionHandler();
        ExecutorService executorService =
                new ThreadPoolExecutor(2,3,10, TimeUnit.SECONDS,new ArrayBlockingQueue<>(1),myRejectedExecutionHandler);
       executorService.execute(new ThreadPoolExecutorTest3("测试1"));
       executorService.execute(new ThreadPoolExecutorTest3("测试2"));
       executorService.execute(new ThreadPoolExecutorTest3("测试3"));
       executorService.execute(new ThreadPoolExecutorTest3("测试4"));
       executorService.execute(new ThreadPoolExecutorTest3("测试5"));
       executorService.execute(new ThreadPoolExecutorTest3("测试6"));
    }
}
class MyRejectedExecutionHandler implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
        System.out.println(((ThreadPoolExecutorTest3)runnable).getUserName()+"被拒绝执行，当前线程池的长度为："+threadPoolExecutor.getPoolSize());
    }
}