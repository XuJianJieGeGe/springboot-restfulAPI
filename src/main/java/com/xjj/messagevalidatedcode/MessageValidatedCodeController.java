package com.xjj.messagevalidatedcode;

import com.xjj.imagevalidated.ImageCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jie on 2018/9/12.
 */
@RestController
public class MessageValidatedCodeController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ValidatedCodeGenerator validatedCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;


    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @GetMapping("/code/sms")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //生成数字图片验证码
        SmsCode smsCode = validatedCodeGenerator.generate(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,smsCode);
        String mobile = ServletRequestUtils.getRequiredStringParameter(request,"mobile");
        smsCodeSender.send(mobile,smsCode.getCode());
    }



}
