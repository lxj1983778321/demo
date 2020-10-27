package com.example.demo.ThreadTest.Fork_Join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * @author muyou
 * @date 2020/10/22 14:37
 * RecursiveAction：任务无返回值，仅执行一次任务
 * 使用RecursiveAction分解任务：
 * 调用ForkJoinTask类中的fork()方法时需要注意效率问题，因为每一次调用fork()方法都会分离任务，增加系统的运行负担
 * 所以ForkJoinTask类中提供了 public static void invokenAll(ForkJoinTask<?> t1,ForkJoinTask<?>t2)方法来优化执行效率。
 * 本测试用例也是用此方法分离任务并执行
 * */
public class RecursiveActionTest1 {
    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(new MyRecursiveAction1(1,10));
        TimeUnit.SECONDS.sleep(5);
    }
}
class MyRecursiveAction1 extends RecursiveAction{
    private int beginValue;
    private int endValue;

    MyRecursiveAction1(int beginValue,int endValue){
        this.beginValue = beginValue;
        this.endValue = endValue;
    }

    @Override
    protected void compute() {
        System.out.println(Thread.currentThread().getName()+" begin implement");
        if(endValue - beginValue >2){
            int middelValue = (endValue+beginValue) / 2;
            MyRecursiveAction1 leftAction = new MyRecursiveAction1(beginValue,middelValue);
            MyRecursiveAction1 rightAction = new MyRecursiveAction1(middelValue+1,endValue);
            RecursiveAction.invokeAll(leftAction,rightAction);
        }else {
            System.out.println("打印组合为：" + beginValue + "--" + endValue);
        }
    }
}