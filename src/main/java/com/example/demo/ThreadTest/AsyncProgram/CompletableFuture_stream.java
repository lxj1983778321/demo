package com.example.demo.ThreadTest.AsyncProgram;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author muyou
 * @date 2020/12/7 10:28
 * CompletableFuture与stream结合使用
 * 例子：消费端对服务提供方集群中的某个服务进行广播调用（轮询调用同一个服务的不同提供者的机器）
 */
public class CompletableFuture_stream {

    private static final int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private static ExecutorService executorService =
            new ThreadPoolExecutor(
                    AVALIABLE_PROCESSORS,
                    AVALIABLE_PROCESSORS*2,
                    1,
                    TimeUnit.MINUTES,
                    new ArrayBlockingQueue<>(5));

    public static String rpc_call(String ip,String param){
        System.out.println(ip + " rpeCall: " + param);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Thread.currentThread().getName() + " : " + param;
    }

    public static void main(String[] args) {
        //生成ipList列表
        List<String> ipList = new ArrayList<String>();
        for (int i = 1; i <= 10; i++) {
            ipList.add("192.168.0."+ i);
        }

        //并发调用
        List<CompletableFuture<String>> futureList =
                ipList.stream().map(
                        ip->CompletableFuture.supplyAsync(()->rpc_call(ip,ip),executorService)//同步转换为异步
                ).collect(Collectors.toList());//收集结果
        //等待异步任务执行完毕，并收集结果
        List<String> resultList = futureList.stream().map(future->future.join()).collect(Collectors.toList());

        resultList.stream().forEach(r-> System.out.println(r));
    }
}
