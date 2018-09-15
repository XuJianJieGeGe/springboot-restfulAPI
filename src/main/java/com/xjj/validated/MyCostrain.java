package com.xjj.validated;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by jie on 2018/9/8.
 * @Target({ElementType.METHOD,ElementType.FIELD})代表作用在什么上
 *
 *
 *
 *
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyCostrainValidated.class)
public @interface MyCostrain {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
