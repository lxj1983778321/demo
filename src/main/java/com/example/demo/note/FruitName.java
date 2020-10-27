package com.example.demo.note;

import java.lang.annotation.*;

/**
 * @Description 自定义注解
 * @Author lixingjian
 * @DATE 2020/6/17 11:13
 * @Version 1.0
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {
    String value() default "";
}
