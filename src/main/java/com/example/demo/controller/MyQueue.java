package com.example.demo.controller;

/**
 * @Description
 * 单边队列----->先进先出---》使用的物理结构为：链表
 * @Author lixingjian
 * @DATE 2020/6/10 16:19
 * @Version 1.0
 **/
public class MyQueue {
    //头节点
    QueueNode head;
    //尾节点
    QueueNode last;
    //当前长度
    int size;

    public void insert(int data){
        QueueNode insertNode = new QueueNode(data);
        //空队列
        if(size == 0){
            head = insertNode;
            last = insertNode;
        }else {
                last.next = insertNode;
                last = insertNode;
            }
        size++;
    }
    public QueueNode remove(){
        QueueNode removeNode = null;
        if(size < 0){
            throw new IndexOutOfBoundsException("队列为空，移除失败");
        }else {
            removeNode = head;
            head = head.next;
        }
        size--;
        return removeNode;
    }

    public void output(){
        QueueNode temp = head;
        while (temp != null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public QueueNode get(int index){
            if(index <0 || index>size){
                throw new IndexOutOfBoundsException("超过队列范围");
            }
            QueueNode temp = head;
            for (int i = 0; i <size ; i++) {
                temp = temp.next;
            }
            return temp;
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.insert(0);
        myQueue.insert(1);
        myQueue.insert(2);
        myQueue.output();
        QueueNode node = myQueue.remove();
        System.out.println("-------remove():"+node.data);
        QueueNode node1 = myQueue.remove();
        System.out.println("-------remove():"+node1.data);
        QueueNode node2 = myQueue.remove();
        System.out.println("-------remove():"+node2.data);
        myQueue.output();

    }
}
//链表节点
class QueueNode{
    int data;
    QueueNode next;
    QueueNode(int data){
        this.data = data;
    }
}