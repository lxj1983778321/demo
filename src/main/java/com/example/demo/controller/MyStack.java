package com.example.demo.controller;

/**
 * @Description
 * 栈
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
       int[] ss = new int[]{4,3,2,1,0};
       //构建栈
        for(int i=0;i<ss.length;i++){
            if(ss[i] == i){

                continue;
            }else {
                myStack.insert(i);
            }
        }
        myStack.output();
        /*int temp = 0;
        if(myStack.size == ss.length){
            for(int i=0;i<myStack.size;i++){
                StackNode node = myStack.remove();
                if(node.data == ss[i]){
                    continue;
                }else {
                    temp = 1;
                }
            }
        }
        if(temp == 0){
            System.out.println(temp);
        }else {
            System.out.println(temp);
        }*/
    }
}
class StackNode{
    StackNode next;
    int data;
    StackNode(int data){
        this.data = data;
    }
}
