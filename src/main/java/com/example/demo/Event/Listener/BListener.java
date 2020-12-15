package com.example.demo.Event.Listener;

import com.example.demo.Event.event.AEvent;
import com.example.demo.Event.event.BEvent;

/**
 * @author muyou
 * @date 2020/11/10 17:07
 */
public class BListener implements ApplicationListener<BEvent>{

    @Override
    public void onEvent(BEvent bEvent) {
        System.out.println("监听到了B事件");
    }
}
