package com.example.demo.controller;

/**
 * @Description TODO
 * @Author lixingjian
 * @DATE 2020/6/10 14:35
 * @Version 1.0
 **/
public class MyStack {
    StackNode head;
    private int size;

    public void insert(int data){
        StackNode insertNode = new StackNode(data);
        //空栈
        if(size == 0){
            head = insertNode;
        }
        //非空栈
        else {
            insertNode.next = head;
            head = insertNode;
        }
        size++;
    }

    public StackNode remove(){
        if(size == 0){
            throw new IndexOutOfBoundsException("超过栈范围");
        }
        StackNode removeNode = null;
        if(size == 1){
            removeNode = head;
            head.next = null;
        }else {
            removeNode = head;
            head = head.next;
        }
        size--;
        return removeNode;
    }

    public void output(){
        StackNode temp = head;
        while (temp != null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.insert(0);
        myStack.insert(1);
        myStack.insert(2);
        myStack.insert(3);
        myStack.output();
        myStack.remove();
        myStack.output();

    }
}
class StackNode{
    StackNode next;
    int data;
    StackNode(int data){
        this.data = data;
    }
}
