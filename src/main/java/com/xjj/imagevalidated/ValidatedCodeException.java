package com.xjj.imagevalidated;


import org.springframework.security.core.AuthenticationException;

/**
 * Created by jie on 2018/9/12.
 */
public class ValidatedCodeException extends AuthenticationException {

    public ValidatedCodeException(String detail) {
        super(detail);
    }
}
