package com.example.demo.ThreadTest.Fork_Join;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/23 9:30
 * ForkJoinPool类中execute()方法是以异步的方式执行任务
 */
public class ForkJoinTest4 {
/*    public static void main(String[] args) {
        try {
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            forkJoinPool.execute(new MyRecursiveAction4());
            TimeUnit.SECONDS.sleep(5);
            //System.out.println("main end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/


    /*public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("ThreadNAme = "+Thread.currentThread().getName());
            }
        });
        TimeUnit.SECONDS.sleep(5);
    }*/

    /**
     * execute处理返回值
     * @param args
     */
    public static void main(String[] args) {
        try {
            MyRecursiveTask4  task = new MyRecursiveTask4();
            ForkJoinPool pool = new ForkJoinPool();
            pool.execute(task);
            //execute()方法没有返回值，想获取返回值需要借助RecursiveTask
            System.out.println(System.currentTimeMillis());
            //具有阻塞特性
            System.out.println(task.get());
            System.out.println(System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
class MyRecursiveAction4 extends RecursiveAction{

    @Override
    protected void compute() {
        try {
            TimeUnit.SECONDS.sleep(4);
            System.out.println("ThreadNAme = "+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyRecursiveTask4 extends RecursiveTask<String>{

    @Override
    protected String compute() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "this is retuen";
    }
}
