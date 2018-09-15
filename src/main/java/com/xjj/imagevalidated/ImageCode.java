package com.xjj.imagevalidated;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.time.LocalDateTime;

/**
 * Created by jie on 2018/9/11.
 */
public class ImageCode {

    public ImageCode(BufferedImage bufferedImage, String code, int expireTime) {
        this.bufferedImage = bufferedImage;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }

    public ImageCode(BufferedImage bufferedImage, String code, LocalDateTime expireTime) {
        this.bufferedImage = bufferedImage;
        this.code = code;
        this.expireTime = expireTime;
    }

    private BufferedImage bufferedImage;//图片

    private String code;//校验码

    private LocalDateTime expireTime;//过期时间

    private boolean isExpired;//判断是否过期

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
