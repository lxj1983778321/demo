package com.example.demo.ThreadTest.CyclicBarrrier;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/15 16:53
 * isBroken()方法：判断当前屏障点是否处于损坏状态
 * CyclicBarrier类对于中断异常的处理是使用全有或者全无的破坏模型，
 * 即如果有一个线程由于中断或者超时等原因离开了屏障点，那么其它线程也会抛出BrokenBarrierException异常或者InterruptedException异常，并离开屏障点
 */
public class CyclicBarrierTest5 implements Runnable{
    private CyclicBarrier cyclicBarrier;

    CyclicBarrierTest5(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }


    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+"到达执行点，等待开始执行，时间为："+System.currentTimeMillis());
            if(Thread.currentThread().getName().equals("pool-1-thread-2")){
                System.out.println(Thread.currentThread().getName()+"进入异常中断方法");
                Thread.currentThread().interrupt();
            }
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName()+"执行后续代码");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"进入InterruptedException异常，屏障点状态："+cyclicBarrier.isBroken());
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            System.out.println(Thread.currentThread().getName()+"进入BrokenBarrierException异常，屏障点状态："+cyclicBarrier.isBroken());
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        CyclicBarrierTest5 cyclicBarrierTest5 = new CyclicBarrierTest5(cyclicBarrier);
        ExecutorService executorService = new ThreadPoolExecutor(4,4,10, TimeUnit.SECONDS,new ArrayBlockingQueue<>(4));
        for (int i = 0; i < 4; i++) {
            executorService.execute(cyclicBarrierTest5);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
