package com.example.demo.ThreadTest.ExecutorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/21 9:27
 *
 * 实验目的：
 * invokeAny()与执行快的任务异常：
 * 当执行快的任务出现异常，默认情况下不在控制台输出异常信息，除非显示使用try-cache捕获，而等待执行慢的任务返回的结果值
 * 如果想显示捕获异常，只需在MycallableA2中使用try-cache块即可，但如果不在MycallableA2的cache块中不重新抛出异常的话，
 * 则main不能取得MyCallableB2任务的返回值，反而会取的MyCallableA2的返回值，即MyCallableA2异常状态未上报，重新抛出异常的话，可以获取
 *以下示例为：MycallableA2不抛出异常，main最终打印MyCallableA2的返回值
 */
public class ExecutorServiceTest2 {

    public static void main(String[] args) {
        try {
            List list = new ArrayList();
            list.add(new MyCallableA2());
            list.add(new MyCallableB2());

            ExecutorService executorService = new ThreadPoolExecutor(2,2,5,TimeUnit.SECONDS,new ArrayBlockingQueue<>(2));
            String getValue = (String) executorService.invokeAny(list);
            System.out.println("main 取得的返回值=========>"+getValue);

        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("main ："+e.getMessage());
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("main ："+e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("main ："+e.getMessage());
        }
    }

}

class MyCallableA2 implements Callable<String>{

    @Override
    public String call() throws Exception {
        try {
            System.out.println("MyCallableA2 "+Thread.currentThread().getName()+" begin implement");
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("MyCallableA2 运行中" + (i+1));
            }
            if(true){
                System.out.println("MyCallableA2 中断了");
                throw new InterruptedException();
            }
            System.out.println("MyCallableA2 " + Thread.currentThread().getName() + " end " + System.currentTimeMillis());
        }catch (Exception e){
            System.out.println(e.getMessage());
           // throw e;
        }

        return "MyCallableA2";
    }
}

class MyCallableB2 implements Callable<String>{

    @Override
    public String call() throws Exception {
        try {
            System.out.println("MyCallableB2 "+Thread.currentThread().getName()+" begin implement"+System.currentTimeMillis());
            for (int i = 0; i < 10; i++) {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("MyCallableB2 运行中" + (i+1));
            }
            System.out.println("MyCallableB2 "+Thread.currentThread().getName()+" end " + System.currentTimeMillis());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage() + " 通过catch块显示捕获异常");
            throw e;
        }

        return "MyCallableB2";
    }
}
