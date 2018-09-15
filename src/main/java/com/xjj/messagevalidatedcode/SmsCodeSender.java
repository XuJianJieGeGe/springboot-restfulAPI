package com.xjj.messagevalidatedcode;

/**
 * Created by jie on 2018/9/12.
 */
public interface SmsCodeSender {

    void send(String mobile,String code);
}
