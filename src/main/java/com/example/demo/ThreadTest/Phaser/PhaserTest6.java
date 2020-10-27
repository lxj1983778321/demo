package com.example.demo.ThreadTest.Phaser;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/16 15:37
 * awaitAdvance(int phase)方法：
 * 如果传入值phase和getPhase()方法返回值一样，则在屏障处等待，否则继续向下面运行
 *方法awaitAdvance(int phase)不参与parties的计数，仅仅具有判断功能,不可被中断
 *
 * awaitAdvanceInterruptibly(int phase)作用是传入参数phase值和当前getPhase()返回值不一致时，则继续执行下面的代码
 * awaitAdvanceInterruptibly(int phase, long timeout, TimeUnit unit)作用是如果在指定的时间内，Phase值未变，则出现异常，否则继续向下运行。
 * awaitAdvanceInterruptibly(int phase)方法/awaitAdvanceInterruptibly(int phase, long timeout, TimeUnit unit)方法可以被中断
 */
public class PhaserTest6 implements Runnable{

    private Phaser phaser;

    PhaserTest6(Phaser phaser){
        this.phaser = phaser;
    }

    public void testMethod() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"进入时间："+System.currentTimeMillis());
        TimeUnit.SECONDS.sleep(2);
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName()+"跨过时间："+System.currentTimeMillis());
    }


    @Override
    public void run() {
        try {
            testMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Phaser phaser = new Phaser(3);
        PhaserTest6 phaserTest6 = new PhaserTest6(phaser);
        PhaserTest6_1 phaserTest6_1 = new PhaserTest6_1(phaser);
        ExecutorService executorService = new ThreadPoolExecutor(4,4,10, TimeUnit.SECONDS,new ArrayBlockingQueue<>(4));
        for (int i = 0; i < 4; i++) {
            if(i == 1){
                executorService.execute(phaserTest6_1);
            }else {
                executorService.execute(phaserTest6);
            }
            TimeUnit.SECONDS.sleep(3);
        }
    }
}

class PhaserTest6_1 implements Runnable{
    Phaser phaser;
    PhaserTest6_1(Phaser phaser){
        this.phaser = phaser;
    }

    public void testMethod(){
        System.out.println(Thread.currentThread().getName()+"进入PhaserTest6_1类方法--->1次打印");
        System.out.println("当前phase数量为："+phaser.getPhase());
        //phase屏障数不一致，所以立即执行
        phaser.awaitAdvance(1);
        //会阻塞，直到phase数量不为1，执行以下代码
        phaser.awaitAdvance(0);
        System.out.println(Thread.currentThread().getName()+"进入PhaserTest6_1类方法---->2次打印");
    }

    @Override
    public void run() {
        testMethod();
    }
}
