package com.xjj.messagevalidatedcode;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jie on 2018/9/12.
 */
@Configuration
public class ValidatedCodeBeanConfig {

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public  SmsCodeSender smsCodeSender(){
        return new DefaultSmsCodeSenderImpl();
    }


}
