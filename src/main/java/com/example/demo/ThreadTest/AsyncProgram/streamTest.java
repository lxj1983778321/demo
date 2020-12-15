package com.example.demo.ThreadTest.AsyncProgram;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author muyou
 * @date 2020/12/7 9:56
 * 丽：从person列表中过滤出年龄大于10岁的人，并且收集对应的name字段到list，然后统一打印处理
 */
public class streamTest {

    public static List<person> create_personList(){
        List<person> personList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            String name = "name" + i;
            person person = new person(i,name);
            personList.add(person);
        }
        return personList;
    }

    /**
     * 如果不使用流的话，我们只能通过遍历list，取出其中的每一个实例，然后通过判断是否大于10，并放入新集合，然后遍历打印
     * 实现并不复杂，但是很繁琐
     */
    public static void useSttream(List<person> personList){
        List<String> nameList = personList.stream()
                .filter(person -> person.getAge() >= 10)//过滤age》=10的元素
                .map(person -> person.getName())//使用map映射元素
                .collect(Collectors.toList());//手机映射后的元素
        nameList.stream().forEach(name-> System.out.println(name));
    }

    public static void main(String[] args) {
        useSttream(create_personList());
    }
}
class person{
    Integer age;
    String name;

    public person(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "person{" +
                "age='" + age + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}