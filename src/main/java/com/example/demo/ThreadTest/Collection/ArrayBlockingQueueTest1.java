package com.example.demo.ThreadTest.Collection;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author muyou
 * @date 2020/10/27 14:16
 * ArrayBlockingQueue：有界阻塞队列
 * put存放数据时，如果没有空余的空间存放数据，则呈阻塞状态，
 * task获取数据时，如果没有元素可获取，也呈阻塞状态
 */
public class ArrayBlockingQueueTest1 {

    public static void main(String[] args) {

        try {
            ArrayBlockingQueue queue = new ArrayBlockingQueue(3);
            queue.put("a");
            queue.put("b");
            queue.put("c");
            System.out.println(System.currentTimeMillis());
            queue.put("d");
            System.out.println(System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
