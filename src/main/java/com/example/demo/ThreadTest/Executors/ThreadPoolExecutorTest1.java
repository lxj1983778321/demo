package com.example.demo.ThreadTest.Executors;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/19 10:38
 * 使用ThreadFactory+execute()对线程池中的线程进行专属定制化
 * 有时需要对线程池中创建的线程进行专属定制化，这时需要配置ThreadFactory线程工厂
 * 例如：对线程池中的线程进行名字命名等
 */
public class ThreadPoolExecutorTest1 implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"进入执行，时间为："+System.currentTimeMillis());
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutorTest1 threadPoolExecutorTest1 = new ThreadPoolExecutorTest1();
        ExecutorService executorService =
                new ThreadPoolExecutor(4,5,10, TimeUnit.SECONDS,new ArrayBlockingQueue<>(5),new MyThreadFactoryTest1());
        //除了使用构造方法添加外，也可以使用setThreadFactory()方法添加自定义线程工厂
        //ThreadPoolExecutor的setThreadFactory()方法添加线程工厂

        for (int i = 0; i < 3; i++) {
            executorService.execute(threadPoolExecutorTest1);
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
class MyThreadFactoryTest1 implements ThreadFactory {
    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName("ThreadPoolExecutorTest1:"+System.currentTimeMillis());
        return thread;
    }
}