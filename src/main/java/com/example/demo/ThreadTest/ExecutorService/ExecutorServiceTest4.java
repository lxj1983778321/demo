package com.example.demo.ThreadTest.ExecutorService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/21 9:27
 * invokeAll(Collection tasks)全正确
 *
 */
public class ExecutorServiceTest4 {

    public static void main(String[] args) {
        try {
            List list = new ArrayList();
            list.add(new MyCallableA4());
            list.add(new MyCallableB4());

            ExecutorService executorService = new ThreadPoolExecutor(2,2,5,TimeUnit.SECONDS,new ArrayBlockingQueue<>(2));
            List<Future<Object>> listFuture = executorService.invokeAll(list);
            for (int i = 0; i < listFuture.size(); i++) {
                System.out.println("main 取得的返回值=========>"+ listFuture.get(i).get());
            }
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

class MyCallableA4 implements Callable<String>{

    @Override
    public String call() throws Exception {
            System.out.println("MyCallableA3 "+Thread.currentThread().getName()+" begin implement "+System.currentTimeMillis());
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("MyCallableA3 运行中" + (i+1));
            }
            System.out.println("MyCallableA3 " + Thread.currentThread().getName() + " end " + System.currentTimeMillis());
        return "MyCallableA3";
    }
}

class MyCallableB4 implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("MyCallableB3 "+Thread.currentThread().getName()+" begin implement "+System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("MyCallableB3 运行中" + (i+1));
        }
        System.out.println("MyCallableB3 "+Thread.currentThread().getName()+" end " + System.currentTimeMillis());
        return "MyCallableB3";
    }
}
