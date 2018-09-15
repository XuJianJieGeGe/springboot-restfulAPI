package com.xjj.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jie on 2018/9/8.
 * @ControllerAdvice :全局异常处理，捕获所有Controller中抛出的异常。
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    //@ExceptionHandler之后，这个Controller其他方法中没有捕获的异常就会以参数的形式传入加了
    // @ExceptionHandler注解的那个方法中
    @ExceptionHandler(UserNotExistException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//代表服务器错误
    @ResponseBody
    public Map<String,Object> handlerUserNotExistException(UserNotExistException ex){
        Map<String,Object> map = new HashMap<>();
        map.put("id",ex.getId());
        map.put("message",ex.getMessage());
        return map;
    }
}
