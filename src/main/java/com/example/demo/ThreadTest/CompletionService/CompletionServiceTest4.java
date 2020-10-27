package com.example.demo.ThreadTest.CompletionService;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/20 15:31
 * poll()：获取并移除表示下一个已完成任务的Future,如果不存在这样的人物，返回null，无阻塞特性
 * poll(long timeout,Timunit unit):等待指定的timeout时间，在timeout时间内取到值时立即向下执行，如果超时也立即向下执行
 * 当timeout的值如果小于任务执行时间，则返回值就是null
 */
public class CompletionServiceTest4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(5,5,5, TimeUnit.SECONDS,new ArrayBlockingQueue<>(5));
        CompletionService completionService = new ExecutorCompletionService(executorService);

        for (int i = 0; i < 5; i++) {
            completionService.submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    TimeUnit.SECONDS.sleep(2);
                    return Thread.currentThread().getName()+"<--->"+System.currentTimeMillis();
                }
            });
            //返回null，说明poll没有阻塞特性
            System.out.println(completionService.poll());
        }

    }
}
