package com.example.demo.ThreadTest.Phaser;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/16 9:27
 * arriveAndAwaitAdvance()方法作用是：当前线程已经到达屏障，等条件满足继续向下执行，满足Phaser构造函数的parties数量
 * 类Phaser具有设置多屏障的功能,与cyclicBarrier类的功能有部分重叠
 */
public class PhaserTest1 implements Runnable{
    private Phaser phaser;

    PhaserTest1(Phaser phaser){
        this.phaser = phaser;
    }

    public void testMethod(){
        try {
            System.out.println(Thread.currentThread().getName()+"进入，准备执行时间为："+System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(2);
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName()+"度过第一层屏障，时间为："+System.currentTimeMillis());
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName()+"度过第二层屏障，时间为："+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        testMethod();
    }

    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        PhaserTest1 phaserTest1 = new PhaserTest1(phaser);
        ExecutorService executorService = new ThreadPoolExecutor(3,3,10,TimeUnit.SECONDS,new ArrayBlockingQueue<>(3));
        for (int i = 0; i < 3; i++) {
            executorService.execute(phaserTest1);
        }
    }

}
