package com.xjj.messagevalidatedcode;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by jie on 2018/9/12.
 */
public interface ValidatedCodeGenerator {

    SmsCode generate(ServletWebRequest request);

}
