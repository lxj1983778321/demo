package com.example.demo.Event.SpringBootEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author muyou
 * @date 2020/11/11 11:09
 */
@RestController
@RequestMapping("/test")
public class testController {

    private Logger logger = LoggerFactory.getLogger(testController.class);

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @GetMapping
    public String test(String code,String message){
        logger.info("发布ApplicationEvent事件，code："+code+" message:" + message);
        applicationEventPublisher.publishEvent(new MessageEvent(this,new MessageEntity(code,message)));

        return "发布成功";
    }
}
