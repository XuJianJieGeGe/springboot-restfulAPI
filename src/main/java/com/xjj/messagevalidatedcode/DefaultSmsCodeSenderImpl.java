package com.xjj.messagevalidatedcode;

/**
 * Created by jie on 2018/9/12.
 */
public class DefaultSmsCodeSenderImpl implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机"+mobile+"发送短信验证码"+code);
    }
}
