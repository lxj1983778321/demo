package com.example.demo.Event.Listener;

import com.example.demo.Event.event.ApplicationEvent;

/**
 * @author muyou
 * @date 2020/11/10 16:23
 */
public interface ApplicationListener<E extends ApplicationEvent> {

    void onEvent(E e);
}
