package com.example.demo.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Order(5) // 有多个日志时，ORDER可以定义切面的执行顺序（数字越大，前置越后执行，后置越前执行）
@Component
public class webAspect {

    private Logger logger = LoggerFactory.getLogger(webAspect.class);
    private ThreadLocal<Long> start = new ThreadLocal<Long>();

    /**
     * 第一个*表示返回值类型为所有类型
     * 定义切点，扫描Controller包下的所有类所有方法
     */
    @Pointcut("execution(* com.example.demo.controller..*.*(..))")
    public void webAspect(){
    }

    @Before("webAspect()")
    public void doBefore(JoinPoint joinPoint){
        //设置当前系统时间
        start.set(System.currentTimeMillis());

        //获取httpServletRequest对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = attributes.getRequest();

        //请求的路径和方法
        logger.info("URL: "+httpServletRequest.getRemoteAddr());
        logger.info("METHOD: "+httpServletRequest.getMethod());
        //请求哪个类的哪个方法
        logger.info("CLASS_METHOD: "+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        //方法传入参数
        logger.info("ARGS:" + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret",pointcut = "webAspect()")
    public void AfetrRuning(Object ret){
        logger.info("RESPONSE： "+ret);
        logger.info("执行时间："+(System.currentTimeMillis()-start.get()));
    }
}
