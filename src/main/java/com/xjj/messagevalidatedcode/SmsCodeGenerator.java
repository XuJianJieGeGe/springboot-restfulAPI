package com.xjj.messagevalidatedcode;

import com.xjj.utils.RandomUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by jie on 2018/9/12.
 */
@Component
public class SmsCodeGenerator implements ValidatedCodeGenerator {
    @Override
    public SmsCode generate(ServletWebRequest request) {
        String code = RandomUtils.generateString(4);
        return new SmsCode(code,60000);
    }
}
