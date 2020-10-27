package com.example.demo.controller.algorithm;/**
 * @author muyou
 * @date 2020/9/10 10:03
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 *@Description TODO
 *@Author lixingjian
 *@DATE 2020/9/10 10:03
 *@Version 1.0
 *猫狗队列
 **/
public class CatAndDogQueue {
    private Queue<PetEnterQeue> dogQ;
    private Queue<PetEnterQeue> catQ;
    private long count;//时间戳，用于记录存入时间，越小的，越早插入
    public CatAndDogQueue(){
        this.dogQ = new LinkedList<PetEnterQeue>();
        this.catQ = new LinkedList<PetEnterQeue>();
        this.count = 0;
    }

    public void add(Pet pet){
        if(pet.getPetType().equals("dog")){
            this.dogQ.add(new PetEnterQeue(pet,this.count++));
        }else if(pet.getPetType().equals("cat")){
            this.catQ.add(new PetEnterQeue(pet,this.count++));
        }else {
            throw new RuntimeException("type Error, pet type is not Dog or Cat");
        }
    }

    public Pet PollAll(){
        //count 越小代表越早放入
        if(!this.dogQ.isEmpty() && !this.catQ.isEmpty()){
            return this.dogQ.peek().getCount() < this.catQ.peek().getCount() ? this.dogQ.poll().getPet() : this.catQ.poll().getPet();
        }else if(!this.dogQ.isEmpty()){
            return this.dogQ.poll().getPet();
        }else if(!this.catQ.isEmpty()){
            return this.catQ.poll().getPet();
        }else {
            throw new NullPointerException(" queue is Empty");
        }
    }

    public Cat PollCat(){
        if(!this.catQ.isEmpty()){
            return (Cat) this.catQ.poll().getPet();
        }else {
            throw new NullPointerException(" Cat Qoeue is Empty");
        }
    }

    public Dog pollDog(){
        if(!this.dogQ.isEmpty()){
            return (Dog) this.dogQ.poll().getPet();
        }else {
            throw new NullPointerException(" Dog Qoeue is Empty");
        }
    }

    public Boolean isEmpty(){
        return this.dogQ.isEmpty() && this.catQ.isEmpty();
    }

    public Boolean isDogQueueEmpty(){
        return this.dogQ.isEmpty();
    }

    public Boolean isCatQueueEmpty(){
        return this.catQ.isEmpty();
    }
}

class PetEnterQeue{
    private Pet pet;
    private long count;

    public PetEnterQeue(Pet pet,long count){
        this.pet = pet;
        this.count = count;
    }

    public Pet getPet(){
        return this.pet;
    }

    public long getCount(){
        return this.count;
    }

    public String getEnterPetType(){
        return this.pet.getPetType();
    }
}
class Pet{
    private String type;

    public Pet(String type){
        this.type = type;
    }

    public String getPetType(){
        return this.type;
    }
}

class Dog extends Pet{
    public Dog(){
        super("dog");
    }
}

class Cat extends Pet{
    public Cat(){
        super("cat");
    }
}