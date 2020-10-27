package com.example.demo.controller.algorithm;


/**
 * @Description 判断链表是否有环----》追击问题
 * @Author lixingjian
 * @DATE 2020/7/13 17:29
 * @Version 1.0
 **/
public class ListIsCycle {

    public static Boolean isCycle(Node node){
        if(node == null){
            return null;
        }
        Node p1 = node;
        Node p2 = node;
        while (p1 != null && p2.next !=null){
            p1 = p1.next;
            p2 = p2.next.next;
            if(p1 == p2){
                //p1为相遇点
                System.out.println("链表的环长为：" + listCycleLength(p1));
                System.out.println("链表的入环节点地址为：" + cycleNode(node, p1));
                return true;
            }
        }
        return false;
    }

    /**
     * 判断环长----》前提是链表有环 -----》环长 = 每一次速度差 * 前进次数 = 前进次数
     * p2 比 p1 快一圈
     * @param node
     * @return
     */
    public static int listCycleLength(Node node){
        Node p1 = node;
        Node p2 = node;
        //环长初始为0
        int listCycleLength = 0;
        while (p1 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
            listCycleLength++;
            if(p1 == p2){
                return listCycleLength;
            }
        }
        return listCycleLength;
    }


    /**
     * 确定入环节点
     * 原理：把其中一个节点放到头节点，另一个节点放在相遇节点，他们都每次前进一步，相遇点即为入环节点
     * @param root
     * @param meetNode
     * @return
     */
    public static Node cycleNode(Node root,Node meetNode){
        //根节点
        Node p1 = root;
        //首次相遇节点
        Node p2 = meetNode;
        while (p1 != null && p2 != null){
            p1 = p1.next;
            p2 = p2.next;
            if(p1 == p2){
                System.out.println("链表入环节点值为："+p1.data);
                return p1;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        Node node1 = new Node(5);
        Node node2 = new Node(3);
        Node node3 = new Node(7);
        Node node4 = new Node(2);
        Node node5 = new Node(6);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node2;

        System.out.println("链表是否存在环节点："+isCycle(node1));

    }
}

class Node{
    int data;
    Node next;
    Node(int data){
        this.data = data;
    }
}
