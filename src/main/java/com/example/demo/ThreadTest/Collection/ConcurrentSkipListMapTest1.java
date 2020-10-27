package com.example.demo.ThreadTest.Collection;

import java.util.Map.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;

/**
 * @author muyou
 * @date 2020/10/26 16:04
 * 当即要求并发安全又要求可以排序的情况时，可以采用ConcurrentSkipListMap类
 */
public class ConcurrentSkipListMapTest1{
    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        ThreadTest a = new ThreadTest(service);
        ThreadTest b= new ThreadTest(service);
        a.start();
        b.start();
    }
}
class ThreadTest extends Thread{

    public MyService service;

    ThreadTest(MyService service){
        this.service = service;
    }

    @Override
    public void run() {
        try {
            while (!service.map.isEmpty()){
                Entry entry = service.map.pollFirstEntry();
                User user = (User) entry.getKey();
                System.out.println(user.getId()+" "+user.getName()+" "+entry.getValue());
                Thread.sleep((long) (Math.random()*1000));
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
class MyService {

    public ConcurrentSkipListMap map = new ConcurrentSkipListMap();

    public MyService(){
        User user1 = new User(1,"user1");
        User user2 = new User(2,"user2");
        User user3 = new User(3,"user3");
        User user4 = new User(4,"user4");
        User user5 = new User(5,"user5");

        map.put(user1,"value1");
        map.put(user4,"value4");
        map.put(user2,"value2");
        map.put(user5,"value5");
        map.put(user3,"value3");
    }
}
class User implements Comparable<User>{

    private int id;
    private String name;

    User(int id,String name){
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
    public int compareTo(User user) {
        if(this.getId() < user.getId()){
            return -1;
        } else if(this.getId() > user.getId()){
            return 1;
        }
        return 0;
    }
}