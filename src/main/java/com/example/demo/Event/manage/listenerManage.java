package com.example.demo.Event.manage;

import com.example.demo.Event.Listener.ApplicationListener;
import com.example.demo.Event.event.ApplicationEvent;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author muyou
 * @date 2020/11/10 16:24
 * 事件驱动
 * 事件管理器
 */
public class listenerManage {
    //保存所有的监听器
   static List<ApplicationListener<?>> list = new ArrayList<ApplicationListener<?>>();

   //如果优雅一点，可以考虑扫描整个项目
   public static void  addListener(ApplicationListener listener){
       list.add(listener);
   }

   //发布事件
   public static void pushEvent(ApplicationEvent event){
        //判断下有哪些对这个事件感兴趣，判断泛型
       for (ApplicationListener e:list) {
            //获取泛型,通过反射获取的
           Class tClass = (Class) ((ParameterizedType)e.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
           //判断泛型
           if (tClass.equals(event.getClass())) {
               e.onEvent(event);
           }
       }
   }
}
