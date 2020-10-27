package com.example.demo.ThreadTest.Collection;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author muyou
 * @date 2020/10/27 15:46
 *LinkedTransferQueue是一个有链表组成的无界阻塞Transfer队列，
 * 相对于其他阻塞队列，LinkedTransferQueue多了tryTransfer和transfer方法
 * LinkedTransferQueue采用一种预占模式，
 * 即消费者线程取走元素时，如果队列不为空，则直接取走数据，
 * 若队列为空，那就生成一个节点（节点元素为null）入队，然后消费者线程等待在这个节点上，
 * 当生产者线程入队时发现队列存在元素为null的节点，生产者线程就不入队了，直接将元素填充到该节点，
 * 并唤醒等待在这的消费者线程，消费者线程立即取走元素，从调用方法返回,这种模式为 “匹配模式”
 *
 * take()方法具有阻塞特性
 * teansfer(Object e)作用：
 * （1）如果当前存在一个正等待取值的消费者线程，则把数据立即传输过去
 * （2）如果没有存在这样的消费者线程，那就将元素插入到尾部，并且进入阻塞状态，直到有消费者线程取走元素
 *
 * tryTransfer(Object e)作用：
 * （1）如果当前存在一个正等待取值的消费者线程，使用tryTransfer(e)则把数据立即传输过去
 * (2)如果不存在，则返回false，并且数据不放入队列，执行效果是不阻塞
 *
 * tryTransfer(Object e,Long timeout,TimeUnit unit)作用：
 * (1)如果当前存在一个正等待取值的消费者线程，则把数据立即传输过去
 * (2)如果不存在，将元素插入到队列尾部，等待消费者线程获取消费掉
 * (3)如果在指定时间内元素没有被消费者线程获取，返回false，并将元素从队列中移除
 */
public class LinkedTransferQueueTest1 {
    public static void main(String[] args) {
        LinkedTransferQueue queue = new LinkedTransferQueue();
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+ " begin "+System.currentTimeMillis());
                    System.out.println("取得的值为：" + queue.take());
                    System.out.println(Thread.currentThread().getName()+ " end " + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
