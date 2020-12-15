package com.example.demo.ThreadTest.AsyncProgram.spring_AsyncProgram;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/12/8 9:56
 * 使用@Async实现异步执行
 */
@Component
@EnableAsync
public class Spring_Async {

    /**
     * 使用自定义线程执行异步任务
     * 具体实现在com.example.demo.Event.SpringBootEvent.AsycConfig;
     * @return
     */
    @Async("taskExecutor")
    public CompletableFuture<String> doSomething(){
        CompletableFuture<String> future = new CompletableFuture<String>();
        //模拟任务执行
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        future.complete("done");
        return future;
    }
}
