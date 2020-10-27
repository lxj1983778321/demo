package com.example.demo.ThreadTest.ExecutorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/21 9:27
 * invokenAny()：取得第一个完成任务的结果值，当第一个任务执行完成后，会调用interrupt()方法将其他任务中断，
 * 可以结合if(Thread.currentThread().isInterrupt()==true)代码来决定任务是否继续执行
 * invokenAll()：等全部任务执行完成后，返回全部任务的结果值
 *
 * 实验目的：
 * invokeAny(Collection tasks)的使用与interrupt
 *（1）无if(Thread.currentThread().isInterrupt())代码，已经获取运行结果值后，其他线程继续运行
 *（2）有if(Thread.currentThread().isInterrupt())代码，已经获取第一个运行结果值后，其他线程如果使用throw new InterruptException()代码
 * ze其他线程全部中断，虽然抛出异常，但main并不进行捕获，如想捕获异常，需要在Callable中使用try-cache块
 */
public class ExecutorServiceTest1 {

    public static void main(String[] args) {
        try {
            List list = new ArrayList();
            list.add(new MyCallableA());
            list.add(new MyCallableB());

            ExecutorService executorService = new ThreadPoolExecutor(2,2,5,TimeUnit.SECONDS,new ArrayBlockingQueue<>(2));
            String getValue = (String) executorService.invokeAny(list);
            System.out.println("=========>"+getValue);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }catch (Exception e){
            System.out.println(Thread.currentThread().getName()+"抛出异常的信息为："+e.getMessage());
        }
    }

}

class MyCallableA implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName()+" begin implement");
        for (int i = 0; i < 123456; i++) {
            Math.random();
            Math.random();
            Math.random();
            System.out.println("MyCallableA " + (i+1));
        }
        System.out.println("MyCallableA end" + System.currentTimeMillis());
        return "MyCallableA";
    }
}

class MyCallableB implements Callable<String>{

    @Override
    public String call() throws Exception {
        try {
            System.out.println("MyCallableB "+Thread.currentThread().getName()+" begin implement"+System.currentTimeMillis());
            for (int i = 0; i < 123456; i++) {
                if(Thread.currentThread().isInterrupted() == false){
                    Math.random();
                    Math.random();
                    Math.random();
                    Math.random();
                    Math.random();
                    System.out.println("MyCallableB " + (i+1));
                }else {
                    System.out.println("MyCallableB ******* 抛出异常中断了 ");
                    throw new InterruptedException();
                }
            }
            System.out.println("MyCallableB "+Thread.currentThread().getName()+" end" + System.currentTimeMillis());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage() + " 通过catch块显示捕获异常");
            throw e;
        }

        return "MyCallableB";
    }
}
