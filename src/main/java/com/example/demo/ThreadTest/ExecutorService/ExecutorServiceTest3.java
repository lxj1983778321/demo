package com.example.demo.ThreadTest.ExecutorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/21 9:27
 *
 * 实验目的：
 * invokeAny()与全部异常
 * 全部任务出现异常时，程序抛出ExecutionException异常
 * <T>T invokeAny(Collection<? extends Callable<T>> Tasks,long timeout, Timunit Unit)
 * 作用是在指定时间内取得第一个先执行完任务的结果值，否则抛出超时异常
 */
public class ExecutorServiceTest3 {

    public static void main(String[] args) {
        try {
            List list = new ArrayList();
            list.add(new MyCallableA3());
            list.add(new MyCallableB3());

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

class MyCallableA3 implements Callable<String>{

    @Override
    public String call() throws Exception {
            System.out.println("MyCallableA3 "+Thread.currentThread().getName()+" begin implement");
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("MyCallableA3 运行中" + (i+1));
            }
            if(true){
                System.out.println("MyCallableA3 中断了");
                throw new Exception("MyCallableA3 报错");
            }
            System.out.println("MyCallableA3 " + Thread.currentThread().getName() + " end " + System.currentTimeMillis());
        return "MyCallableA3";
    }
}

class MyCallableB3 implements Callable<String>{

    @Override
    public String call() throws Exception {
            System.out.println("MyCallableB3 "+Thread.currentThread().getName()+" begin implement"+System.currentTimeMillis());
            for (int i = 0; i < 10; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("MyCallableB3 运行中" + (i+1));
            }
            System.out.println("MyCallableB3 "+Thread.currentThread().getName()+" end " + System.currentTimeMillis());
        if(true){
            System.out.println("MyCallableB3 中断了");
            throw new Exception("MyCallableB3 报错");
        }
        return "MyCallableB3";
    }
}
