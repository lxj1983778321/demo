package com.example.demo;


import com.example.demo.ThreadTest.AsyncProgram.spring_AsyncProgram.Spring_Async;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

//@EnableAsync 启用spring的异步方法执行功能，需要和@Configuration注解一起使用，然后需要异步执行的方法加上@Asymc注解，说明当前方法异步执行执行
@SpringBootApplication
@EnableAspectJAutoProxy
//@EnableAsync
@MapperScan(value = "com.example.demo.mapper")
public class DemoApplication implements CommandLineRunner {


	public static void main(String[] args)
	{
		System.out.println("-------》main启动所使用的线程是：" + Thread.currentThread().getName());
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		Spring_Async async = context.getBean(Spring_Async.class);
		CompletableFuture<String> future = async.doSomething();
		future.whenComplete(new BiConsumer<String, Throwable>() {
			@Override
			public void accept(String s, Throwable throwable) {
				if (throwable == null){
					System.out.println(Thread.currentThread().getName() + " 回调异步参数为： " + s);
				}else {
					System.out.println(Thread.currentThread().getName() + "《--error---》" + throwable.getMessage());
				}
				System.out.println(Thread.currentThread().getName() + "-----end-----");
			}
		});
		System.out.println("------------------》main执行结束《------------------");
	}
	@Override
	public void run(String... args) throws Exception {

	}
}
