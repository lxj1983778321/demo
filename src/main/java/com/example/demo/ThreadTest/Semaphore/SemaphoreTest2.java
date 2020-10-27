package com.example.demo.ThreadTest.Semaphore;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/14 10:17
 * 类Semaphore构造方法perimits参数作用
 * perimits参数：作用是用来设置许可的个数
 */
public class SemaphoreTest2 implements Runnable{
    /**
     * 当permits不为1时，该类并不能保证线程安全性，因为还是会有可能出现多个线程共同访问实例变量，出现脏数据的可能性
     * 这种情况下可以对共享变量加上volatile进行控制
     */
    private Semaphore semaphore = new Semaphore(3);

    private volatile int i = 1;

    /**
     * 执行结果：
     * pool-1-thread-1进入执行，时间为1602642157203
     * pool-1-thread-2进入执行，时间为1602642157203
     * pool-1-thread-3进入执行，时间为1602642157204
     * pool-1-thread-2执行完毕时间为：1602642167204
     * pool-1-thread-3执行完毕时间为：1602642167204
     * pool-1-thread-1执行完毕时间为：1602642167204
     * pool-1-thread-1和pool-1-thread-2同步执行pool-1-thread-3被排斥
     */
    public void testMethod(){
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName()+"进入执行，时间为"+System.currentTimeMillis());
            /*if(Thread.currentThread().getName().equals("pool-1-thread-2")){
                System.out.println(Thread.currentThread().getName()+"进入中断方法");
                throw new InterruptedException();
            }*/
            i = i+1;
            System.out.println(Thread.currentThread().getName()+"修改共享变量的值为：" + i);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+"执行完毕时间为："+System.currentTimeMillis());
            semaphore.release();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        testMethod();
    }

    public static void main(String[] args) {
        SemaphoreTest2 semaphoreTest2 = new SemaphoreTest2();
        ExecutorService executorService = new ThreadPoolExecutor(4,4,10,TimeUnit.SECONDS,new ArrayBlockingQueue<>(4));
        for (int i = 0; i < 3; i++) {
            executorService.execute(semaphoreTest2);
        }
        executorService.shutdown();
    }
}
