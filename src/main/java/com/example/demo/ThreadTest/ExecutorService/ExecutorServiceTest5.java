package com.example.demo.ThreadTest.ExecutorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/21 9:27
 * invokeAll()快的正确慢的异常
 *结果表明：
 * invokeAll()方法对Callable抛出的异常可以进行处理
 *main打印了MyCallableA5的返回值，并进入cache块抛出MyCallableB5的中断异常
 */
public class ExecutorServiceTest5 {

    public static void main(String[] args) {
        try {
            List list = new ArrayList();
            list.add(new MyCallableA5());
            list.add(new MyCallableB5());

            ExecutorService executorService = new ThreadPoolExecutor(2,2,5,TimeUnit.SECONDS,new ArrayBlockingQueue<>(2));
            List<Future<Object>> listFuture = executorService.invokeAll(list);
            for (int i = 0; i < listFuture.size(); i++) {
                System.out.println("main 取得的返回值=========>"+ listFuture.get(i).get());
            }
            System.out.println("main 正常结束");
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

class MyCallableA5 implements Callable<String>{

    @Override
    public String call() throws Exception {
            System.out.println("MyCallableA5 "+Thread.currentThread().getName()+" begin implement "+System.currentTimeMillis());
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("MyCallableA5 运行中" + (i+1));
            }
            System.out.println("MyCallableA5 " + Thread.currentThread().getName() + " end " + System.currentTimeMillis());
        return "MyCallableA5";
    }
}

class MyCallableB5 implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("MyCallableB5 "+Thread.currentThread().getName()+" begin implement "+System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("MyCallableB5 运行中" + (i+1));
        }
        if(true){
            throw new InterruptedException("MyCallableB5 中断");
        }
        System.out.println("MyCallableB5 "+Thread.currentThread().getName()+" end " + System.currentTimeMillis());
        return "MyCallableB5";
    }
}
