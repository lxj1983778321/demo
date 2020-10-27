package com.example.demo.ThreadTest.Phaser;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/16 9:51
 * arriveAndDeregister()方法：
 * 使当前线程退出执行，并使parties的值减一
 * 目的：使pool-1-thread-2线程不执行第二层屏障，只执行第一层屏障
 */
public class PhaserTest2 implements Runnable{
    private Phaser phaser;

    PhaserTest2(Phaser phaser){
        this.phaser = phaser;
    }

    public void testMethod(){
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName()+"进入，准备执行时间为："+System.currentTimeMillis());
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName()+"度过第一层屏障，度过时间为："+System.currentTimeMillis());
            System.out.println("度过第一层屏障之后的注册parties数量为："+phaser.getRegisteredParties());
            if(Thread.currentThread().getName().equals("pool-1-thread-2")){
                //不执行任务，并使计数减一
                phaser.arriveAndDeregister();
            }
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName()+"度过第二层屏障，度过时间为："+System.currentTimeMillis());
            System.out.println("度过第二层屏障之后的注册parties数量为："+phaser.getRegisteredParties());

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
        PhaserTest2 phaserTest2 = new PhaserTest2(phaser);
        ExecutorService executorService = new ThreadPoolExecutor(3,3,10,TimeUnit.SECONDS,new ArrayBlockingQueue<>(3));
        for (int i = 0; i < 3; i++) {
            executorService.execute(phaserTest2);
        }
    }
}
