package com.xjj.validated;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by jie on 2018/9/8.
 */
public class MyCostrainValidated implements ConstraintValidator<MyCostrain,Object> {

    @Override
    public void initialize(MyCostrain constraintAnnotation) {
        System.out.println("my validated init()");
    }

    //需要校验的逻辑
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(o);
        //return true代表校验成功，return false代表校验失败
        return true;
    }
}
