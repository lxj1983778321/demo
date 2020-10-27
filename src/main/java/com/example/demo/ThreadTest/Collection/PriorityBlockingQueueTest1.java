package com.example.demo.ThreadTest.Collection;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author muyou
 * @date 2020/10/27 14:40
 * take会产生阻塞效果
 */
public class PriorityBlockingQueueTest1 {

    public static void main(String[] args) throws InterruptedException {
        //默认容量11，不使用比较器
        PriorityBlockingQueue<User2> blockingQueue = new PriorityBlockingQueue<User2>();
        blockingQueue.add(new User2(15));
        blockingQueue.add(new User2(50));
        blockingQueue.add(new User2(3));
        blockingQueue.add(new User2(17));
        blockingQueue.add(new User2(52));
        System.out.println(blockingQueue.poll().getId());
        System.out.println(blockingQueue.poll().getId());
        System.out.println(blockingQueue.poll().getId());
        System.out.println(blockingQueue.poll().getId());
        System.out.println(blockingQueue.poll().getId());
        System.out.println(blockingQueue.take().getId());

    }
}
class User2 implements Comparable<User2>{
    private int id;

    User2(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }


    @Override
    public int compareTo(User2 user2) {
        if(this.id < user2.getId()){
            return -1;
        }else if(this.id > user2.getId()){
            return 1;
        }
        return 0;
    }
}
