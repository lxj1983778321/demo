package com.example.demo.controller;

/**
 * @Description
 * 链表
 * @Author lixingjian
 * @DATE 2020/6/10 9:58
 * @Version 1.0
 **/
public class MyLinkedList {
    private LinkedNode head;
    private LinkedNode last;
    private int size;

    /**
     * 链表插入-->头插，尾插，中间插
     */
    public void insert(int data , int index){
        if(index <0 || index > size){
            throw new IndexOutOfBoundsException("超过链表节点范围");
        }
        //生成新节点
        LinkedNode insertLinkedNode = new LinkedNode(data);
        //空链表
        if(size == 0){
            head = insertLinkedNode;
            last = insertLinkedNode;
        }
        //头插
        else if(index == 0){
            //head存的是现有头节点
            insertLinkedNode.next = head;
            head = insertLinkedNode;
        }
        //尾插
        else if(index == size){
            last.next = insertLinkedNode;
            last = insertLinkedNode;
        }
        //插中间
        else {
            LinkedNode prevLinkedNode = get(index-1);
            insertLinkedNode.next = prevLinkedNode.next;
            prevLinkedNode.next = insertLinkedNode;
        }
        size++;
    }

    public LinkedNode remove(int index){
        if(index < 0 || index >size){
            throw new IndexOutOfBoundsException("超过链表节点范围");
        }
        LinkedNode removeLinkedNode = null;
        //删除头节点
        if(index == 0){
            removeLinkedNode = head;
            head = head.next;
        }
        //删除尾节点
        else if(index == size){
            LinkedNode lastLinkedNode= get(index-1);
            removeLinkedNode = lastLinkedNode.next;
            last = lastLinkedNode;
            lastLinkedNode.next = null;
        }
        //删除中间节点
        else {
            LinkedNode prevLinkedNode = get(index-1);
            removeLinkedNode = prevLinkedNode.next;
            prevLinkedNode.next = prevLinkedNode.next.next;
        }
        size--;
        return removeLinkedNode;
    }

    //输出链表节点
    public void outPut(){
        LinkedNode temp = head;
        while (temp != null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    //查找链表节点
    public LinkedNode get(int index){
        LinkedNode temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }
    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.insert(0,0);
        myLinkedList.insert(1,1);
        myLinkedList.insert(2,2);
        myLinkedList.insert(3,3);
        myLinkedList.insert(4,4);
        myLinkedList.outPut();
        myLinkedList.remove(3);
        myLinkedList.outPut();
    }
}

//链表节点
class LinkedNode{
    LinkedNode next;
    int data;
    LinkedNode(int data){
        this.data = data;
    }
}

