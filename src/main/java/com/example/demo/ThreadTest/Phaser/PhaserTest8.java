package com.example.demo.ThreadTest.Phaser;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/16 16:25
 * phaser对异常的处理：
 *phaser对于中断异常的处理是：抛出中断异常的那个线程，进入异常处理，其他线程只要满足phaser构造函数的perties的数量，就会正常执行，不会受到影响
 * 可以分批执行
 */
public class PhaserTest8 implements Runnable{

    private Phaser phaser;

    PhaserTest8(Phaser phaser){
        this.phaser = phaser;
    }

    public void testMethod(){
        try {
            System.out.println(Thread.currentThread().getName()+"进入，时间为：" + System.currentTimeMillis());
            if(Thread.currentThread().getName().equals("pool-1-thread-1")){
                throw new InterruptedException();
            }
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName()+"跨过第一层屏障，时间为："+System.currentTimeMillis());
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName()+"跨过第二层屏障，时间为："+System.currentTimeMillis());
        }catch (InterruptedException e){
            System.out.println(Thread.currentThread().getName()+"进入catch模块");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        testMethod();
    }

    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser(4);
        PhaserTest8 phaserTest8 = new PhaserTest8(phaser);
        ExecutorService executorService = new ThreadPoolExecutor(10,10,10, TimeUnit.SECONDS,new ArrayBlockingQueue<>(10));
        for (int i = 0; i < 10; i++) {
            executorService.execute(phaserTest8);
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
