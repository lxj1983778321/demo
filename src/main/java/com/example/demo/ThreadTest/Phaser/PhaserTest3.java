package com.example.demo.ThreadTest.Phaser;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author muyou
 * @date 2020/10/16 10:05
 * getPhase()方法：获取已经到达第几个屏障
 * onAdvance()作用是通过新的屏障时被调用执行，增加自定义代码
 */
public class PhaserTest3 implements Runnable{
    private Phaser phaser;

    PhaserTest3(Phaser phaser){
        this.phaser = phaser;
    }

    public void testMethod(){
        try {
            System.out.println(Thread.currentThread().getName()+"准备执行，时间为："+System.currentTimeMillis());
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName()+"度过第一层屏障，时间为："+System.currentTimeMillis());
            System.out.println(Thread.currentThread().getName()+"当前度过的屏障数为："+phaser.getPhase());
            TimeUnit.SECONDS.sleep(2);
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName()+"度过第二层屏障，时间为："+System.currentTimeMillis());
            System.out.println(Thread.currentThread().getName()+"当前度过的屏障数为："+phaser.getPhase());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        testMethod();
    }

    public static void main(String[] args) throws InterruptedException{
        Phaser phaser = new Phaser(3){
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
               if(Thread.currentThread().getName().equals("pool-1-thread-1")){
                    System.out.println("\n"+Thread.currentThread().getName()+"执行自定义代码段");
                    return true;
                }
                //返回true，表示不再等待，Phaser呈无效/销毁状态，
                //返回false，表示Phaser继续正常向下执行，下次遇到新屏障，线程正常进入该方法
                return false;
            }
        };

        PhaserTest3 phaserTest3 = new PhaserTest3(phaser);
        ExecutorService executorService = new ThreadPoolExecutor(3,3,10,TimeUnit.SECONDS,new ArrayBlockingQueue<>(3));
        for (int i = 0; i < 3; i++) {
            executorService.execute(phaserTest3);
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
