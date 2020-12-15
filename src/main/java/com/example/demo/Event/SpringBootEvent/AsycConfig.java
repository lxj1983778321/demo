package com.example.demo.Event.SpringBootEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * @author muyou
 * @date 2020/11/11 14:22
 * 设置自定义线程池用于实现事件异步执行
 * @EnableAsync开启对异步线程的支持
 */
@Configuration
@EnableAsync
public class AsycConfig implements AsyncConfigurer {
    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private Logger logger = LoggerFactory.getLogger(AsycConfig.class);


    @Override
    public Executor getAsyncExecutor() {
        //这里使用的是spring的线程池，不是并发类中的
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数
        taskExecutor.setCorePoolSize(AVALIABLE_PROCESSORS);
        //最大线程数
        taskExecutor.setMaxPoolSize(AVALIABLE_PROCESSORS*2);
        //队列大小
        taskExecutor.setQueueCapacity(AVALIABLE_PROCESSORS+(AVALIABLE_PROCESSORS*2));
        //线程名前缀
        taskExecutor.setThreadNamePrefix("async-thread-");
        taskExecutor.initialize();

        return taskExecutor;
    }

    /**
     * 捕捉IllegalArgumentException异常（不合法参数异常）
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncExceptionHandler();
    }

    class MyAsyncExceptionHandler implements AsyncUncaughtExceptionHandler{

        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            logger.info("TASK Exception message - " + throwable.getMessage());
            logger.info("Method name - " + method.getName());

            for (Object param: objects) {
                logger.info("Parameter value - " + param);
            }
        }
    }
}
