package com.example.demo.controller;

/**
 * @Description TODO
 * @Author lixingjian
 * @DATE 2020/6/10 9:58
 * @Version 1.0
 **/
public class MyLinkedList {
    private Node head;
    private Node last;
    private int size;

    /**
     * 链表插入-->头插，尾插，中间插
     */
    public void insert(int data , int index){
        if(index <0 || index > size){
            throw new IndexOutOfBoundsException("超过链表节点范围");
        }
        //生成新节点
        Node insertNode = new Node(data);
        //空链表
        if(size == 0){
            head = insertNode;
            last = insertNode;
        }
        //头插
        else if(index == 0){
            //head存的是现有头节点
            insertNode.next = head;
            head = insertNode;
        }
        //尾插
        else if(index == size){
            last.next = insertNode;
            last = insertNode;
        }
        //插中间
        else {
            Node prevNode = get(index-1);
            insertNode.next = prevNode.next;
            prevNode.next = insertNode;
        }
        size++;
    }

    public Node remove(int index){
        if(index < 0 || index >size){
            throw new IndexOutOfBoundsException("超过链表节点范围");
        }
        Node removeNode = null;
        //删除头节点
        if(index == 0){
            removeNode = head;
            head = head.next;
        }
        //删除尾节点
        else if(index == size){
            Node lastnode= get(index-1);
            removeNode = lastnode.next;
            last = lastnode;
            lastnode.next = null;
        }
        //删除中间节点
        else {
            Node prevNode = get(index-1);
            removeNode = prevNode.next;
            prevNode.next = prevNode.next.next;
        }
        size--;
        return removeNode;
    }

    //输出链表节点
    public void outPut(){
        Node temp = head;
        while (temp != null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    //查找链表节点
    public Node get(int index){
        Node temp = head;
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
class Node{
    Node next;
    int data;
    Node(int data){
        this.data = data;
    }
}

