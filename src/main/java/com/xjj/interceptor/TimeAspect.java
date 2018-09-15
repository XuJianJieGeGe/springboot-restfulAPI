package com.xjj.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 拦截的顺序：Filter-->Interceptor--》ControllerAdvice-->Aspect--》Controller
 */
//@Aspect
//@Component
public class TimeAspect {

    /**
     * ProceedingJoinPoint joinPoint代表拦截的方法的所有信息
     * @param joinPoint
     * @return
     */
    //代表作用于UserController类下的所有方法
    @Around("execution(* com.xjj.controller.UserController.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("time aspect start...");
        long start = new Date().getTime();
        //获取所有方法的参数
        Object[] args = joinPoint.getArgs();
        for(Object o:args){
            System.out.println("arg:"+o);
        }
        //进入方法
        Object object = joinPoint.proceed();
        System.out.println("time aspect 耗时："+(new Date().getTime()-start));
        System.out.println("time aspect end...");
       return object;
    }


}
