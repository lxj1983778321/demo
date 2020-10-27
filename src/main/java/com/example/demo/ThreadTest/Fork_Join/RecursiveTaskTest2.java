package com.example.demo.ThreadTest.Fork_Join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * @author muyou
 * @date 2020/10/22 15:57
 * 使用RecursiveTask 实现字符串累加
 * 方法join()和get()都可以获取返回值，不同在于异常处理上，get()方法执行任务时，当子任务出现异常，可以在main中捕获异常
 */
public class RecursiveTaskTest2 {

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        ForkJoinTask<String> forkJoinTask = forkJoinPool.submit(new MyRecursiveTask2(1,1000));
        System.out.println(forkJoinTask.join());
        TimeUnit.SECONDS.sleep(5);
    }

}

class MyRecursiveTask2 extends RecursiveTask<String>{
    private int beginValue;
    private int endValue;

    MyRecursiveTask2(int beginValue,int endValue){
        this.beginValue = beginValue;
        this.endValue = endValue;
    }

    /**
     * invokeAll()是同步方法，直到提交给池的任务完成才可以工作窃取
     * fork()是异步方法，提交任务给执行者的方法会立即返回，继续执行，只有调用get()或join()等待任务执行完成时，才可以使用工作窃取
     * @return
     */
    @Override
    protected String compute() {
        if(endValue-beginValue>2){
            int middleValue = (endValue+beginValue) / 2;
            MyRecursiveTask2 leftTask = new MyRecursiveTask2(beginValue,middleValue);
            MyRecursiveTask2 rightTask = new MyRecursiveTask2(middleValue+1,endValue);
            RecursiveTask.invokeAll(leftTask,rightTask);
            return leftTask.join()+rightTask.join();
        }else {
            String returnString = "";
            for (int i = beginValue; i <= endValue; i++) {
                returnString = returnString + (i) +" ";
            }
            System.out.println("else 返回值为：" + returnString +" 初始值为："+beginValue+" 末尾值为："+endValue);
            return returnString;
        }
    }
}