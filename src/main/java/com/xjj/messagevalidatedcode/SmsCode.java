package com.xjj.messagevalidatedcode;

import java.time.LocalDateTime;

/**
 * Created by jie on 2018/9/12.
 */
public class SmsCode {

    private String code;

    private LocalDateTime expiredTime;

    public SmsCode(String code, int expiredIn) {
        this.code = code;
        this.expiredTime = LocalDateTime.now().plusSeconds(expiredIn);
    }

    public SmsCode(String code, LocalDateTime expiredTime) {
        this.code = code;
        this.expiredTime = expiredTime;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiredTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(LocalDateTime expiredTime) {
        this.expiredTime = expiredTime;
    }
}
