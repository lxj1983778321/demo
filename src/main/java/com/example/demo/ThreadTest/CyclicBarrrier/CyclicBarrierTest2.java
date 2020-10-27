package com.example.demo.ThreadTest.CyclicBarrrier;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/15 15:15
 * 验证当线程数大于设置的barrier数时，是否可以分批处理
 */
public class CyclicBarrierTest2 implements Runnable{
    private CyclicBarrier cyclicBarrier;

    CyclicBarrierTest2(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }


    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName()+"等待集齐俩个线程，进行执行,等待时间为："+System.currentTimeMillis());
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName()+"达到条件，时间为："+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException{

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"执行自定义扩展代码，执行时间："+System.currentTimeMillis());
            }
        });
        CyclicBarrierTest2 cyclicBarrierTest2 = new CyclicBarrierTest2(cyclicBarrier);
        ExecutorService executorService = new ThreadPoolExecutor(6,6,10,TimeUnit.SECONDS,new ArrayBlockingQueue<>(6));
        for (int i = 0; i < 6; i++) {
            executorService.execute(cyclicBarrierTest2);
            TimeUnit.SECONDS.sleep(2);
        }
        executorService.shutdown();
    }
}
