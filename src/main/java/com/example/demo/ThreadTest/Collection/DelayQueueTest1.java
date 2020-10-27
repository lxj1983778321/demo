package com.example.demo.ThreadTest.Collection;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author muyou
 * @date 2020/10/27 15:19
 * DelayQueue：延时执行任务的队列
 * 在指定时间才能获取队列元素的功能，队列头元素是最接近过期的元素
 * 没有过期元素时，使用poll方法会返回null值
 * 超时判定是通过getDelay(TimeUnit.NANOSECONDS)方法返回值小于等于0来判断
 * 延时队列不能放空元素
 * 一般通过使用take方法阻塞等待，有过期元素时继续
 *
 */
public class DelayQueueTest1 {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<User3> delayQueue = new DelayQueue<User3>();
        delayQueue.add(new User3(7L,"name7"));
        delayQueue.add(new User3(6L,"name6"));
        delayQueue.add(new User3(5L,"name5"));
        delayQueue.add(new User3(4L,"name4"));
        delayQueue.add(new User3(3L,"name3"));

        System.out.println("      " + System.currentTimeMillis());
        System.out.println(delayQueue.take().getName()+" "+System.currentTimeMillis());
        System.out.println(delayQueue.take().getName()+" "+System.currentTimeMillis());
        System.out.println(delayQueue.take().getName()+" "+System.currentTimeMillis());
        System.out.println(delayQueue.take().getName()+" "+System.currentTimeMillis());
        System.out.println(delayQueue.take().getName()+" "+System.currentTimeMillis());
        System.out.println(delayQueue.take().getName()+" "+System.currentTimeMillis());
    }
}
class User3 implements Delayed {

    //延迟的纳秒
    private Long delayNanoTime;
    private String name;

    User3(Long delayTime,String name){
        this.name = name;
        TimeUnit unit = TimeUnit.SECONDS;
        delayNanoTime = System.nanoTime()+unit.toNanos(delayTime);
    }

    public String getName() {
        return name;
    }

    public Long getDelayNanoTime() {
        return delayNanoTime;
    }

    @Override
    public long getDelay(TimeUnit timeUnit) {

        return timeUnit.convert(delayNanoTime - System.nanoTime(),TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed delayed) {
        if(this.getDelay(TimeUnit.NANOSECONDS) - delayed.getDelay(TimeUnit.NANOSECONDS) < 0){
            return -1;
        }else if(this.getDelay(TimeUnit.NANOSECONDS)- delayed.getDelay(TimeUnit.NANOSECONDS) > 0){
            return 1;
        }
        return 0;
    }
}
