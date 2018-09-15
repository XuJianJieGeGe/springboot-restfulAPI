package com.xjj.interceptor;

import org.omg.PortableInterceptor.Interceptor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by jie on 2018/9/9.
 */
//@Component
public class TimeInterceptor implements HandlerInterceptor{

    //比filter的好处就是多了handler属性
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle()...");
        //可以拿到方法和声明，拿不到方法的值
        System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod)handler).getMethod().getName());
        request.setAttribute("startTime",new Date().getTime());
        //return true代表调用后边的方法，return false代表不调用后面的方法
        return true;
    }

    //抛出异常不会被调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle()...");
        long startTime = (long) request.getAttribute("startTime");
        System.out.println("time interceptor 耗时："+(new Date().getTime()-startTime));
    }


    /**
     * 无论是否抛出异常都会被调用的方法
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("afterCompletion()...");
        long startTime = (long) request.getAttribute("startTime");
        System.out.println("time interceptor 耗时："+(new Date().getTime()-startTime));
        System.out.println("ex:"+ex);
    }
}
