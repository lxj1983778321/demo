package com.example.demo.Event.SpringBootEvent;

import org.springframework.context.ApplicationEvent;

/**
 * @author muyou
 * @date 2020/11/11 10:43
 * 事件源
 */
public class MessageEvent extends ApplicationEvent {

    private MessageEntity messageEntity;

    public MessageEvent(Object source,MessageEntity messageEntity) {
        super(source);
        this.messageEntity = messageEntity;
    }

    public MessageEntity getMessageEntity() {
        return messageEntity;
    }
}
