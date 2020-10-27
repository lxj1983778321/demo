package com.example.demo.ThreadTest.ExecutorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/21 9:27
 * invokeAll()快的异常，慢的正确
 *结果表明：
 *在第一次循环获取返回值时，产生异常并退出for循环，所以在main中没有获取B的返回值，程序直接进入cache块
 * 使用invokeAll()方法如果全部出现异常，打印效果与此一样
 * invokeAll(Collection tasks,long timeout,TimeUnit unit) : 如果全部任务在指定时间内没有完成，抛出异常
 */
public class ExecutorServiceTest6 {

    public static void main(String[] args) {
        try {
            List list = new ArrayList();
            list.add(new MyCallableA6());
            list.add(new MyCallableB6());

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

class MyCallableA6 implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("MyCallableA6 "+Thread.currentThread().getName()+" begin implement "+System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("MyCallableA6 运行中" + (i+1));
        }
        if(true){
        throw new InterruptedException("MyCallableA6 中断");
        }
        System.out.println("MyCallableA6 " + Thread.currentThread().getName() + " end " + System.currentTimeMillis());
        return "MyCallableA6";
    }
}

class MyCallableB6 implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("MyCallableB6 "+Thread.currentThread().getName()+" begin implement "+System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("MyCallableB6 运行中" + (i+1));
        }

        System.out.println("MyCallableB6 "+Thread.currentThread().getName()+" end " + System.currentTimeMillis());
        return "MyCallableB6";
    }
}
