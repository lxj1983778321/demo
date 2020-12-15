package com.example.demo.Event.Listener;

import com.example.demo.Event.event.AEvent;

/**
 * @author muyou
 * @date 2020/11/10 17:07
 */
public class AListener implements ApplicationListener<AEvent>{
    @Override
    public void onEvent(AEvent aEvent) {
        System.out.println("监听到了A事件");
    }
}
