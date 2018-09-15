package com.xjj.interceptor;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * Created by jie on 2018/9/8.
 * 用于计算Controller中，进入某个方法的时间
 * @Component：代表所有的过虑器
 *
 */
//@Component
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter start...");
        long start = new Date().getTime();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("time filter 耗时:"+(new Date().getTime()-start));
        System.out.println("filter finish...");

    }

    @Override
    public void destroy() {
        System.out.println("filter destroy...");
    }
}
