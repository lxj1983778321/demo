package com.example.demo.controller.algorithm;

import java.util.*;

/**
 * @Description 递归与非递归遍历二叉树
 * @Author lixingjian
 * @DATE 2020/7/6 9:18
 * @Version 1.0
 **/
public class Tree {
    /**
     * 构建二叉树
     */
    public static TreeNode createBinaryTree(LinkedList<Integer> inputList){
        TreeNode node = null;
        if(inputList==null || inputList.isEmpty()){
            return null;
        }
        Integer data = null;
        try {
            data = inputList.removeFirst();
        }catch (NoSuchElementException e){
            data = null;
        }
        if(data != null){
            node = new TreeNode(data);
            node.left = createBinaryTree(inputList);
            node.right = createBinaryTree(inputList);
        }
        return node;
    }

    /**
     * 递归方式--->前序遍历---》根左右
     */
    public static void preOrderTraveral(TreeNode node){
        if(node == null){
           return;
        }
        System.out.println(node.data);
        preOrderTraveral(node.left);
        preOrderTraveral(node.right);
    }
    /**
     * 非递归方式----》前序遍历---》根左右
     */
    public static void preOrderTraveralWithStack(TreeNode node){
        if(node == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode treeNode = node;
        //当treeNode遍历完，或者是stack没有元素时，遍历结束
        while (treeNode != null || !stack.isEmpty()){
            while (treeNode != null){
                System.out.println(treeNode.data);
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            if(!stack.isEmpty()){
                treeNode = stack.pop();
                treeNode = treeNode.right;
            }
        }
    }

    /**
     * 递归方式--->中序遍历-->左根右
     */
    public static void inOrderTraveral(TreeNode node){
        if(node == null){
            return;
        }
        inOrderTraveral(node.left);
        System.out.println(node.data);
        inOrderTraveral(node.right);
    }

    /**
     * 非递归方式----->中序遍历---》左根右
     */
    public static void inOrderTraveralWithStack(TreeNode node){
        if(node == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode treeNode = node;
        while (treeNode != null || !stack.isEmpty()){
            while (treeNode != null){
                //根节点入栈
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            if(!stack.isEmpty()){
                treeNode = stack.pop();
                System.out.println(treeNode.data);
                treeNode = treeNode.right;
            }
        }
    }

    /**
     *递归方式----》后序遍历---》根左右
     */
    public static void postOrderTraveral(TreeNode node){
        if(node == null){
            return;
        }
        postOrderTraveral(node.left);
        postOrderTraveral(node.right);
        System.out.println(node.data);
    }

    /**
     *非递归方式---->后序遍历--->根左右
     */
    public static void postOrderTraveralWithStack(TreeNode node){
        if(node == null){
            return;
        }
        LinkedList<TreeNode> input = new LinkedList<TreeNode>();
        LinkedList<TreeNode> output = new LinkedList<TreeNode>();
        input.addLast(node);
        while (!input.isEmpty()){
                TreeNode treeNode = input.removeFirst();
                output.addLast(treeNode);
                if(treeNode.left!=null){
                    input.addLast(treeNode.left);
                }
                if(treeNode.right != null) {
                    input.addLast(treeNode.right);
                }
            }
            //输出最终后序遍历的结果
            while(!output.isEmpty()){
                System.out.println(output.removeFirst().data);
            }
        }

    /**
     * 二叉树层序遍历---》利用队列的先进先出特性实现层序遍历
     */
    public static void levelOrderTraversal(TreeNode node){
        if (node == null){
            return;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(node);
        while (!queue.isEmpty()){
            TreeNode treeNode = queue.poll();
            System.out.println(treeNode.data);
            if(treeNode.left != null){
                queue.offer(treeNode.left);
            }
            if(treeNode.right != null){
                queue.offer(treeNode.right);
            }
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> inputList = new LinkedList<Integer>(Arrays. asList(new Integer[]{3,2,9,null,null,10,null, null,8,null,4}));
        TreeNode treeNode = createBinaryTree(inputList);
        /*preOrderTraveral(treeNode);
        System.out.println("----------------------------");*/
        //preOrderTraveralWithStack(treeNode);
        /*inOrderTraveral(treeNode);
        System.out.println("----------------------------");
        inOrderTraveralWithStack(treeNode);*/
        /*postOrderTraveral(treeNode);
        System.out.println("----------------------------");*/
        postOrderTraveralWithStack(treeNode);
        //使用队列实现二叉树层序遍历
        //levelOrderTraversal(treeNode);
    }
}

class TreeNode{
    //值
    int data;
    //左节点
    TreeNode left;
    //右节点
    TreeNode right;

    TreeNode(int data){
        this.data = data;
    }
}