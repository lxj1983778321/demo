package com.example.demo.Event.SpringBootEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.context.support.ServletRequestHandledEvent;

import java.util.concurrent.TimeUnit;

/**
 * @author muyou
 * @date 2020/11/11 10:45
 * 事件监听器
 */
@Configuration
public class MessageListener {

    private Logger logger = LoggerFactory.getLogger(MessageListener.class);

    //监听任意类型的事件
    @EventListener
    public void handleEvent(Object event){
        if(event instanceof ServletRequestHandledEvent){
            logger.info("监听到了ServletRequestEvent事件：："+event.toString());
        }
    }

    //这个方法异步执行
    //加了 @Async注解使用的是spring内部默认的线程池
    //我们可以自定义这个线程池
    @EventListener
    @Async
    public void handleMessageEvent(MessageEvent event){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("监听到了MessageEvent事件,消息为："+event.getMessageEntity().toString()+" 发布时间为："+String.valueOf(event.getTimestamp()));
    }

    @EventListener(condition = "#event.messageEntity.code == 'okEvent'")
    public void handleMessageEventByCode(MessageEvent event){
        logger.info("监听到了code为okEvent事件\n");
    }

    @EventListener
    public void handleObjectEvent(MessageEntity entity){
        logger.info("监听到对象事件，消息为："+entity.toString());
    }


}
