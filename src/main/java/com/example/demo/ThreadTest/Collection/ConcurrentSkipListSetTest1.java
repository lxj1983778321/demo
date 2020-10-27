package com.example.demo.ThreadTest.Collection;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author muyou
 * @date 2020/10/26 16:40
 * ConcurrentSkipListSet支持排序而且不允许重复的元素
 */
public class ConcurrentSkipListSetTest1 {

    public static void main(String[] args) {
        MyService1 service1 = new MyService1();
        ThreadTest1 a = new ThreadTest1(service1);
        ThreadTest1 b = new ThreadTest1(service1);

        a.start();
        b.start();
    }
}
class ThreadTest1 extends Thread{
    private MyService1 service;

    ThreadTest1(MyService1 service){
        this.service = service;
    }

    @Override
    public void run() {
        try {
            while (!service.set.isEmpty()){
                User1 user1 = (User1)service.set.pollFirst();
                System.out.println(user1.getId()+" "+user1.getName());
                Thread.sleep((long) (Math.random()*1000));
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
class MyService1 {
    public ConcurrentSkipListSet set = new ConcurrentSkipListSet();

    MyService1(){
        User1 user1 = new User1(1,"user1");
        User1 user3 = new User1(3,"user3");
        User1 user5 = new User1(5,"user5");
        User1 user4 = new User1(4,"user4");
        User1 user44 = new User1(4,"user4");

        set.add(user1);
        set.add(user3);
        set.add(user5);
        set.add(user4);
        set.add(user44);
    }
}
class User1 implements Comparable<User1>{
    private int id;
    private String name;

    User1(int id,String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(User1 user) {
        if(this.getId() < user.getId()){
            return -1;
        } else if(this.getId() > user.getId()){
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User1 user1 = (User1) obj;
       if(id != user1.id){
           return false;
       }
       if(name == null){
           if(user1.name != null){
               return false;
           }
       }else if(!name.equals(user1.name)){
           return false;
       }
       return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null)? 0:name.hashCode());
        return result;
    }
}