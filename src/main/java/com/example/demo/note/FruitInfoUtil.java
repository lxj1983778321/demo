package com.example.demo.note;

import java.lang.reflect.Field;


/**
 * @Description TODO
 * @Author lixingjian
 * @DATE 2020/6/17 11:39
 * @Version 1.0
 **/
public class FruitInfoUtil {

    public static void getFruitInfo(Class<?> clazz){
        String ss = "水果名称";
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields) {
            if(field.isAnnotationPresent(FruitName.class)){
               FruitName fruitName =  (FruitName) field.getAnnotation(FruitName.class);
                System.out.println(fruitName.value());
                System.out.println(ss+fruitName.value());
            }
        }
    }
}
