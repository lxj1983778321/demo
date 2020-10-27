package com.example.demo.ThreadTest.Fork_Join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author muyou
 * @date 2020/10/22 16:32
 * 使用RecursiveTask实现求和
 */
public class RecursiveTaskTest3 {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(new MyRecursiveTask3(1,10));
        System.out.println("main 接受的最终结果值为：" + forkJoinTask.join());
    }
}
class MyRecursiveTask3 extends RecursiveTask<Integer>{
    private ReentrantLock reentrantLock = new ReentrantLock();
    private int beginNumber;
    private int endNumber;

    MyRecursiveTask3(int beginNumber,int endNumber){
        this.beginNumber = beginNumber;
        this.endNumber = endNumber;
        System.out.println("# "+beginNumber+" " + endNumber);
    }

    @Override
    protected Integer compute() {

            System.out.println("compute = " + beginNumber + " " + endNumber);
            if(endNumber - beginNumber !=0 ){
                int middleNumber = (endNumber+beginNumber)/2;
                System.out.println("left值为：" + beginNumber + " "+endNumber);
                MyRecursiveTask3 leftRecursive = new MyRecursiveTask3(beginNumber,middleNumber);
                System.out.println("right值为：" + beginNumber + " "+endNumber);
                MyRecursiveTask3 rightRecursive = new MyRecursiveTask3(middleNumber+1,endNumber);

                RecursiveTask.invokeAll(leftRecursive,rightRecursive);
                Integer leftJoin = leftRecursive.join();
                Integer rightJoin = rightRecursive.join();
                Integer sumJoin = leftJoin+rightJoin;
                System.out.println("sum的值为：" + sumJoin);
                return sumJoin;
            }else {
                return this.endNumber;
            }
    }

}