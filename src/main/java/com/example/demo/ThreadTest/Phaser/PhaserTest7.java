package com.example.demo.ThreadTest.Phaser;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/16 16:00
 *forceTermination()方法：是phaser对象的屏障功能失效
 * isTerminated()方法：判断phaser对象是否已经呈销毁状态
 */
public class PhaserTest7 implements Runnable{

    private Phaser phaser;

    PhaserTest7(Phaser phaser){
        this.phaser = phaser;
    }

    public void testMethod(){
        System.out.println(Thread.currentThread().getName()+"进入时间："+System.currentTimeMillis());
        phaser.forceTermination();
        System.out.println("当前屏障是否失效："+phaser.isTerminated());
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName()+"跨过时间："+System.currentTimeMillis());
    }


    @Override
    public void run() {
        testMethod();
    }

    public static void main(String[] args) throws InterruptedException{
        Phaser phaser = new Phaser(3);
        PhaserTest7 phaserTest7 = new PhaserTest7(phaser);
        ExecutorService executorService = new ThreadPoolExecutor(4,4,10, TimeUnit.SECONDS,new ArrayBlockingQueue<>(4));
        for (int i = 0; i < 4; i++) {
            executorService.execute(phaserTest7);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
